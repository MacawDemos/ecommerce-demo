package com.salesmanager.core.business.services.customer.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerOptionValueServiceClient implements CustomerOptionValueService {
  private Megastore megastore;

  public CustomerOptionValueServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public CustomerOptionValue getByCode(MerchantStore store, String optionValueCode) {
    return megastore.customeroptionvalueGetByCode(store,optionValueCode);
  }

  public void saveOrUpdate(CustomerOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionvalueSaveOrUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.customeroptionvalueListByStore(store,language);
  }

  public void delete(CustomerOptionValue customerOptionValue) throws ServiceException {
    megastore.customeroptionvalueDelete1(customerOptionValue);
  }

  public CustomerOptionValue getById(Long id) {
    return megastore.customeroptionvalueGetById(id);
  }

  public Long count() {
    return megastore.customeroptionvalueCount();
  }

  public void update(CustomerOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionvalueUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(CustomerOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionvalueCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.customeroptionvalueList();
  }

  public void save(CustomerOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionvalueSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.customeroptionvalueFlush();
  }
}
