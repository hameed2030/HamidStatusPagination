package com.abuleath.whatsapp_status;

public class Status {


    private String statusText;
    private int statusId;
    private int categoryId;
    private String created_at;


    public Status(String statusText, int statusId , int categoryId , String created_at) {
        this.statusText = statusText;
        this.statusId = statusId;
        this.categoryId = categoryId;
        this.created_at = created_at;
    }

    public Status(){}

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
