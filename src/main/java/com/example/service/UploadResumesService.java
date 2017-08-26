package com.example.service;

/**
 * Created by Administrator on 2017/8/25.
 */
public interface UploadResumesService {
    void uploadResumesToTempFolder(String originalResumesPath, String tempPath, String dataPath, String companyName, String companyId, String emailAddress);
}
