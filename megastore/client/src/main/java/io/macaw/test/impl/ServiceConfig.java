package io.macaw.test.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cfx.service.client.api.LoginException;
import com.cfx.service.client.api.NonServiceSourceServiceClientContext;
import com.cfx.service.client.api.ServiceClientContext;
import com.cfx.service.client.api.ServiceInvoker;
import com.cfx.service.client.api.Session;

public class ServiceConfig implements Runnable {
    
    private static final Logger logger = LoggerFactory.getLogger(ServiceConfig.class);
        
    private static final ServiceConfig _instance = new ServiceConfig();
    private Properties macawProperties = null;
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private ServiceClientContext serviceClientContext;
    private ScheduledFuture<?> scheduledFuture;

    private Session session;
 
    private String userid;
    private String password;
    
    static {
        _instance.start();
    }
            
    private ServiceConfig() {
    }
    
    public static ServiceConfig getInstance() {
        return _instance;
    }
    
    public void start()  {
        this.macawProperties = getMacawProperties();
        NonServiceSourceServiceClientContext nssscc = new NonServiceSourceServiceClientContext(this.macawProperties);
        nssscc.bootstrap();
        serviceClientContext = nssscc;
        scheduledFuture = scheduler.scheduleAtFixedRate(this, 5, 5, TimeUnit.MINUTES);
    }
    
    public void stop() {
        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
        }
        if (this.session != null) {
            this.session.logout();
        }
        
        if (serviceClientContext != null) {
            try {
                serviceClientContext.close();
            } catch (Exception e) {
                //no-op
            }
        }
    }
    
    public String getPropertyByKey(String key) {
        return macawProperties.getProperty(key);
    }
    
    public Properties getMacawProperties() {
        if (macawProperties == null || macawProperties.isEmpty()) {
                try {
                    macawProperties = loadMacawProperties();
                } catch (Exception e) {
                        throw new RuntimeException("Failed to load macaw properties", e);
                }
        }
        return macawProperties;
    }
    
    public Properties loadMacawProperties() throws Exception {
        final String userHome = System.getProperty("user.home");
        final File macawConfFileDefault = new File(userHome, "macaw.conf");

        String preferredPath = System.getenv("MACAW_CONF_DIR");
        if (preferredPath == null || preferredPath.equals(""))
        {
                preferredPath = "/opt/cfx-config/common";
        }
        final File macawConfFilePreferred = new File(preferredPath, "macaw.conf");
        File macawConfFile = macawConfFilePreferred;

        if (!macawConfFile.exists() || !macawConfFile.isFile()) {
                macawConfFile = macawConfFileDefault;
        }

        if (macawConfFile.exists() && macawConfFile.isFile()) {
                logger.info("Loading macaw.conf from " + macawConfFile.getAbsolutePath());
                // load the properties
                try (final FileInputStream fileInputStream = new FileInputStream(
                                macawConfFile)) {
                        final Properties macawConfProperties = new Properties();
                        macawConfProperties.load(fileInputStream);
                        userid = macawConfProperties.getProperty("io.macaw.demo.user", "admin@macaw.io");
                        password = macawConfProperties.getProperty("io.macaw.demo.password", "admin123$");
                        return macawConfProperties;
                }
        }
        return null;
    }

    public String getProperty(String name) throws Exception {
    
            String value = macawProperties.getProperty(name);
            if (value == null) {
                    throw new Exception("Macaw property " + name + "does not exist");
            }
            return value;
    }
    
    public ServiceClientContext getServiceClientContext() {
        return serviceClientContext;
    }
    
    public ServiceInvoker locateServiceInvoker(String namespace, String name) {
        try {
            return serviceClientContext.getServiceLocator().locateServiceInvoker(getSession(), namespace, name);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }
    
    public <S> S locateService(Class<S> serviceType, String namespace, String name) {
        try {
            return serviceClientContext.getServiceLocator().locateService(getSession(), serviceType, namespace, name);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Session getSession() throws LoginException {
        if (this.session != null) {
            try {
                this.session = this.session.renew();
                return this.session;
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }

        this.session = this.serviceClientContext.login(null, userid, password);
        return this.session;
    }

    @Override
    public void run() {
        try {
            getSession();
        } catch (LoginException e) {
            logger.warn(e.getMessage(), e);
        }
    }
}
