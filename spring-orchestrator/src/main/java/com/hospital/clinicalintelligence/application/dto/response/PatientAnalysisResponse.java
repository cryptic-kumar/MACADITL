package com.hospital.clinicalintelligence.application.dto.response;

import java.util.List;

public record PatientAnalysisResponse(
        String patientId,
        String encounterId,
        String workflowStatus,
        String riskLevel,
        List<String> triggeredFactors,
        List<String> suggestedActions,
        List<String> similarCaseIds,
        ExplainabilitySnapshot explainability
) {
}
