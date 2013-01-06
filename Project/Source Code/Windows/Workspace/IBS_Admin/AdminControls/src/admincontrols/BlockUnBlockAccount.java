/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package admincontrols;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class BlockUnBlockAccount extends DBConnect{

    Connection DBConn;
    Statement DBStmt;
    ResultSet Result;
    String query;
    
    public BlockUnBlockAccount() {
        super();
    }
    
    int BlockAccount(String Username, String Reason) {
        
        try{
            
        DBConn = getDBConnection();
        DBStmt = CreateQueryStatement(DBConn);
        
        query = "SELECT ALL ADMINISTRATOR.ALOGININFO.USERNAME FROM ADMINISTRATOR.ALOGININFO WHERE ADMINISTRATOR.ALOGININFO.USERNAME='"+Username+"'";
        System.out.print(query);
        Result = ExecuteQuery(query, DBStmt, true);
        
        if(Result.next()) {
            query = "update administrator.alogininfo set administrator.alogininfo.blockstatus=3 where administrator.alogininfo.username='"+Username+"'";
            System.out.print(query);
            ExecuteQuery(query, DBStmt, false);
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Username "+Username+" Blocked successfully");
        }
        else {
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Username "+Username+" does not exist!!");
        }
        DBConn.close();
        }
        catch(SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return 0;
    }
    
    int UnBlockAccount(String Username, String Comment) {
        
        try{
            
        DBConn = getDBConnection();
        DBStmt = CreateQueryStatement(DBConn);
        
        query = "SELECT ALL ADMINISTRATOR.ALOGININFO.USERNAME FROM ADMINISTRATOR.ALOGININFO WHERE ADMINISTRATOR.ALOGININFO.USERNAME='"+Username+"'";
        System.out.print(query);
        Result = ExecuteQuery(query, DBStmt, true);
        
        if(Result.next()) {
            query = "update administrator.alogininfo set administrator.alogininfo.blockstatus=0 where administrator.alogininfo.username='"+Username+"'";
            System.out.print(query);
            ExecuteQuery(query, DBStmt, false);
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Username "+Username+" UnBlocked successfully");
        }
        else {
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Username "+Username+" does not exist!!");
        }
        
        DBConn.close();
        }
        catch(SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return 0;
    }
    
    int ResetProfilePassword(String Username, String Password) {
        
        try{
            
        DBConn = getDBConnection();
        DBStmt = CreateQueryStatement(DBConn);
        
        query = "SELECT ALL ADMINISTRATOR.ALOGININFO.USERNAME FROM ADMINISTRATOR.ALOGININFO WHERE ADMINISTRATOR.ALOGININFO.USERNAME='"+Username+"'";
        System.out.print(query);
        Result = ExecuteQuery(query, DBStmt, true);
        
        if(Result.next()) {
            query = "update administrator.alogininfo set administrator.alogininfo.password='"+Password+"' where administrator.alogininfo.username='"+Username+"'";
            System.out.print(query);
            ExecuteQuery(query, DBStmt, false);
            
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Profile password for Username "+Username+" has been Reset");
        }
        else {
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Username "+Username+" does not exist!!");
        }
        
        DBConn.close();
        }
        catch(SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return 0;
    }
    
    int ResetTransactionPassword(String Username, String TransactionPassword) {
        
        try{
            
        DBConn = getDBConnection();
        DBStmt = CreateQueryStatement(DBConn);
        
        query = "SELECT ALL ADMINISTRATOR.ALOGININFO.USERNAME FROM ADMINISTRATOR.ALOGININFO WHERE ADMINISTRATOR.ALOGININFO.USERNAME='"+Username+"'";
        System.out.print(query);
        Result = ExecuteQuery(query, DBStmt, true);
        
        if(Result.next()) {
            query = "update administrator.alogininfo set administrator.alogininfo.transactionPass='"+TransactionPassword+"' where administrator.alogininfo.username='"+Username+"'";
            System.out.print(query);
            ExecuteQuery(query, DBStmt, false);
            
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Transaction password for Username "+Username+" has been Reset");
        }
        else {
        
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(Message, "Username "+Username+" does not exist!!");
        
        }
        
        DBConn.close();
        }
        catch(SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return 0;
    }

}