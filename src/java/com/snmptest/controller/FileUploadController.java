/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmptest.controller;

import com.snmptest.domain.User;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author MMayor
 */
@Controller
@RequestMapping("/testSession.htm")
@SessionAttributes({"user"})
public class FileUploadController {


    @RequestMapping(method=RequestMethod.GET)
    public String index( User user, SessionStatus status, Model model) throws IOException {
    
        return "redirect:index.htm";
    }
}
