package com.hospital.clinicalintelligence.agent.agent4;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DecisionSupportService {

    public Map<String, Object> buildDecisionSupport(Map<String, Object> riskResult, Map<String, Object> similarCases) {
        List<String> actions = new ArrayList<>();

        if ("HIGH".equals(riskResult.get("riskLevel"))) {
            actions.add("Repeat vital signs within 15 minutes");
            actions.add("Notify the responsible clinician immediately");
            actions.add("Consider CBC and arterial blood gas based on physician judgment");
        } else if ("MEDIUM".equals(riskResult.get("riskLevel"))) {
            actions.add("Increase monitoring frequency");
            actions.add("Review recent labs and symptom progression");
        } else {
            actions.add("Continue routine monitoring");
        }

        actions.add("Decision support only — not a diagnosis or replacement for clinician judgment");

        return Map.of(
                "suggestedActions", actions,
                "similarCases", similarCases.get("similarCaseIds"),
                "summary", "Aggregated explainable recommendation output for clinician review."
        );
    }
}
