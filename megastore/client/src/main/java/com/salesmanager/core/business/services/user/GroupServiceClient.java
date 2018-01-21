package com.salesmanager.core.business.services.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceClient implements GroupService {
  private Megastore megastore;

  public GroupServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List listGroupByIds(Set ids) throws ServiceException {
    return megastore.groupListGroupByIds(ids);
  }

  public List listGroup(GroupType groupType) throws ServiceException {
    return megastore.groupListGroup(groupType);
  }

  public Group getById(Integer id) {
    return megastore.groupGetById(id);
  }

  public Long count() {
    return megastore.groupCount();
  }

  public void update(Group entity) throws ServiceException {
    try {
        Object out = megastore.groupUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Group entity) throws ServiceException {
    megastore.groupDelete(entity);
  }

  public void create(Group entity) throws ServiceException {
    try {
        Object out = megastore.groupCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.groupList();
  }

  public void save(Group entity) throws ServiceException {
    try {
        Object out = megastore.groupSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.groupFlush();
  }
}
