package com.hospital.clinicalintelligence.api;

import com.hospital.clinicalintelligence.application.dto.request.NewPatientRecordRequest;
import com.hospital.clinicalintelligence.application.dto.response.PatientAnalysisResponse;
import com.hospital.clinicalintelligence.application.orchestration.NewPatientRecordOrchestrator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patient-records")
public class PatientRecordController {

    private final NewPatientRecordOrchestrator orchestrator;

    public PatientRecordController(NewPatientRecordOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PatientAnalysisResponse submitRecord(@Valid @RequestBody NewPatientRecordRequest request) {
        return orchestrator.process(request);
    }
}
