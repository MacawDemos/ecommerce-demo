package com.salesmanager.core.business.services.reference.currency;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.reference.currency.Currency;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceClient implements CurrencyService {
  private Megastore megastore;

  public CurrencyServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public Currency getByCode(String code) {
    return megastore.currencyGetByCode(code);
  }

  public Currency getById(Long id) {
    return megastore.currencyGetById(id);
  }

  public Long count() {
    return megastore.currencyCount();
  }

  public void update(Currency entity) throws ServiceException {
    try {
        Object out = megastore.currencyUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Currency entity) throws ServiceException {
    megastore.currencyDelete(entity);
  }

  public void create(Currency entity) throws ServiceException {
    try {
        Object out = megastore.currencyCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.currencyList();
  }

  public void save(Currency entity) throws ServiceException {
    try {
        Object out = megastore.currencySave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.currencyFlush();
  }
}
