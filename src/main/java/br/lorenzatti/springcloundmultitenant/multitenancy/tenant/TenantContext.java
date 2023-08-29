package br.lorenzatti.springcloundmultitenant.multitenancy.tenant;

public class TenantContext {

    final public static TenantProperties DEFAULT_TENANT = new TenantProperties();

    private static final ThreadLocal<TenantProperties> currentTenant = ThreadLocal.withInitial(() -> DEFAULT_TENANT);

    public static void setCurrentTenant(TenantProperties tenant) {
        currentTenant.set(tenant);
    }

    public static TenantProperties getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
