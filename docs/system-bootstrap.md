# Multi-Agent Clinical Intelligence System Bootstrap

## 1. Recommended workspace layout

### Spring Boot workspace (`spring-orchestrator/`)

```text
spring-orchestrator/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/hospital/clinicalintelligence/
│   │   │   ├── ClinicalIntelligenceApplication.java
│   │   │   ├── config/
│   │   │   │   ├── JacksonConfig.java
│   │   │   │   ├── OpenApiConfig.java
│   │   │   │   ├── RestClientConfig.java
│   │   │   │   ├── ChromaProperties.java
│   │   │   │   └── RiskThresholdProperties.java
│   │   │   ├── api/
│   │   │   │   ├── PatientRecordController.java
│   │   │   │   ├── AlertController.java
│   │   │   │   └── HealthController.java
│   │   │   ├── application/
│   │   │   │   ├── orchestration/
│   │   │   │   │   ├── NewPatientRecordOrchestrator.java
│   │   │   │   │   ├── AgentWorkflowCoordinator.java
│   │   │   │   │   └── ClinicalWorkflowAuditService.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── request/
│   │   │   │   │   │   ├── NewPatientRecordRequest.java
│   │   │   │   │   │   ├── VitalSignsDto.java
│   │   │   │   │   │   ├── LabResultDto.java
│   │   │   │   │   │   └── ClinicalNoteDto.java
│   │   │   │   │   ├── response/
│   │   │   │   │   │   ├── PatientAnalysisResponse.java
│   │   │   │   │   │   ├── RiskAssessmentResponse.java
│   │   │   │   │   │   └── AlertResponse.java
│   │   │   │   │   └── internal/
│   │   │   │   │       ├── ClinicalSignalsPayload.java
│   │   │   │   │       ├── EmbeddingPayload.java
│   │   │   │   │       ├── PatternAnalysisResult.java
│   │   │   │   │       ├── SimilarCaseResult.java
│   │   │   │   │       └── DecisionSupportResult.java
│   │   │   │   └── service/
│   │   │   │       ├── PatientIngestionService.java
│   │   │   │       ├── TimelineAggregationService.java
│   │   │   │       └── ClinicalExplanationService.java
│   │   │   ├── domain/
│   │   │   │   ├── patient/
│   │   │   │   │   ├── Patient.java
│   │   │   │   │   ├── Encounter.java
│   │   │   │   │   ├── ClinicalNote.java
│   │   │   │   │   ├── LabResult.java
│   │   │   │   │   └── VitalSignsSnapshot.java
│   │   │   │   ├── risk/
│   │   │   │   │   ├── RiskScore.java
│   │   │   │   │   ├── RiskFactor.java
│   │   │   │   │   └── ThresholdBreach.java
│   │   │   │   ├── memory/
│   │   │   │   │   ├── SimilarCase.java
│   │   │   │   │   └── PatientEmbedding.java
│   │   │   │   └── alert/
│   │   │   │       ├── ClinicalAlert.java
│   │   │   │       ├── AlertSeverity.java
│   │   │   │       └── SuggestedAction.java
│   │   │   ├── agent/
│   │   │   │   ├── agent1/
│   │   │   │   │   ├── ClinicalDataIntelligenceClient.java
│   │   │   │   │   ├── Agent1RequestMapper.java
│   │   │   │   │   └── Agent1ResponseMapper.java
│   │   │   │   ├── agent2/
│   │   │   │   │   ├── PatternRiskAnalysisService.java
│   │   │   │   │   ├── VitalTrendAnalyzer.java
│   │   │   │   │   ├── LabThresholdEvaluator.java
│   │   │   │   │   ├── RuleBasedRiskScoreCalculator.java
│   │   │   │   │   └── RiskExplanationBuilder.java
│   │   │   │   ├── agent3/
│   │   │   │   │   ├── ClinicalMemoryService.java
│   │   │   │   │   ├── ChromaCaseRepository.java
│   │   │   │   │   ├── EmbeddingStorageService.java
│   │   │   │   │   └── SimilarCaseMapper.java
│   │   │   │   └── agent4/
│   │   │   │       ├── DecisionSupportService.java
│   │   │   │       ├── AlertGenerationService.java
│   │   │   │       ├── NextStepRecommendationService.java
│   │   │   │       └── AlertRoutingService.java
│   │   │   ├── infrastructure/
│   │   │   │   ├── persistence/
│   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── PatientEntity.java
│   │   │   │   │   │   ├── EncounterEntity.java
│   │   │   │   │   │   ├── RiskAssessmentEntity.java
│   │   │   │   │   │   └── AlertEntity.java
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   ├── PatientRepository.java
│   │   │   │   │   │   ├── EncounterRepository.java
│   │   │   │   │   │   ├── RiskAssessmentRepository.java
│   │   │   │   │   │   └── AlertRepository.java
│   │   │   │   │   └── mapper/
│   │   │   │   │       ├── PatientPersistenceMapper.java
│   │   │   │   │       └── AlertPersistenceMapper.java
│   │   │   │   ├── clients/
│   │   │   │   │   ├── chroma/
│   │   │   │   │   │   └── ChromaGateway.java
│   │   │   │   │   └── python/
│   │   │   │   │       └── FastApiAgentGateway.java
│   │   │   │   └── messaging/
│   │   │   │       └── AuditEventPublisher.java
│   │   │   ├── support/
│   │   │   │   ├── exception/
│   │   │   │   │   ├── AgentCommunicationException.java
│   │   │   │   │   ├── ValidationException.java
│   │   │   │   │   └── ResourceNotFoundException.java
│   │   │   │   └── util/
│   │   │   │       ├── DateTimeUtils.java
│   │   │   │       └── JsonUtils.java
│   │   │   └── security/
│   │   │       └── ApiKeyFilter.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-local.yml
│   │       ├── db/migration/
│   │       │   ├── V1__create_patient_tables.sql
│   │       │   ├── V2__create_risk_and_alert_tables.sql
│   │       │   └── V3__seed_risk_thresholds.sql
│   │       └── rules/
│   │           ├── vital-thresholds.yml
│   │           ├── lab-thresholds.yml
│   │           └── alert-recommendations.yml
│   └── test/
│       └── java/com/hospital/clinicalintelligence/
│           ├── api/PatientRecordControllerTest.java
│           ├── application/NewPatientRecordOrchestratorTest.java
│           ├── agent/agent2/RuleBasedRiskScoreCalculatorTest.java
│           ├── agent/agent3/ClinicalMemoryServiceTest.java
│           └── agent/agent4/DecisionSupportServiceTest.java
└── .mvn/
```

#### Spring Boot directory intent

- `api/`: REST entry points for hospital systems, EMR adapters, and alert retrieval.
- `application/orchestration/`: workflow coordination that sequences Agents 1-4 without embedding clinical rules directly in controllers.
- `agent/agent1`: adapter layer to the Python NLP microservice.
- `agent/agent2`: explainable statistical/rule engine for risk analysis.
- `agent/agent3`: vector-memory integration with ChromaDB.
- `agent/agent4`: final alerting and recommendation assembly for clinicians.
- `infrastructure/persistence/`: relational storage for patients, encounters, risk assessments, and alerts.
- `resources/rules/`: configurable clinical thresholds so doctors and analysts can audit rule behavior.

### Python FastAPI workspace (`python-clinical-nlp/`)

```text
python-clinical-nlp/
├── requirements.txt
├── README.md
├── app/
│   ├── main.py
│   ├── api/
│   │   ├── routes/
│   │   │   ├── health.py
│   │   │   └── clinical_text.py
│   │   └── dependencies.py
│   ├── core/
│   │   ├── config.py
│   │   ├── logging.py
│   │   └── security.py
│   ├── models/
│   │   ├── request_models.py
│   │   ├── response_models.py
│   │   └── domain_models.py
│   ├── services/
│   │   ├── clinical_text_pipeline.py
│   │   ├── bioclinicalbert_service.py
│   │   ├── entity_signal_extractor.py
│   │   ├── embedding_service.py
│   │   └── note_preprocessor.py
│   ├── clients/
│   │   └── terminology_client.py
│   ├── utils/
│   │   ├── text_cleaning.py
│   │   └── response_builders.py
│   └── tests/
│       ├── test_health.py
│       ├── test_clinical_text.py
│       └── test_entity_signal_extractor.py
├── models_cache/
│   └── .gitkeep
└── scripts/
    ├── download_model.py
    └── smoke_test.py
```

#### Python directory intent

- `api/routes/clinical_text.py`: REST endpoint that receives clinical notes/history and returns structured signals plus embeddings.
- `services/bioclinicalbert_service.py`: loads pretrained BioClinicalBERT once at startup and exposes inference helpers.
- `services/entity_signal_extractor.py`: converts transformer outputs into explainable structured findings, such as symptoms, abnormal phrases, and confidence metadata.
- `models/`: Pydantic contracts shared across the service.
- `scripts/download_model.py`: optional warm-up utility to prefetch model artifacts during deployment.

## 2. Initial Spring Boot `pom.xml` dependencies

Use a single Spring Boot service as the primary orchestrator and home for Agents 2, 3, and 4. The dependencies below support REST APIs, validation, database persistence, migrations, Chroma/HTTP integration, observability, and testing.

### Dependency rationale

- `spring-boot-starter-web`: REST entry points and JSON serialization.
- `spring-boot-starter-validation`: bean validation for incoming patient records.
- `spring-boot-starter-data-jpa`: persistence for patient, risk, and alert data.
- `spring-boot-starter-actuator`: operational health checks and metrics.
- `spring-boot-starter-security`: API-key or service-to-service auth hardening.
- `spring-boot-starter-webflux`: lightweight `WebClient` for Agent 1 and Chroma HTTP calls.
- `flyway-core`: schema migration management.
- `postgresql`: production relational database driver.
- `mysql-connector-j`: optional alternative if MySQL is chosen.
- `jackson-datatype-jsr310`: Java time support for clinical event timestamps.
- `springdoc-openapi-starter-webmvc-ui`: API documentation for integration teams.
- `micrometer-tracing-bridge-brave`: request tracing across agent boundaries.
- `lombok`: reduces boilerplate in DTOs/entities.
- `spring-boot-starter-test`, `mockito-junit-jupiter`, `wiremock`: unit and integration testing.

## 3. High-level orchestration flow for a new patient record

Assume a hospital system sends a `POST /api/v1/patient-records` request to the Spring Boot backend with demographics, structured vitals/labs, and unstructured notes.

### Step-by-step sequence

1. **REST ingestion and validation**
   - `PatientRecordController` receives the payload.
   - Spring validation checks required demographics, timestamps, vital ranges, and note presence rules.
   - `PatientIngestionService` stores the raw encounter and structured values in PostgreSQL/MySQL for auditability.

2. **Agent 1 invocation: clinical text intelligence**
   - `NewPatientRecordOrchestrator` maps unstructured notes, history, and lab commentary into an Agent 1 request.
   - `ClinicalDataIntelligenceClient` calls the FastAPI microservice.
   - The Python service runs preprocessing, BioClinicalBERT inference, and signal extraction.
   - Agent 1 returns:
     - structured clinical signals (symptoms, findings, abnormal phrases),
     - embeddings for vector storage/search,
     - evidence snippets/confidence metadata for explainability.

3. **Agent 2 execution: explainable pattern and risk analysis**
   - Spring Boot merges Agent 1 signals with structured vitals/labs from the incoming payload and relevant prior measurements from the relational database.
   - `PatternRiskAnalysisService` calculates trend changes, threshold breaches, and rule matches.
   - Risk scores are assembled from explicit factors such as tachycardia persistence, oxygen saturation decline, fever trend, or lab abnormalities.
   - The output is a transparent risk object containing score, severity, triggered rules, and human-readable explanation.

4. **Agent 3 execution: clinical memory lookup**
   - `ClinicalMemoryService` persists the new embedding into ChromaDB with metadata such as patient ID, encounter ID, note type, major findings, and timestamp.
   - The same service performs a similarity search against prior cases or prior encounters.
   - Agent 3 returns top similar cases, matching signal summaries, and distance scores so the doctor can see why a case is considered similar.

5. **Agent 4 execution: decision support and alerting**
   - `DecisionSupportService` aggregates:
     - Agent 1 extracted findings,
     - Agent 2 explainable risk results,
     - Agent 3 similar-case context.
   - It applies final medical rules and hospital policy thresholds.
   - It generates alert objects with:
     - severity level,
     - reason/explanation,
     - suggested next actions such as ordering CBC, repeating vitals, or escalating to rapid response,
     - a reminder that the output is decision support for clinicians, not a diagnosis.

6. **Persistence and response**
   - Risk assessments and alerts are saved in the relational database.
   - An audit event can be published for downstream dashboards or notification channels.
   - The API response returns the encounter ID, explainable risk summary, similar-case references, and any active alerts.

### Why this flow fits your rules

- **Doctor-assist only**: the final output is recommendations and alerts, not autonomous diagnosis.
- **Explainable**: all risk scoring comes from thresholds, trends, and traceable extracted findings.
- **Modular**: Agent 1 is isolated as a Python NLP service, while Agents 2-4 stay in Java.
- **Backend-first**: orchestration, persistence, audit, and rules all live in the backend services.

### Suggested API interaction pattern

```text
Hospital EMR -> Spring Boot REST API
             -> Persist encounter data
             -> Call Agent 1 (FastAPI NLP)
             -> Run Agent 2 (Java risk engine)
             -> Run Agent 3 (Java + ChromaDB)
             -> Run Agent 4 (Java decision support)
             -> Persist alerts/assessments
             -> Return explainable response to clinician-facing system
```
