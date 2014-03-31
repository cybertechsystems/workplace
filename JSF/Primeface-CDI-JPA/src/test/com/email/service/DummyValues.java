package com.email.service;

import java.util.Date;

import com.email.model.ComposeMail;
import com.email.model.Template;

public class DummyValues {
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
}
