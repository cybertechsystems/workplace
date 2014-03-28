package com.zsg.dcvps.util;

import java.security.MessageDigest;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Util {
	public static String getMD5Password(String password) throws Exception {
		byte[] bytesOfMessage = password.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		
		//convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < thedigest.length; i++) {
         sb.append(Integer.toString((thedigest[i] & 0xff) + 0x100, 16).substring(1));
        }
        
		return sb.toString();
	}
	
	public static void addErrorMsg(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}
	
	public static void addInfoMsg(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}
	
	public static void addMsg(FacesMessage msg) {
		FacesContext.getCurrentInstance().addMessage(null,  msg);
	}
	
	public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
    }
	
	public static boolean isNumeric(String s) {
        return (s == null) ? false : s.matches("[-+]?\\d*\\.?\\d+");  
    }
}
