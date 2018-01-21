package com.salesmanager.core.business.services.payments;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.payments.Transaction;
import io.macaw.test.impl.ServiceConfig;
import java.lang.Long;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceClient implements TransactionService {
  private Megastore megastore;

  public TransactionServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List listTransactions(Order order) throws ServiceException {
    return megastore.transactionListTransactions(order);
  }

  public Transaction getCapturableTransaction(Order order) throws ServiceException {
    return megastore.transactionGetCapturableTransaction(order);
  }

  public Transaction getRefundableTransaction(Order order) throws ServiceException {
    return megastore.transactionGetRefundableTransaction(order);
  }

  public void create(Transaction transaction) throws ServiceException {
    try {
        Object out = megastore.transactionCreate(transaction);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(transaction, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Transaction getById(Long id) {
    return megastore.transactionGetById(id);
  }

  public Long count() {
    return megastore.transactionCount();
  }

  public void update(Transaction entity) throws ServiceException {
    try {
        Object out = megastore.transactionUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void delete(Transaction entity) throws ServiceException {
    megastore.transactionDelete(entity);
  }

  public List list() {
    return megastore.transactionList();
  }

  public void save(Transaction entity) throws ServiceException {
    try {
        Object out = megastore.transactionSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.transactionFlush();
  }
}
