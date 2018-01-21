package com.salesmanager.core.business.services.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.user.User;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceClient implements UserService {
  private Megastore megastore;

  public UserServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(User user) throws ServiceException {
    try {
        Object out = megastore.userSaveOrUpdate(user);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(user, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store) throws ServiceException {
    return megastore.userListByStore(store);
  }

  public User getByUserName(String userName) throws ServiceException {
    return megastore.userGetByUserName(userName);
  }

  public List listUser() throws ServiceException {
    return megastore.userListUser();
  }

  public void delete(User user) throws ServiceException {
    megastore.userDelete(user);
  }

  public User getById(Long id) {
    return megastore.userGetById(id);
  }

  public Long count() {
    return megastore.userCount();
  }

  public void update(User entity) throws ServiceException {
    try {
        Object out = megastore.userUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(User entity) throws ServiceException {
    try {
        Object out = megastore.userCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.userList();
  }

  public void save(User entity) throws ServiceException {
    try {
        Object out = megastore.userSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.userFlush();
  }
}
