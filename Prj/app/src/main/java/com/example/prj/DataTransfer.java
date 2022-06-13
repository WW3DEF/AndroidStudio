package com.example.prj;

import android.graphics.drawable.Drawable;

public class DataTransfer {
    private String docNum;
    private String docTitle;
    private String docClass;
    private String docSummary;
    private String docExplain;
    private String docDate;

    public DataTransfer(){

    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocClass() {
        return docClass;
    }

    public void setDocClass(String docClass) {
        this.docClass = docClass;
    }

    public String getDocSummary() {
        return docSummary;
    }

    public void setDocSummary(String docSummary) {
        this.docSummary = docSummary;
    }

    public String getDocExplain() {
        return docExplain;
    }

    public void setDocExplain(String docExplain) {
        this.docExplain = docExplain;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }
}
