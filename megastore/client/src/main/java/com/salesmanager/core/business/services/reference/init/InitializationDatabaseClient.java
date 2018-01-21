package com.salesmanager.core.business.services.reference.init;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import io.macaw.test.impl.ServiceConfig;
import java.lang.String;
import org.springframework.stereotype.Service;

@Service
public class InitializationDatabaseClient implements InitializationDatabase {
  private Megastore megastore;

  public InitializationDatabaseClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void populate(String contextName) throws ServiceException {
    megastore.initializationdatabaseimplPopulate(contextName);
  }

  public boolean isEmpty() {
    return megastore.initializationdatabaseimplIsEmpty();
  }
}
