package com.salesmanager.core.business.services.customer.attribute;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.merchant.MerchantStore;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerAttributeServiceClient implements CustomerAttributeService {
  private Megastore megastore;

  public CustomerAttributeServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(CustomerAttribute customerAttribute) throws ServiceException {
    try {
        Object out = megastore.customerattributeSaveOrUpdate(customerAttribute);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(customerAttribute, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public CustomerAttribute getByCustomerOptionId(MerchantStore store, Long customerId, Long id) {
    return megastore.customerattributeGetByCustomerOptionId(store,customerId,id);
  }

  public List getByCustomerOptionValueId(MerchantStore store, Long id) {
    return megastore.customerattributeGetByCustomerOptionValueId(store,id);
  }

  public List getByOptionId(MerchantStore store, Long id) {
    return megastore.customerattributeGetByOptionId(store,id);
  }

  public List getByCustomer(MerchantStore store, Customer customer) {
    return megastore.customerattributeGetByCustomer(store,customer);
  }

  public void delete(CustomerAttribute attribute) throws ServiceException {
    megastore.customerattributeDelete(attribute);
  }

  public CustomerAttribute getById(Long id) {
    return megastore.customerattributeGetById(id);
  }

  public Long count() {
    return megastore.customerattributeCount();
  }

  public void update(CustomerAttribute entity) throws ServiceException {
    try {
        Object out = megastore.customerattributeUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(CustomerAttribute entity) throws ServiceException {
    try {
        Object out = megastore.customerattributeCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.customerattributeList();
  }

  public void save(CustomerAttribute entity) throws ServiceException {
    try {
        Object out = megastore.customerattributeSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.customerattributeFlush();
  }
}
