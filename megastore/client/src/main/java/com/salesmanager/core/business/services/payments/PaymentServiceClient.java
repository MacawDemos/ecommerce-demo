package com.salesmanager.core.business.services.payments;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.CreditCardType;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;
import io.macaw.test.impl.ServiceConfig;
import java.lang.String;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceClient implements PaymentService {
  private Megastore megastore;

  public PaymentServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getPaymentMethods(MerchantStore store) throws ServiceException {
    return megastore.paymentGetPaymentMethods(store);
  }

  public List getAcceptedPaymentMethods(MerchantStore store) throws ServiceException {
    return megastore.paymentGetAcceptedPaymentMethods(store);
  }

  public IntegrationModule getPaymentMethodByType(MerchantStore store, String type) throws
      ServiceException {
    return megastore.paymentGetPaymentMethodByType(store,type);
  }

  public IntegrationModule getPaymentMethodByCode(MerchantStore store, String code) throws
      ServiceException {
    return megastore.paymentGetPaymentMethodByCode(store,code);
  }

  public IntegrationConfiguration getPaymentConfiguration(String moduleCode, MerchantStore store)
      throws ServiceException {
    return megastore.paymentGetPaymentConfiguration(moduleCode,store);
  }

  public Map getPaymentModulesConfigured(MerchantStore store) throws ServiceException {
    return megastore.paymentGetPaymentModulesConfigured(store);
  }

  public void savePaymentModuleConfiguration(IntegrationConfiguration configuration,
      MerchantStore store) throws ServiceException {
    try {
        Object out = megastore.paymentSavePaymentModuleConfiguration(configuration,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(configuration, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void removePaymentModuleConfiguration(String moduleCode, MerchantStore store) throws
      ServiceException {
    megastore.paymentRemovePaymentModuleConfiguration(moduleCode,store);
  }

  public Transaction processPayment(Customer customer, MerchantStore store, Payment payment,
      List items, Order order) throws ServiceException {
    return megastore.paymentProcessPayment(customer,store,payment,items,order);
  }

  public PaymentModule getPaymentModule(String paymentModuleCode) throws ServiceException {
    return megastore.paymentGetPaymentModule(paymentModuleCode);
  }

  public Transaction processCapturePayment(Order order, Customer customer, MerchantStore store)
      throws ServiceException {
    return megastore.paymentProcessCapturePayment(order,customer,store);
  }

  public Transaction processRefund(Order order, Customer customer, MerchantStore store,
      BigDecimal amount) throws ServiceException {
    return megastore.paymentProcessRefund(order,customer,store,amount);
  }

  public void validateCreditCard(String number, CreditCardType creditCard, String month,
      String date) throws ServiceException {
    megastore.paymentValidateCreditCard(number,creditCard,month,date);
  }
}
