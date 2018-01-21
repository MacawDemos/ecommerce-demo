package com.salesmanager.core.business.services.macaw.impl;

import com.cfx.service.api.Service;
import com.cfx.service.api.ServiceException;
import com.cfx.service.api.StartContext;
import com.cfx.service.api.StopContext;
import com.cfx.service.api.config.Configuration;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.catalog.category.CategoryDescription;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.ProductList;
import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.catalog.product.file.DigitalProduct;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.catalog.product.image.ProductImageDescription;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import com.salesmanager.core.model.catalog.product.manufacturer.ManufacturerDescription;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPriceDescription;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.catalog.product.review.ProductReview;
import com.salesmanager.core.model.catalog.product.type.ProductType;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.core.model.common.Delivery;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.customer.CustomerCriteria;
import com.salesmanager.core.model.customer.CustomerList;
import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import com.salesmanager.core.model.customer.attribute.CustomerOption;
import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import com.salesmanager.core.model.email.Email;
import com.salesmanager.core.model.email.EmailConfig;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.Order;
import com.salesmanager.core.model.order.OrderCriteria;
import com.salesmanager.core.model.order.OrderList;
import com.salesmanager.core.model.order.OrderSummary;
import com.salesmanager.core.model.order.OrderTotalSummary;
import com.salesmanager.core.model.order.OrderTotalVariation;
import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import com.salesmanager.core.model.payments.CreditCardType;
import com.salesmanager.core.model.payments.Payment;
import com.salesmanager.core.model.payments.Transaction;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.country.CountryDescription;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.core.model.reference.zone.Zone;
import com.salesmanager.core.model.reference.zone.ZoneDescription;
import com.salesmanager.core.model.search.SearchKeywords;
import com.salesmanager.core.model.search.SearchResponse;
import com.salesmanager.core.model.shipping.ShippingConfiguration;
import com.salesmanager.core.model.shipping.ShippingMetaData;
import com.salesmanager.core.model.shipping.ShippingOption;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import com.salesmanager.core.model.shipping.ShippingQuote;
import com.salesmanager.core.model.shipping.ShippingSummary;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;
import com.salesmanager.core.model.system.MerchantLog;
import com.salesmanager.core.model.system.SystemConfiguration;
import com.salesmanager.core.model.tax.TaxConfiguration;
import com.salesmanager.core.model.tax.taxclass.TaxClass;
import com.salesmanager.core.model.tax.taxrate.TaxRate;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.core.model.user.Permission;
import com.salesmanager.core.model.user.PermissionCriteria;
import com.salesmanager.core.model.user.PermissionList;
import com.salesmanager.core.model.user.User;
import com.salesmanager.core.modules.integration.payment.model.PaymentModule;
import com.salesmanager.core.business.MegastoreApp;
import com.salesmanager.core.business.services.search.SearchService;
import com.shopizer.search.utils.ServerConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.context.ConfigurableApplicationContext;

public class Megastore implements Service, com.salesmanager.core.business.services.macaw.Megastore {
  private MegastoreApp megastoreApp;

  private java.util.Properties properties;

  public void initialize(Configuration config) throws ServiceException {
    properties = config.toProperties();
  }

  public void start(StartContext startContext) throws ServiceException {
    ConfigurableApplicationContext ctx = org.springframework.boot.SpringApplication.run(MegastoreApp.class, new String[0]);
    megastoreApp = ctx.getBean(MegastoreApp.class);
    ServerConfiguration serverConfiguration = new ServerConfiguration();
    serverConfiguration.setClusterName(properties.getProperty("cfx.advanced.search.db.clustername", "shopizer"));
    serverConfiguration.setMode(properties.getProperty("elasticsearch.mode", "remote"));
    String clusterNodeStr = properties.getProperty("cfx.advanced.search.db.clusternodes", "localhost:9200");
    String [] nodes = clusterNodeStr.split(",");
    String [] hostAndPort = nodes[0].split(":");
    serverConfiguration.setClusterHost("http://" + hostAndPort[0]);
    serverConfiguration.setClusterPort(Integer.parseInt(properties.getProperty("elasticsearch.server.port", "9200")));
    serverConfiguration.setProxyUser(properties.getProperty("elasticsearch.server.proxy.user"));
    serverConfiguration.setProxyPassword(properties.getProperty("elasticsearch.server.proxy.password"));
    SearchService searchService = (SearchService) ctx.getBean("productSearchService");
    searchService.initService(serverConfiguration);
    megastoreApp.initializationLoader.init();
  }

  public void stop(StopContext stopContext) throws ServiceException {
  }

  public Country countryGetByCode(String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.countryService.getByCode(code);
  }

  public Map countryGetCountriesMap(Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.countryService.getCountriesMap(language);
  }

  public List countryGetCountries(Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.countryService.getCountries(language);
  }

  public List countryGetCountries1(List isoCodes, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.countryService.getCountries(isoCodes,language);
  }

  public Country countryAddCountryDescription(Country country, CountryDescription description)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.countryService.addCountryDescription(country,description);
    return country;
  }

  public Country countryGetById(Integer id) {
    return megastoreApp.countryService.getById(id);
  }

  public Long countryCount() {
    return megastoreApp.countryService.count();
  }

  public Country countryUpdate(Country entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.countryService.update(entity);
    return entity;
  }

  public void countryDelete(Country entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.countryService.delete(entity);
  }

  public Country countryCreate(Country entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.countryService.create(entity);
    return entity;
  }

  public List countryList() {
    return megastoreApp.countryService.list();
  }

  public Country countrySave(Country entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.countryService.save(entity);
    return entity;
  }

  public void countryFlush() {
    megastoreApp.countryService.flush();
  }

  public CustomerOption customeroptionGetByCode(MerchantStore store, String optionCode) {
    return megastoreApp.customerOptionService.getByCode(store,optionCode);
  }

  public CustomerOption customeroptionSaveOrUpdate(CustomerOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionService.saveOrUpdate(entity);
    return entity;
  }

  public List customeroptionListByStore(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.customerOptionService.listByStore(store,language);
  }

  public void customeroptionDelete(SalesManagerEntity customerOption) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionService.delete((CustomerOption)customerOption);
  }

  public void customeroptionDelete1(CustomerOption customerOption) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionService.delete(customerOption);
  }

  public CustomerOption customeroptionGetById(Long id) {
    return megastoreApp.customerOptionService.getById(id);
  }

  public Long customeroptionCount() {
    return megastoreApp.customerOptionService.count();
  }

  public CustomerOption customeroptionUpdate(CustomerOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionService.update(entity);
    return entity;
  }

  public CustomerOption customeroptionCreate(CustomerOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionService.create(entity);
    return entity;
  }

  public List customeroptionList() {
    return megastoreApp.customerOptionService.list();
  }

  public CustomerOption customeroptionSave(CustomerOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionService.save(entity);
    return entity;
  }

  public void customeroptionFlush() {
    megastoreApp.customerOptionService.flush();
  }

  public ProductType producttypeGetProductType(String productTypeCode) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productTypeService.getProductType(productTypeCode);
  }

  public ProductType producttypeGetById(Long id) {
    return megastoreApp.productTypeService.getById(id);
  }

  public Long producttypeCount() {
    return megastoreApp.productTypeService.count();
  }

  public ProductType producttypeUpdate(ProductType entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productTypeService.update(entity);
    return entity;
  }

  public void producttypeDelete(ProductType entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productTypeService.delete(entity);
  }

  public ProductType producttypeCreate(ProductType entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productTypeService.create(entity);
    return entity;
  }

  public List producttypeList() {
    return megastoreApp.productTypeService.list();
  }

  public ProductType producttypeSave(ProductType entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productTypeService.save(entity);
    return entity;
  }

  public void producttypeFlush() {
    megastoreApp.productTypeService.flush();
  }

  public List productoptionGetByName(MerchantStore store, String name, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productOptionService.getByName(store,name,language);
  }

  public ProductOption productoptionGetByCode(MerchantStore store, String optionCode) {
    return megastoreApp.productOptionService.getByCode(store,optionCode);
  }

  public ProductOption productoptionGetById(MerchantStore store, Long optionId) {
    return megastoreApp.productOptionService.getById(store,optionId);
  }

  public ProductOption productoptionSaveOrUpdate(ProductOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionService.saveOrUpdate(entity);
    return entity;
  }

  public List productoptionListByStore(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productOptionService.listByStore(store,language);
  }

  public List productoptionListReadOnly(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productOptionService.listReadOnly(store,language);
  }

  public void productoptionDelete(ProductOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionService.delete(entity);
  }

  public void productoptionDelete1(SalesManagerEntity entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionService.delete((ProductOption)entity);
  }

  public ProductOption productoptionGetById1(Long id) {
    return megastoreApp.productOptionService.getById(id);
  }

  public Long productoptionCount() {
    return megastoreApp.productOptionService.count();
  }

  public ProductOption productoptionUpdate(ProductOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionService.update(entity);
    return entity;
  }

  public ProductOption productoptionCreate(ProductOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionService.create(entity);
    return entity;
  }

  public List productoptionList() {
    return megastoreApp.productOptionService.list();
  }

  public ProductOption productoptionSave(ProductOption entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionService.save(entity);
    return entity;
  }

  public void productoptionFlush() {
    megastoreApp.productOptionService.flush();
  }

  public CustomerAttribute customerattributeSaveOrUpdate(CustomerAttribute customerAttribute) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerAttributeService.saveOrUpdate(customerAttribute);
    return customerAttribute;
  }

  public CustomerAttribute customerattributeGetByCustomerOptionId(MerchantStore store,
      Long customerId, Long id) {
    return megastoreApp.customerAttributeService.getByCustomerOptionId(store,customerId,id);
  }

  public List customerattributeGetByCustomerOptionValueId(MerchantStore store, Long id) {
    return megastoreApp.customerAttributeService.getByCustomerOptionValueId(store,id);
  }

  public List customerattributeGetByOptionId(MerchantStore store, Long id) {
    return megastoreApp.customerAttributeService.getByOptionId(store,id);
  }

  public List customerattributeGetByCustomer(MerchantStore store, Customer customer) {
    return megastoreApp.customerAttributeService.getByCustomer(store,customer);
  }

  public void customerattributeDelete(CustomerAttribute attribute) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerAttributeService.delete(attribute);
  }

  public void customerattributeDelete1(SalesManagerEntity attribute) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerAttributeService.delete((CustomerAttribute) attribute);
  }

  public CustomerAttribute customerattributeGetById(Long id) {
    return megastoreApp.customerAttributeService.getById(id);
  }

  public Long customerattributeCount() {
    return megastoreApp.customerAttributeService.count();
  }

  public CustomerAttribute customerattributeUpdate(CustomerAttribute entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerAttributeService.update(entity);
    return entity;
  }

  public CustomerAttribute customerattributeCreate(CustomerAttribute entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerAttributeService.create(entity);
    return entity;
  }

  public List customerattributeList() {
    return megastoreApp.customerAttributeService.list();
  }

  public CustomerAttribute customerattributeSave(CustomerAttribute entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerAttributeService.save(entity);
    return entity;
  }

  public void customerattributeFlush() {
    megastoreApp.customerAttributeService.flush();
  }

  public Currency currencyGetByCode(String code) {
    return megastoreApp.currencyService.getByCode(code);
  }

  public Currency currencyGetById(Long id) {
    return megastoreApp.currencyService.getById(id);
  }

  public Long currencyCount() {
    return megastoreApp.currencyService.count();
  }

  public Currency currencyUpdate(Currency entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.currencyService.update(entity);
    return entity;
  }

  public void currencyDelete(Currency entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.currencyService.delete(entity);
  }

  public Currency currencyCreate(Currency entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.currencyService.create(entity);
    return entity;
  }

  public List currencyList() {
    return megastoreApp.currencyService.list();
  }

  public Currency currencySave(Currency entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.currencyService.save(entity);
    return entity;
  }

  public void currencyFlush() {
    megastoreApp.currencyService.flush();
  }

  public OrderTotalSummary shoppingcartcalculationCalculate(ShoppingCart cartModel,
      MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartCalculationService.calculate(cartModel,store,language);
  }

  public OrderTotalSummary shoppingcartcalculationCalculate1(ShoppingCart cartModel,
      Customer customer, MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartCalculationService.calculate(cartModel,customer,store,language);
  }

  public List orderproductdownloadGetByOrderId(Long orderId) {
    return megastoreApp.orderProductDownloadService.getByOrderId(orderId);
  }

  public OrderProductDownload orderproductdownloadGetById(Long id) {
    return megastoreApp.orderProductDownloadService.getById(id);
  }

  public Long orderproductdownloadCount() {
    return megastoreApp.orderProductDownloadService.count();
  }

  public OrderProductDownload orderproductdownloadUpdate(OrderProductDownload entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderProductDownloadService.update(entity);
    return entity;
  }

  public void orderproductdownloadDelete(OrderProductDownload entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderProductDownloadService.delete(entity);
  }

  public OrderProductDownload orderproductdownloadCreate(OrderProductDownload entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderProductDownloadService.create(entity);
    return entity;
  }

  public List orderproductdownloadList() {
    return megastoreApp.orderProductDownloadService.list();
  }

  public OrderProductDownload orderproductdownloadSave(OrderProductDownload entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderProductDownloadService.save(entity);
    return entity;
  }

  public void orderproductdownloadFlush() {
    megastoreApp.orderProductDownloadService.flush();
  }

  public MerchantStore merchantstoreGetByCode(String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.merchantStoreService.getByCode(code);
  }

  public MerchantStore merchantstoreSaveOrUpdate(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantStoreService.saveOrUpdate(store);
    return store;
  }

  public MerchantStore merchantstoreGetMerchantStore(String merchantStoreCode) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.merchantStoreService.getMerchantStore(merchantStoreCode);
  }

  public MerchantStore merchantstoreGetById(Integer id) {
    return megastoreApp.merchantStoreService.getById(id);
  }

  public Long merchantstoreCount() {
    return megastoreApp.merchantStoreService.count();
  }

  public MerchantStore merchantstoreUpdate(MerchantStore entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantStoreService.update(entity);
    return entity;
  }

  public void merchantstoreDelete(MerchantStore entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantStoreService.delete(entity);
  }

  public MerchantStore merchantstoreCreate(MerchantStore entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantStoreService.create(entity);
    return entity;
  }

  public List merchantstoreList() {
    return megastoreApp.merchantStoreService.list();
  }

  public MerchantStore merchantstoreSave(MerchantStore entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantStoreService.save(entity);
    return entity;
  }

  public void merchantstoreFlush() {
    megastoreApp.merchantStoreService.flush();
  }

  public Language languageGetByCode(String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.languageService.getByCode(code);
  }

  public Locale languageToLocale(Language language) {
    return megastoreApp.languageService.toLocale(language);
  }

  public Map languageGetLanguagesMap() throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.languageService.getLanguagesMap();
  }

  public List languageGetLanguages() throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.languageService.getLanguages();
  }

  public Language languageToLanguage(Locale locale) {
    return megastoreApp.languageService.toLanguage(locale);
  }

  public Language languageDefaultLanguage() {
    return megastoreApp.languageService.defaultLanguage();
  }

  public Language languageGetById(Integer id) {
    return megastoreApp.languageService.getById(id);
  }

  public Long languageCount() {
    return megastoreApp.languageService.count();
  }

  public Language languageUpdate(Language entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.languageService.update(entity);
    return entity;
  }

  public void languageDelete(Language entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.languageService.delete(entity);
  }

  public Language languageCreate(Language entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.languageService.create(entity);
    return entity;
  }

  public List languageList() {
    return megastoreApp.languageService.list();
  }

  public Language languageSave(Language entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.languageService.save(entity);
    return entity;
  }

  public void languageFlush() {
    megastoreApp.languageService.flush();
  }

  public ShoppingCart shoppingcartGetByCode(String code, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.getByCode(code,store);
  }

  public ShoppingCart shoppingcartGetById(Long id, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.getById(id,store);
  }

  public ShoppingCart shoppingcartGetById1(Long id) {
    return megastoreApp.shoppingCartService.getById(id);
  }

  public SalesManagerEntity shoppingcartGetById2(Serializable id) {
    return megastoreApp.shoppingCartService.getById((Long)id);
  }

  public ShoppingCart shoppingcartSaveOrUpdate(ShoppingCart shoppingCart) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.saveOrUpdate(shoppingCart);
    return shoppingCart;
  }

  public boolean shoppingcartRequiresShipping(ShoppingCart cart) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.requiresShipping(cart);
  }

  public ShoppingCart shoppingcartGetByCustomer(Customer customer) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.getByCustomer(customer);
  }

  public ShoppingCart shoppingcartGetShoppingCart(Customer customer) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.getShoppingCart(customer);
  }

  public void shoppingcartDeleteCart(ShoppingCart shoppingCart) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.deleteCart(shoppingCart);
  }

  public ShoppingCartItem shoppingcartPopulateShoppingCartItem(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.populateShoppingCartItem(product);
  }

  public List shoppingcartCreateShippingProduct(ShoppingCart cart) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.createShippingProduct(cart);
  }

  public boolean shoppingcartIsFreeShoppingCart(ShoppingCart cart) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.isFreeShoppingCart(cart);
  }

  public boolean shoppingcartIsFreeShoppingCart1(List items) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shoppingCartService.isFreeShoppingCart(items);
  }

  public void shoppingcartRemoveShoppingCart(ShoppingCart cart) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.removeShoppingCart(cart);
  }

  public ShoppingCart shoppingcartMergeShoppingCarts(ShoppingCart userShoppingModel,
      ShoppingCart sessionCart, MerchantStore store) {
    try {
        return megastoreApp.shoppingCartService.mergeShoppingCarts(userShoppingModel,sessionCart,store);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void shoppingcartDeleteShoppingCartItem(Long id) {
    megastoreApp.shoppingCartService.deleteShoppingCartItem(id);
  }

  public Long shoppingcartCount() {
    return megastoreApp.shoppingCartService.count();
  }

  public ShoppingCart shoppingcartUpdate(ShoppingCart entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.update(entity);
    return entity;
  }

  public void shoppingcartDelete(ShoppingCart entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.delete(entity);
  }

  public ShoppingCart shoppingcartCreate(ShoppingCart entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.create(entity);
    return entity;
  }

  public List shoppingcartList() {
    return megastoreApp.shoppingCartService.list();
  }

  public ShoppingCart shoppingcartSave(ShoppingCart entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shoppingCartService.save(entity);
    return entity;
  }

  public void shoppingcartFlush() {
    megastoreApp.shoppingCartService.flush();
  }

  public ProductPrice productpriceSaveOrUpdate(ProductPrice price) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.saveOrUpdate(price);
    return price;
  }

  public ProductPrice productpriceAddDescription(ProductPrice price,
      ProductPriceDescription description) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.addDescription(price,description);
    return price;
  }

  public void productpriceDelete(ProductPrice price) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.delete(price);
  }

  public void productpriceDelete1(SalesManagerEntity price) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.delete((ProductPrice)price);
  }

  public ProductPrice productpriceGetById(Long id) {
    return megastoreApp.productPriceService.getById(id);
  }

  public Long productpriceCount() {
    return megastoreApp.productPriceService.count();
  }

  public ProductPrice productpriceUpdate(ProductPrice entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.update(entity);
    return entity;
  }

  public ProductPrice productpriceCreate(ProductPrice entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.create(entity);
    return entity;
  }

  public List productpriceList() {
    return megastoreApp.productPriceService.list();
  }

  public ProductPrice productpriceSave(ProductPrice entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productPriceService.save(entity);
    return entity;
  }

  public void productpriceFlush() {
    megastoreApp.productPriceService.flush();
  }

  public List transactionListTransactions(Order order) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.transactionService.listTransactions(order);
  }

  public Transaction transactionGetCapturableTransaction(Order order) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.transactionService.getCapturableTransaction(order);
  }

  public Transaction transactionGetRefundableTransaction(Order order) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.transactionService.getRefundableTransaction(order);
  }

  public Transaction transactionCreate(Transaction transaction) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.transactionService.create(transaction);
    return transaction;
  }

  public SalesManagerEntity transactionCreate1(SalesManagerEntity transaction) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.transactionService.create((Transaction)transaction);
    return transaction;
  }

  public Transaction transactionGetById(Long id) {
    return megastoreApp.transactionService.getById(id);
  }

  public Long transactionCount() {
    return megastoreApp.transactionService.count();
  }

  public Transaction transactionUpdate(Transaction entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.transactionService.update(entity);
    return entity;
  }

  public void transactionDelete(Transaction entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.transactionService.delete(entity);
  }

  public List transactionList() {
    return megastoreApp.transactionService.list();
  }

  public Transaction transactionSave(Transaction entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.transactionService.save(entity);
    return entity;
  }

  public void transactionFlush() {
    megastoreApp.transactionService.flush();
  }

  public List productoptionvalueGetByName(MerchantStore store, String name, Language language)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productOptionValueService.getByName(store,name,language);
  }

  public ProductOptionValue productoptionvalueGetByCode(MerchantStore store,
      String optionValueCode) {
    return megastoreApp.productOptionValueService.getByCode(store,optionValueCode);
  }

  public ProductOptionValue productoptionvalueGetById(MerchantStore store, Long optionValueId) {
    return megastoreApp.productOptionValueService.getById(store,optionValueId);
  }

  public ProductOptionValue productoptionvalueSaveOrUpdate(ProductOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionValueService.saveOrUpdate(entity);
    return entity;
  }

  public List productoptionvalueListByStore(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productOptionValueService.listByStore(store,language);
  }

  public List productoptionvalueListByStoreNoReadOnly(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productOptionValueService.listByStoreNoReadOnly(store,language);
  }

  public void productoptionvalueDelete(ProductOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionValueService.delete(entity);
  }

  public void productoptionvalueDelete1(SalesManagerEntity entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionValueService.delete((ProductOptionValue)entity);
  }

  public ProductOptionValue productoptionvalueGetById1(Long id) {
    return megastoreApp.productOptionValueService.getById(id);
  }

  public Long productoptionvalueCount() {
    return megastoreApp.productOptionValueService.count();
  }

  public ProductOptionValue productoptionvalueUpdate(ProductOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionValueService.update(entity);
    return entity;
  }

  public ProductOptionValue productoptionvalueCreate(ProductOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionValueService.create(entity);
    return entity;
  }

  public List productoptionvalueList() {
    return megastoreApp.productOptionValueService.list();
  }

  public ProductOptionValue productoptionvalueSave(ProductOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productOptionValueService.save(entity);
    return entity;
  }

  public void productoptionvalueFlush() {
    megastoreApp.productOptionValueService.flush();
  }

  public OrderTotalVariation ordertotalFindOrderTotalVariation(OrderSummary summary,
      Customer customer, MerchantStore store, Language language) {
    try {
        return megastoreApp.orderTotalService.findOrderTotalVariation(summary,customer,store,language);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public ShippingOrigin shippingoriginGetByStore(MerchantStore store) {
    return megastoreApp.shippingOriginService.getByStore(store);
  }

  public ShippingOrigin shippingoriginGetById(Long id) {
    return megastoreApp.shippingOriginService.getById(id);
  }

  public Long shippingoriginCount() {
    return megastoreApp.shippingOriginService.count();
  }

  public ShippingOrigin shippingoriginUpdate(ShippingOrigin entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingOriginService.update(entity);
    return entity;
  }

  public void shippingoriginDelete(ShippingOrigin entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingOriginService.delete(entity);
  }

  public ShippingOrigin shippingoriginCreate(ShippingOrigin entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingOriginService.create(entity);
    return entity;
  }

  public List shippingoriginList() {
    return megastoreApp.shippingOriginService.list();
  }

  public ShippingOrigin shippingoriginSave(ShippingOrigin entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingOriginService.save(entity);
    return entity;
  }

  public void shippingoriginFlush() {
    megastoreApp.shippingOriginService.flush();
  }

  public CustomerOptionSet customeroptionsetSaveOrUpdate(CustomerOptionSet entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionSetService.saveOrUpdate(entity);
    return entity;
  }

  public List customeroptionsetListByOption(CustomerOption option, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.customerOptionSetService.listByOption(option,store);
  }

  public List customeroptionsetListByStore(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.customerOptionSetService.listByStore(store,language);
  }

  public List customeroptionsetListByOptionValue(CustomerOptionValue optionValue,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.customerOptionSetService.listByOptionValue(optionValue,store);
  }

  public void customeroptionsetDelete(CustomerOptionSet customerOptionSet) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionSetService.delete(customerOptionSet);
  }

  public void customeroptionsetDelete1(SalesManagerEntity customerOptionSet) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionSetService.delete((CustomerOptionSet)customerOptionSet);
  }

  public CustomerOptionSet customeroptionsetGetById(Long id) {
    return megastoreApp.customerOptionSetService.getById(id);
  }

  public Long customeroptionsetCount() {
    return megastoreApp.customerOptionSetService.count();
  }

  public CustomerOptionSet customeroptionsetUpdate(CustomerOptionSet entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionSetService.update(entity);
    return entity;
  }

  public CustomerOptionSet customeroptionsetCreate(CustomerOptionSet entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionSetService.create(entity);
    return entity;
  }

  public List customeroptionsetList() {
    return megastoreApp.customerOptionSetService.list();
  }

  public CustomerOptionSet customeroptionsetSave(CustomerOptionSet entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionSetService.save(entity);
    return entity;
  }

  public void customeroptionsetFlush() {
    megastoreApp.customerOptionSetService.flush();
  }

  public List categoryGetByName(MerchantStore store, String name, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.getByName(store,name,language);
  }

  public CategoryDescription categoryGetDescription(Category category, Language language) {
    try {
        return megastoreApp.categoryService.getDescription(category,language);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public Category categoryGetByLanguage(long categoryId, Language language) {
    return megastoreApp.categoryService.getByLanguage(categoryId,language);
  }

  public Category categoryGetByCode(String storeCode, String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.getByCode(storeCode,code);
  }

  public Category categoryGetByCode1(MerchantStore store, String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.getByCode(store,code);
  }

  public Category categoryGetById(Long id) {
    return megastoreApp.categoryService.getById(id);
  }

  public SalesManagerEntity categoryGetById1(Serializable id) {
    return megastoreApp.categoryService.getById((Long)id);
  }

  public Category categorySaveOrUpdate(Category category) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.saveOrUpdate(category);
    return category;
  }

  public Category categoryGetBySeUrl(MerchantStore store, String seUrl) {
    return megastoreApp.categoryService.getBySeUrl(store,seUrl);
  }

  public List categoryListByStore(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listByStore(store,language);
  }

  public List categoryListByStore1(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listByStore(store);
  }

  public List categoryListByLineage(MerchantStore store, String lineage) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listByLineage(store,lineage);
  }

  public List categoryListByLineage1(String storeCode, String lineage) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listByLineage(storeCode,lineage);
  }

  public List categoryListBySeUrl(MerchantStore store, String seUrl) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listBySeUrl(store,seUrl);
  }

  public void categoryAddChild(Category parent, Category child) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.addChild(parent,child);
  }

  public List categoryListByParent(Category category, Language language) {
    return megastoreApp.categoryService.listByParent(category,language);
  }

  public List categoryListByParent1(Category category) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listByParent(category);
  }

  public List categoryListByStoreAndParent(MerchantStore store, Category category) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.listByStoreAndParent(store,category);
  }

  public void categoryAddCategoryDescription(Category category, CategoryDescription description)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.addCategoryDescription(category,description);
  }

  public List categoryListByDepth(MerchantStore store, int depth) {
    return megastoreApp.categoryService.listByDepth(store,depth);
  }

  public List categoryListByDepth1(MerchantStore store, int depth, Language language) {
    return megastoreApp.categoryService.listByDepth(store,depth,language);
  }

  public List categoryCountProductsByCategories(MerchantStore store, List categoryIds) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.categoryService.countProductsByCategories(store,categoryIds);
  }

  public List categoryListByCodes(MerchantStore store, List codes, Language language) {
    return megastoreApp.categoryService.listByCodes(store,codes,language);
  }

  public List categoryListByIds(MerchantStore store, List ids, Language language) {
    return megastoreApp.categoryService.listByIds(store,ids,language);
  }

  public void categoryDelete(Category category) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.delete(category);
  }

  public void categoryDelete1(SalesManagerEntity category) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.delete((Category)category);
  }

  public Category categoryCreate(Category category) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.create(category);
    return category;
  }

  public SalesManagerEntity categoryCreate1(SalesManagerEntity category) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.create((Category)category);
    return category;
  }

  public Long categoryCount() {
    return megastoreApp.categoryService.count();
  }

  public Category categoryUpdate(Category entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.update(entity);
    return entity;
  }

  public List categoryList() {
    return megastoreApp.categoryService.list();
  }

  public Category categorySave(Category entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.categoryService.save(entity);
    return entity;
  }

  public void categoryFlush() {
    megastoreApp.categoryService.flush();
  }

  public List merchantconfigurationListByType(MerchantConfigurationType type, MerchantStore store)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.merchantConfigurationService.listByType(type,store);
  }

  public MerchantConfiguration merchantconfigurationSaveOrUpdate(MerchantConfiguration entity)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.saveOrUpdate(entity);
    return entity;
  }

  public MerchantConfiguration merchantconfigurationGetMerchantConfiguration(String key,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.merchantConfigurationService.getMerchantConfiguration(key,store);
  }

  public List merchantconfigurationListByStore(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.merchantConfigurationService.listByStore(store);
  }

  public MerchantConfig merchantconfigurationSaveMerchantConfig(MerchantConfig config,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.saveMerchantConfig(config,store);
    return config;
  }

  public MerchantConfig merchantconfigurationGetMerchantConfig(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.merchantConfigurationService.getMerchantConfig(store);
  }

  public void merchantconfigurationDelete(MerchantConfiguration merchantConfiguration) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.delete(merchantConfiguration);
  }

  public void merchantconfigurationDelete1(SalesManagerEntity merchantConfiguration) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.delete((MerchantConfiguration)merchantConfiguration);
  }

  public MerchantConfiguration merchantconfigurationGetById(Long id) {
    return megastoreApp.merchantConfigurationService.getById(id);
  }

  public Long merchantconfigurationCount() {
    return megastoreApp.merchantConfigurationService.count();
  }

  public MerchantConfiguration merchantconfigurationUpdate(MerchantConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.update(entity);
    return entity;
  }

  public MerchantConfiguration merchantconfigurationCreate(MerchantConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.create(entity);
    return entity;
  }

  public List merchantconfigurationList() {
    return megastoreApp.merchantConfigurationService.list();
  }

  public MerchantConfiguration merchantconfigurationSave(MerchantConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantConfigurationService.save(entity);
    return entity;
  }

  public void merchantconfigurationFlush() {
    megastoreApp.merchantConfigurationService.flush();
  }

  public List contentListByType(List contentType, MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.listByType(contentType,store,language);
  }

  public List contentListByType1(ContentType contentType, MerchantStore store, Language language)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.listByType(contentType,store,language);
  }

  public List contentListByType2(List contentType, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.listByType(contentType,store);
  }

  public Content contentGetByLanguage(Long id, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.getByLanguage(id,language);
  }

  public List contentListNameByType(List contentType, MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.listNameByType(contentType,store,language);
  }

  public Content contentGetByCode(String code, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.getByCode(code,store);
  }

  public Content contentGetByCode1(String code, MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.getByCode(code,store,language);
  }

  public Content contentGetById(Long id) {
    return megastoreApp.contentService.getById(id);
  }

  public SalesManagerEntity contentGetById1(Serializable id) {
    return megastoreApp.contentService.getById((Long)id);
  }

  public Content contentSaveOrUpdate(Content content) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.saveOrUpdate(content);
    return content;
  }

  public void contentAddContentFile(String merchantStoreCode, InputContentFile contentFile) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.addContentFile(merchantStoreCode,contentFile);
  }

  public void contentAddLogo(String merchantStoreCode, InputContentFile cmsContentImage) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.addLogo(merchantStoreCode,cmsContentImage);
  }

  public void contentAddOptionImage(String merchantStoreCode, InputContentFile cmsContentImage)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.addOptionImage(merchantStoreCode,cmsContentImage);
  }

  public void contentAddContentFiles(String merchantStoreCode, List contentFilesList) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.addContentFiles(merchantStoreCode,contentFilesList);
  }

  public void contentRemoveFile(String merchantStoreCode, FileContentType fileContentType,
      String fileName) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.removeFile(merchantStoreCode,fileContentType,fileName);
  }

  public void contentRemoveFiles(String merchantStoreCode) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.removeFiles(merchantStoreCode);
  }

  public OutputContentFile contentGetContentFile(String merchantStoreCode,
      FileContentType fileContentType, String fileName) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.getContentFile(merchantStoreCode,fileContentType,fileName);
  }

  public List contentGetContentFiles(String merchantStoreCode, FileContentType fileContentType)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.getContentFiles(merchantStoreCode,fileContentType);
  }

  public List contentGetContentFilesNames(String merchantStoreCode, FileContentType fileContentType)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.contentService.getContentFilesNames(merchantStoreCode,fileContentType);
  }

  public ContentDescription contentGetBySeUrl(MerchantStore store, String seUrl) {
    return megastoreApp.contentService.getBySeUrl(store,seUrl);
  }

  public void contentDelete(SalesManagerEntity content) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.delete((Content)content);
  }

  public void contentDelete1(Content content) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.delete(content);
  }

  public Long contentCount() {
    return megastoreApp.contentService.count();
  }

  public Content contentUpdate(Content entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.update(entity);
    return entity;
  }

  public Content contentCreate(Content entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.create(entity);
    return entity;
  }

  public List contentList() {
    return megastoreApp.contentService.list();
  }

  public Content contentSave(Content entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.contentService.save(entity);
    return entity;
  }

  public void contentFlush() {
    megastoreApp.contentService.flush();
  }

  public List customerGetByName(String firstName) {
    return megastoreApp.customerService.getByName(firstName);
  }

  public Address customerGetCustomerAddress(MerchantStore store, String ipAddress) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.customerService.getCustomerAddress(store,ipAddress);
  }

  public SalesManagerEntity customerGetById(Serializable id) {
    return megastoreApp.customerService.getById((Long)id);
  }

  public Customer customerGetById1(Long id) {
    return megastoreApp.customerService.getById(id);
  }

  public Customer customerSaveOrUpdate(Customer customer) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerService.saveOrUpdate(customer);
    return customer;
  }

  public List customerListByStore(MerchantStore store) {
    return megastoreApp.customerService.listByStore(store);
  }

  public CustomerList customerListByStore1(MerchantStore store, CustomerCriteria criteria) {
    return megastoreApp.customerService.listByStore(store,criteria);
  }

  public Customer customerGetByNick(String nick) {
    return megastoreApp.customerService.getByNick(nick);
  }

  public Customer customerGetByNick1(String nick, int storeId) {
    return megastoreApp.customerService.getByNick(nick,storeId);
  }

  public void customerDelete(SalesManagerEntity customer) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerService.delete((Customer)customer);
  }

  public void customerDelete1(Customer customer) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerService.delete(customer);
  }

  public Long customerCount() {
    return megastoreApp.customerService.count();
  }

  public Customer customerUpdate(Customer entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerService.update(entity);
    return entity;
  }

  public Customer customerCreate(Customer entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerService.create(entity);
    return entity;
  }

  public List customerList() {
    return megastoreApp.customerService.list();
  }

  public Customer customerSave(Customer entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerService.save(entity);
    return entity;
  }

  public void customerFlush() {
    megastoreApp.customerService.flush();
  }

  public IntegrationConfiguration shippingGetShippingConfiguration(String moduleCode,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingConfiguration(moduleCode,store);
  }

  public ShippingConfiguration shippingGetShippingConfiguration1(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingConfiguration(store);
  }

  public CustomIntegrationConfiguration shippingGetCustomShippingConfiguration(String moduleCode,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getCustomShippingConfiguration(moduleCode,store);
  }

  public ShippingConfiguration shippingSaveShippingConfiguration(ShippingConfiguration shippingConfiguration,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingService.saveShippingConfiguration(shippingConfiguration,store);
    return shippingConfiguration;
  }

  public CustomIntegrationConfiguration shippingSaveCustomShippingConfiguration(String moduleCode,
      CustomIntegrationConfiguration shippingConfiguration, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingService.saveCustomShippingConfiguration(moduleCode,shippingConfiguration,store);
    return shippingConfiguration;
  }

  public List shippingGetSupportedCountries(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getSupportedCountries(store);
  }

  public List shippingGetShipToCountryList(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShipToCountryList(store,language);
  }

  public void shippingSetSupportedCountries(MerchantStore store, List countryCodes) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingService.setSupportedCountries(store,countryCodes);
  }

  public List shippingGetPackagesDetails(List products, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getPackagesDetails(products,store);
  }

  public boolean shippingRequiresShipping(List items, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.requiresShipping(items,store);
  }

  public List shippingGetShippingMethods(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingMethods(store);
  }

  public IntegrationConfiguration shippingSaveShippingQuoteModuleConfiguration(IntegrationConfiguration configuration,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingService.saveShippingQuoteModuleConfiguration(configuration,store);
    return configuration;
  }

  public void shippingRemoveShippingQuoteModuleConfiguration(String moduleCode, MerchantStore store)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingService.removeShippingQuoteModuleConfiguration(moduleCode,store);
  }

  public void shippingRemoveCustomShippingQuoteModuleConfiguration(String moduleCode,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.shippingService.removeCustomShippingQuoteModuleConfiguration(moduleCode,store);
  }

  public Map shippingGetShippingModulesConfigured(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingModulesConfigured(store);
  }

  public ShippingSummary shippingGetShippingSummary(MerchantStore store,
      ShippingQuote shippingQuote, ShippingOption selectedShippingOption) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingSummary(store,shippingQuote,selectedShippingOption);
  }

  public ShippingQuote shippingGetShippingQuote(MerchantStore store, Delivery delivery,
      List products, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingQuote(store,delivery,products,language);
  }

  public ShippingMetaData shippingGetShippingMetaData(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.shippingService.getShippingMetaData(store);
  }

  public IntegrationModule moduleconfigurationGetByCode(String moduleCode) {
    return megastoreApp.moduleConfigurationService.getByCode(moduleCode);
  }

  public void moduleconfigurationCreateOrUpdateModule(String json) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.moduleConfigurationService.createOrUpdateModule(json);
  }

  public List moduleconfigurationGetIntegrationModules(String module) {
    return megastoreApp.moduleConfigurationService.getIntegrationModules(module);
  }

  public IntegrationModule moduleconfigurationGetById(Long id) {
    return megastoreApp.moduleConfigurationService.getById(id);
  }

  public Long moduleconfigurationCount() {
    return megastoreApp.moduleConfigurationService.count();
  }

  public IntegrationModule moduleconfigurationUpdate(IntegrationModule entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.moduleConfigurationService.update(entity);
    return entity;
  }

  public void moduleconfigurationDelete(IntegrationModule entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.moduleConfigurationService.delete(entity);
  }

  public IntegrationModule moduleconfigurationCreate(IntegrationModule entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.moduleConfigurationService.create(entity);
    return entity;
  }

  public List moduleconfigurationList() {
    return megastoreApp.moduleConfigurationService.list();
  }

  public IntegrationModule moduleconfigurationSave(IntegrationModule entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.moduleConfigurationService.save(entity);
    return entity;
  }

  public void moduleconfigurationFlush() {
    megastoreApp.moduleConfigurationService.flush();
  }

  public ProductRelationship productrelationshipSaveOrUpdate(ProductRelationship relationship)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.saveOrUpdate(relationship);
    return relationship;
  }

  public List productrelationshipGetGroups(MerchantStore store) {
    return megastoreApp.productRelationshipService.getGroups(store);
  }

  public void productrelationshipAddGroup(MerchantStore store, String groupName) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.addGroup(store,groupName);
  }

  public List productrelationshipGetByGroup(MerchantStore store, String groupName,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.getByGroup(store,groupName,language);
  }

  public List productrelationshipGetByGroup1(MerchantStore store, String groupName) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.getByGroup(store,groupName);
  }

  public void productrelationshipDeleteGroup(MerchantStore store, String groupName) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.deleteGroup(store,groupName);
  }

  public void productrelationshipDeactivateGroup(MerchantStore store, String groupName) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.deactivateGroup(store,groupName);
  }

  public void productrelationshipActivateGroup(MerchantStore store, String groupName) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.activateGroup(store,groupName);
  }

  public List productrelationshipGetByType(MerchantStore store, ProductRelationshipType type) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.getByType(store,type);
  }

  public List productrelationshipGetByType1(MerchantStore store, ProductRelationshipType type,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.getByType(store,type,language);
  }

  public List productrelationshipGetByType2(MerchantStore store, Product product,
      ProductRelationshipType type, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.getByType(store,product,type,language);
  }

  public List productrelationshipGetByType3(MerchantStore store, Product product,
      ProductRelationshipType type) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.getByType(store,product,type);
  }

  public List productrelationshipListByProduct(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productRelationshipService.listByProduct(product);
  }

  public void productrelationshipDelete(ProductRelationship relationship) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.delete(relationship);
  }

  public void productrelationshipDelete1(SalesManagerEntity relationship) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.delete((ProductRelationship)relationship);
  }

  public ProductRelationship productrelationshipGetById(Long id) {
    return megastoreApp.productRelationshipService.getById(id);
  }

  public Long productrelationshipCount() {
    return megastoreApp.productRelationshipService.count();
  }

  public ProductRelationship productrelationshipUpdate(ProductRelationship entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.update(entity);
    return entity;
  }

  public ProductRelationship productrelationshipCreate(ProductRelationship entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.create(entity);
    return entity;
  }

  public List productrelationshipList() {
    return megastoreApp.productRelationshipService.list();
  }

  public ProductRelationship productrelationshipSave(ProductRelationship entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productRelationshipService.save(entity);
    return entity;
  }

  public void productrelationshipFlush() {
    megastoreApp.productRelationshipService.flush();
  }

  public void initializationdatabaseimplPopulate(String contextName) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.initializationDatabase.populate(contextName);
  }

  public boolean initializationdatabaseimplIsEmpty() {
    return megastoreApp.initializationDatabase.isEmpty();
  }

  public Zone zoneGetByCode(String code) {
    return megastoreApp.zoneService.getByCode(code);
  }

  public Zone zoneAddDescription(Zone zone, ZoneDescription description) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.zoneService.addDescription(zone,description);
    return zone;
  }

  public Map zoneGetZones(Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.zoneService.getZones(language);
  }

  public List zoneGetZones1(Country country, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.zoneService.getZones(country,language);
  }

  public Zone zoneGetById(Long id) {
    return megastoreApp.zoneService.getById(id);
  }

  public Long zoneCount() {
    return megastoreApp.zoneService.count();
  }

  public Zone zoneUpdate(Zone entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.zoneService.update(entity);
    return entity;
  }

  public void zoneDelete(Zone entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.zoneService.delete(entity);
  }

  public Zone zoneCreate(Zone entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.zoneService.create(entity);
    return entity;
  }

  public List zoneList() {
    return megastoreApp.zoneService.list();
  }

  public Zone zoneSave(Zone entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.zoneService.save(entity);
    return entity;
  }

  public void zoneFlush() {
    megastoreApp.zoneService.flush();
  }

  public List permissionGetByName() {
    return megastoreApp.permissionService.getByName();
  }

  public Permission permissionGetById(Integer permissionId) {
    return megastoreApp.permissionService.getById(permissionId);
  }

  public SalesManagerEntity permissionGetById1(Serializable permissionId) {
    return megastoreApp.permissionService.getById((Integer)permissionId);
  }

  public void permissionDeletePermission(Permission permission) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.permissionService.deletePermission(permission);
  }

  public PermissionList permissionListByCriteria(PermissionCriteria criteria) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.permissionService.listByCriteria(criteria);
  }

  public void permissionRemovePermission(Permission permission, Group group) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.permissionService.removePermission(permission,group);
  }

  public List permissionListPermission() throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.permissionService.listPermission();
  }

  public List permissionGetPermissions(List groupIds) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.permissionService.getPermissions(groupIds);
  }

  public Long permissionCount() {
    return megastoreApp.permissionService.count();
  }

  public Permission permissionUpdate(Permission entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.permissionService.update(entity);
    return entity;
  }

  public void permissionDelete(Permission entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.permissionService.delete(entity);
  }

  public Permission permissionCreate(Permission entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.permissionService.create(entity);
    return entity;
  }

  public List permissionList() {
    return megastoreApp.permissionService.list();
  }

  public Permission permissionSave(Permission entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.permissionService.save(entity);
    return entity;
  }

  public void permissionFlush() {
    megastoreApp.permissionService.flush();
  }

  public EmailConfig emailGetEmailConfiguration(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.emailService.getEmailConfiguration(store);
  }

  public EmailConfig emailSaveEmailConfiguration(EmailConfig emailConfig, MerchantStore store)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.emailService.saveEmailConfiguration(emailConfig,store);
    return emailConfig;
  }

  public void emailSendHtmlEmail(MerchantStore store, Email email) throws
      com.salesmanager.core.business.exception.ServiceException {
    try {
        megastoreApp.emailService.sendHtmlEmail(store,email);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public SalesManagerEntity productimageGetById(Serializable id) {
    return megastoreApp.productImageService.getById((Long)id);
  }

  public ProductImage productimageGetById1(Long id) {
    return megastoreApp.productImageService.getById(id);
  }

  public ProductImage productimageSaveOrUpdate(ProductImage productImage) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.saveOrUpdate(productImage);
    return productImage;
  }

  public Product productimageAddProductImages(Product product, List productImages) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.addProductImages(product,productImages);
    return product;
  }

  public ProductImage productimageAddProductImageDescription(ProductImage productImage,
      ProductImageDescription description) throws
      com.salesmanager.core.business.exception.ServiceException {
    return productImage;
  }

  public List productimageGetProductImages(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productImageService.getProductImages(product);
  }

  public void productimageRemoveProductImage(ProductImage productImage) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.removeProductImage(productImage);
  }

  public OutputContentFile productimageGetProductImage(ProductImage productImage,
      ProductImageSize size) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productImageService.getProductImage(productImage,size);
  }

  public OutputContentFile productimageGetProductImage1(String storeCode, String productCode,
      String fileName, ProductImageSize size) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productImageService.getProductImage(storeCode,productCode,fileName,size);
  }

  public Product productimageAddProductImage(Product product, ProductImage productImage,
      ImageContentFile inputImage) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.addProductImage(product,productImage,inputImage);
    return product;
  }

  public Long productimageCount() {
    return megastoreApp.productImageService.count();
  }

  public ProductImage productimageUpdate(ProductImage entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.update(entity);
    return entity;
  }

  public void productimageDelete(ProductImage entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.delete(entity);
  }

  public ProductImage productimageCreate(ProductImage entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.create(entity);
    return entity;
  }

  public List productimageList() {
    return megastoreApp.productImageService.list();
  }

  public ProductImage productimageSave(ProductImage entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productImageService.save(entity);
    return entity;
  }

  public void productimageFlush() {
    megastoreApp.productImageService.flush();
  }

  public TaxClass taxclassGetByCode(String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxClassService.getByCode(code);
  }

  public TaxClass taxclassGetByCode1(String code, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxClassService.getByCode(code,store);
  }

  public TaxClass taxclassGetById(Long id) {
    return megastoreApp.taxClassService.getById(id);
  }

  public SalesManagerEntity taxclassGetById1(Serializable id) {
    return megastoreApp.taxClassService.getById((Long)id);
  }

  public List taxclassListByStore(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxClassService.listByStore(store);
  }

  public void taxclassDelete(SalesManagerEntity taxClass) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxClassService.delete((TaxClass) taxClass);
  }

  public void taxclassDelete1(TaxClass taxClass) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxClassService.delete(taxClass);
  }

  public Long taxclassCount() {
    return megastoreApp.taxClassService.count();
  }

  public TaxClass taxclassUpdate(TaxClass entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxClassService.update(entity);
    return entity;
  }

  public TaxClass taxclassCreate(TaxClass entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxClassService.create(entity);
    return entity;
  }

  public List taxclassList() {
    return megastoreApp.taxClassService.list();
  }

  public TaxClass taxclassSave(TaxClass entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxClassService.save(entity);
    return entity;
  }

  public void taxclassFlush() {
    megastoreApp.taxClassService.flush();
  }

  public List paymentGetPaymentMethods(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getPaymentMethods(store);
  }

  public List paymentGetAcceptedPaymentMethods(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getAcceptedPaymentMethods(store);
  }

  public IntegrationModule paymentGetPaymentMethodByType(MerchantStore store, String type) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getPaymentMethodByType(store,type);
  }

  public IntegrationModule paymentGetPaymentMethodByCode(MerchantStore store, String code) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getPaymentMethodByCode(store,code);
  }

  public IntegrationConfiguration paymentGetPaymentConfiguration(String moduleCode,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getPaymentConfiguration(moduleCode,store);
  }

  public Map paymentGetPaymentModulesConfigured(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getPaymentModulesConfigured(store);
  }

  public IntegrationConfiguration paymentSavePaymentModuleConfiguration(IntegrationConfiguration configuration,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.paymentService.savePaymentModuleConfiguration(configuration,store);
    return configuration;
  }

  public void paymentRemovePaymentModuleConfiguration(String moduleCode, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.paymentService.removePaymentModuleConfiguration(moduleCode,store);
  }

  public Transaction paymentProcessPayment(Customer customer, MerchantStore store, Payment payment,
      List items, Order order) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.processPayment(customer,store,payment,items,order);
  }

  public PaymentModule paymentGetPaymentModule(String paymentModuleCode) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.getPaymentModule(paymentModuleCode);
  }

  public Transaction paymentProcessCapturePayment(Order order, Customer customer,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.processCapturePayment(order,customer,store);
  }

  public Transaction paymentProcessRefund(Order order, Customer customer, MerchantStore store,
      BigDecimal amount) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.paymentService.processRefund(order,customer,store,amount);
  }

  public void paymentValidateCreditCard(String number, CreditCardType creditCard, String month,
      String date) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.paymentService.validateCreditCard(number,creditCard,month,date);
  }

  public MerchantLog merchantlogGetById(Long id) {
    return megastoreApp.merchantLogService.getById(id);
  }

  public Long merchantlogCount() {
    return megastoreApp.merchantLogService.count();
  }

  public MerchantLog merchantlogUpdate(MerchantLog entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantLogService.update(entity);
    return entity;
  }

  public void merchantlogDelete(MerchantLog entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantLogService.delete(entity);
  }

  public MerchantLog merchantlogCreate(MerchantLog entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantLogService.create(entity);
    return entity;
  }

  public List merchantlogList() {
    return megastoreApp.merchantLogService.list();
  }

  public MerchantLog merchantlogSave(MerchantLog entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.merchantLogService.save(entity);
    return entity;
  }

  public void merchantlogFlush() {
    megastoreApp.merchantLogService.flush();
  }

  public Manufacturer manufacturerGetByCode(MerchantStore store, String code) {
    return megastoreApp.manufacturerService.getByCode(store,code);
  }

  public Manufacturer manufacturerSaveOrUpdate(Manufacturer manufacturer) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.saveOrUpdate(manufacturer);
    return manufacturer;
  }

  public List manufacturerListByStore(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.manufacturerService.listByStore(store);
  }

  public List manufacturerListByStore1(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.manufacturerService.listByStore(store,language);
  }

  public Long manufacturerGetCountManufAttachedProducts(Manufacturer manufacturer) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.manufacturerService.getCountManufAttachedProducts(manufacturer);
  }

  public List manufacturerListByProductsByCategoriesId(MerchantStore store, List ids,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.manufacturerService.listByProductsByCategoriesId(store,ids,language);
  }

  public Manufacturer manufacturerAddManufacturerDescription(Manufacturer manufacturer,
      ManufacturerDescription description) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.addManufacturerDescription(manufacturer,description);
    return manufacturer;
  }

  public void manufacturerDelete(Manufacturer manufacturer) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.delete(manufacturer);
  }

  public void manufacturerDelete1(SalesManagerEntity manufacturer) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.delete((Manufacturer)manufacturer);
  }

  public Manufacturer manufacturerGetById(Long id) {
    return megastoreApp.manufacturerService.getById(id);
  }

  public Long manufacturerCount() {
    return megastoreApp.manufacturerService.count();
  }

  public Manufacturer manufacturerUpdate(Manufacturer entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.update(entity);
    return entity;
  }

  public Manufacturer manufacturerCreate(Manufacturer entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.create(entity);
    return entity;
  }

  public List manufacturerList() {
    return megastoreApp.manufacturerService.list();
  }

  public Manufacturer manufacturerSave(Manufacturer entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.manufacturerService.save(entity);
    return entity;
  }

  public void manufacturerFlush() {
    megastoreApp.manufacturerService.flush();
  }

  public void searchDeleteIndex(MerchantStore store, Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.searchService.deleteIndex(store,product);
  }

  public SearchKeywords searchSearchForKeywords(String collectionName, String jsonString,
      int entriesCount) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.searchService.searchForKeywords(collectionName,jsonString,entriesCount);
  }

  public void searchInitService() {
    megastoreApp.searchService.initService();
  }

  public void searchIndex(MerchantStore store, Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.searchService.index(store,product);
  }

  public SearchResponse searchSearch(MerchantStore store, String languageCode, String jsonString,
      int entriesCount, int startIndex) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.searchService.search(store,languageCode,jsonString,entriesCount,startIndex);
  }

  public Order orderSaveOrUpdate(Order order) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.saveOrUpdate(order);
    return order;
  }

  public OrderList orderListByStore(MerchantStore store, OrderCriteria criteria) {
    return megastoreApp.orderService.listByStore(store,criteria);
  }

  public Order orderAddOrderStatusHistory(Order order, OrderStatusHistory history) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.addOrderStatusHistory(order,history);
    return order;
  }

  public OrderTotalSummary orderCalculateShoppingCartTotal(ShoppingCart shoppingCart,
      Customer customer, MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.calculateShoppingCartTotal(shoppingCart,customer,store,language);
  }

  public OrderTotalSummary orderCalculateShoppingCartTotal1(ShoppingCart shoppingCart,
      MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.calculateShoppingCartTotal(shoppingCart,store,language);
  }

  public OrderTotalSummary orderCaculateOrderTotal(OrderSummary orderSummary, MerchantStore store,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.caculateOrderTotal(orderSummary,store,language);
  }

  public OrderTotalSummary orderCaculateOrderTotal1(OrderSummary orderSummary, Customer customer,
      MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.caculateOrderTotal(orderSummary,customer,store,language);
  }

  public ByteArrayOutputStream orderGenerateInvoice(MerchantStore store, Order order,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.generateInvoice(store,order,language);
  }

  public Order orderGetOrder(Long orderId) {
    return megastoreApp.orderService.getOrder(orderId);
  }

  public Order orderProcessOrder(Order order, Customer customer, List items,
      OrderTotalSummary summary, Payment payment, Transaction transaction, MerchantStore store)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.processOrder(order,customer,items,summary,payment,transaction,store);
  }

  public Order orderProcessOrder1(Order order, Customer customer, List items,
      OrderTotalSummary summary, Payment payment, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.processOrder(order,customer,items,summary,payment,store);
  }

  public boolean orderHasDownloadFiles(Order order) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.orderService.hasDownloadFiles(order);
  }

  public void orderDelete(Order order) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.delete(order);
  }

  public void orderDelete1(SalesManagerEntity order) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.delete((Order) order);
  }

  public Order orderGetById(Long id) {
    return megastoreApp.orderService.getById(id);
  }

  public Long orderCount() {
    return megastoreApp.orderService.count();
  }

  public Order orderUpdate(Order entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.update(entity);
    return entity;
  }

  public Order orderCreate(Order entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.create(entity);
    return entity;
  }

  public List orderList() {
    return megastoreApp.orderService.list();
  }

  public Order orderSave(Order entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.orderService.save(entity);
    return entity;
  }

  public void orderFlush() {
    megastoreApp.orderService.flush();
  }

  public TaxRate taxrateGetByCode(String code, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxRateService.getByCode(code,store);
  }

  public List taxrateListByCountryStateProvinceAndTaxClass(Country country, String stateProvince,
      TaxClass taxClass, MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxRateService.listByCountryStateProvinceAndTaxClass(country,stateProvince,taxClass,store,language);
  }

  public List taxrateListByCountryZoneAndTaxClass(Country country, Zone zone, TaxClass taxClass,
      MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxRateService.listByCountryZoneAndTaxClass(country,zone,taxClass,store,language);
  }

  public List taxrateListByStore(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxRateService.listByStore(store);
  }

  public List taxrateListByStore1(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxRateService.listByStore(store,language);
  }

  public void taxrateDelete(TaxRate taxRate) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxRateService.delete(taxRate);
  }

  public void taxrateDelete1(SalesManagerEntity taxRate) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxRateService.delete((TaxRate) taxRate);
  }

  public TaxRate taxrateGetById(Long id) {
    return megastoreApp.taxRateService.getById(id);
  }

  public Long taxrateCount() {
    return megastoreApp.taxRateService.count();
  }

  public TaxRate taxrateUpdate(TaxRate entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxRateService.update(entity);
    return entity;
  }

  public TaxRate taxrateCreate(TaxRate entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxRateService.create(entity);
    return entity;
  }

  public List taxrateList() {
    return megastoreApp.taxRateService.list();
  }

  public TaxRate taxrateSave(TaxRate entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxRateService.save(entity);
    return entity;
  }

  public void taxrateFlush() {
    megastoreApp.taxRateService.flush();
  }

  public CustomerOptionValue customeroptionvalueGetByCode(MerchantStore store,
      String optionValueCode) {
    return megastoreApp.customerOptionValueService.getByCode(store,optionValueCode);
  }

  public CustomerOptionValue customeroptionvalueSaveOrUpdate(CustomerOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionValueService.saveOrUpdate(entity);
    return entity;
  }

  public List customeroptionvalueListByStore(MerchantStore store, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.customerOptionValueService.listByStore(store,language);
  }

  public void customeroptionvalueDelete(SalesManagerEntity customerOptionValue) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionValueService.delete((CustomerOptionValue)customerOptionValue);
  }

  public void customeroptionvalueDelete1(CustomerOptionValue customerOptionValue) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionValueService.delete(customerOptionValue);
  }

  public CustomerOptionValue customeroptionvalueGetById(Long id) {
    return megastoreApp.customerOptionValueService.getById(id);
  }

  public Long customeroptionvalueCount() {
    return megastoreApp.customerOptionValueService.count();
  }

  public CustomerOptionValue customeroptionvalueUpdate(CustomerOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionValueService.update(entity);
    return entity;
  }

  public CustomerOptionValue customeroptionvalueCreate(CustomerOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionValueService.create(entity);
    return entity;
  }

  public List customeroptionvalueList() {
    return megastoreApp.customerOptionValueService.list();
  }

  public CustomerOptionValue customeroptionvalueSave(CustomerOptionValue entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.customerOptionValueService.save(entity);
    return entity;
  }

  public void customeroptionvalueFlush() {
    megastoreApp.customerOptionValueService.flush();
  }

  public ProductAvailability productavailabilitySaveOrUpdate(ProductAvailability availability)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAvailabilityService.saveOrUpdate(availability);
    return availability;
  }

  public ProductAvailability productavailabilityGetById(Long id) {
    return megastoreApp.productAvailabilityService.getById(id);
  }

  public Long productavailabilityCount() {
    return megastoreApp.productAvailabilityService.count();
  }

  public ProductAvailability productavailabilityUpdate(ProductAvailability entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAvailabilityService.update(entity);
    return entity;
  }

  public void productavailabilityDelete(ProductAvailability entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAvailabilityService.delete(entity);
  }

  public ProductAvailability productavailabilityCreate(ProductAvailability entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAvailabilityService.create(entity);
    return entity;
  }

  public List productavailabilityList() {
    return megastoreApp.productAvailabilityService.list();
  }

  public ProductAvailability productavailabilitySave(ProductAvailability entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAvailabilityService.save(entity);
    return entity;
  }

  public void productavailabilityFlush() {
    megastoreApp.productAvailabilityService.flush();
  }

  public List groupListGroupByIds(Set ids) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.groupService.listGroupByIds(ids);
  }

  public List groupListGroup(GroupType groupType) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.groupService.listGroup(groupType);
  }

  public Group groupGetById(Integer id) {
    return megastoreApp.groupService.getById(id);
  }

  public Long groupCount() {
    return megastoreApp.groupService.count();
  }

  public Group groupUpdate(Group entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.groupService.update(entity);
    return entity;
  }

  public void groupDelete(Group entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.groupService.delete(entity);
  }

  public Group groupCreate(Group entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.groupService.create(entity);
    return entity;
  }

  public List groupList() {
    return megastoreApp.groupService.list();
  }

  public Group groupSave(Group entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.groupService.save(entity);
    return entity;
  }

  public void groupFlush() {
    megastoreApp.groupService.flush();
  }

  public SystemConfiguration systemconfigurationGetByKey(String key) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.systemConfigurationService.getByKey(key);
  }

  public SystemConfiguration systemconfigurationGetById(Long id) {
    return megastoreApp.systemConfigurationService.getById(id);
  }

  public Long systemconfigurationCount() {
    return megastoreApp.systemConfigurationService.count();
  }

  public SystemConfiguration systemconfigurationUpdate(SystemConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.systemConfigurationService.update(entity);
    return entity;
  }

  public void systemconfigurationDelete(SystemConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.systemConfigurationService.delete(entity);
  }

  public SystemConfiguration systemconfigurationCreate(SystemConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.systemConfigurationService.create(entity);
    return entity;
  }

  public List systemconfigurationList() {
    return megastoreApp.systemConfigurationService.list();
  }

  public SystemConfiguration systemconfigurationSave(SystemConfiguration entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.systemConfigurationService.save(entity);
    return entity;
  }

  public void systemconfigurationFlush() {
    megastoreApp.systemConfigurationService.flush();
  }

  public Product productGetByCode(String productCode, Language language) {
    return megastoreApp.productService.getByCode(productCode,language);
  }

  public Product productGetById(Long productId) {
    return megastoreApp.productService.getById(productId);
  }

  public SalesManagerEntity productGetById1(Serializable productId) {
    return megastoreApp.productService.getById((Long)productId);
  }

  public Product productGetBySeUrl(MerchantStore store, String seUrl, Locale locale) {
    return megastoreApp.productService.getBySeUrl(store,seUrl,locale);
  }

  public List productGetProducts(List categoryIds, Language language) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productService.getProducts(categoryIds,language);
  }

  public List productGetProducts1(List categoryIds) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productService.getProducts(categoryIds);
  }

  public ProductList productListByStore(MerchantStore store, Language language,
      ProductCriteria criteria) {
    return megastoreApp.productService.listByStore(store,language,criteria);
  }

  public List productListByStore1(MerchantStore store) {
    return megastoreApp.productService.listByStore(store);
  }

  public Product productAddProductDescription(Product product, ProductDescription description)
      throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.addProductDescription(product,description);
    return product;
  }

  public ProductDescription productGetProductDescription(Product product, Language language) {
    return megastoreApp.productService.getProductDescription(product,language);
  }

  public Product productGetProductForLocale(long productId, Language language, Locale locale) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productService.getProductForLocale(productId,language,locale);
  }

  public List productGetProductsForLocale(Category category, Language language, Locale locale)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productService.getProductsForLocale(category,language,locale);
  }

  public List productListByTaxClass(TaxClass taxClass) {
    return megastoreApp.productService.listByTaxClass(taxClass);
  }

  public SalesManagerEntity productUpdate(SalesManagerEntity product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.update((Product)product);
    return product;
  }

  public Product productUpdate1(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.update(product);
    return product;
  }

  public void productDelete(SalesManagerEntity product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.delete((Product) product);
  }

  public void productDelete1(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.delete(product);
  }

  public Product productCreate(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.create(product);
    return product;
  }

  public SalesManagerEntity productCreate1(SalesManagerEntity product) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.create((Product) product);
    return product;
  }

  public Long productCount() {
    return megastoreApp.productService.count();
  }

  public List productList() {
    return megastoreApp.productService.list();
  }

  public Product productSave(Product entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productService.save(entity);
    return entity;
  }

  public void productFlush() {
    megastoreApp.productService.flush();
  }

  public User userSaveOrUpdate(User user) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.userService.saveOrUpdate(user);
    return user;
  }

  public List userListByStore(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.userService.listByStore(store);
  }

  public User userGetByUserName(String userName) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.userService.getByUserName(userName);
  }

  public List userListUser() throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.userService.listUser();
  }

  public void userDelete(User user) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.userService.delete(user);
  }

  public void userDelete1(SalesManagerEntity user) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.userService.delete((User) user);
  }

  public User userGetById(Long id) {
    return megastoreApp.userService.getById(id);
  }

  public Long userCount() {
    return megastoreApp.userService.count();
  }

  public User userUpdate(User entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.userService.update(entity);
    return entity;
  }

  public User userCreate(User entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.userService.create(entity);
    return entity;
  }

  public List userList() {
    return megastoreApp.userService.list();
  }

  public User userSave(User entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.userService.save(entity);
    return entity;
  }

  public void userFlush() {
    megastoreApp.userService.flush();
  }

  public SalesManagerEntity productattributeGetById(Serializable id) {
    return megastoreApp.productAttributeService.getById((Long)id);
  }

  public ProductAttribute productattributeGetById1(Long id) {
    return megastoreApp.productAttributeService.getById(id);
  }

  public ProductAttribute productattributeSaveOrUpdate(ProductAttribute productAttribute) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAttributeService.saveOrUpdate(productAttribute);
    return productAttribute;
  }

  public List productattributeGetByOptionId(MerchantStore store, Long id) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productAttributeService.getByOptionId(store,id);
  }

  public List productattributeGetByOptionValueId(MerchantStore store, Long id) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productAttributeService.getByOptionValueId(store,id);
  }

  public List productattributeGetByProductId(MerchantStore store, Product product,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productAttributeService.getByProductId(store,product,language);
  }

  public List productattributeGetByAttributeIds(MerchantStore store, Product product, List ids)
      throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.productAttributeService.getByAttributeIds(store,product,ids);
  }

  public void productattributeDelete(ProductAttribute attribute) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAttributeService.delete(attribute);
  }

  public void productattributeDelete1(SalesManagerEntity attribute) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAttributeService.delete((ProductAttribute)attribute);
  }

  public Long productattributeCount() {
    return megastoreApp.productAttributeService.count();
  }

  public ProductAttribute productattributeUpdate(ProductAttribute entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAttributeService.update(entity);
    return entity;
  }

  public ProductAttribute productattributeCreate(ProductAttribute entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAttributeService.create(entity);
    return entity;
  }

  public List productattributeList() {
    return megastoreApp.productAttributeService.list();
  }

  public ProductAttribute productattributeSave(ProductAttribute entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productAttributeService.save(entity);
    return entity;
  }

  public void productattributeFlush() {
    megastoreApp.productAttributeService.flush();
  }

  public DigitalProduct digitalproductSaveOrUpdate(DigitalProduct digitalProduct) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.saveOrUpdate(digitalProduct);
    return digitalProduct;
  }

  public DigitalProduct digitalproductGetByProduct(MerchantStore store, Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.digitalProductService.getByProduct(store,product);
  }

  public Product digitalproductAddProductFile(Product product, DigitalProduct digitalProduct,
      InputContentFile inputFile) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.addProductFile(product,digitalProduct,inputFile);
    return product;
  }

  public void digitalproductDelete(SalesManagerEntity digitalProduct) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.delete((DigitalProduct)digitalProduct);
  }

  public void digitalproductDelete1(DigitalProduct digitalProduct) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.delete(digitalProduct);
  }

  public DigitalProduct digitalproductGetById(Long id) {
    return megastoreApp.digitalProductService.getById(id);
  }

  public Long digitalproductCount() {
    return megastoreApp.digitalProductService.count();
  }

  public DigitalProduct digitalproductUpdate(DigitalProduct entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.update(entity);
    return entity;
  }

  public DigitalProduct digitalproductCreate(DigitalProduct entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.create(entity);
    return entity;
  }

  public List digitalproductList() {
    return megastoreApp.digitalProductService.list();
  }

  public DigitalProduct digitalproductSave(DigitalProduct entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.digitalProductService.save(entity);
    return entity;
  }

  public void digitalproductFlush() {
    megastoreApp.digitalProductService.flush();
  }

  public String pricingGetDisplayAmount(BigDecimal amount, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.getDisplayAmount(amount,store);
  }

  public String pricingGetDisplayAmount1(BigDecimal amount, Locale locale, Currency currency,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.getDisplayAmount(amount,locale,currency,store);
  }

  public FinalPrice pricingCalculateProductPrice(Product product, List attributes,
      Customer customer) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.calculateProductPrice(product,attributes,customer);
  }

  public FinalPrice pricingCalculateProductPrice1(Product product, List attributes) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.calculateProductPrice(product,attributes);
  }

  public FinalPrice pricingCalculateProductPrice2(Product product, Customer customer) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.calculateProductPrice(product,customer);
  }

  public FinalPrice pricingCalculateProductPrice3(Product product) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.calculateProductPrice(product);
  }

  public String pricingGetStringAmount(BigDecimal amount, MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.pricingService.getStringAmount(amount,store);
  }

  public TaxConfiguration taxGetTaxConfiguration(MerchantStore store) throws
      com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxService.getTaxConfiguration(store);
  }

  public TaxConfiguration taxSaveTaxConfiguration(TaxConfiguration shippingConfiguration,
      MerchantStore store) throws com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.taxService.saveTaxConfiguration(shippingConfiguration,store);
    return shippingConfiguration;
  }

  public List taxCalculateTax(OrderSummary orderSummary, Customer customer, MerchantStore store,
      Language language) throws com.salesmanager.core.business.exception.ServiceException {
    return megastoreApp.taxService.calculateTax(orderSummary,customer,store,language);
  }

  public List productreviewGetByCustomer(Customer customer) {
    return megastoreApp.productReviewService.getByCustomer(customer);
  }

  public List productreviewGetByProductNoCustomers(Product product) {
    return megastoreApp.productReviewService.getByProductNoCustomers(product);
  }

  public List productreviewGetByProduct(Product product, Language language) {
    return megastoreApp.productReviewService.getByProduct(product,language);
  }

  public List productreviewGetByProduct1(Product product) {
    return megastoreApp.productReviewService.getByProduct(product);
  }

  public ProductReview productreviewGetByProductAndCustomer(Long productId, Long customerId) {
    return megastoreApp.productReviewService.getByProductAndCustomer(productId,customerId);
  }

  public ProductReview productreviewCreate(ProductReview review) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productReviewService.create(review);
    return review;
  }

  public SalesManagerEntity productreviewCreate1(SalesManagerEntity review) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productReviewService.create((ProductReview)review);
    return review;
  }

  public ProductReview productreviewGetById(Long id) {
    return megastoreApp.productReviewService.getById(id);
  }

  public Long productreviewCount() {
    return megastoreApp.productReviewService.count();
  }

  public ProductReview productreviewUpdate(ProductReview entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productReviewService.update(entity);
    return entity;
  }

  public void productreviewDelete(ProductReview entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productReviewService.delete(entity);
  }

  public List productreviewList() {
    return megastoreApp.productReviewService.list();
  }

  public ProductReview productreviewSave(ProductReview entity) throws
      com.salesmanager.core.business.exception.ServiceException {
    megastoreApp.productReviewService.save(entity);
    return entity;
  }

  public void productreviewFlush() {
    megastoreApp.productReviewService.flush();
  }
}
