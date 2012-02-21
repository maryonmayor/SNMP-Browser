/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmptest.controller;

import com.snmptest.domain.MIBBrowserForm;
import com.snmptest.service.ISnmpManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.net.Inet4Address;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author MMayor
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"user"})
public class MainController {

    private ISnmpManager snmpManager;

    @Autowired
    @Qualifier("snmpManager")
    public void setSnmpManager(ISnmpManager snmpManager) {
        this.snmpManager = snmpManager;
    }

    @RequestMapping(value = "index.htm", method = RequestMethod.POST)
    public String mibResult( Model model,
            @ModelAttribute("mibForm") MIBBrowserForm mibbf,
            BindingResult bindingResult, SessionStatus sessionStatus)
            throws IOException, Exception {
        //determine if the input IP address is reachable
        if(!Inet4Address.getByName(mibbf.getAddress()).isReachable(10000)) {
            model.addAttribute("error", "ERROR: unreachable IP:"+mibbf.getAddress());
            return "manager/form";
        }
        model.addAttribute("oids",snmpManager.extractOids(mibbf.getMib(), mibbf.getAddress()));
        return "manager/index";
    }

    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("mibForm", new MIBBrowserForm());
        return "manager/form";
    }

    @ModelAttribute("mibs")
    public List<String> showMibs(){
          List<String> mibFiles = snmpManager.listOfFiles();
          return mibFiles;
    }

    @RequestMapping(value = "upload_mib.htm", method = RequestMethod.POST)
    public String mibList(@RequestParam("oid_file") MultipartFile file) throws FileNotFoundException, IOException {
        snmpManager.writeToFile(file);
        return "redirect:index.htm";
    }
}
