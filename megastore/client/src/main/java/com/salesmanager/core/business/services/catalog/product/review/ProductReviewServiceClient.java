package com.salesmanager.core.business.services.catalog.product.review;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceClient implements ProductReviewService {
  private Megastore megastore;

  public ProductReviewServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List getByCustomer(Customer customer) {
    return megastore.productreviewGetByCustomer(customer);
  }

  public List getByProductNoCustomers(Product product) {
    return megastore.productreviewGetByProductNoCustomers(product);
  }

  public List getByProduct(Product product, Language language) {
    return megastore.productreviewGetByProduct(product,language);
  }

  public List getByProduct(Product product) {
    return megastore.productreviewGetByProduct1(product);
  }

  public ProductReview getByProductAndCustomer(Long productId, Long customerId) {
    return megastore.productreviewGetByProductAndCustomer(productId,customerId);
  }

  public void create(ProductReview review) throws ServiceException {
    try {
        Object out = megastore.productreviewCreate(review);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(review, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public ProductReview getById(Long id) {
    return megastore.productreviewGetById(id);
  }

  public Long count() {
    return megastore.productreviewCount();
  }

  public void update(ProductReview entity) throws ServiceException {
    try {
        Object out = megastore.productreviewUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ProductReview entity) throws ServiceException {
    megastore.productreviewDelete(entity);
  }

  public List list() {
    return megastore.productreviewList();
  }

  public void save(ProductReview entity) throws ServiceException {
    try {
        Object out = megastore.productreviewSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productreviewFlush();
  }
}
