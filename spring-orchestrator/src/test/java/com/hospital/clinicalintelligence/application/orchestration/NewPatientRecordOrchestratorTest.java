package com.hospital.clinicalintelligence.application.orchestration;

import com.hospital.clinicalintelligence.agent.agent1.ClinicalDataIntelligenceClient;
import com.hospital.clinicalintelligence.agent.agent2.PatternRiskAnalysisService;
import com.hospital.clinicalintelligence.agent.agent3.ClinicalMemoryService;
import com.hospital.clinicalintelligence.agent.agent4.DecisionSupportService;
import com.hospital.clinicalintelligence.application.dto.request.NewPatientRecordRequest;
import com.hospital.clinicalintelligence.application.dto.response.PatientAnalysisResponse;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class NewPatientRecordOrchestratorTest {

    @Test
    void shouldAggregateAllFourAgentsIntoExplainableResponse() {
        NewPatientRecordOrchestrator orchestrator = new NewPatientRecordOrchestrator(
                new ClinicalDataIntelligenceClient(),
                new PatternRiskAnalysisService(),
                new ClinicalMemoryService(),
                new DecisionSupportService()
        );

        NewPatientRecordRequest request = new NewPatientRecordRequest(
                "patient-1",
                "encounter-1",
                Instant.parse("2026-03-20T00:00:00Z"),
                List.of("Patient reports shortness of breath and fatigue."),
                Map.of("spo2", 90.0, "heartRate", 118.0),
                Map.of("wbc", "13.4"),
                Map.of("age", "67")
        );

        PatientAnalysisResponse response = orchestrator.process(request);

        assertThat(response.patientId()).isEqualTo("patient-1");
        assertThat(response.riskLevel()).isEqualTo("HIGH");
        assertThat(response.triggeredFactors()).contains("spo2-below-threshold", "persistent-tachycardia-threshold");
        assertThat(response.suggestedActions()).anyMatch(action -> action.contains("Decision support only"));
        assertThat(response.similarCaseIds()).contains("case-1024", "case-2048");
    }
}
