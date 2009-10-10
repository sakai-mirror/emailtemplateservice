/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/emailtemplateservice/branches/sakai_2-6-x/impl/logic/src/test/org/sakaiproject/emailtemplateservice/logic/test/TestEmailTemplateService.java $
 * $Id: TestEmailTemplateService.java 60508 2009-04-21 21:22:46Z arwhyte@umich.edu $
 ***********************************************************************************
 *
 * Copyright (c) 2008, 2009 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.emailtemplateservice.logic.test;

import java.util.Date;

import org.sakaiproject.emailtemplateservice.dao.impl.EmailTemplateServiceDao;
import org.sakaiproject.emailtemplateservice.model.EmailTemplate;
import org.sakaiproject.emailtemplateservice.service.EmailTemplateService;
import org.sakaiproject.emailtemplateservice.service.impl.EmailTemplateServiceImpl;
import org.springframework.test.AbstractTransactionalSpringContextTests;




public class TestEmailTemplateService extends AbstractTransactionalSpringContextTests {

	private static final String KEY_1 = "key1";
	private static final String KEY_2 = "key2";
	
	private static final String DEFAULT_LOCALE = null;

	private static final String ADMIN_USER = "admin";


	EmailTemplateServiceDao dao;
	EmailTemplateServiceImpl emailTemplateService;


	EmailTemplate template1 = new EmailTemplate();
	EmailTemplate template2 = new EmailTemplate();

	protected String[] getConfigLocations() {
		// point to the needed spring config files, must be on the classpath
		// (add component/src/webapp/WEB-INF to the build path in Eclipse),
		// they also need to be referenced in the project.xml file
		return new String[] {"hibernate-test.xml", "spring-hibernate.xml"};
	}
	// run this before each test starts
	protected void onSetUpBeforeTransaction() throws Exception {
		dao = (EmailTemplateServiceDao)applicationContext.getBean("org.sakaiproject.emailtemplateservice.dao.EmailTemplateServiceDao");
		if (dao == null) {
			throw new NullPointerException("DAO could not be retrieved from spring context");
		}

		emailTemplateService = new EmailTemplateServiceImpl();
		emailTemplateService.setDao(dao);
		

		template1.setKey(KEY_1);
		template1.setLocale(DEFAULT_LOCALE);
		template1.setLastModified(new Date());
		template1.setOwner(ADMIN_USER);
		template1.setSubject("Subject 1");
		template1.setMessage("message 1");
		emailTemplateService.saveTemplate(template1);

		template2.setKey(KEY_2);
		template2.setLocale(DEFAULT_LOCALE);
		template2.setLastModified(new Date());
		template2.setOwner(ADMIN_USER);
		template2.setSubject("Subject 2");
		template2.setMessage("message 2");
	}


	public void testSaveTemplate() {




		
		//saving should set the ID
		assertNotNull(template1.getId());

		emailTemplateService.saveTemplate(template2);
		assertNotNull(template2.getId());
		//if these are the same there is something very wrong
		assertNotSame(template2.getId(), template1.getId());
		
		
		
	}


	public void testGetTemplatebyId() {
		EmailTemplate t1 =this.emailTemplateService.getEmailTemplateById(new Long(1));
		assertNotNull(t1);
		assertEquals(t1.getKey(), KEY_1);
		
	}


	public void testGetEmailTemplate() {

	}


}
