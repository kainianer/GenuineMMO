/*
 * The MIT License
 *
 * Copyright 2014 kainianer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.kainianer.mmo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author kainianer
 */
public class DatabaseUtil {

    public static DatabaseUtil instance = new DatabaseUtil();
    private String userName = "root";
    private String password = "";
    private String serverName = "localhost";
    private int portNumber = 3306;

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection(
                "jdbc:mysql://"
                + this.serverName
                + ":" + this.portNumber + "/mcserver",
                connectionProps);

        return conn;
    }

    public static DatabaseUtil getInstance() {
        return instance;
    }

    public static ResultSet getResultSet(String query) throws SQLException {
        Connection con = DatabaseUtil.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement(query);
        ResultSet set = st.executeQuery();
        return set;
    }

    public static void executeQuery(String query) throws SQLException {
        Connection con = DatabaseUtil.getInstance().getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate(query);
    }

    public static int countRows(String query) throws SQLException {
        Connection con = DatabaseUtil.getInstance().getConnection();
        PreparedStatement st = con.prepareStatement(query);
        ResultSet set = st.executeQuery();
        set.next();
        int i = set.getInt(1);
        set.close();
        return i;
    }
}
