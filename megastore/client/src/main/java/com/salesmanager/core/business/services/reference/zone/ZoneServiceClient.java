package com.salesmanager.core.business.services.reference.zone;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.reference.zone.ZoneDescription;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceClient implements ZoneService {
  private Megastore megastore;

  public ZoneServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public Zone getByCode(String code) {
    return megastore.zoneGetByCode(code);
  }

  public void addDescription(Zone zone, ZoneDescription description) throws ServiceException {
    try {
        Object out = megastore.zoneAddDescription(zone,description);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(zone, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Map getZones(Language language) throws ServiceException {
    return megastore.zoneGetZones(language);
  }

  public List getZones(Country country, Language language) throws ServiceException {
    return megastore.zoneGetZones1(country,language);
  }

  public Zone getById(Long id) {
    return megastore.zoneGetById(id);
  }

  public Long count() {
    return megastore.zoneCount();
  }

  public void update(Zone entity) throws ServiceException {
    try {
        Object out = megastore.zoneUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Zone entity) throws ServiceException {
    megastore.zoneDelete(entity);
  }

  public void create(Zone entity) throws ServiceException {
    try {
        Object out = megastore.zoneCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.zoneList();
  }

  public void save(Zone entity) throws ServiceException {
    try {
        Object out = megastore.zoneSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.zoneFlush();
  }
}
