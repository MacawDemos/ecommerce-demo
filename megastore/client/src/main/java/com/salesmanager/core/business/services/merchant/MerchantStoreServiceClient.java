package com.salesmanager.core.business.services.merchant;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.merchant.MerchantStore;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MerchantStoreServiceClient implements MerchantStoreService {
  private Megastore megastore;

  public MerchantStoreServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public MerchantStore getByCode(String code) throws ServiceException {
    return megastore.merchantstoreGetByCode(code);
  }

  public void saveOrUpdate(MerchantStore store) throws ServiceException {
    try {
        Object out = megastore.merchantstoreSaveOrUpdate(store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(store, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public MerchantStore getMerchantStore(String merchantStoreCode) throws ServiceException {
    return megastore.merchantstoreGetMerchantStore(merchantStoreCode);
  }

  public MerchantStore getById(Integer id) {
    return megastore.merchantstoreGetById(id);
  }

  public Long count() {
    return megastore.merchantstoreCount();
  }

  public void update(MerchantStore entity) throws ServiceException {
    try {
        Object out = megastore.merchantstoreUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(MerchantStore entity) throws ServiceException {
    megastore.merchantstoreDelete(entity);
  }

  public void create(MerchantStore entity) throws ServiceException {
    try {
        Object out = megastore.merchantstoreCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.merchantstoreList();
  }

  public void save(MerchantStore entity) throws ServiceException {
    try {
        Object out = megastore.merchantstoreSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.merchantstoreFlush();
  }
}
