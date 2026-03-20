package com.hospital.clinicalintelligence.agent.agent2;

import com.hospital.clinicalintelligence.application.dto.internal.Agent1AnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.RiskAnalysisResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PatternRiskAnalysisService {

    public RiskAnalysisResult calculateRisk(Map<String, Double> vitalSigns, Agent1AnalysisResult agent1Result) {
        List<String> triggeredFactors = new ArrayList<>();
        double score = 0;

        Double oxygenSaturation = vitalSigns != null ? vitalSigns.getOrDefault("spo2", 100.0) : 100.0;
        if (oxygenSaturation < 92.0) {
            score += 40;
            triggeredFactors.add("spo2-below-threshold");
        }

        Double heartRate = vitalSigns != null ? vitalSigns.getOrDefault("heartRate", 0.0) : 0.0;
        if (heartRate > 110.0) {
            score += 25;
            triggeredFactors.add("persistent-tachycardia-threshold");
        }

        if (agent1Result.extractedSignals().contains("possible-respiratory-distress")) {
            score += 20;
            triggeredFactors.add("agent-1-respiratory-signal");
        }

        String riskLevel = score >= 60 ? "HIGH" : score >= 25 ? "MEDIUM" : "LOW";
        return new RiskAnalysisResult(
                score,
                riskLevel,
                List.copyOf(triggeredFactors),
                "Explainable threshold-based risk score using vital-sign breaches and extracted clinical signals."
        );
    }
}
