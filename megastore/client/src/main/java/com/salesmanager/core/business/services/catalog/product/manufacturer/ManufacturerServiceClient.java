package com.salesmanager.core.business.services.catalog.product.manufacturer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceClient implements ManufacturerService {
  private Megastore megastore;

  public ManufacturerServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public Manufacturer getByCode(MerchantStore store, String code) {
    return megastore.manufacturerGetByCode(store,code);
  }

  public void saveOrUpdate(Manufacturer manufacturer) throws ServiceException {
    try {
        Object out = megastore.manufacturerSaveOrUpdate(manufacturer);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(manufacturer, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store) throws ServiceException {
    return megastore.manufacturerListByStore(store);
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.manufacturerListByStore1(store,language);
  }

  public Long getCountManufAttachedProducts(Manufacturer manufacturer) throws ServiceException {
    return megastore.manufacturerGetCountManufAttachedProducts(manufacturer);
  }

  public List listByProductsByCategoriesId(MerchantStore store, List ids, Language language) throws
      ServiceException {
    return megastore.manufacturerListByProductsByCategoriesId(store,ids,language);
  }

  public void addManufacturerDescription(Manufacturer manufacturer,
      ManufacturerDescription description) throws ServiceException {
    try {
        Object out = megastore.manufacturerAddManufacturerDescription(manufacturer,description);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(manufacturer, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Manufacturer manufacturer) throws ServiceException {
    megastore.manufacturerDelete(manufacturer);
  }

  public Manufacturer getById(Long id) {
    return megastore.manufacturerGetById(id);
  }

  public Long count() {
    return megastore.manufacturerCount();
  }

  public void update(Manufacturer entity) throws ServiceException {
    try {
        Object out = megastore.manufacturerUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(Manufacturer entity) throws ServiceException {
    try {
        Object out = megastore.manufacturerCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.manufacturerList();
  }

  public void save(Manufacturer entity) throws ServiceException {
    try {
        Object out = megastore.manufacturerSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.manufacturerFlush();
  }
}
