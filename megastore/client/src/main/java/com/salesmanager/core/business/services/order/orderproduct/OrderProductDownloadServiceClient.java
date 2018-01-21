package com.salesmanager.core.business.services.order.orderproduct;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderProductDownloadServiceClient implements OrderProductDownloadService {
  private Megastore megastore;

  public OrderProductDownloadServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByOrderId(Long orderId) {
    return megastore.orderproductdownloadGetByOrderId(orderId);
  }

  public OrderProductDownload getById(Long id) {
    return megastore.orderproductdownloadGetById(id);
  }

  public Long count() {
    return megastore.orderproductdownloadCount();
  }

  public void update(OrderProductDownload entity) throws ServiceException {
    try {
        Object out = megastore.orderproductdownloadUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(OrderProductDownload entity) throws ServiceException {
    megastore.orderproductdownloadDelete(entity);
  }

  public void create(OrderProductDownload entity) throws ServiceException {
    try {
        Object out = megastore.orderproductdownloadCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.orderproductdownloadList();
  }

  public void save(OrderProductDownload entity) throws ServiceException {
    try {
        Object out = megastore.orderproductdownloadSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.orderproductdownloadFlush();
  }
}
