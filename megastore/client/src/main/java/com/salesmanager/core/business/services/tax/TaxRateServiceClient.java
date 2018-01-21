package com.salesmanager.core.business.services.tax;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.core.model.tax.taxrate.TaxRate;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaxRateServiceClient implements TaxRateService {
  private Megastore megastore;

  public TaxRateServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public TaxRate getByCode(String code, MerchantStore store) throws ServiceException {
    return megastore.taxrateGetByCode(code,store);
  }

  public List listByCountryStateProvinceAndTaxClass(Country country, String stateProvince,
      TaxClass taxClass, MerchantStore store, Language language) throws ServiceException {
    return megastore.taxrateListByCountryStateProvinceAndTaxClass(country,stateProvince,taxClass,store,language);
  }

  public List listByCountryZoneAndTaxClass(Country country, Zone zone, TaxClass taxClass,
      MerchantStore store, Language language) throws ServiceException {
    return megastore.taxrateListByCountryZoneAndTaxClass(country,zone,taxClass,store,language);
  }

  public List listByStore(MerchantStore store) throws ServiceException {
    return megastore.taxrateListByStore(store);
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.taxrateListByStore1(store,language);
  }

  public void delete(TaxRate taxRate) throws ServiceException {
    megastore.taxrateDelete(taxRate);
  }

  public TaxRate getById(Long id) {
    return megastore.taxrateGetById(id);
  }

  public Long count() {
    return megastore.taxrateCount();
  }

  public void update(TaxRate entity) throws ServiceException {
    try {
        Object out = megastore.taxrateUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(TaxRate entity) throws ServiceException {
    try {
        Object out = megastore.taxrateCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.taxrateList();
  }

  public void save(TaxRate entity) throws ServiceException {
    try {
        Object out = megastore.taxrateSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.taxrateFlush();
  }
}
