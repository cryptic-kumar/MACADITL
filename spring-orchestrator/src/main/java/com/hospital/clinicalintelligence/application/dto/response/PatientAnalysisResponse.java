package com.hospital.clinicalintelligence.application.dto.response;

import java.util.List;
import java.util.Map;

public record PatientAnalysisResponse(
        String patientId,
        String encounterId,
        String workflowStatus,
        String riskLevel,
        List<String> triggeredFactors,
        List<String> suggestedActions,
        List<String> similarCaseIds,
        Map<String, Object> explainability
) {
}
