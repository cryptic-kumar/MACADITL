from fastapi import FastAPI

from app.models.request_models import ClinicalTextRequest
from app.models.response_models import ClinicalTextResponse
from app.services.clinical_text_pipeline import ClinicalTextPipeline

app = FastAPI(title="Clinical Data Intelligence Agent", version="0.1.0")
pipeline = ClinicalTextPipeline()


@app.get("/health")
def health() -> dict[str, str]:
    return {"status": "ok"}


@app.post("/api/v1/clinical-text/analyze", response_model=ClinicalTextResponse)
def analyze_clinical_text(payload: ClinicalTextRequest) -> ClinicalTextResponse:
    return pipeline.analyze(payload)
