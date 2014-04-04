package com.email.service;

import java.util.Date;

import com.email.model.Component;
import com.email.model.Data;
import com.email.model.ComposeMail;
import com.email.model.Template;

public class DummyValues {
	
	public static final int TEMPLATE_OPTION_VALUE=2;
	public static final int SELECTED_MESSAGE_OPTION = 2;
	
	public static final String USERNAME="cybertech";
	public static final String PASSWORD="cybertech";
	public static final String TEMPLATE_VALUE="7";
	public static final String TEMPLATE_NAME="Test Templates CID";
	public static final String TEMPLATE_CONTENT="Test Content Value";
	public static final String MESSAGE_SUBJECT="Subject";
	public static final String MESSAGE_SENDER="subject@subject.com";
	public static final String SELECTED_MESSAGE_ROW="4";
	
	public static final String COMPONENT_MODEL="testModel";
	public static final String MANUFACTURER="Ferrari";
	public static final String COLOR="Green";
	public static final String YEAR="2012";
	public static final String SELECTED_ROW="6";
	
	public static final String COMPONENT_NAME="MessageList";
	public static final String COMPONENT_PAGE="messageList";
	
	public static Component createDummyComponents() {
		Component component = new Component();
		component.setName("TestValue");
		component.setPage("messageList");
		return component;
	}
	
	public static Template createDummyTemplate() {
		Template template = new Template();
		template.setContent("templateContent");
		template.setName("templateName");
		return template;
	}

	public static Template editDummyTemplate() {
		Template template = new Template();
		template.setId(16);
		template.setContent("templateContent");
		template.setName("templateName");
		return template;
	}
	
	public static ComposeMail createDummyMail() {
		ComposeMail composeMail = new ComposeMail();
		composeMail.setMessage("message1");
		composeMail.setSendDate(new Date());
		composeMail.setToAddress("address1");
		composeMail.setContent("messageContent");
		return composeMail;
	}
	
	public static Data createDummyComponent() {
		Data component = new Data();
		component.setColour("Black");
		component.setManufacturer("Opel");
		component.setModel("21009a40");
		component.setYear("2005");
		return component;
	}
}
