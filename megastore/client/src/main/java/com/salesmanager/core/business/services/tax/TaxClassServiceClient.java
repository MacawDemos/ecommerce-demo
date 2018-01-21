package com.salesmanager.core.business.services.tax;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaxClassServiceClient implements TaxClassService {
  private Megastore megastore;

  public TaxClassServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public TaxClass getByCode(String code) throws ServiceException {
    return megastore.taxclassGetByCode(code);
  }

  public TaxClass getByCode(String code, MerchantStore store) throws ServiceException {
    return megastore.taxclassGetByCode1(code,store);
  }

  public TaxClass getById(Long id) {
    return megastore.taxclassGetById(id);
  }

  public List listByStore(MerchantStore store) throws ServiceException {
    return megastore.taxclassListByStore(store);
  }

  public void delete(TaxClass taxClass) throws ServiceException {
    megastore.taxclassDelete1(taxClass);
  }

  public Long count() {
    return megastore.taxclassCount();
  }

  public void update(TaxClass entity) throws ServiceException {
    try {
        Object out = megastore.taxclassUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(TaxClass entity) throws ServiceException {
    try {
        Object out = megastore.taxclassCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.taxclassList();
  }

  public void save(TaxClass entity) throws ServiceException {
    try {
        Object out = megastore.taxclassSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.taxclassFlush();
  }
}
