/*
 * SearchResult.java
 *
 * Created on October 30, 2008, 7:15 PM
 */

package admincontrols;

import java.awt.ScrollPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

/**
 *
 * @author  Administrator
 */
public class SearchResult extends javax.swing.JFrame {
    
    int columnCount;
    private Vector ColumnName;
    private Vector Data;
    private JScrollPane ScrollPane, ScrollPaneOne,ScrollPaneTwo;
    JTable Table;
    JPanel Panel;
    private JTable TableTwo;
    private JPanel PanelTwo;
    
    /** Creates new form SearchResult */
    public SearchResult() {
        initComponents();
        columnCount=0;
        ColumnName = new Vector();
        Data=new Vector();
        
    }
    
    public int DisplayResult(ResultSet Result){
        try {
            ResultSetMetaData MetaData = Result.getMetaData();
            columnCount=MetaData.getColumnCount();
            System.out.print(columnCount);
            if(columnCount>0)
            {
                for(int i=0; i<columnCount; i++) {
                    ColumnName.add(MetaData.getColumnName(i+1));
                }
            }
            else
            {
                JOptionPane Message = new JOptionPane();
                JOptionPane.showMessageDialog(Message, "Zero column in result");
            }
            
            Data=(getRowData(Result));                
                
            Table=new JTable(Data, ColumnName);
            ScrollPane = new JScrollPane();
            //Table;// = new  JTable();
            ScrollPane.setViewportView(Table);
            Panel = new JPanel();
            Panel.add(ScrollPane);
        
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setContentPane(Panel);
            Table.repaint();
        
            pack();
            
            
            
            this.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void DisplayTwoResults(ResultSet ResultOne, String TextOne, ResultSet ResultTwo, String TextTwo)    {
        
        try {
            ResultSetMetaData MetaData = ResultOne.getMetaData();
            columnCount=MetaData.getColumnCount();
            System.out.print(columnCount);
            if(columnCount>0)
            {
                for(int i=0; i<columnCount; i++) {
                    ColumnName.add(MetaData.getColumnName(i+1));
                }
            }
            else
            {
                JOptionPane Message = new JOptionPane();
                JOptionPane.showMessageDialog(Message, "Zero column in result");
            }
            
            Data=(getRowData(ResultOne));                
                
            Table=new JTable(Data, ColumnName);
            JLabel LabelOne = new JLabel(TextOne);
            ScrollPaneOne = new JScrollPane();
            //Table;// = new  JTable();
            ScrollPaneOne.setViewportView(Table);
            Panel = new JPanel();
            Panel.add(LabelOne);
            Panel.add(ScrollPaneOne);
            
            Data=getRowData(ResultTwo);
            TableTwo=new JTable(Data, ColumnName);
            JLabel LabelTwo = new JLabel(TextTwo);
            ScrollPaneTwo = new JScrollPane();
            //Table;// = new  JTable();
            ScrollPaneTwo.setViewportView(TableTwo);
            PanelTwo = new JPanel();
            PanelTwo.add(LabelOne);
            PanelTwo.add(ScrollPaneTwo);
        
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setContentPane(Panel);
            Table.repaint();
        
            pack();
            
            
            
            this.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
public Vector getRowData (ResultSet Result) throws SQLException {

    ResultSetMetaData meta = Result.getMetaData();
    String[] colNames = new String[meta.getColumnCount()];
    Vector cells = new Vector();
    for( int col = 0; col < colNames.length; col++) {
        colNames[col] = meta.getColumnName(col + 1);
        cells.add(new Vector());
    }
    
    // hold data from result set
    while(Result.next()) {
        for(int col = 0; col < colNames.length; col++) {
            
            Object cell = Result.getObject(colNames[col]);
            ( (Vector)cells.elementAt(col)).add(cell);
        }
    }
    
    return cells;
}
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1210, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchResult().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
