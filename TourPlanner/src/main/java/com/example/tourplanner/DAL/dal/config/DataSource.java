package com.example.tourplanner.DAL.dal.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.sql.Connection;
import java.sql.SQLException;

public class DataSource implements DbConnector {

    private final HikariDataSource ds;

    private DataSource() {
        HikariConfig config = new HikariConfig("TourPlanner/src/main/resources/hikari.properties");
        ds = new HikariDataSource(config);
    }

    private static DataSource dataSource;

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    @Override
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("Database not available");
        }
    }
}