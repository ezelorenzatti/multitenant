package br.lorenzatti.springcloundmultitenant.multitenancy.routing;

import br.lorenzatti.springcloundmultitenant.multitenancy.resolver.TenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TenantRoutingDatasource extends AbstractRoutingDataSource {

    @Autowired
    private TenantIdentifierResolver tenantIdentifierResolver;

    TenantRoutingDatasource() {
        setDefaultTargetDataSource(createEmbeddedDatabase("default"));
        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("0", createEmbeddedDatabase("0"));
        targetDataSources.put("1", createEmbeddedDatabase("1"));
        setTargetDataSources(targetDataSources);
    }

    @Override
    protected String determineCurrentLookupKey() {
        return tenantIdentifierResolver.resolveCurrentTenantIdentifier();
    }

    private EmbeddedDatabase createEmbeddedDatabase(String name) {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName(name)
                .addScript("database.sql")
                .build();
    }
}
