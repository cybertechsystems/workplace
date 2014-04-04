package com.email.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.email.model.Template;
import com.email.services.TemplateService;
import com.email.util.Constants;
import com.email.view.ManageTemplate;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class TestManageTemplate {
	
	private WebClient webClient;
	private HtmlPage loginPage;
	private HtmlPage homePage;
	private Template template;
	private ManageTemplate manageTemplate;
	@Inject
	private TemplateService templateService;
	
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
		    
		    template = new Template();
		    manageTemplate = new ManageTemplate();
		} catch (Exception e) {
			
		}
	}

	@Test
	public void validateLogin() {
		
		HtmlForm form = loginPage.getFormByName("login");
		form.getInputByName("j_username").setValueAttribute(DummyValues.USERNAME);
	    form.getInputByName("j_password").setValueAttribute(DummyValues.PASSWORD);

	    HtmlSubmitInput button = (HtmlSubmitInput) form.getElementById("submitBtn");
		try {
			homePage = (HtmlPage) button.click();
		} catch (IOException e) {
			
		}
		String menu = homePage.asText();
		
		assertTrue(menu.contains("Menu"));
	}
	
	/**
	 * This method is used for testing save template.
	 * this method get the template name input and content editor field.
	 * And set the values for save.
	 */
	@Test
	public void testSaveTemplate() {
		
		HtmlPage manageTemplatePage;
		try {
			//Get the first page
			manageTemplatePage = webClient.getPage(Constants.MANAGE_TEMPLATE_URL);
			HtmlSelect select = (HtmlSelect) manageTemplatePage.getElementById("select");
			int optionSizeBefore = select.getOptionSize()-1;
			
			//get the template name and content field from Html page
			HtmlTextInput templateNameInputTxt = (HtmlTextInput)manageTemplatePage.getElementById("name");
			HtmlTextArea templateContent = (HtmlTextArea)manageTemplatePage.getByXPath("//textarea").get(0);
			
			//set templatename and content.
			templateNameInputTxt.setValueAttribute(DummyValues.TEMPLATE_NAME);
			templateContent.setText(DummyValues.TEMPLATE_CONTENT);
			
			//get the Create Template Button from the Html Page
			HtmlButton createTemplateBtn = (HtmlButton) manageTemplatePage.getElementById("createTemplateBtn");
			//HtmlSubmitInput createTemplateBtn = (HtmlSubmitInput) manageTemplatePage.getElementById("createTemplateBtn");
			//createTemplateBtn.removeAttribute("oncomplete");
			//createTemplateBtn.removeAttribute("onclick");
			homePage = (HtmlPage) createTemplateBtn.click();
			
			//get the refreshed page
			HtmlPage newPage = webClient.getPage(Constants.MANAGE_TEMPLATE_URL);
			HtmlSelect selectNew = (HtmlSelect) newPage.getElementById("select");
			int optionSizeAfer = selectNew.getOptionSize()-1;
			
			assertEquals(optionSizeAfer, optionSizeBefore + 1);
			
			webClient.closeAllWindows();
			
		} catch (IOException e) {
			
		}
		
	}
	/**
	 * This method is used for testing edit template method.
	 * Get id from the Html page and set updated value. 
	 */
	@Test
	public void testEditTemplate() {
		
		HtmlPage manageTemplatePage;
		try {
			//Get the first page
			manageTemplatePage = webClient.getPage(Constants.MANAGE_TEMPLATE_URL);
			HtmlSelect select = (HtmlSelect) manageTemplatePage.getElementById("select");
			select.getOption(DummyValues.TEMPLATE_OPTION_VALUE);
			
			//get template name and content field.
			HtmlHiddenInput templateId = (HtmlHiddenInput) manageTemplatePage.getElementById("id");
			HtmlTextInput templateNameInputTxt = (HtmlTextInput)manageTemplatePage.getElementById("name");
			HtmlTextArea templateContent = (HtmlTextArea)manageTemplatePage.getByXPath("//textarea").get(0);
			
			 // Change the value of the fields
			templateId.setValueAttribute("7");
			templateNameInputTxt.setValueAttribute("Templates");
			templateContent.setText("TestEditContent");
		
			//Get Edit Template button and submit the data
			HtmlButton editTemplateBtn = (HtmlButton) manageTemplatePage.getElementById("editTemplateBtn");
			homePage = (HtmlPage) editTemplateBtn.click();
			
			assertEquals(templateNameInputTxt.getText(), "Templates");
			assertEquals(templateContent.getText(),"TestEditContent");
			
			//close the window
			webClient.closeAllWindows();
			
		} catch (IOException e) {
			
		}
		
	}
	
	/**
	 * This method is used for testing delete template method.
	 * onchange of dropdown getting template values. 
	 */
	@Test
	public void testDeleteTemplate() {
		HtmlPage manageTemplatePage;
		try {
			//Get the first page
			manageTemplatePage = webClient.getPage(Constants.MANAGE_TEMPLATE_URL);
			HtmlSelect select = (HtmlSelect) manageTemplatePage.getElementById("select");
			//onchange of option,getting the template values
			int optionSizeBefore = select.getOptionSize()-1;
			HtmlOption option = select.getOption(DummyValues.TEMPLATE_OPTION_VALUE);
			select.setSelectedAttribute(option, true);
			
			//get template button from html page and delete templates.
			HtmlButton deleteTemplateBtn = (HtmlButton) manageTemplatePage.getElementById("deleteTemplateBtn");
			homePage = (HtmlPage) deleteTemplateBtn.click();
			
			//get refreshed page
			HtmlPage newPage = webClient.getPage(Constants.MANAGE_TEMPLATE_URL);
			HtmlSelect selectNew = (HtmlSelect) newPage.getElementById("select");
			int optionSizeAfer = selectNew.getOptionSize()-1;
			
			assertEquals(optionSizeAfer, optionSizeBefore - 1);
			
			//close html page
			webClient.closeAllWindows();
			
		}catch (IOException e) {
			
		}
	}
}
