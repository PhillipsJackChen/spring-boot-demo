package com.example.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResumeProperty {
    @Value("${properties.tempPath}")
    private String tempPath;

    @Value("${properties.companyName}")
    private String companyName;

    @Value("${properties.companyId}")
    private String companyId;

    @Value("${properties.dataPath}")
    private String dataPath;

    @Value("${properties.originalResumesPath}")
    private String originalResumesPath;

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getOriginalResumesPath() {
        return originalResumesPath;
    }

    public void setOriginalResumesPath(String originalResumesPath) {
        this.originalResumesPath = originalResumesPath;
    }
}
