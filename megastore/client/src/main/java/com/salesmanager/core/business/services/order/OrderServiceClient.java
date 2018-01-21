package com.salesmanager.core.business.services.order;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import io.macaw.test.impl.ServiceConfig;
import java.io.ByteArrayOutputStream;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceClient implements OrderService {
  private Megastore megastore;

  public OrderServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void saveOrUpdate(Order order) throws ServiceException {
    try {
        Object out = megastore.orderSaveOrUpdate(order);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(order, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public OrderList listByStore(MerchantStore store, OrderCriteria criteria) {
    return megastore.orderListByStore(store,criteria);
  }

  public void addOrderStatusHistory(Order order, OrderStatusHistory history) throws
      ServiceException {
    try {
        Object out = megastore.orderAddOrderStatusHistory(order,history);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(order, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public OrderTotalSummary calculateShoppingCartTotal(ShoppingCart shoppingCart, Customer customer,
      MerchantStore store, Language language) throws ServiceException {
    return megastore.orderCalculateShoppingCartTotal(shoppingCart,customer,store,language);
  }

  public OrderTotalSummary calculateShoppingCartTotal(ShoppingCart shoppingCart,
      MerchantStore store, Language language) throws ServiceException {
    return megastore.orderCalculateShoppingCartTotal1(shoppingCart,store,language);
  }

  public OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, MerchantStore store,
      Language language) throws ServiceException {
    return megastore.orderCaculateOrderTotal(orderSummary,store,language);
  }

  public OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, Customer customer,
      MerchantStore store, Language language) throws ServiceException {
    return megastore.orderCaculateOrderTotal1(orderSummary,customer,store,language);
  }

  public ByteArrayOutputStream generateInvoice(MerchantStore store, Order order, Language language)
      throws ServiceException {
    return megastore.orderGenerateInvoice(store,order,language);
  }

  public Order getOrder(Long orderId) {
    return megastore.orderGetOrder(orderId);
  }

  public Order processOrder(Order order, Customer customer, List items, OrderTotalSummary summary,
      Payment payment, Transaction transaction, MerchantStore store) throws ServiceException {
    return megastore.orderProcessOrder(order,customer,items,summary,payment,transaction,store);
  }

  public Order processOrder(Order order, Customer customer, List items, OrderTotalSummary summary,
      Payment payment, MerchantStore store) throws ServiceException {
    return megastore.orderProcessOrder1(order,customer,items,summary,payment,store);
  }

  public boolean hasDownloadFiles(Order order) throws ServiceException {
    return megastore.orderHasDownloadFiles(order);
  }

  public void delete(Order order) throws ServiceException {
    megastore.orderDelete(order);
  }

  public Order getById(Long id) {
    return megastore.orderGetById(id);
  }

  public Long count() {
    return megastore.orderCount();
  }

  public void update(Order entity) throws ServiceException {
    try {
        Object out = megastore.orderUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(Order entity) throws ServiceException {
    try {
        Object out = megastore.orderCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.orderList();
  }

  public void save(Order entity) throws ServiceException {
    try {
        Object out = megastore.orderSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.orderFlush();
  }
}
