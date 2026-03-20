from app.models.request_models import ClinicalTextRequest
from app.models.response_models import ClinicalTextResponse


class ClinicalTextPipeline:
    """Placeholder pipeline for the future BioClinicalBERT-backed Agent 1 service."""

    def analyze(self, payload: ClinicalTextRequest) -> ClinicalTextResponse:
        signals = ["possible-respiratory-distress"] if any(
            "shortness of breath" in note.lower() for note in payload.clinical_notes
        ) else ["no-high-risk-phrase-detected"]

        embeddings = [0.12, 0.34, 0.56, 0.78]

        return ClinicalTextResponse(
            patient_id=payload.patient_id,
            encounter_id=payload.encounter_id,
            extracted_signals=signals,
            embeddings=embeddings,
            explainability={
                "model": "BioClinicalBERT-placeholder",
                "note_count": len(payload.clinical_notes),
                "disclaimer": "Decision-support extraction only; no diagnosis is produced.",
            },
        )
