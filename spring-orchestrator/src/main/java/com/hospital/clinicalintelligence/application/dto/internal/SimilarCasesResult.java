package com.hospital.clinicalintelligence.application.dto.internal;

import java.util.List;

public record SimilarCasesResult(
        String patientId,
        List<String> similarCaseIds,
        String summary
) {
}
