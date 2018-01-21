package com.salesmanager.core.business.services.catalog.product.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceClient implements ProductAttributeService {
  private Megastore megastore;

  public ProductAttributeServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public ProductAttribute getById(Long id) {
    return megastore.productattributeGetById1(id);
  }

  public void saveOrUpdate(ProductAttribute productAttribute) throws ServiceException {
    try {
        Object out = megastore.productattributeSaveOrUpdate(productAttribute);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(productAttribute, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List getByOptionId(MerchantStore store, Long id) throws ServiceException {
    return megastore.productattributeGetByOptionId(store,id);
  }

  public List getByOptionValueId(MerchantStore store, Long id) throws ServiceException {
    return megastore.productattributeGetByOptionValueId(store,id);
  }

  public List getByProductId(MerchantStore store, Product product, Language language) throws
      ServiceException {
    return megastore.productattributeGetByProductId(store,product,language);
  }

  public List getByAttributeIds(MerchantStore store, Product product, List ids) throws
      ServiceException {
    return megastore.productattributeGetByAttributeIds(store,product,ids);
  }

  public void delete(ProductAttribute attribute) throws ServiceException {
    megastore.productattributeDelete(attribute);
  }

  public Long count() {
    return megastore.productattributeCount();
  }

  public void update(ProductAttribute entity) throws ServiceException {
    try {
        Object out = megastore.productattributeUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(ProductAttribute entity) throws ServiceException {
    try {
        Object out = megastore.productattributeCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productattributeList();
  }

  public void save(ProductAttribute entity) throws ServiceException {
    try {
        Object out = megastore.productattributeSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productattributeFlush();
  }
}
