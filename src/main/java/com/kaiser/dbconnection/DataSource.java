package com.kaiser.dbconnection;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {

    private static DataSource datasource;
    private BasicDataSource ds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUsername("root");
//        ds.setPassword("root");
//        ds.setUrl("jdbc:mysql://localhost/test");

        ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        ds.setUsername("");
        ds.setPassword("");
        ds.setUrl("jdbc:derby:StudentDB;create=true");

        // the settings below are optional -- dbcp can work with defaults
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            System.out.println("Creating new connection");
            return datasource;
        } else {
            System.out.println("Returning old connection");
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

    // This Method Is Used To Print The Connection Pool Status
    public void printDbStatus() throws SQLException {
        System.out.println("Max.: " + ds.getMaxTotal() + "; Active: " + ds.getNumActive() + "; Idle: " + ds.getNumIdle());
    }

}