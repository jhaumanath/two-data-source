package com.umanath.twodatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Ensure this method is only called within a transactional context
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            System.out.println("No active transaction");
            return "write"; // Default to write if no transaction is active
        }

        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        String currentDb = isReadOnly ? "read" : "write";
        System.out.println("Transaction is read-only: " + isReadOnly);
        System.out.println("Current DataSource in determineCurrentLookupKey: " + currentDb);
        return currentDb;
    }
}
