package com.salesmanager.core.business.services.search;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.search.SearchKeywords;
import com.salesmanager.core.model.search.SearchResponse;
import io.macaw.test.impl.ServiceConfig;
import java.lang.String;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceClient implements SearchService {
  private Megastore megastore;

  public SearchServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public void deleteIndex(MerchantStore store, Product product) throws ServiceException {
    megastore.searchDeleteIndex(store,product);
  }

  public SearchKeywords searchForKeywords(String collectionName, String jsonString,
      int entriesCount) throws ServiceException {
    return megastore.searchSearchForKeywords(collectionName,jsonString,entriesCount);
  }

  public void initService() {
    megastore.searchInitService();
  }

  public void index(MerchantStore store, Product product) throws ServiceException {
    megastore.searchIndex(store,product);
  }

  public SearchResponse search(MerchantStore store, String languageCode, String jsonString,
      int entriesCount, int startIndex) throws ServiceException {
    return megastore.searchSearch(store,languageCode,jsonString,entriesCount,startIndex);
  }
}
