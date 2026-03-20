package com.hospital.clinicalintelligence.application.dto.internal;

import java.util.List;

public record Agent1AnalysisResult(
        List<String> extractedSignals,
        String embeddingStatus,
        int notesAnalyzed,
        List<ClinicalSignalEvidence> evidences
) {
}
