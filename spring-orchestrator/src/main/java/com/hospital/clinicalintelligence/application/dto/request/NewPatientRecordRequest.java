package com.hospital.clinicalintelligence.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record NewPatientRecordRequest(
        @NotBlank String patientId,
        @NotBlank String encounterId,
        @NotNull Instant observedAt,
        @NotEmpty List<String> clinicalNotes,
        Map<String, Double> vitalSigns,
        Map<String, String> labResults,
        Map<String, String> demographics
) {
}
