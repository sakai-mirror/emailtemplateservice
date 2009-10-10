/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/emailtemplateservice/branches/sakai_2-6-x/tool/src/java/org/sakaiproject/emailtemplateservice/tool/locators/EmailTemplateLocator.java $
 * $Id: EmailTemplateLocator.java 60508 2009-04-21 21:22:46Z arwhyte@umich.edu $
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

package org.sakaiproject.emailtemplateservice.tool.locators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.emailtemplateservice.model.EmailTemplate;
import org.sakaiproject.emailtemplateservice.service.EmailTemplateService;

import uk.org.ponder.beanutil.WriteableBeanLocator;
import uk.org.ponder.messageutil.TargettedMessage;
import uk.org.ponder.messageutil.TargettedMessageList;

public class EmailTemplateLocator implements WriteableBeanLocator {

   private static Log log = LogFactory.getLog(EmailTemplateLocator.class);

   public static final String NEW_PREFIX = "new ";
   public static String NEW_1 = NEW_PREFIX + "1";
   private Map delivered = new HashMap();

   private EmailTemplateService emailTemplateService;
   public void setEmailTemplateService(EmailTemplateService ets) {
      emailTemplateService = ets;
   }

   private TargettedMessageList messages;
   public void setMessages(TargettedMessageList messages) {
      this.messages = messages;
   }

   public Object locateBean(String name) {
      Object togo=delivered.get(name);
      if (togo == null){
         if(name.startsWith(NEW_PREFIX)){
            togo = new EmailTemplate();
         }
         else { 
            log.info("looking for template: " + name);
            Long emailTemplateId = Long.valueOf(name);
            togo = emailTemplateService.getEmailTemplateById(emailTemplateId);
         }
         delivered.put(name, togo);
      }
      return togo;
   }


   public boolean remove(String beanname) {
      throw new UnsupportedOperationException("Not implemented");

   }
   public void set(String beanname, Object toset) {
      throw new UnsupportedOperationException("Not implemented");

   }


   public void saveAll() {
	   log.debug("Saving all!");
	   for (Iterator<String> it = delivered.keySet().iterator(); it.hasNext();) {
         String key = it.next();
         log.debug("got key: " + key);
         EmailTemplate emailTemplate = (EmailTemplate) delivered.get(key);
         if (key.startsWith(NEW_PREFIX)) {
            // add in extra logic needed for new items here
        	 if  (emailTemplate.getLocale() == null)
        		 emailTemplate.setLocale("");
         }

         emailTemplateService.saveTemplate(emailTemplate);
         messages.addMessage( new TargettedMessage("controlemailtemplates.template.saved.message",
               new Object[] { emailTemplate.getLocale(), emailTemplate.getSubject() }, 
               TargettedMessage.SEVERITY_INFO));
      }
   }

}
