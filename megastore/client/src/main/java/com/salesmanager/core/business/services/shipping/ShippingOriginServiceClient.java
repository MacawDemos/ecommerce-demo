package com.salesmanager.core.business.services.shipping;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShippingOriginServiceClient implements ShippingOriginService {
  private Megastore megastore;

  public ShippingOriginServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public ShippingOrigin getByStore(MerchantStore store) {
    return megastore.shippingoriginGetByStore(store);
  }

  public ShippingOrigin getById(Long id) {
    return megastore.shippingoriginGetById(id);
  }

  public Long count() {
    return megastore.shippingoriginCount();
  }

  public void update(ShippingOrigin entity) throws ServiceException {
    try {
        Object out = megastore.shippingoriginUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ShippingOrigin entity) throws ServiceException {
    megastore.shippingoriginDelete(entity);
  }

  public void create(ShippingOrigin entity) throws ServiceException {
    try {
        Object out = megastore.shippingoriginCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.shippingoriginList();
  }

  public void save(ShippingOrigin entity) throws ServiceException {
    try {
        Object out = megastore.shippingoriginSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.shippingoriginFlush();
  }
}
