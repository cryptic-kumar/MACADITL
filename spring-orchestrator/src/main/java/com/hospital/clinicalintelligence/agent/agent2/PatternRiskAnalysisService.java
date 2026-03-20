package com.hospital.clinicalintelligence.agent.agent2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PatternRiskAnalysisService {

    public Map<String, Object> calculateRisk(Map<String, Double> vitalSigns, Map<String, Object> agent1Signals) {
        List<String> triggeredFactors = new ArrayList<>();
        double score = 0;

        Double oxygenSaturation = vitalSigns.getOrDefault("spo2", 100.0);
        if (oxygenSaturation < 92.0) {
            score += 40;
            triggeredFactors.add("spo2-below-threshold");
        }

        Double heartRate = vitalSigns.getOrDefault("heartRate", 0.0);
        if (heartRate > 110.0) {
            score += 25;
            triggeredFactors.add("persistent-tachycardia-threshold");
        }

        if (((List<?>) agent1Signals.getOrDefault("signals", List.of())).contains("possible-respiratory-distress")) {
            score += 20;
            triggeredFactors.add("agent-1-respiratory-signal");
        }

        String riskLevel = score >= 60 ? "HIGH" : score >= 25 ? "MEDIUM" : "LOW";
        return Map.of(
                "score", score,
                "riskLevel", riskLevel,
                "triggeredFactors", triggeredFactors,
                "explanation", "Explainable threshold-based risk score using vital-sign breaches and extracted clinical signals."
        );
    }
}
