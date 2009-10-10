/**********************************************************************************
 * $URL: https://source.sakaiproject.org/svn/emailtemplateservice/branches/sakai_2-6-x/api/src/java/org/sakaiproject/emailtemplateservice/model/RenderedTemplate.java $
 * $Id: RenderedTemplate.java 60508 2009-04-21 21:22:46Z arwhyte@umich.edu $
 ***********************************************************************************
 *
 * Copyright (c) 2006, 2007, 2008, 2009 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
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

package org.sakaiproject.emailtemplateservice.model;

public class RenderedTemplate extends EmailTemplate {

   private String renderedSubject;
   private String renderedMessage;

   public RenderedTemplate(EmailTemplate template) {
      this.setId(template.getId());
      this.setSubject(template.getSubject());
      this.setMessage(template.getMessage());
      this.setKey(template.getKey());
      this.setLocale(template.getLocale());

   }

   public String getRenderedMessage() {
      return renderedMessage;
   }

   public void setRenderedMessage(String renderedMessage) {
      this.renderedMessage = renderedMessage;
   }

   public String getRenderedSubject() {
      return renderedSubject;
   }

   public void setRenderedSubject(String renderedSubject) {
      this.renderedSubject = renderedSubject;
   }

}
