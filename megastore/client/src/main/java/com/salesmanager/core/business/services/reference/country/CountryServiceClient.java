package com.salesmanager.core.business.services.reference.country;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.country.CountryDescription;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceClient implements CountryService {
  private Megastore megastore;

  public CountryServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public Country getByCode(String code) throws ServiceException {
    return megastore.countryGetByCode(code);
  }

  public Map getCountriesMap(Language language) throws ServiceException {
    return megastore.countryGetCountriesMap(language);
  }

  public List getCountries(Language language) throws ServiceException {
    return megastore.countryGetCountries(language);
  }

  public List getCountries(List isoCodes, Language language) throws ServiceException {
    return megastore.countryGetCountries1(isoCodes,language);
  }

  public void addCountryDescription(Country country, CountryDescription description) throws
      ServiceException {
    try {
        Object out = megastore.countryAddCountryDescription(country,description);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(country, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Country getById(Integer id) {
    return megastore.countryGetById(id);
  }

  public Long count() {
    return megastore.countryCount();
  }

  public void update(Country entity) throws ServiceException {
    try {
        Object out = megastore.countryUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Country entity) throws ServiceException {
    megastore.countryDelete(entity);
  }

  public void create(Country entity) throws ServiceException {
    try {
        Object out = megastore.countryCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.countryList();
  }

  public void save(Country entity) throws ServiceException {
    try {
        Object out = megastore.countrySave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.countryFlush();
  }
}
