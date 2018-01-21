package com.salesmanager.core.business.services.customer;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.customer.CustomerList;
import com.salesmanager.core.model.merchant.MerchantStore;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceClient implements CustomerService {
  private Megastore megastore;

  public CustomerServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByName(String firstName) {
    return megastore.customerGetByName(firstName);
  }

  public Address getCustomerAddress(MerchantStore store, String ipAddress) throws ServiceException {
    return megastore.customerGetCustomerAddress(store,ipAddress);
  }

  public Customer getById(Long id) {
    return megastore.customerGetById1(id);
  }

  public void saveOrUpdate(Customer customer) throws ServiceException {
    try {
        Object out = megastore.customerSaveOrUpdate(customer);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(customer, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List listByStore(MerchantStore store) {
    return megastore.customerListByStore(store);
  }

  public CustomerList listByStore(MerchantStore store, CustomerCriteria criteria) {
    return megastore.customerListByStore1(store,criteria);
  }

  public Customer getByNick(String nick) {
    return megastore.customerGetByNick(nick);
  }

  public Customer getByNick(String nick, int storeId) {
    return megastore.customerGetByNick1(nick,storeId);
  }

  public void delete(Customer customer) throws ServiceException {
    megastore.customerDelete1(customer);
  }

  public Long count() {
    return megastore.customerCount();
  }

  public void update(Customer entity) throws ServiceException {
    try {
        Object out = megastore.customerUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(Customer entity) throws ServiceException {
    try {
        Object out = megastore.customerCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.customerList();
  }

  public void save(Customer entity) throws ServiceException {
    try {
        Object out = megastore.customerSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.customerFlush();
  }
}
