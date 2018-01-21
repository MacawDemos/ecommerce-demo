package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.system.SystemConfiguration;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigurationServiceClient implements SystemConfigurationService {
  private Megastore megastore;

  public SystemConfigurationServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public SystemConfiguration getByKey(String key) throws ServiceException {
    return megastore.systemconfigurationGetByKey(key);
  }

  public SystemConfiguration getById(Long id) {
    return megastore.systemconfigurationGetById(id);
  }

  public Long count() {
    return megastore.systemconfigurationCount();
  }

  public void update(SystemConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.systemconfigurationUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(SystemConfiguration entity) throws ServiceException {
    megastore.systemconfigurationDelete(entity);
  }

  public void create(SystemConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.systemconfigurationCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.systemconfigurationList();
  }

  public void save(SystemConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.systemconfigurationSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.systemconfigurationFlush();
  }
}
