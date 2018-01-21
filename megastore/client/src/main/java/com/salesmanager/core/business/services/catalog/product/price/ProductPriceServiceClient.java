package com.salesmanager.core.business.services.catalog.product.price;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceServiceClient implements ProductPriceService {
  private Megastore megastore;

  public ProductPriceServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(ProductPrice price) throws ServiceException {
    try {
        Object out = megastore.productpriceSaveOrUpdate(price);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(price, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void addDescription(ProductPrice price, ProductPriceDescription description) throws
      ServiceException {
    try {
        Object out = megastore.productpriceAddDescription(price,description);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(price, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ProductPrice price) throws ServiceException {
    megastore.productpriceDelete(price);
  }

  public ProductPrice getById(Long id) {
    return megastore.productpriceGetById(id);
  }

  public Long count() {
    return megastore.productpriceCount();
  }

  public void update(ProductPrice entity) throws ServiceException {
    try {
        Object out = megastore.productpriceUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(ProductPrice entity) throws ServiceException {
    try {
        Object out = megastore.productpriceCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productpriceList();
  }

  public void save(ProductPrice entity) throws ServiceException {
    try {
        Object out = megastore.productpriceSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productpriceFlush();
  }
}
