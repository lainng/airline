package com.epamtc.airline;

import com.epamtc.airline.dao.connection.ConnectionPool;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class ConnectionPoolExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        String uniqueKey = this.getClass().getName();
        Object value = extensionContext.getRoot().getStore(GLOBAL).get(uniqueKey);
        if (value == null) {
            ConnectionPool.getInstance().init();
            extensionContext.getRoot().getStore(GLOBAL).put(uniqueKey, this);
        }
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().terminate();
    }
}
