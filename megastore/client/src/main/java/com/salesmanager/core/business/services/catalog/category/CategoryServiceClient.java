package com.salesmanager.core.business.services.catalog.category;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceClient implements CategoryService {
  private Megastore megastore;

  public CategoryServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByName(MerchantStore store, String name, Language language) throws
      ServiceException {
    return megastore.categoryGetByName(store,name,language);
  }

  public CategoryDescription getDescription(Category category, Language language) {
    return megastore.categoryGetDescription(category,language);
  }

  public Category getByLanguage(long categoryId, Language language) {
    return megastore.categoryGetByLanguage(categoryId,language);
  }

  public Category getByCode(String storeCode, String code) throws ServiceException {
    return megastore.categoryGetByCode(storeCode,code);
  }

  public Category getByCode(MerchantStore store, String code) throws ServiceException {
    return megastore.categoryGetByCode1(store,code);
  }

  public Category getById(Long id) {
    return megastore.categoryGetById(id);
  }

  public void saveOrUpdate(Category category) throws ServiceException {
    try {
        Object out = megastore.categorySaveOrUpdate(category);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(category, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Category getBySeUrl(MerchantStore store, String seUrl) {
    return megastore.categoryGetBySeUrl(store,seUrl);
  }

  public List listByStore(MerchantStore store, Language language) throws ServiceException {
    return megastore.categoryListByStore(store,language);
  }

  public List listByStore(MerchantStore store) throws ServiceException {
    return megastore.categoryListByStore1(store);
  }

  public List listByLineage(MerchantStore store, String lineage) throws ServiceException {
    return megastore.categoryListByLineage(store,lineage);
  }

  public List listByLineage(String storeCode, String lineage) throws ServiceException {
    return megastore.categoryListByLineage1(storeCode,lineage);
  }

  public List listBySeUrl(MerchantStore store, String seUrl) throws ServiceException {
    return megastore.categoryListBySeUrl(store,seUrl);
  }

  public void addChild(Category parent, Category child) throws ServiceException {
    megastore.categoryAddChild(parent,child);
  }

  public List listByParent(Category category, Language language) {
    return megastore.categoryListByParent(category,language);
  }

  public List listByParent(Category category) throws ServiceException {
    return megastore.categoryListByParent1(category);
  }

  public List listByStoreAndParent(MerchantStore store, Category category) throws ServiceException {
    return megastore.categoryListByStoreAndParent(store,category);
  }

  public void addCategoryDescription(Category category, CategoryDescription description) throws
      ServiceException {
    megastore.categoryAddCategoryDescription(category,description);
  }

  public List listByDepth(MerchantStore store, int depth) {
    return megastore.categoryListByDepth(store,depth);
  }

  public List listByDepth(MerchantStore store, int depth, Language language) {
    return megastore.categoryListByDepth1(store,depth,language);
  }

  public List countProductsByCategories(MerchantStore store, List categoryIds) throws
      ServiceException {
    return megastore.categoryCountProductsByCategories(store,categoryIds);
  }

  public List listByCodes(MerchantStore store, List codes, Language language) {
    return megastore.categoryListByCodes(store,codes,language);
  }

  public List listByIds(MerchantStore store, List ids, Language language) {
    return megastore.categoryListByIds(store,ids,language);
  }

  public void delete(Category category) throws ServiceException {
    megastore.categoryDelete(category);
  }

  public void create(Category category) throws ServiceException {
    try {
        Object out = megastore.categoryCreate(category);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(category, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Long count() {
    return megastore.categoryCount();
  }

  public void update(Category entity) throws ServiceException {
    try {
        Object out = megastore.categoryUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.categoryList();
  }

  public void save(Category entity) throws ServiceException {
    try {
        Object out = megastore.categorySave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.categoryFlush();
  }
}
