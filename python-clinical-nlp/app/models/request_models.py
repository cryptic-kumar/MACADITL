from datetime import datetime
from typing import Dict, List, Optional

from pydantic import BaseModel, Field


class ClinicalTextRequest(BaseModel):
    patient_id: str = Field(..., min_length=1)
    encounter_id: str = Field(..., min_length=1)
    observed_at: datetime
    clinical_notes: List[str] = Field(..., min_length=1)
    demographics: Optional[Dict[str, str]] = None
