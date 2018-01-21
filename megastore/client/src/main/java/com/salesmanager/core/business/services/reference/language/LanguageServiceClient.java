package com.salesmanager.core.business.services.reference.language;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceClient implements LanguageService {
  private Megastore megastore;

  public LanguageServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public Language getByCode(String code) throws ServiceException {
    return megastore.languageGetByCode(code);
  }

  public Locale toLocale(Language language) {
    return megastore.languageToLocale(language);
  }

  public Map getLanguagesMap() throws ServiceException {
    return megastore.languageGetLanguagesMap();
  }

  public List getLanguages() throws ServiceException {
    return megastore.languageGetLanguages();
  }

  public Language toLanguage(Locale locale) {
    return megastore.languageToLanguage(locale);
  }

  public Language defaultLanguage() {
    return megastore.languageDefaultLanguage();
  }

  public Language getById(Integer id) {
    return megastore.languageGetById(id);
  }

  public Long count() {
    return megastore.languageCount();
  }

  public void update(Language entity) throws ServiceException {
    try {
        Object out = megastore.languageUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Language entity) throws ServiceException {
    megastore.languageDelete(entity);
  }

  public void create(Language entity) throws ServiceException {
    try {
        Object out = megastore.languageCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.languageList();
  }

  public void save(Language entity) throws ServiceException {
    try {
        Object out = megastore.languageSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.languageFlush();
  }
}
