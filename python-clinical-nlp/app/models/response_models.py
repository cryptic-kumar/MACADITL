from typing import Any, Dict, List

from pydantic import BaseModel


class ClinicalTextResponse(BaseModel):
    patient_id: str
    encounter_id: str
    extracted_signals: List[str]
    embeddings: List[float]
    explainability: Dict[str, Any]
