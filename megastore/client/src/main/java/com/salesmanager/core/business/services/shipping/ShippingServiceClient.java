package com.salesmanager.core.business.services.shipping;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingMetaData;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import io.macaw.test.impl.ServiceConfig;
import java.lang.String;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceClient implements ShippingService {
  private Megastore megastore;

  public ShippingServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public IntegrationConfiguration getShippingConfiguration(String moduleCode, MerchantStore store)
      throws ServiceException {
    return megastore.shippingGetShippingConfiguration(moduleCode,store);
  }

  public ShippingConfiguration getShippingConfiguration(MerchantStore store) throws
      ServiceException {
    return megastore.shippingGetShippingConfiguration1(store);
  }

  public CustomIntegrationConfiguration getCustomShippingConfiguration(String moduleCode,
      MerchantStore store) throws ServiceException {
    return megastore.shippingGetCustomShippingConfiguration(moduleCode,store);
  }

  public void saveShippingConfiguration(ShippingConfiguration shippingConfiguration,
      MerchantStore store) throws ServiceException {
    try {
        Object out = megastore.shippingSaveShippingConfiguration(shippingConfiguration,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(shippingConfiguration, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void saveCustomShippingConfiguration(String moduleCode,
      CustomIntegrationConfiguration shippingConfiguration, MerchantStore store) throws
      ServiceException {
    try {
        Object out = megastore.shippingSaveCustomShippingConfiguration(moduleCode,shippingConfiguration,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(shippingConfiguration, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List getSupportedCountries(MerchantStore store) throws ServiceException {
    return megastore.shippingGetSupportedCountries(store);
  }

  public List getShipToCountryList(MerchantStore store, Language language) throws ServiceException {
    return megastore.shippingGetShipToCountryList(store,language);
  }

  public void setSupportedCountries(MerchantStore store, List countryCodes) throws
      ServiceException {
    megastore.shippingSetSupportedCountries(store,countryCodes);
  }

  public List getPackagesDetails(List products, MerchantStore store) throws ServiceException {
    return megastore.shippingGetPackagesDetails(products,store);
  }

  public boolean requiresShipping(List items, MerchantStore store) throws ServiceException {
    return megastore.shippingRequiresShipping(items,store);
  }

  public List getShippingMethods(MerchantStore store) throws ServiceException {
    return megastore.shippingGetShippingMethods(store);
  }

  public void saveShippingQuoteModuleConfiguration(IntegrationConfiguration configuration,
      MerchantStore store) throws ServiceException {
    try {
        Object out = megastore.shippingSaveShippingQuoteModuleConfiguration(configuration,store);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(configuration, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void removeShippingQuoteModuleConfiguration(String moduleCode, MerchantStore store) throws
      ServiceException {
    megastore.shippingRemoveShippingQuoteModuleConfiguration(moduleCode,store);
  }

  public void removeCustomShippingQuoteModuleConfiguration(String moduleCode, MerchantStore store)
      throws ServiceException {
    megastore.shippingRemoveCustomShippingQuoteModuleConfiguration(moduleCode,store);
  }

  public Map getShippingModulesConfigured(MerchantStore store) throws ServiceException {
    return megastore.shippingGetShippingModulesConfigured(store);
  }

  public ShippingSummary getShippingSummary(MerchantStore store, ShippingQuote shippingQuote,
      ShippingOption selectedShippingOption) throws ServiceException {
    return megastore.shippingGetShippingSummary(store,shippingQuote,selectedShippingOption);
  }

  public ShippingQuote getShippingQuote(MerchantStore store, Delivery delivery, List products,
      Language language) throws ServiceException {
    return megastore.shippingGetShippingQuote(store,delivery,products,language);
  }

  public ShippingMetaData getShippingMetaData(MerchantStore store) throws ServiceException {
    return megastore.shippingGetShippingMetaData(store);
  }
}
