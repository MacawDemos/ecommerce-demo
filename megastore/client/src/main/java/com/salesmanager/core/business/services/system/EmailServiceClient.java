package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.email.Email;
import com.salesmanager.core.model.email.EmailConfig;
import com.salesmanager.core.model.merchant.MerchantStore;
import io.macaw.test.impl.ServiceConfig;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceClient implements EmailService {
  private Megastore megastore;

  public EmailServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException {
    return megastore.emailGetEmailConfiguration(store);
  }

  public void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws
      ServiceException {
    try {
        Object out = megastore.emailSaveEmailConfiguration(emailConfig,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(emailConfig, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException {
    megastore.emailSendHtmlEmail(store,email);
  }
}
