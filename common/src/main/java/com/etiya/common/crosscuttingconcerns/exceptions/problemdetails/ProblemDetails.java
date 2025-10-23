package com.etiya.common.crosscuttingconcerns.exceptions.problemdetails;

public class ProblemDetails {
    //Problemin türünü tanımlayan URI (Örn: https://example.com/probs/business).
    private String type;

    //Problemin kısa, insan tarafından okunabilir özeti (Örn: "Business Rule Violation").
    private String title;

    //HTTP Durum Kodu (Örn: 400, 500).
    private int status;

    //Problemin oluş nedeni hakkında özel bir açıklama
    private String detail;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ProblemDetails(String type, String title, int status, String detail) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public ProblemDetails() {
    }
}
