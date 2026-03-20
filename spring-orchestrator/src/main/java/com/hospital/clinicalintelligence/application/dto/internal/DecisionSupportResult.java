package com.hospital.clinicalintelligence.application.dto.internal;

import java.util.List;

public record DecisionSupportResult(
        List<String> suggestedActions,
        List<String> similarCaseIds,
        String summary
) {
}
