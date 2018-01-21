package com.salesmanager.core.business;

import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.product.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.services.catalog.product.availability.ProductAvailabilityService;
import com.salesmanager.core.business.services.catalog.product.file.DigitalProductService;
import com.salesmanager.core.business.services.catalog.product.image.ProductImageService;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.catalog.product.price.ProductPriceService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.business.services.catalog.product.review.ProductReviewService;
import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.business.services.content.ContentService;
import com.salesmanager.core.business.services.customer.CustomerService;
import com.salesmanager.core.business.services.customer.attribute.CustomerAttributeService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionSetService;
import com.salesmanager.core.business.services.customer.attribute.CustomerOptionValueService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.order.OrderService;
import com.salesmanager.core.business.services.order.orderproduct.OrderProductDownloadService;
import com.salesmanager.core.business.services.order.ordertotal.OrderTotalService;
import com.salesmanager.core.business.services.payments.PaymentService;
import com.salesmanager.core.business.services.payments.TransactionService;
import com.salesmanager.core.business.services.reference.country.CountryService;
import com.salesmanager.core.business.services.reference.currency.CurrencyService;
import com.salesmanager.core.business.services.reference.init.InitializationDatabase;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.reference.zone.ZoneService;
import com.salesmanager.core.business.services.search.SearchService;
import com.salesmanager.core.business.services.shipping.ShippingOriginService;
import com.salesmanager.core.business.services.shipping.ShippingService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartCalculationService;
import com.salesmanager.core.business.services.shoppingcart.ShoppingCartService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.business.services.system.MerchantLogService;
import com.salesmanager.core.business.services.system.ModuleConfigurationService;
import com.salesmanager.core.business.services.system.SystemConfigurationService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.business.services.tax.TaxRateService;
import com.salesmanager.core.business.services.tax.TaxService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.business.utils.InitializationLoader;
import javax.inject.Inject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


//@EntityScan(basePackages = {"com.salesmanager.core.model"})
//@ComponentScan({"com.salesmanager.core.business.services","com.salesmanager.core.business.utils"})
//@ImportResource("classpath:/spring/shopizer-core-context.xml")
@SpringBootApplication
public class MegastoreApp {

  @Inject
  public InitializationLoader initializationLoader;

  @Inject
  public CountryService countryService;

  @Inject
  public CustomerOptionService customerOptionService;

  @Inject
  public ProductTypeService productTypeService;

  @Inject
  public ProductOptionService productOptionService;

  @Inject
  public CustomerAttributeService customerAttributeService;

  @Inject
  public CurrencyService currencyService;

  @Inject
  public ShoppingCartCalculationService shoppingCartCalculationService;

  @Inject
  public OrderProductDownloadService orderProductDownloadService;

  @Inject
  public MerchantStoreService merchantStoreService;

  @Inject
  public LanguageService languageService;

  @Inject
  public ShoppingCartService shoppingCartService;

  @Inject
  public ProductPriceService productPriceService;

  @Inject
  public TransactionService transactionService;

  @Inject
  public ProductOptionValueService productOptionValueService;

  @Inject
  public OrderTotalService orderTotalService;

  @Inject
  public ShippingOriginService shippingOriginService;

  @Inject
  public CustomerOptionSetService customerOptionSetService;

  @Inject
  public CategoryService categoryService;

  @Inject
  public MerchantConfigurationService merchantConfigurationService;

  @Inject
  public ContentService contentService;

  @Inject
  public CustomerService customerService;

  @Inject
  public ShippingService shippingService;

  @Inject
  public ModuleConfigurationService moduleConfigurationService;

  @Inject
  public ProductRelationshipService productRelationshipService;

  @Inject
  public InitializationDatabase initializationDatabase;

  @Inject
  public ZoneService zoneService;

  @Inject
  public PermissionService permissionService;

  @Inject
  public EmailService emailService;

  @Inject
  public ProductImageService productImageService;

  @Inject
  public TaxClassService taxClassService;

  @Inject
  public PaymentService paymentService;

  @Inject
  public MerchantLogService merchantLogService;

  @Inject
  public ManufacturerService manufacturerService;

  @Inject
  public SearchService searchService;

  @Inject
  public OrderService orderService;

  @Inject
  public TaxRateService taxRateService;

  @Inject
  public CustomerOptionValueService customerOptionValueService;

  @Inject
  public ProductAvailabilityService productAvailabilityService;

  @Inject
  public GroupService groupService;

  @Inject
  public SystemConfigurationService systemConfigurationService;

  @Inject
  public ProductService productService;

  @Inject
  public UserService userService;

  @Inject
  public ProductAttributeService productAttributeService;

  @Inject
  public DigitalProductService digitalProductService;

  @Inject
  public PricingService pricingService;

  @Inject
  public TaxService taxService;

  @Inject
  public ProductReviewService productReviewService;
}
