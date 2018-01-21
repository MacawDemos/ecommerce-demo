package com.salesmanager.core.business.services.catalog.product.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductOptionServiceClient implements ProductOptionService {
  private Megastore megastore;

  public ProductOptionServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByName(MerchantStore store, String name, Language language) throws
      ServiceException {
    return megastore.productoptionGetByName(store,name,language);
  }

  public ProductOption getByCode(MerchantStore store, String optionCode) {
    return megastore.productoptionGetByCode(store,optionCode);
  }

  public ProductOption getById(MerchantStore store, Long optionId) {
    return megastore.productoptionGetById(store,optionId);
  }

  public void saveOrUpdate(ProductOption entity) throws ServiceException {
    try {
        Object out = megastore.productoptionSaveOrUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.productoptionListByStore(store,language);
  }

  public List listReadOnly(MerchantStore store, Language language) throws ServiceException {
    return megastore.productoptionListReadOnly(store,language);
  }

  public void delete(ProductOption entity) throws ServiceException {
    megastore.productoptionDelete(entity);
  }

  public ProductOption getById(Long id) {
    return megastore.productoptionGetById1(id);
  }

  public Long count() {
    return megastore.productoptionCount();
  }

  public void update(ProductOption entity) throws ServiceException {
    try {
        Object out = megastore.productoptionUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(ProductOption entity) throws ServiceException {
    try {
        Object out = megastore.productoptionCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productoptionList();
  }

  public void save(ProductOption entity) throws ServiceException {
    try {
        Object out = megastore.productoptionSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productoptionFlush();
  }
}
