package com.salesmanager.core.business.services.catalog.product.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductOptionValueServiceClient implements ProductOptionValueService {
  private Megastore megastore;

  public ProductOptionValueServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByName(MerchantStore store, String name, Language language) throws
      ServiceException {
    return megastore.productoptionvalueGetByName(store,name,language);
  }

  public ProductOptionValue getByCode(MerchantStore store, String optionValueCode) {
    return megastore.productoptionvalueGetByCode(store,optionValueCode);
  }

  public ProductOptionValue getById(MerchantStore store, Long optionValueId) {
    return megastore.productoptionvalueGetById(store,optionValueId);
  }

  public void saveOrUpdate(ProductOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.productoptionvalueSaveOrUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.productoptionvalueListByStore(store,language);
  }

  public List listByStoreNoReadOnly(MerchantStore store, Language language) throws
      ServiceException {
    return megastore.productoptionvalueListByStoreNoReadOnly(store,language);
  }

  public void delete(ProductOptionValue entity) throws ServiceException {
    megastore.productoptionvalueDelete(entity);
  }

  public ProductOptionValue getById(Long id) {
    return megastore.productoptionvalueGetById1(id);
  }

  public Long count() {
    return megastore.productoptionvalueCount();
  }

  public void update(ProductOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.productoptionvalueUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(ProductOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.productoptionvalueCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productoptionvalueList();
  }

  public void save(ProductOptionValue entity) throws ServiceException {
    try {
        Object out = megastore.productoptionvalueSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productoptionvalueFlush();
  }
}
