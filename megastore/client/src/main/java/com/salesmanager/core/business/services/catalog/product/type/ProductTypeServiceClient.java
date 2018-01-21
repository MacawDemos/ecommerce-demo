package com.salesmanager.core.business.services.catalog.product.type;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceClient implements ProductTypeService {
  private Megastore megastore;

  public ProductTypeServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public ProductType getProductType(String productTypeCode) throws ServiceException {
    return megastore.producttypeGetProductType(productTypeCode);
  }

  public ProductType getById(Long id) {
    return megastore.producttypeGetById(id);
  }

  public Long count() {
    return megastore.producttypeCount();
  }

  public void update(ProductType entity) throws ServiceException {
    try {
        Object out = megastore.producttypeUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ProductType entity) throws ServiceException {
    megastore.producttypeDelete(entity);
  }

  public void create(ProductType entity) throws ServiceException {
    try {
        Object out = megastore.producttypeCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.producttypeList();
  }

  public void save(ProductType entity) throws ServiceException {
    try {
        Object out = megastore.producttypeSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.producttypeFlush();
  }
}
