/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package admincontrols;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Search extends DBConnect{

    String query=null;
    Connection DBConn;
    Statement DBStmt;
    ResultSet Result;
    
    public Search() {
    
        super();
    }
    
    public void SearchByName(String Name) {
        try {
            
            DBConn = getDBConnection();
            DBStmt = CreateQueryStatement(DBConn);
            //String ColoumnName=null;

            query = "SELECT * FROM ADMINISTRATOR.MYPROFILE WHERE ADMINISTRATOR.MYPROFILE.MYNAME='"+Name+"'";
            Result = ExecuteQuery(query, DBStmt, true);
            
            SearchResult SR = new SearchResult();
            SR.DisplayResult(Result);
            }
            catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print(ex.getMessage());
            }
            /*
            ResultSetMetaData MetaData;
            
            if (Result.next()) {
                JTable Table = new JTable();
                MetaData =Result.getMetaData();
                int NumOfColumn = MetaData.getColumnCount();
                
                for (int i=0;i<NumOfColumn;i++){
                
                    ColoumnName = MetaData.getColumnName(i);
                    //Table.a();
                }
                
                
               do{
                   
               } while(Result.next());
            }
            else{
                
            }
             
            
        */
    }

    void QueryOutput(String InputQuery, int Type) {
        
        try {
            
            DBConn = getDBConnection();
            DBStmt = CreateQueryStatement(DBConn);
            //String ColoumnName=null;
            if(Type==0) {
                Result = ExecuteQuery(InputQuery, DBStmt, true);
                SearchResult SR = new SearchResult();
                SR.DisplayResult(Result);
            }
            else if(Type==1){
                ExecuteQuery(InputQuery, DBStmt, false);
                JOptionPane Message = new JOptionPane();
                JOptionPane.showMessageDialog(null,"Query executed successfully","Info",0);
            }
            
       } catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print(ex.getMessage());
       }
    }

    void SearchByAccounts(String name, int Type) {
        try {
            int Account = Integer.parseInt(name);
            DBConn = getDBConnection();
            DBStmt = CreateQueryStatement(DBConn);
            //String ColoumnName=null;
            
            if(Type==0){
                
                query = "SELECT ALL ADMINISTRATOR.MYPROFILE.MYNAME, ADMINISTRATOR.MYPROFILE.MYADDRESS, ADMINISTRATOR.MYPROFILE.MYPHONE, ADMINISTRATOR.MYPROFILE.MAIL, ADMINISTRATOR.MYPROFILE.IDPROOF, ADMINISTRATOR.ACCOUNTMAPPING.ACCOUNTNUMBER, ADMINISTRATOR.ACCOUNTDETAILS.ACCOUNTTYPE, ADMINISTRATOR.ACCOUNTDETAILS.OPENDATE, ADMINISTRATOR.ACCOUNTDETAILS.BALANCE, ADMINISTRATOR.ACCOUNTDETAILS.HOLDER1, ADMINISTRATOR.ACCOUNTDETAILS.HOLDER2, ADMINISTRATOR.ACCOUNTDETAILS.HOLDER3, ADMINISTRATOR.ACCOUNTDETAILS.GURANTEE1, ADMINISTRATOR.ACCOUNTDETAILS.GURANTEE2, ADMINISTRATOR.ACCOUNTDETAILS.DRAFTLIMIT FROM ADMINISTRATOR.MYPROFILE, ADMINISTRATOR.ACCOUNTMAPPING, ADMINISTRATOR.ACCOUNTDETAILS WHERE ADMINISTRATOR.MYPROFILE.USERID = ADMINISTRATOR.ACCOUNTMAPPING.USERID AND ADMINISTRATOR.ACCOUNTDETAILS.ACCOUNTNUMBER = ADMINISTRATOR.ACCOUNTMAPPING.ACCOUNTNUMBER AND ADMINISTRATOR.ACCOUNTMAPPING.ACCOUNTNUMBER="+Account;
                Result = ExecuteQuery(query, DBStmt, true);
            
                SearchResult SR = new SearchResult();
                SR.DisplayResult(Result);
            }
            else if(Type == 1) {
                
                query = "SELECT ALL ADMINISTRATOR.LOANINFO.LOANACCOUNT, ADMINISTRATOR.LOANINFO.LOANTYPE, ADMINISTRATOR.LOANINFO.ISSUEDAMOUNT, ADMINISTRATOR.LOANINFO.EMI, ADMINISTRATOR.LOANINFO.TIMEISSUE, ADMINISTRATOR.LOANINFO.OVERDUE, ADMINISTRATOR.LOANINFO.MORTGAGESTUFF, ADMINISTRATOR.MYPROFILE.USERID, ADMINISTRATOR.MYPROFILE.MYNAME, ADMINISTRATOR.MYPROFILE.MYADDRESS, ADMINISTRATOR.MYPROFILE.MYPHONE, ADMINISTRATOR.MYPROFILE.MAIL, ADMINISTRATOR.MYPROFILE.IDPROOF, ADMINISTRATOR.LOANMAPPING.USERID, ADMINISTRATOR.LOANMAPPING.LOANACCOUNT FROM ADMINISTRATOR.LOANINFO, ADMINISTRATOR.MYPROFILE, ADMINISTRATOR.LOANMAPPING WHERE ADMINISTRATOR.MYPROFILE.USERID = ADMINISTRATOR.LOANMAPPING.USERID AND ADMINISTRATOR.LOANMAPPING.LOANACCOUNT = ADMINISTRATOR.LOANINFO.LOANACCOUNT AND ADMINISTRATOR.LOANINFO.LOANACCOUNT="+Account;
                Result = ExecuteQuery(query, DBStmt, true);
            
                SearchResult SR = new SearchResult();
                SR.DisplayResult(Result);
            }
                    
        }catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print(ex.getMessage());
            }
    }

    void SearchByTransaction(String TidString) {
        
        try {
            int tid = Integer.parseInt(TidString);
            DBConn = getDBConnection();
            DBStmt = CreateQueryStatement(DBConn);
            ResultSet ResultTwo;
            //String ColoumnName=null;

            query = "SELECT ALL ADMINISTRATOR.TRANSACTION.TRANSACTIONID, ADMINISTRATOR.TRANSACTION.TIME, ADMINISTRATOR.TRANSACTION.DATE, ADMINISTRATOR.DEBIT.AMOUNT, ADMINISTRATOR.DEBIT.DETAILS, ADMINISTRATOR.DEBIT.ACCOUNTNUMBER FROM ADMINISTRATOR.TRANSACTION, ADMINISTRATOR.DEBIT WHERE ADMINISTRATOR.TRANSACTION.TRANSACTIONID=ADMINISTRATOR.DEBIT.TRANSACTIONID AND ADMINISTRATOR.DEBIT.TRANSACTIONID="+tid;
            Result = ExecuteQuery(query, DBStmt, true);
            
            SearchResult SR = new SearchResult();
            //JLabel DebitLabel = new JLabel("Debit Entry");
            //SR.add(DebitLabel);
            //SR.DisplayResult(Result);
            
            query = "SELECT ALL ADMINISTRATOR.TRANSACTION.TRANSACTIONID, ADMINISTRATOR.TRANSACTION.TIME, ADMINISTRATOR.TRANSACTION.DATE, ADMINISTRATOR.Credit.AMOUNT, ADMINISTRATOR.Credit.DETAILS, ADMINISTRATOR.Credit.ACCOUNTNUMBER FROM ADMINISTRATOR.TRANSACTION, ADMINISTRATOR.Credit WHERE ADMINISTRATOR.TRANSACTION.TRANSACTIONID=ADMINISTRATOR.Credit.TRANSACTIONID AND ADMINISTRATOR.Credit.TRANSACTIONID="+tid;
            ResultTwo = ExecuteQuery(query, DBStmt, true);
            
            //JLabel CreditLabel = new JLabel("Credit Entry");
            //SR.add(CreditLabel);
            SR.DisplayTwoResults(ResultTwo, "Debit Entry", ResultTwo, "Credit Entry");
            
            }
            catch (Exception ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print(ex.getMessage());
            }
    }

}
