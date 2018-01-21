package com.salesmanager.core.business.modules.email;

import com.salesmanager.core.model.email.Email;
import com.salesmanager.core.model.email.EmailConfig;

public interface HtmlEmailSender {
	
	public void send(final Email email) throws Exception;

	public void setEmailConfig(EmailConfig emailConfig);

}
