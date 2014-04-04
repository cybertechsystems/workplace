package com.email.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.email.model.ComposeMail;
import com.email.services.MessageService;
import com.email.util.Constants;
import com.email.view.ManageMessage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TestManageMessage {
	
	private WebClient webClient;
	private HtmlPage loginPage;
	private HtmlPage homePage;
	private HtmlPage mailPage;
	private HtmlPage manageMessagePage;
	private ComposeMail composeMail;
	private ManageMessage manageMessage;
	@Inject
	private MessageService messageService;
	
	@Before
	public void setUp() {
		try {
			webClient = new WebClient();
			webClient.setThrowExceptionOnScriptError(false);
			loginPage = webClient.getPage(Constants.APLICATTION_URL);
			
			HtmlForm form = loginPage.getFormByName("login");
			form.getInputByName("j_username").setValueAttribute(DummyValues.USERNAME);
		    form.getInputByName("j_password").setValueAttribute(DummyValues.PASSWORD);

		    HtmlSubmitInput button = (HtmlSubmitInput) form.getElementById("submitBtn");
		    homePage = (HtmlPage) button.click();
		    
		    composeMail = new ComposeMail();
		    manageMessage = new ManageMessage();
		} catch (Exception e) {
			
		}
	}
	/**
	 * This method is used for testing send mail.
	 * In this get the Htmlpage and set the values.
	 */
	@Test
	public void testSendMail() {
		
		try {
			//getting the html page
			HtmlPage listPage = webClient.getPage(Constants.MANAGE_MESSAGE_URL);
			//get the table and count number
			HtmlDivision divTag = (HtmlDivision) listPage.getElementById("datatable");
			int rowsBefore = divTag.getHtmlElementsByTagName("tr").size();
			manageMessagePage = webClient.getPage(Constants.MANAGE_MAIL_URL);
			
			//fetch the option from dropdown by id
			HtmlSelect select = (HtmlSelect) manageMessagePage.getElementById("templates");
			HtmlOption option = select.getOption(DummyValues.SELECTED_MESSAGE_OPTION);
			select.setSelectedAttribute(option, true);
			String content = option.getValueAttribute();
			
			//get subject,address and content field from page.
			HtmlTextInput subjectInputTxt = (HtmlTextInput)manageMessagePage.getElementById("subject");
			HtmlTextInput toAddressInputTxt = (HtmlTextInput)manageMessagePage.getElementById("toAddress");
			HtmlTextArea messageContent = (HtmlTextArea)manageMessagePage.getByXPath("//textarea").get(0);
			
			//set the values to subject,address and content.
			subjectInputTxt.setValueAttribute(DummyValues.MESSAGE_SUBJECT);
			toAddressInputTxt.setValueAttribute(DummyValues.MESSAGE_SENDER);
			messageContent.setText(content);
			
			//get the send button from the Html page and submit the data
			HtmlButton sendButton = (HtmlButton) manageMessagePage.getElementById("sendButton");
			homePage = (HtmlPage) sendButton.click();
			
			HtmlPage newPage = webClient.getPage(Constants.MANAGE_MESSAGE_URL);
			HtmlDivision divTagAfter = (HtmlDivision) newPage.getElementById("datatable");
			int rowsAfter = divTagAfter.getHtmlElementsByTagName("tr").size();
			
			assertEquals(rowsAfter, rowsBefore + 1);
			
			//close HtmlPage
			webClient.closeAllWindows();
		}catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is used to test delete message method.
	 * this method get selected row and delete that row from table
	 */
	@Test
	public void testDelete() {
		try {
			manageMessagePage = webClient.getPage(Constants.MANAGE_MESSAGE_URL);
			HtmlDivision divTag = (HtmlDivision) manageMessagePage.getElementById("datatable");
			int rowsBefore = divTag.getHtmlElementsByTagName("tr").size();
			
			//select a row
			HtmlHiddenInput select = (HtmlHiddenInput) manageMessagePage.getElementById("datatable_selection");
			select.setValueAttribute(DummyValues.SELECTED_MESSAGE_ROW);
			
			//get delete button from html page and delete selected row
			HtmlButton deleteButton = (HtmlButton) manageMessagePage.getElementById("datatable:deleteButton");
			mailPage = deleteButton.click();
			
			HtmlPage newPage = webClient.getPage(Constants.MANAGE_MESSAGE_URL);
			HtmlDivision divTagAfter = (HtmlDivision) newPage.getElementById("datatable");
			int rowsAfter = divTagAfter.getHtmlElementsByTagName("tr").size();
			
			assertEquals(rowsAfter, rowsBefore - 1);
			
			//close Html page
			webClient.closeAllWindows();
		}catch (Exception e) {
			
		}
	}
}