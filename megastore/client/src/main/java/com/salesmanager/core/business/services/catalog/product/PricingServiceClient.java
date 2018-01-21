package com.salesmanager.core.business.services.catalog.product;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.currency.Currency;
import io.macaw.test.impl.ServiceConfig;
import java.lang.String;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceClient implements PricingService {
  private Megastore megastore;

  public PricingServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public String getDisplayAmount(BigDecimal amount, MerchantStore store) throws ServiceException {
    return megastore.pricingGetDisplayAmount(amount,store);
  }

  public String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency,
      MerchantStore store) throws ServiceException {
    return megastore.pricingGetDisplayAmount1(amount,locale,currency,store);
  }

  public FinalPrice calculateProductPrice(Product product, List attributes, Customer customer)
      throws ServiceException {
    return megastore.pricingCalculateProductPrice(product,attributes,customer);
  }

  public FinalPrice calculateProductPrice(Product product, List attributes) throws
      ServiceException {
    return megastore.pricingCalculateProductPrice1(product,attributes);
  }

  public FinalPrice calculateProductPrice(Product product, Customer customer) throws
      ServiceException {
    return megastore.pricingCalculateProductPrice2(product,customer);
  }

  public FinalPrice calculateProductPrice(Product product) throws ServiceException {
    return megastore.pricingCalculateProductPrice3(product);
  }

  public String getStringAmount(BigDecimal amount, MerchantStore store) throws ServiceException {
    return megastore.pricingGetStringAmount(amount,store);
  }
}
