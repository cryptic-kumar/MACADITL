package com.hospital.clinicalintelligence.application.dto.internal;

public record ClinicalSignalEvidence(
        String signal,
        String evidence,
        double confidence
) {
}
