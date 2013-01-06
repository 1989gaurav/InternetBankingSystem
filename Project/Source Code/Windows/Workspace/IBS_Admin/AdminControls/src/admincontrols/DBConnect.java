/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admincontrols;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Administrator
 */
public class DBConnect{

    private Connection conn;		// Refers to the connection variable
    public Statement stmt;		// Refers to the SQL Query Statement
    public ResultSet rset;		// Refers to the Result Set of the Query 

    public DBConnect() {
        conn = null;
        stmt = null;
        rset = null;
    }

    public Connection getDBConnection()  //Create the connection to DatabasePool & return the connection variable
    {
        try {
        
        Class.forName("com.ibm.db2.jcc.DB2Driver");
        conn=DriverManager.getConnection("jdbc:db2://10.8.41.187:50000/ibs","db2admin","thesaurus");

        } catch (Exception ex) {
        
            //Error Condition
            System.out.print(ex.getMessage());
        }
        return (conn);
    }

    public Statement CreateQueryStatement(Connection c) // Creates the SQL Statement variable w.r.t to the Connection variable 
    {
        try {
            System.out.println("\nThis is connection :"+c);
            stmt = c.createStatement();
            System.out.println("\nThis is statement :"+stmt);
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (stmt);
    }

    public ResultSet ExecuteQuery(String query, Statement s, boolean SearchType) // Returns the Result of the Query Executed
    {
        // SearchType is a variable which should be set to true if query is of search type
        // and should be set to false if query is of update type i.e. insert, update or delete.
        try {
            if (SearchType) {
                rset = s.executeQuery(query);
            } else {
                s.executeUpdate(query);
            }
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
        return (rset);
    }

    public String getCurrentDate() {
        //calculate current date and current time
        int yyyy, mm, dd;
        Calendar c = Calendar.getInstance();
        yyyy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH) + 1;
        dd = c.get(Calendar.DAY_OF_MONTH);
        String date = dd + "." + mm + "." + yyyy;

        return date;

    }

    public String getCurrentTime() {
        int min, hr, sec;
        Calendar c = Calendar.getInstance();
        hr = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);
        sec = c.get(Calendar.SECOND);
        String time = hr + ":" + min + ":" + sec;

        return time;
    }
}