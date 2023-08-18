package be.civaform.demorabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {

    private static final Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());
    private static final ThreadLocal<String> currentTenant = new ThreadLocal();
    private static final ThreadLocal<String> currentApplication = new ThreadLocal();

    private TenantContext() {
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        clearCurrentTenant();
        logger.debug("Setting tenant to {} - {}", tenant, currentTenant);
        currentTenant.set(tenant);
    }

    public static void clearCurrentTenant() {
        logger.debug("Clear tenant");
        currentTenant.remove();
    }

    public static String getCurrentApplication() {
        return currentApplication.get();
    }

    public static void setCurrentApplication(String application) {
        clearCurrentApplication();
        logger.debug("Setting application to {} - {}", application, currentApplication);
        currentApplication.set(application);
    }

    public static void clearCurrentApplication() {
        logger.debug("Clear application");
        currentApplication.remove();
    }

}
