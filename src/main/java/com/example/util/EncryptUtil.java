package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EncryptUtil {
    public static void encryptByGpg4win(String emailAddress, String tempPath, String companyName) {
        String encryptStr = "D:\\gpg4win\\GnuPG\\pub\\gpg --encrypt-files -r " + emailAddress + " --always-trust  \"" + tempPath + "\\" + companyName + ".zip\"";
        try {
            System.out.println(encryptStr);
            Runtime.getRuntime().exec(encryptStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getGpgFingerprint() {
        String getFingerprint = "D:\\gpg4win\\GnuPG\\pub\\gpg --fingerprint";
        String line;
        List<String> emailAddress = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(getFingerprint);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("<") && line.contains(">")) {
                    emailAddress.add(line.substring(line.indexOf("<") + 1, line.indexOf(">")));
                }
            }
            return emailAddress;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
