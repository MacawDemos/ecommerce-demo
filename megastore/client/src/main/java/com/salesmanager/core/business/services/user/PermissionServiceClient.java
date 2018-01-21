package com.salesmanager.core.business.services.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceClient implements PermissionService {
  private Megastore megastore;

  public PermissionServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByName() {
    return megastore.permissionGetByName();
  }

  public Permission getById(Integer permissionId) {
    return megastore.permissionGetById(permissionId);
  }

  public void deletePermission(Permission permission) throws ServiceException {
    megastore.permissionDeletePermission(permission);
  }

  public PermissionList listByCriteria(PermissionCriteria criteria) throws ServiceException {
    return megastore.permissionListByCriteria(criteria);
  }

  public void removePermission(Permission permission, Group group) throws ServiceException {
    megastore.permissionRemovePermission(permission,group);
  }

  public List listPermission() throws ServiceException {
    return megastore.permissionListPermission();
  }

  public List getPermissions(List groupIds) throws ServiceException {
    return megastore.permissionGetPermissions(groupIds);
  }

  public Long count() {
    return megastore.permissionCount();
  }

  public void update(Permission entity) throws ServiceException {
    try {
        Object out = megastore.permissionUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Permission entity) throws ServiceException {
    megastore.permissionDelete(entity);
  }

  public void create(Permission entity) throws ServiceException {
    try {
        Object out = megastore.permissionCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.permissionList();
  }

  public void save(Permission entity) throws ServiceException {
    try {
        Object out = megastore.permissionSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.permissionFlush();
  }
}
