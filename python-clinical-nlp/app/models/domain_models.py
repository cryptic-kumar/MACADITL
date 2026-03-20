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
