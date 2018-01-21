package com.salesmanager.core.business.services.customer.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerOptionServiceClient implements CustomerOptionService {
  private Megastore megastore;

  public CustomerOptionServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public CustomerOption getByCode(MerchantStore store, String optionCode) {
    return megastore.customeroptionGetByCode(store,optionCode);
  }

  public void saveOrUpdate(CustomerOption entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionSaveOrUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.customeroptionListByStore(store,language);
  }

  public void delete(CustomerOption customerOption) throws ServiceException {
    megastore.customeroptionDelete1(customerOption);
  }

  public CustomerOption getById(Long id) {
    return megastore.customeroptionGetById(id);
  }

  public Long count() {
    return megastore.customeroptionCount();
  }

  public void update(CustomerOption entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(CustomerOption entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.customeroptionList();
  }

  public void save(CustomerOption entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.customeroptionFlush();
  }
}
