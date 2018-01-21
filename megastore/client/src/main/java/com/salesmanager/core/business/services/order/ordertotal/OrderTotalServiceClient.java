package com.salesmanager.core.business.services.order.ordertotal;

import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalVariation;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import org.springframework.stereotype.Service;

@Service
public class OrderTotalServiceClient implements OrderTotalService {
  private Megastore megastore;

  public OrderTotalServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public OrderTotalVariation findOrderTotalVariation(OrderSummary summary, Customer customer,
      MerchantStore store, Language language) {
    return megastore.ordertotalFindOrderTotalVariation(summary,customer,store,language);
  }
}
