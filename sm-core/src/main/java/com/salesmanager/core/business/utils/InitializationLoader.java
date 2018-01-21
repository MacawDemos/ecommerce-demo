package com.salesmanager.core.business.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.core.business.services.user.UserService;
import com.salesmanager.core.business.constants.SystemConstants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.init.InitializationDatabase;
import com.salesmanager.core.business.services.system.MerchantConfigurationService;
import com.salesmanager.core.business.services.system.SystemConfigurationService;
import com.salesmanager.core.business.services.user.GroupService;
import com.salesmanager.core.business.services.user.PermissionService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.system.MerchantConfig;
import com.salesmanager.core.model.system.SystemConfiguration;
import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import com.salesmanager.core.model.user.Permission;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.transform.stream.StreamSource;


@Component
public class InitializationLoader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitializationLoader.class);

	private static final String DEFAULT_INITIAL_PASSWORD = "password";

	@Inject
	private UserService userService;
	

	@Inject
	private MerchantStoreService merchantStoreService;
	
	@Inject
	@Named("passwordEncoder")
	private PasswordEncoder passwordEncoder;

	
	@Inject
	private MerchantConfigurationService merchantConfigurationService;

	
	@Inject
	private InitializationDatabase initializationDatabase;
	
	@Inject
	private InitData initData;
	
	@Inject
	private SystemConfigurationService systemConfigurationService;
	
	@Inject
	protected PermissionService  permissionService;
	
	@Inject
	protected GroupService   groupService;
	
	@Inject
	private CoreConfiguration configuration;
	
	@Inject
	protected MerchantStoreService merchantService;

    @Inject
    private ObjectMapper jacksonObjectMapper;

    @Inject
    private ResourceLoader resourceLoader;
	
	//@PostConstruct
	public void init() {
		
		try {
			
			if (initializationDatabase.isEmpty()) {
                LOGGER.info(" ==================== Initializing database ==================== ");

                java.io.InputStream permissionXML=resourceLoader.getResource("classpath:/permission/permission.json").getInputStream();
                StreamSource xmlSource = new StreamSource(permissionXML);
                //Permissions permissions= (Permissions) jaxb2Marshaller.unmarshal(xmlSource);

                Permissions permissions= jacksonObjectMapper.readValue(permissionXML,Permissions.class);

				//All default data to be created
				
				LOGGER.info(String.format("%s : Shopizer database is empty, populate it....", "sm-shop"));
		
				 initializationDatabase.populate("sm-shop");
				
				 MerchantStore store = merchantService.getByCode(MerchantStore.DEFAULT_STORE);
				
				 //security groups and permissions

                Map<String, Group> groupMap = new HashMap<String,Group>();
                if(CollectionUtils.isNotEmpty(permissions.getShopPermission())){

                    for(ShopPermission shopPermission : permissions.getShopPermission()){

                        Permission permission = new Permission(shopPermission.getType());

                        for(String groupName: shopPermission.getShopGroup().getName()){
                            if(groupMap.get(groupName) == null){
                                Group group = new Group(groupName);
                                group.setGroupType(GroupType.ADMIN);
                                groupService.create(group);
                                groupMap.put(groupName,group);
                                permission.getGroups().add(group);
                            }
                            else{
                                permission.getGroups().add(groupMap.get(groupName)) ;
                            }
                            permissionService.create( permission);
                        }


                    }
                }

                  createDefaultAdmin();
                  MerchantConfig config = new MerchantConfig();
				  config.setAllowPurchaseItems(true);
				  config.setDisplayAddToCartOnFeaturedItems(true);
				  
				  merchantConfigurationService.saveMerchantConfig(config, store);

				  loadData();

			}
			
		} catch (Exception e) {
			LOGGER.error("Error in the init method",e);
		}
		

		
	}
	
	private void loadData() throws ServiceException {
		
		String loadTestData = configuration.getProperty(Constants.POPULATE_TEST_DATA);
		boolean loadData =  !StringUtils.isBlank(loadTestData) && loadTestData.equals(SystemConstants.CONFIG_VALUE_TRUE);


		if(loadData) {

			SystemConfiguration configuration = systemConfigurationService.getByKey(Constants.TEST_DATA_LOADED);

			if(configuration!=null) {
					if(configuration.getKey().equals(Constants.TEST_DATA_LOADED)) {
						if(configuration.getValue().equals(SystemConstants.CONFIG_VALUE_TRUE)) {
							return;
						}
					}
			}

			initData.initInitialData();

			configuration = new SystemConfiguration();
			configuration.getAuditSection().setModifiedBy(SystemConstants.SYSTEM_USER);
			configuration.setKey(Constants.TEST_DATA_LOADED);
			configuration.setValue(SystemConstants.CONFIG_VALUE_TRUE);
			systemConfigurationService.create(configuration);


		}
	}

	public void createDefaultAdmin() throws Exception {
		
		  //TODO create all groups and permissions
		
		  MerchantStore store = merchantStoreService.getMerchantStore(MerchantStore.DEFAULT_STORE);

		  String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		  
		  List<Group> groups = groupService.listGroup(GroupType.ADMIN);
		  
		  //creation of the super admin admin:password)
		  com.salesmanager.core.model.user.User user = new com.salesmanager.core.model.user.User("admin",password,"admin@shopizer.com");
		  user.setFirstName("Administrator");
		  user.setLastName("User");
		  
		  for(Group group : groups) {
			  if(group.getGroupName().equals(Constants.GROUP_SUPERADMIN) || group.getGroupName().equals(Constants.GROUP_ADMIN)) {
				  user.getGroups().add(group);
			  }
		  }

		  user.setMerchantStore(store);		  
		  userService.create(user);
		
		
	}



}
