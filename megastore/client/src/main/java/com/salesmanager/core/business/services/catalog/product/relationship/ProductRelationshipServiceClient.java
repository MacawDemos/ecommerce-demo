package com.salesmanager.core.business.services.catalog.product.relationship;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductRelationshipServiceClient implements ProductRelationshipService {
  private Megastore megastore;

  public ProductRelationshipServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(ProductRelationship relationship) throws ServiceException {
    try {
        Object out = megastore.productrelationshipSaveOrUpdate(relationship);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(relationship, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List getGroups(MerchantStore store) {
    return megastore.productrelationshipGetGroups(store);
  }

  public void addGroup(MerchantStore store, String groupName) throws ServiceException {
    megastore.productrelationshipAddGroup(store,groupName);
  }

  public List getByGroup(MerchantStore store, String groupName, Language language) throws
      ServiceException {
    return megastore.productrelationshipGetByGroup(store,groupName,language);
  }

  public List getByGroup(MerchantStore store, String groupName) throws ServiceException {
    return megastore.productrelationshipGetByGroup1(store,groupName);
  }

  public void deleteGroup(MerchantStore store, String groupName) throws ServiceException {
    megastore.productrelationshipDeleteGroup(store,groupName);
  }

  public void deactivateGroup(MerchantStore store, String groupName) throws ServiceException {
    megastore.productrelationshipDeactivateGroup(store,groupName);
  }

  public void activateGroup(MerchantStore store, String groupName) throws ServiceException {
    megastore.productrelationshipActivateGroup(store,groupName);
  }

  public List getByType(MerchantStore store, ProductRelationshipType type) throws ServiceException {
    return megastore.productrelationshipGetByType(store,type);
  }

  public List getByType(MerchantStore store, ProductRelationshipType type, Language language) throws
      ServiceException {
    return megastore.productrelationshipGetByType1(store,type,language);
  }

  public List getByType(MerchantStore store, Product product, ProductRelationshipType type,
      Language language) throws ServiceException {
    return megastore.productrelationshipGetByType2(store,product,type,language);
  }

  public List getByType(MerchantStore store, Product product, ProductRelationshipType type) throws
      ServiceException {
    return megastore.productrelationshipGetByType3(store,product,type);
  }

  public List listByProduct(Product product) throws ServiceException {
    return megastore.productrelationshipListByProduct(product);
  }

  public void delete(ProductRelationship relationship) throws ServiceException {
    megastore.productrelationshipDelete(relationship);
  }

  public ProductRelationship getById(Long id) {
    return megastore.productrelationshipGetById(id);
  }

  public Long count() {
    return megastore.productrelationshipCount();
  }

  public void update(ProductRelationship entity) throws ServiceException {
    try {
        Object out = megastore.productrelationshipUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(ProductRelationship entity) throws ServiceException {
    try {
        Object out = megastore.productrelationshipCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productrelationshipList();
  }

  public void save(ProductRelationship entity) throws ServiceException {
    try {
        Object out = megastore.productrelationshipSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productrelationshipFlush();
  }
}
