/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.snmptest.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

/**
 *
 * @author MMayor
 */
public interface IXmlMarshaller {

    public String[] marshallXML(MultipartFile file) throws SAXException, ParserConfigurationException, IOException, Exception;


}
