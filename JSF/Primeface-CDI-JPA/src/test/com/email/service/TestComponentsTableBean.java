package com.email.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.email.model.Data;
import com.email.services.ComponentService;
import com.email.util.Constants;
import com.email.view.ComponentsTableBean;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TestComponentsTableBean {
	private WebClient webClient;
	private HtmlPage loginPage;
	private HtmlPage homePage;
	private HtmlPage componentPage;
	private HtmlPage listPage;
	private Data component;
	private ComponentsTableBean componentsTableBean;
	@Inject
	private ComponentService componentService;
	
	
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
		    
		    component = new Data();
		    componentsTableBean = new ComponentsTableBean();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is used for testing add data to datatable.
	 * get all inputfield and set the values.
	 */
	@Test
	public void testSave() {
		try {
			//Get the first page
			listPage = webClient.getPage(Constants.COMPONENT_URL);
			HtmlDivision divTag = (HtmlDivision) listPage.getElementById("datatable");
			int rowsBefore = divTag.getHtmlElementsByTagName("tr").size();
			componentPage = webClient.getPage(Constants.COMPONENT_ADD_URL);
			
			//get the input field from Html page
			HtmlTextInput componentModelInputTxt = (HtmlTextInput)componentPage.getElementById("model");
			HtmlTextInput componentManufacturerInputTxt = (HtmlTextInput)componentPage.getElementById("manufacturer");
			HtmlTextInput componentColorInputTxt = (HtmlTextInput)componentPage.getElementById("colour");
			HtmlTextInput componentYearInputTxt = (HtmlTextInput)componentPage.getElementById("year");
			
			//set inputFiled values.
			componentModelInputTxt.setValueAttribute(DummyValues.COMPONENT_MODEL);
			componentManufacturerInputTxt.setValueAttribute(DummyValues.MANUFACTURER);
			componentColorInputTxt.setValueAttribute(DummyValues.COLOR);
			componentYearInputTxt.setValueAttribute(DummyValues.YEAR);
			
			//get add Button from the Html Page
			HtmlButton createBtn = (HtmlButton) componentPage.getElementById("sendButton");
			homePage = (HtmlPage) createBtn.click();
			
			//get the refreshed page.
			listPage = webClient.getPage(Constants.COMPONENT_URL);
			HtmlDivision divTagAfter = (HtmlDivision) listPage.getElementById("datatable");
			int rowsAfter = divTagAfter.getHtmlElementsByTagName("tr").size();
			
			assertEquals(rowsAfter, rowsBefore + 1);
			
			//close the window
			webClient.closeAllWindows();
			
		} catch (IOException e) {
			
		}
		
	}
	
	/**
	 * This method is used to test delete data from the data table.
	 * get selected row and delete selected row from table
	 */
	@Test
	public void testDelete() {
		try {
			componentPage = webClient.getPage(Constants.COMPONENT_URL);
			HtmlDivision divTag = (HtmlDivision) componentPage.getElementById("datatable");
			int rowsBefore = divTag.getHtmlElementsByTagName("tr").size();
			
			//set a selected a row
			HtmlHiddenInput select = (HtmlHiddenInput) componentPage.getElementById("datatable_selection");
			select.setValueAttribute(DummyValues.SELECTED_ROW);
			
			//get delete button from html page and delete selected row
			HtmlButton deleteButton = (HtmlButton) componentPage.getElementById("datatable:deleteButton");
			homePage = deleteButton.click();
			
			HtmlPage newPage = webClient.getPage(Constants.COMPONENT_URL);
			HtmlDivision divTagAfter = (HtmlDivision) newPage.getElementById("datatable");
			int rowsAfter = divTagAfter.getHtmlElementsByTagName("tr").size();
			
			assertEquals(rowsAfter, rowsBefore - 1);
			
			//close Html page
			webClient.closeAllWindows();
		}catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is used for testing add data to datatable.
	 * get all inputfield and set the values.
	 */
	@Test
	public void testAddComponent() {
		try {
			//Get the first page
			listPage = webClient.getPage(Constants.COMPONENT_LIST_PAGE_URL);
			HtmlDivision divTag = (HtmlDivision) listPage.getElementById("datatable");
			int rowsBefore = divTag.getHtmlElementsByTagName("tr").size();
			componentPage = webClient.getPage(Constants.COMPONENT_LIST_ADD_URL);
			
			//get the input field from Html page
			HtmlTextInput componentNameInputTxt = (HtmlTextInput)componentPage.getElementById("name");
			HtmlTextInput componentPageInputTxt = (HtmlTextInput)componentPage.getElementById("page");
			
			//set inputFiled values.
			componentNameInputTxt.setValueAttribute(DummyValues.COMPONENT_NAME);
			componentPageInputTxt.setValueAttribute(DummyValues.COMPONENT_PAGE);
			
			//get add Button from the Html Page
			HtmlButton createBtn = (HtmlButton) componentPage.getElementById("sendButton");
			homePage = (HtmlPage) createBtn.click();
			
			//get the refreshed page.
			listPage = webClient.getPage(Constants.COMPONENT_LIST_PAGE_URL);
			HtmlDivision divTagAfter = (HtmlDivision) listPage.getElementById("datatable");
			int rowsAfter = divTagAfter.getHtmlElementsByTagName("tr").size();
			
			assertEquals(rowsAfter, rowsBefore + 1);
			
			//close the window
			webClient.closeAllWindows();
			
		} catch (IOException e) {
			
		}
		
	}

}
