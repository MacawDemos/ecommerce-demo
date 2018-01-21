package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.system.MerchantLog;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MerchantLogServiceClient implements MerchantLogService {
  private Megastore megastore;

  public MerchantLogServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public MerchantLog getById(Long id) {
    return megastore.merchantlogGetById(id);
  }

  public Long count() {
    return megastore.merchantlogCount();
  }

  public void update(MerchantLog entity) throws ServiceException {
    try {
        Object out = megastore.merchantlogUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(MerchantLog entity) throws ServiceException {
    megastore.merchantlogDelete(entity);
  }

  public void create(MerchantLog entity) throws ServiceException {
    try {
        Object out = megastore.merchantlogCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.merchantlogList();
  }

  public void save(MerchantLog entity) throws ServiceException {
    try {
        Object out = megastore.merchantlogSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.merchantlogFlush();
  }
}
