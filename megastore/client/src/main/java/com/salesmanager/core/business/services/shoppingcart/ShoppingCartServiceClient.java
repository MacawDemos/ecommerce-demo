package com.salesmanager.core.business.services.shoppingcart;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceClient implements ShoppingCartService {
  private Megastore megastore;

  public ShoppingCartServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public ShoppingCart getByCode(String code, MerchantStore store) throws ServiceException {
    return megastore.shoppingcartGetByCode(code,store);
  }

  public ShoppingCart getById(Long id, MerchantStore store) throws ServiceException {
    return megastore.shoppingcartGetById(id,store);
  }

  public ShoppingCart getById(Long id) {
    return megastore.shoppingcartGetById1(id);
  }

  public void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException {
    try {
        Object out = megastore.shoppingcartSaveOrUpdate(shoppingCart);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(shoppingCart, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public boolean requiresShipping(ShoppingCart cart) throws ServiceException {
    return megastore.shoppingcartRequiresShipping(cart);
  }

  public ShoppingCart getByCustomer(Customer customer) throws ServiceException {
    return megastore.shoppingcartGetByCustomer(customer);
  }

  public ShoppingCart getShoppingCart(Customer customer) throws ServiceException {
    return megastore.shoppingcartGetShoppingCart(customer);
  }

  public void deleteCart(ShoppingCart shoppingCart) throws ServiceException {
    megastore.shoppingcartDeleteCart(shoppingCart);
  }

  public ShoppingCartItem populateShoppingCartItem(Product product) throws ServiceException {
    return megastore.shoppingcartPopulateShoppingCartItem(product);
  }

  public List createShippingProduct(ShoppingCart cart) throws ServiceException {
    return megastore.shoppingcartCreateShippingProduct(cart);
  }

  public boolean isFreeShoppingCart(ShoppingCart cart) throws ServiceException {
    return megastore.shoppingcartIsFreeShoppingCart(cart);
  }

  public boolean isFreeShoppingCart(List items) throws ServiceException {
    return megastore.shoppingcartIsFreeShoppingCart1(items);
  }

  public void removeShoppingCart(ShoppingCart cart) throws ServiceException {
    megastore.shoppingcartRemoveShoppingCart(cart);
  }

  public ShoppingCart mergeShoppingCarts(ShoppingCart userShoppingModel, ShoppingCart sessionCart,
      MerchantStore store) {
    return megastore.shoppingcartMergeShoppingCarts(userShoppingModel,sessionCart,store);
  }

  public void deleteShoppingCartItem(Long id) {
    megastore.shoppingcartDeleteShoppingCartItem(id);
  }

  public Long count() {
    return megastore.shoppingcartCount();
  }

  public void update(ShoppingCart entity) throws ServiceException {
    try {
        Object out = megastore.shoppingcartUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(ShoppingCart entity) throws ServiceException {
    megastore.shoppingcartDelete(entity);
  }

  public void create(ShoppingCart entity) throws ServiceException {
    try {
        Object out = megastore.shoppingcartCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.shoppingcartList();
  }

  public void save(ShoppingCart entity) throws ServiceException {
    try {
        Object out = megastore.shoppingcartSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.shoppingcartFlush();
  }
}
