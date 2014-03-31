package com.email.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.email.common.BaseTestCase;
import com.email.dao.TemplateDAO;
import com.email.dao.TemplateDAOImpl;
import com.email.model.Template;
import com.email.services.TemplateService;
import com.email.services.TemplateServiceImpl;

public class TestManageService extends BaseTestCase
{
		Template template = new Template();
		@Inject
		 TemplateService templateService = new TemplateServiceImpl();
		@Inject
		 TemplateDAO templateDAO = new TemplateDAOImpl();
		
		@Before
		public void setUp() {
			
			try {
				templateService.setTemplateDAO(templateDAO);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used for testing save template checking the size of
		 * templates in database saving the new values again checking the size of
		 * templates in database when comparing if it is greater than one,success
		 * the test
		 */
		@Test
		public void testSave() {

			try {
				List<Template> templates = this.templateService.findAll();
				int countBefore = templates.size();
				saveTemplate();
				templates = this.templateService.findAll();
				int countAfter = templates.size();
				assertEquals(countAfter, countBefore + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used for testing edit template saving the edited values
		 * retrieving the template object based on id which we edited when comparing
		 * if it is equals,success the test
		 */
		@Test
		public void testEdit() {

			try {
				template = DummyValues.editDummyTemplate();
			    this.templateService.saveOrUpdate(template);
				template = this.templateService.findById((long) (16));
				assertEquals(template.getContent(), "templateContent");
				assertEquals(template.getName(), "templateName");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used for testing delete template checking the size of
		 * templates in database deleting values again checking the size of
		 * templates in database when comparing if it is less than one,success the
		 * test
		 */
		@Test
		public void testDelete() {

			try {
				saveTemplate();
				List<Template> templates = this.templateService.findAll();
				int countBefore = templates.size();
				this.templateService.remove(template);
				templates = this.templateService.findAll();
				int countAfter = templates.size();
				assertEquals(countAfter - 1, countBefore);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used for testing name validation checking name is existing
		 * in database if name not exists,success the test
		 */
		@Test
		public void testNameValidation() {

			try {
				String name = "templateName1";
				boolean ifExists = templateService.findByName(name);
				assertTrue(!ifExists);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveTemplate() throws Exception {
			template = DummyValues.createDummyTemplate();
			this.templateService.saveOrUpdate(template);
		}

}
