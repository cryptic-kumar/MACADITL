package com.hospital.clinicalintelligence.application.orchestration;

import com.hospital.clinicalintelligence.agent.agent1.ClinicalDataIntelligenceClient;
import com.hospital.clinicalintelligence.agent.agent2.PatternRiskAnalysisService;
import com.hospital.clinicalintelligence.agent.agent3.ClinicalMemoryService;
import com.hospital.clinicalintelligence.agent.agent4.DecisionSupportService;
import com.hospital.clinicalintelligence.application.dto.request.NewPatientRecordRequest;
import com.hospital.clinicalintelligence.application.dto.response.PatientAnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewPatientRecordOrchestrator {

    private final ClinicalDataIntelligenceClient clinicalDataIntelligenceClient;
    private final PatternRiskAnalysisService patternRiskAnalysisService;
    private final ClinicalMemoryService clinicalMemoryService;
    private final DecisionSupportService decisionSupportService;

    public NewPatientRecordOrchestrator(
            ClinicalDataIntelligenceClient clinicalDataIntelligenceClient,
            PatternRiskAnalysisService patternRiskAnalysisService,
            ClinicalMemoryService clinicalMemoryService,
            DecisionSupportService decisionSupportService
    ) {
        this.clinicalDataIntelligenceClient = clinicalDataIntelligenceClient;
        this.patternRiskAnalysisService = patternRiskAnalysisService;
        this.clinicalMemoryService = clinicalMemoryService;
        this.decisionSupportService = decisionSupportService;
    }

    public PatientAnalysisResponse process(NewPatientRecordRequest request) {
        Map<String, Object> agent1Result = clinicalDataIntelligenceClient.analyzeClinicalText(request);
        Map<String, Object> riskResult = patternRiskAnalysisService.calculateRisk(request.vitalSigns(), agent1Result);
        Map<String, Object> similarCases = clinicalMemoryService.findSimilarCases(request.patientId());
        Map<String, Object> decisionSupport = decisionSupportService.buildDecisionSupport(riskResult, similarCases);

        return new PatientAnalysisResponse(
                request.patientId(),
                request.encounterId(),
                "COMPLETED",
                String.valueOf(riskResult.get("riskLevel")),
                castStringList(riskResult.get("triggeredFactors")),
                castStringList(decisionSupport.get("suggestedActions")),
                castStringList(similarCases.get("similarCaseIds")),
                Map.of(
                        "agent1", agent1Result,
                        "agent2", riskResult,
                        "agent3", similarCases,
                        "agent4", decisionSupport
                )
        );
    }

    @SuppressWarnings("unchecked")
    private List<String> castStringList(Object value) {
        return value instanceof List<?> list ? (List<String>) list : List.of();
    }
}
