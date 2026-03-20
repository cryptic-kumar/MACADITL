package com.hospital.clinicalintelligence.application.orchestration;

import com.hospital.clinicalintelligence.agent.agent1.ClinicalDataIntelligenceClient;
import com.hospital.clinicalintelligence.agent.agent2.PatternRiskAnalysisService;
import com.hospital.clinicalintelligence.agent.agent3.ClinicalMemoryService;
import com.hospital.clinicalintelligence.agent.agent4.DecisionSupportService;
import com.hospital.clinicalintelligence.application.dto.internal.Agent1AnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.DecisionSupportResult;
import com.hospital.clinicalintelligence.application.dto.internal.RiskAnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.SimilarCasesResult;
import com.hospital.clinicalintelligence.application.dto.request.NewPatientRecordRequest;
import com.hospital.clinicalintelligence.application.dto.response.ExplainabilitySnapshot;
import com.hospital.clinicalintelligence.application.dto.response.PatientAnalysisResponse;
import org.springframework.stereotype.Service;

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
        Agent1AnalysisResult agent1Result = clinicalDataIntelligenceClient.analyzeClinicalText(request);
        RiskAnalysisResult riskResult = patternRiskAnalysisService.calculateRisk(request.vitalSigns(), agent1Result);
        SimilarCasesResult similarCases = clinicalMemoryService.findSimilarCases(request.patientId());
        DecisionSupportResult decisionSupport = decisionSupportService.buildDecisionSupport(riskResult, similarCases);

        return new PatientAnalysisResponse(
                request.patientId(),
                request.encounterId(),
                "COMPLETED",
                riskResult.riskLevel(),
                riskResult.triggeredFactors(),
                decisionSupport.suggestedActions(),
                similarCases.similarCaseIds(),
                new ExplainabilitySnapshot(agent1Result, riskResult, similarCases, decisionSupport)
        );
    }
}
