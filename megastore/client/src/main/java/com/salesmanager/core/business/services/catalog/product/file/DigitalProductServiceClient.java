package com.salesmanager.core.business.services.catalog.product.file;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.DigitalProduct;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DigitalProductServiceClient implements DigitalProductService {
  private Megastore megastore;

  public DigitalProductServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(DigitalProduct digitalProduct) throws ServiceException {
    try {
        Object out = megastore.digitalproductSaveOrUpdate(digitalProduct);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(digitalProduct, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public DigitalProduct getByProduct(MerchantStore store, Product product) throws ServiceException {
    return megastore.digitalproductGetByProduct(store,product);
  }

  public void addProductFile(Product product, DigitalProduct digitalProduct,
      InputContentFile inputFile) throws ServiceException {
    try {
        Object out = megastore.digitalproductAddProductFile(product,digitalProduct,inputFile);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(product, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(DigitalProduct digitalProduct) throws ServiceException {
    megastore.digitalproductDelete1(digitalProduct);
  }

  public DigitalProduct getById(Long id) {
    return megastore.digitalproductGetById(id);
  }

  public Long count() {
    return megastore.digitalproductCount();
  }

  public void update(DigitalProduct entity) throws ServiceException {
    try {
        Object out = megastore.digitalproductUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(DigitalProduct entity) throws ServiceException {
    try {
        Object out = megastore.digitalproductCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.digitalproductList();
  }

  public void save(DigitalProduct entity) throws ServiceException {
    try {
        Object out = megastore.digitalproductSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.digitalproductFlush();
  }
}
