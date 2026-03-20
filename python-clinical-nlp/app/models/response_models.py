from typing import List

from pydantic import BaseModel

from app.models.domain_models import ExplainabilityDetails


class ClinicalTextResponse(BaseModel):
    patient_id: str
    encounter_id: str
    extracted_signals: List[str]
    embeddings: List[float]
    explainability: ExplainabilityDetails
