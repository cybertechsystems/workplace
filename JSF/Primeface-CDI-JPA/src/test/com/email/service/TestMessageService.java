package com.email.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperPrint;

import org.junit.Before;
import org.junit.Test;

import com.email.dao.MessageDAO;
import com.email.dao.MessageDAOImpl;
import com.email.model.ComposeMail;
import com.email.services.MessageService;
import com.email.services.MessageServiceImpl;

public class TestMessageService {

	ComposeMail composeMail = new ComposeMail();
	@Inject
	MessageService messageService = new MessageServiceImpl();
	@Inject
	MessageDAO messageDAO = new MessageDAOImpl();
	
	@Before
	public void setUp() {
		
		try {
			messageService.setMessageDAO(messageDAO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used for testing save mail checking the size of
	 * mails in database saving the new values, again checking the size of
	 * mails in database when comparing if it is greater than one,success
	 * the test
	 */
	@Test
	public void testSaveMail() {

		try {
			List<ComposeMail> mails = this.messageService.findAllMail();
			int countBefore = mails.size();
			saveMail();
			mails =  this.messageService.findAllMail();
			int countAfter = mails.size();
			assertEquals(countAfter, countBefore + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used for testing delete mail, checking the size of
	 * mails in database deleting values, again checking the size of
	 * mails in database when comparing if it is less than one,success the
	 * test
	 */
	@Test
	public void testDelete() {

		try {
			saveMail();
			List<ComposeMail> mails = this.messageService.findAllMail();
			int countBefore = mails.size();
			this.messageService.delete(composeMail);
			mails =  this.messageService.findAllMail();
			int countAfter = mails.size();
			assertEquals(countAfter, countBefore - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveMail() throws Exception {
		composeMail = DummyValues.createDummyMail();
		this.messageService.save(composeMail);
	}
	
	@Test
	public void testReport() {

		try {
			String rootPath = "C://mailModule/";
			List<ComposeMail> mailList = messageService.findAllMail();
			JasperPrint jasperPrint = messageService.messageList(mailList,rootPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
