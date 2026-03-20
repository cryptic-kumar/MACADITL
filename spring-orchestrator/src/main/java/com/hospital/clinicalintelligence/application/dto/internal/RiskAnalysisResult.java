package com.hospital.clinicalintelligence.application.dto.internal;

import java.util.List;

public record RiskAnalysisResult(
        double score,
        String riskLevel,
        List<String> triggeredFactors,
        String explanation
) {
}
