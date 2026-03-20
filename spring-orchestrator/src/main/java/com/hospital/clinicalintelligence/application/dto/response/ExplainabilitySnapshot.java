package com.hospital.clinicalintelligence.application.dto.response;

import com.hospital.clinicalintelligence.application.dto.internal.Agent1AnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.DecisionSupportResult;
import com.hospital.clinicalintelligence.application.dto.internal.RiskAnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.SimilarCasesResult;

public record ExplainabilitySnapshot(
        Agent1AnalysisResult agent1,
        RiskAnalysisResult agent2,
        SimilarCasesResult agent3,
        DecisionSupportResult agent4
) {
}
