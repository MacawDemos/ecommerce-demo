package com.salesmanager.core.business.services.system;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MerchantConfigurationServiceClient implements MerchantConfigurationService {
  private Megastore megastore;

  public MerchantConfigurationServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List listByType(MerchantConfigurationType type, MerchantStore store) throws
      ServiceException {
    return megastore.merchantconfigurationListByType(type,store);
  }

  public void saveOrUpdate(MerchantConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.merchantconfigurationSaveOrUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store) throws
      ServiceException {
    return megastore.merchantconfigurationGetMerchantConfiguration(key,store);
  }

  public List listByStore(MerchantStore store) throws ServiceException {
    return megastore.merchantconfigurationListByStore(store);
  }

  public void saveMerchantConfig(MerchantConfig config, MerchantStore store) throws
      ServiceException {
    try {
        Object out = megastore.merchantconfigurationSaveMerchantConfig(config,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(config, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public MerchantConfig getMerchantConfig(MerchantStore store) throws ServiceException {
    return megastore.merchantconfigurationGetMerchantConfig(store);
  }

  public void delete(MerchantConfiguration merchantConfiguration) throws ServiceException {
    megastore.merchantconfigurationDelete(merchantConfiguration);
  }

  public MerchantConfiguration getById(Long id) {
    return megastore.merchantconfigurationGetById(id);
  }

  public Long count() {
    return megastore.merchantconfigurationCount();
  }

  public void update(MerchantConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.merchantconfigurationUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(MerchantConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.merchantconfigurationCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.merchantconfigurationList();
  }

  public void save(MerchantConfiguration entity) throws ServiceException {
    try {
        Object out = megastore.merchantconfigurationSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.merchantconfigurationFlush();
  }
}
