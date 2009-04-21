/**********************************************************************************
 * $URL$
 * $Id$
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

package org.sakaiproject.emailtemplateservice.tool.producers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.emailtemplateservice.model.EmailTemplate;
import org.sakaiproject.emailtemplateservice.service.EmailTemplateService;
import org.sakaiproject.emailtemplateservice.tool.params.EmailTemplateViewParams;

import uk.org.ponder.rsf.components.UIBranchContainer;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.DefaultView;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.ViewParameters;

public class MainViewProducer implements ViewComponentProducer, DefaultView {

	public static String VIEW_ID = "main";
	
	public String getViewID() {
		// TODO Auto-generated method stub
		return VIEW_ID;
	}

	private static Log log = LogFactory.getLog(MainViewProducer.class);
	
	private EmailTemplateService emailTemplateService;
	public void setEmailTemplateService(EmailTemplateService ets) {
		emailTemplateService = ets;
	}
	
	public void fillComponents(UIContainer tofill, ViewParameters viewparams,
			ComponentChecker checker) {
		
		UIInternalLink.make(tofill,"actions-add", new EmailTemplateViewParams(ModifyEmailProducer.VIEW_ID, null));
		
		List<EmailTemplate> templates = emailTemplateService.getEmailTemplates(0, 0);
		for (int i =0; i < templates.size(); i++) {
			EmailTemplate template = templates.get(i);
			log.debug("got template: " + template.getKey());
			UIBranchContainer row = UIBranchContainer.make(tofill, "template-row:", template.getId().toString());
			UIOutput.make(row, "template-key", template.getKey());
			String locale = template.getLocale();
			if (locale == null )
				locale = "";
			UIOutput.make(row, "template-locale", locale);
			UIInternalLink.make(row,"template-edit", new EmailTemplateViewParams(ModifyEmailProducer.VIEW_ID, template.getId().toString()));
		}
	}

}
