package com.salesmanager.core.business.services.catalog.product.availability;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductAvailabilityServiceClient implements ProductAvailabilityService {
  private Megastore megastore;

  public ProductAvailabilityServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(ProductAvailability availability) throws ServiceException {
    try {
        Object out = megastore.productavailabilitySaveOrUpdate(availability);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(availability, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public ProductAvailability getById(Long id) {
    return megastore.productavailabilityGetById(id);
  }

  public Long count() {
    return megastore.productavailabilityCount();
  }

  public void update(ProductAvailability entity) throws ServiceException {
    try {
        Object out = megastore.productavailabilityUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ProductAvailability entity) throws ServiceException {
    megastore.productavailabilityDelete(entity);
  }

  public void create(ProductAvailability entity) throws ServiceException {
    try {
        Object out = megastore.productavailabilityCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productavailabilityList();
  }

  public void save(ProductAvailability entity) throws ServiceException {
    try {
        Object out = megastore.productavailabilitySave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productavailabilityFlush();
  }
}
