package com.email.services;

import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;

import com.email.dao.MessageDAO;
import com.email.dao.TemplateDAO;
import com.email.model.ComposeMail;

public interface MessageService {
	/* Is called when the user saves the mail. */
	public void save (ComposeMail mail) throws Exception;
	
	/* Is called when the user listing all mails. */
	public List<ComposeMail> findAllMail() throws Exception;
	
	/* Is called when the user delete the mail. */
	public void delete(ComposeMail ComposeMail) throws Exception;
	
	/* Is called when the user download the mail. */
	public JasperPrint messageList(List<ComposeMail> mailList,String rootPath) throws Exception;
	
	public void setMessageDAO(MessageDAO messageDAO);

}
