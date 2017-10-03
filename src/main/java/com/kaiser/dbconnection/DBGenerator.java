package com.kaiser.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBGenerator {

    public DBGenerator(Connection conn) {
        dropTables(conn);
        buildStudentTable(conn);
    }

    /**
     * The buildCustomerTable method creates the
     * Customer table and adds some rows to it.
     */
    public static void buildStudentTable(Connection conn)
    {
        try
        {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            // Create the table.
            stmt.execute("CREATE TABLE Students" +
                    "( rollNo INT NOT NULL PRIMARY KEY, " +
                    "  Name varchar(25) )");

            // Add some rows to the new table.
            stmt.executeUpdate("INSERT INTO Students VALUES" +
                    "(101, 'Kaiser Ahmed')");

            stmt.executeUpdate("INSERT INTO Students VALUES" +
                    "(102, 'Zahid Hasan')");

            stmt.executeUpdate("INSERT INTO Students VALUES" +
                    "(103, 'John Robert')");

            System.out.println("Students table created with some dummy data.");
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * The dropTables method drops any existing
     * in case the database already exists.
     */
    public static void dropTables(Connection conn)
    {
        System.out.println("Checking for existing tables.");

        try
        {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            try
            {
                // Drop the UnpaidOrder table.
                stmt.execute("DROP TABLE Students");
                System.out.println("Students table dropped.");
            } catch (SQLException ex)
            {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
