package com.salesmanager.core.business.services.shoppingcart;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import io.macaw.test.impl.ServiceConfig;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartCalculationServiceClient implements ShoppingCartCalculationService {
  private Megastore megastore;

  public ShoppingCartCalculationServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public ShoppingCartService getShoppingCartService() {
    return new ShoppingCartServiceClient();
  }

  public OrderTotalSummary calculate(ShoppingCart cartModel, MerchantStore store, Language language)
      throws ServiceException {
    return megastore.shoppingcartcalculationCalculate(cartModel,store,language);
  }

  public OrderTotalSummary calculate(ShoppingCart cartModel, Customer customer, MerchantStore store,
      Language language) throws ServiceException {
    return megastore.shoppingcartcalculationCalculate1(cartModel,customer,store,language);
  }
}
