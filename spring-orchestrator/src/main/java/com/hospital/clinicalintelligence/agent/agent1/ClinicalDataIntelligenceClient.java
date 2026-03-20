package com.hospital.clinicalintelligence.agent.agent1;

import com.hospital.clinicalintelligence.application.dto.internal.Agent1AnalysisResult;
import com.hospital.clinicalintelligence.application.dto.internal.ClinicalSignalEvidence;
import com.hospital.clinicalintelligence.application.dto.request.NewPatientRecordRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClinicalDataIntelligenceClient {

    public Agent1AnalysisResult analyzeClinicalText(NewPatientRecordRequest request) {
        return new Agent1AnalysisResult(
                List.of("possible-respiratory-distress", "fatigue"),
                "placeholder-generated-by-agent-1",
                request.clinicalNotes().size(),
                List.of(
                        new ClinicalSignalEvidence("possible-respiratory-distress", "shortness of breath referenced in note text", 0.86),
                        new ClinicalSignalEvidence("fatigue", "fatigue referenced in note text", 0.72)
                )
        );
    }
}
