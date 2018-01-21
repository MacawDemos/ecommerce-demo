package com.salesmanager.core.business.services.catalog.product;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient implements ProductService {
  private Megastore megastore;

  public ProductServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public Product getByCode(String productCode, Language language) {
    return megastore.productGetByCode(productCode,language);
  }

  public Product getById(Long productId) {
    return megastore.productGetById(productId);
  }

  public Product getBySeUrl(MerchantStore store, String seUrl, Locale locale) {
    return megastore.productGetBySeUrl(store,seUrl,locale);
  }

  public List getProducts(List categoryIds, Language language) throws ServiceException {
    return megastore.productGetProducts(categoryIds,language);
  }

  public List getProducts(List categoryIds) throws ServiceException {
    return megastore.productGetProducts1(categoryIds);
  }

  public ProductList listByStore(MerchantStore store, Language language, ProductCriteria criteria) {
    return megastore.productListByStore(store,language,criteria);
  }

  public List listByStore(MerchantStore store) {
    return megastore.productListByStore1(store);
  }

  public void addProductDescription(Product product, ProductDescription description) throws
      ServiceException {
    try {
        Object out = megastore.productAddProductDescription(product,description);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(product, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public ProductDescription getProductDescription(Product product, Language language) {
    return megastore.productGetProductDescription(product,language);
  }

  public Product getProductForLocale(long productId, Language language, Locale locale) throws
      ServiceException {
    return megastore.productGetProductForLocale(productId,language,locale);
  }

  public List getProductsForLocale(Category category, Language language, Locale locale) throws
      ServiceException {
    return megastore.productGetProductsForLocale(category,language,locale);
  }

  public List listByTaxClass(TaxClass taxClass) {
    return megastore.productListByTaxClass(taxClass);
  }

  public void update(Product product) throws ServiceException {
    try {
        Object out = megastore.productUpdate1(product);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(product, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Product product) throws ServiceException {
    megastore.productDelete1(product);
  }

  public void create(Product product) throws ServiceException {
    try {
        Object out = megastore.productCreate(product);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(product, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Long count() {
    return megastore.productCount();
  }

  public List list() {
    return megastore.productList();
  }

  public void save(Product entity) throws ServiceException {
    try {
        Object out = megastore.productSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productFlush();
  }
}
