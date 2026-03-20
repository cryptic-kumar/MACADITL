from datetime import datetime, timezone

from app.models.request_models import ClinicalTextRequest
from app.services.clinical_text_pipeline import ClinicalTextPipeline


def test_pipeline_returns_explainable_placeholder_analysis():
    pipeline = ClinicalTextPipeline()
    payload = ClinicalTextRequest(
        patient_id="patient-1",
        encounter_id="encounter-1",
        observed_at=datetime(2026, 3, 20, tzinfo=timezone.utc),
        clinical_notes=["Patient reports shortness of breath overnight."],
    )

    response = pipeline.analyze(payload)

    assert response.patient_id == "patient-1"
    assert response.extracted_signals == ["possible-respiratory-distress"]
    assert response.explainability["disclaimer"].startswith("Decision-support")
