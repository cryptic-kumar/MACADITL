package com.hospital.clinicalintelligence.agent.agent3;

import com.hospital.clinicalintelligence.application.dto.internal.SimilarCasesResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalMemoryService {

    public SimilarCasesResult findSimilarCases(String patientId) {
        return new SimilarCasesResult(
                patientId,
                List.of("case-1024", "case-2048"),
                "Placeholder ChromaDB lookup result for architecture bootstrap."
        );
    }
}
