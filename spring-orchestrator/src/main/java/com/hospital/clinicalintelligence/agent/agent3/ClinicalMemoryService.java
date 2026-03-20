package com.hospital.clinicalintelligence.agent.agent3;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClinicalMemoryService {

    public Map<String, Object> findSimilarCases(String patientId) {
        return Map.of(
                "patientId", patientId,
                "similarCaseIds", List.of("case-1024", "case-2048"),
                "summary", "Placeholder ChromaDB lookup result for architecture bootstrap."
        );
    }
}
