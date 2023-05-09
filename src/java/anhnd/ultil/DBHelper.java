/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.ultil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DucAnh
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws ClassNotFoundException, SQLException, NamingException {
        //1. load file
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. create connertion string
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=master";
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url,"sa","ducanh");
//        
//        return con;
//1 get current context
        Context context = new InitialContext();
        //2 get tomcat context
        Context tomcat = (Context)context.lookup("java:comp/env");
        //3 lookup DS
        DataSource ds = (DataSource)tomcat.lookup("BeanDS");
        //4 open connection
        Connection con = ds.getConnection();
        return con;
    }

}
