package com.example.prj;

import android.graphics.drawable.Drawable;

public class DataTransfer {
    private String docNum;
    private String docTitle;
    private Drawable docClass;
    private String docSummary;
    private String docDate;


    public DataTransfer(String docNum, String docTitle, Drawable docClass, String docSummary, String docDate) {
        this.docNum = docNum;
        this.docTitle = docTitle;
        this.docClass = docClass;
        this.docSummary = docSummary;
        this.docDate = docDate;
    }
    public DataTransfer() {

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

    public Drawable getDocClass() {
        return docClass;
    }

    public void setDocClass(Drawable docClass) {
        this.docClass = docClass;
    }

    public String getDocSummary() {
        return docSummary;
    }

    public void setDocSummary(String docSummary) {
        this.docSummary = docSummary;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }
}
