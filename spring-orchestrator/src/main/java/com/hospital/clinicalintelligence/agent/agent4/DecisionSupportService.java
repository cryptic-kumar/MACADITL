package com.hospital.clinicalintelligence.agent.agent4;

import com.hospital.clinicalintelligence.application.dto.internal.DecisionSupportResult;
import com.hospital.clinicalintelligence.application.dto.internal.RiskAnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.SimilarCasesResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DecisionSupportService {

    public DecisionSupportResult buildDecisionSupport(RiskAnalysisResult riskResult, SimilarCasesResult similarCases) {
        List<String> actions = new ArrayList<>();

        if ("HIGH".equals(riskResult.riskLevel())) {
            actions.add("Repeat vital signs within 15 minutes");
            actions.add("Notify the responsible clinician immediately");
            actions.add("Consider CBC and arterial blood gas based on physician judgment");
        } else if ("MEDIUM".equals(riskResult.riskLevel())) {
            actions.add("Increase monitoring frequency");
            actions.add("Review recent labs and symptom progression");
        } else {
            actions.add("Continue routine monitoring");
        }

        actions.add("Decision support only — not a diagnosis or replacement for clinician judgment");

        return new DecisionSupportResult(
                List.copyOf(actions),
                similarCases.similarCaseIds(),
                "Aggregated explainable recommendation output for clinician review."
        );
    }
}
