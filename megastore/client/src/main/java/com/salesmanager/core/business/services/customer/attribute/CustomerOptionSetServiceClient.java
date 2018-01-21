package com.salesmanager.core.business.services.customer.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerOptionSetServiceClient implements CustomerOptionSetService {
  private Megastore megastore;

  public CustomerOptionSetServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(CustomerOptionSet entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionsetSaveOrUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByOption(CustomerOption option, MerchantStore store) throws ServiceException {
    return megastore.customeroptionsetListByOption(option,store);
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.customeroptionsetListByStore(store,language);
  }

  public List listByOptionValue(CustomerOptionValue optionValue, MerchantStore store) throws
      ServiceException {
    return megastore.customeroptionsetListByOptionValue(optionValue,store);
  }

  public void delete(CustomerOptionSet customerOptionSet) throws ServiceException {
    megastore.customeroptionsetDelete(customerOptionSet);
  }

  public CustomerOptionSet getById(Long id) {
    return megastore.customeroptionsetGetById(id);
  }

  public Long count() {
    return megastore.customeroptionsetCount();
  }

  public void update(CustomerOptionSet entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionsetUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(CustomerOptionSet entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionsetCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.customeroptionsetList();
  }

  public void save(CustomerOptionSet entity) throws ServiceException {
    try {
        Object out = megastore.customeroptionsetSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.customeroptionsetFlush();
  }
}
