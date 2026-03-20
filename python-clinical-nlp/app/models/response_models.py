from typing import List

from pydantic import BaseModel


class SignalEvidence(BaseModel):
    signal: str
    evidence: str
    confidence: float


class ExplainabilityDetails(BaseModel):
    model: str
    note_count: int
    disclaimer: str
    evidences: List[SignalEvidence]


class ClinicalTextResponse(BaseModel):
    patient_id: str
    encounter_id: str
    extracted_signals: List[str]
    embeddings: List[float]
    explainability: ExplainabilityDetails
