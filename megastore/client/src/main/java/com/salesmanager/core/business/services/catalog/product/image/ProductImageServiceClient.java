package com.salesmanager.core.business.services.catalog.product.image;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.image.ProductImageDescription;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceClient implements ProductImageService {
  private Megastore megastore;

  public ProductImageServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public ProductImage getById(Long id) {
    return megastore.productimageGetById1(id);
  }

  public void saveOrUpdate(ProductImage productImage) throws ServiceException {
    try {
        Object out = megastore.productimageSaveOrUpdate(productImage);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(productImage, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void addProductImages(Product product, List productImages) throws ServiceException {
    try {
        Object out = megastore.productimageAddProductImages(product,productImages);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(product, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void addProductImageDescription(ProductImage productImage,
      ProductImageDescription description) throws ServiceException {
    try {
        Object out = megastore.productimageAddProductImageDescription(productImage,description);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(productImage, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List getProductImages(Product product) throws ServiceException {
    return megastore.productimageGetProductImages(product);
  }

  public void removeProductImage(ProductImage productImage) throws ServiceException {
    megastore.productimageRemoveProductImage(productImage);
  }

  public OutputContentFile getProductImage(ProductImage productImage, ProductImageSize size) throws
      ServiceException {
    return megastore.productimageGetProductImage(productImage,size);
  }

  public OutputContentFile getProductImage(String storeCode, String productCode, String fileName,
      ProductImageSize size) throws ServiceException {
    return megastore.productimageGetProductImage1(storeCode,productCode,fileName,size);
  }

  public void addProductImage(Product product, ProductImage productImage,
      ImageContentFile inputImage) throws ServiceException {
    try {
        Object out = megastore.productimageAddProductImage(product,productImage,inputImage);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(product, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Long count() {
    return megastore.productimageCount();
  }

  public void update(ProductImage entity) throws ServiceException {
    try {
        Object out = megastore.productimageUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ProductImage entity) throws ServiceException {
    megastore.productimageDelete(entity);
  }

  public void create(ProductImage entity) throws ServiceException {
    try {
        Object out = megastore.productimageCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.productimageList();
  }

  public void save(ProductImage entity) throws ServiceException {
    try {
        Object out = megastore.productimageSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.productimageFlush();
  }
}
