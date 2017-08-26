package com.example.controller;

import com.example.model.EmailAddress;
import com.example.model.ResumeProperty;
import com.example.service.UploadResumesService;
import com.example.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class Login {
    @Autowired
    private ResumeProperty resumeProperty;

    @Autowired
    private UploadResumesService uploadResumesService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        return "Hello World~";
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ModelAndView index(Model model){
        ModelAndView mav = new ModelAndView("email");
        List<String> emailAddresses = EncryptUtil.getGpgFingerprint();
        List<EmailAddress> emailAddressList = new ArrayList<>();
        for (String emailAddress : emailAddresses) {
            emailAddressList.add(new EmailAddress(emailAddress));
        }
        model.addAttribute("emails", emailAddressList);
        mav.addObject(model);
        return mav;
    }

    @RequestMapping(value="/cmd",method = RequestMethod.GET)
    public void executeCmdCommand() {
        String originalResumesPath = resumeProperty.getOriginalResumesPath();
        String tempPath = resumeProperty.getTempPath();
        String companyName = resumeProperty.getCompanyName();
        String companyId = resumeProperty.getCompanyId();
        String dataPath = resumeProperty.getDataPath();

        String email = "18862172316@163.com";
        uploadResumesService.uploadResumesToTempFolder(originalResumesPath, tempPath, dataPath, companyName, companyId, email);
    }

}