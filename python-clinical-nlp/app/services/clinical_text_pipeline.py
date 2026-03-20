from app.models.domain_models import ExplainabilityDetails, SignalEvidence
from app.models.request_models import ClinicalTextRequest
from app.models.response_models import ClinicalTextResponse


class ClinicalTextPipeline:
    """Placeholder pipeline for the future BioClinicalBERT-backed Agent 1 service."""

    def analyze(self, payload: ClinicalTextRequest) -> ClinicalTextResponse:
        respiratory_signal_detected = any(
            "shortness of breath" in note.lower() for note in payload.clinical_notes
        )
        signals = ["possible-respiratory-distress"] if respiratory_signal_detected else ["no-high-risk-phrase-detected"]
        evidences = [
            SignalEvidence(
                signal=signals[0],
                evidence="Matched phrase: shortness of breath" if respiratory_signal_detected else "No respiratory distress phrase matched",
                confidence=0.86 if respiratory_signal_detected else 0.51,
            )
        ]
        embeddings = [0.12, 0.34, 0.56, 0.78]

        return ClinicalTextResponse(
            patient_id=payload.patient_id,
            encounter_id=payload.encounter_id,
            extracted_signals=signals,
            embeddings=embeddings,
            explainability=ExplainabilityDetails(
                model="BioClinicalBERT-placeholder",
                note_count=len(payload.clinical_notes),
                disclaimer="Decision-support extraction only; no diagnosis is produced.",
                evidences=evidences,
            ),
        )
