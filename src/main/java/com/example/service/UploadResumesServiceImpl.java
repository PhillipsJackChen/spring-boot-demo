package com.example.service;

import com.example.util.EncryptUtil;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UploadResumesServiceImpl implements UploadResumesService {
    @Override
    public void uploadResumesToTempFolder(String originalResumesPath, String tempPath, String dataPath, String companyName, String companyId, String emailAddress) {
        String xcopyResume1 = "xcopy " + originalResumesPath + "\\" + companyName + "  " + tempPath + "\\" + companyName + "\\" + companyName + "_resumes(1)\\" + " /E /H";
        String xcopyResume2 = "xcopy " + originalResumesPath + "\\" + companyId + "  " + tempPath + "\\" + companyName + "\\" + companyName + "_resumes(2)\\" + " /E /H";
        String xcopyData = "xcopy " + dataPath + "\\output" + "  " + tempPath + "\\" + companyName + "\\" + companyName + "_data\\" + " /E /H";
        String compressResumeAndData = "C:\\Program Files\\7-Zip\\7z.exe a -tzip " + tempPath + "\\" + companyName + ".zip" + " " + tempPath + "\\" + companyName + "\\";
        String deleteFolder = "cmd /c rd /s /q " + "\"" + tempPath + "\\" + companyName + "\\\"";
        String findString = "findstr \"Execute\" " + dataPath + "\\company-data-export-tool.log";
        System.out.println("---------------start copy-----------------");
        try {
            Process process = Runtime.getRuntime().exec(findString);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (bufferedReader.readLine() == null) {
                System.out.println("waiting for generate \"Execute\"......");
                Thread.sleep(30000);
                process = Runtime.getRuntime().exec(findString);
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            }
            System.out.println("generate \"Execute\" success! Starting execute xcopy......");

            Runtime.getRuntime().exec(xcopyResume1);
            Runtime.getRuntime().exec(xcopyResume2);
            Runtime.getRuntime().exec(xcopyData);
            System.out.println("Execute xcopy finished! Starting compress resuems and data......");
            Runtime.getRuntime().exec(compressResumeAndData);
            System.out.println("compress finished!");
            System.out.println(deleteFolder);
            Runtime.getRuntime().exec(deleteFolder);
            System.out.println("Starting encrypt .zip file by this email address: " + emailAddress + " ......");
            EncryptUtil.encryptByGpg4win(emailAddress, tempPath, companyName);
            System.out.println("encrypt finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to copy resume and data");
        }
    }
}
