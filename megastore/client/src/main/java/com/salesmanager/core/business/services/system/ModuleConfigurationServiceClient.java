package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.system.IntegrationModule;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModuleConfigurationServiceClient implements ModuleConfigurationService {
  private Megastore megastore;

  public ModuleConfigurationServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public IntegrationModule getByCode(String moduleCode) {
    return megastore.moduleconfigurationGetByCode(moduleCode);
  }

  public void createOrUpdateModule(String json) throws ServiceException {
    megastore.moduleconfigurationCreateOrUpdateModule(json);
  }

  public List getIntegrationModules(String module) {
    return megastore.moduleconfigurationGetIntegrationModules(module);
  }

  public IntegrationModule getById(Long id) {
    return megastore.moduleconfigurationGetById(id);
  }

  public Long count() {
    return megastore.moduleconfigurationCount();
  }

  public void update(IntegrationModule entity) throws ServiceException {
    try {
        Object out = megastore.moduleconfigurationUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(IntegrationModule entity) throws ServiceException {
    megastore.moduleconfigurationDelete(entity);
  }

  public void create(IntegrationModule entity) throws ServiceException {
    try {
        Object out = megastore.moduleconfigurationCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.moduleconfigurationList();
  }

  public void save(IntegrationModule entity) throws ServiceException {
    try {
        Object out = megastore.moduleconfigurationSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.moduleconfigurationFlush();
  }
}
