package com.salesmanager.core.business.services.tax;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.TaxConfiguration;
import io.macaw.test.impl.ServiceConfig;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceClient implements TaxService {
  private Megastore megastore;

  public TaxServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public TaxConfiguration getTaxConfiguration(MerchantStore store) throws ServiceException {
    return megastore.taxGetTaxConfiguration(store);
  }

  public void saveTaxConfiguration(TaxConfiguration shippingConfiguration, MerchantStore store)
      throws ServiceException {
    try {
        Object out = megastore.taxSaveTaxConfiguration(shippingConfiguration,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(shippingConfiguration, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List calculateTax(OrderSummary orderSummary, Customer customer, MerchantStore store,
      Language language) throws ServiceException {
    return megastore.taxCalculateTax(orderSummary,customer,store,language);
  }
}
