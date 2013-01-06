/*
 * AdminControlsView.java
 */

package admincontrols;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.FrameView;

/**
 * The application's main frame.
 */
public class AdminControlsView extends FrameView {

    String Username="karanjain";
     DBConnect DBC;
     Connection DBConn;
     Statement   DBStmt;
    private ResultSet Result;
    public AdminControlsView(SingleFrameApplication app) {
        super(app);

        initComponents();
        SetMyProfile("karanjain");

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = AdminControlsApp.getApplication().getMainFrame();
            aboutBox = new AdminControlsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        AdminControlsApp.getApplication().show(aboutBox);
    }

    private void SetMyProfile(String MyUser) {
        
        int userid=0;
        
        DBConnect DBC = new DBConnect();
        Connection c;
        ResultSet Result;
        Statement S;
        c = DBC.getDBConnection();
        S = DBC.CreateQueryStatement(c);

        
        try {
            
            String query = "SELECT * FROM ADMINISTRATOR.ALOGININFO where Administrator.alogininfo.username='" + MyUser + "'";
            Result = DBC.ExecuteQuery(query, S, true);
            
            if (Result.next()) {
                userid = Result.getInt("userID");
                DefaultAccountField.setText(Result.getString("defaultAccount"));
                
                Date Day = Result.getDate("lastloginDate");
                Time T = Result.getTime("lastlogintime");

                LoginDate.setText(Day.getDate()+"/"+Day.getMonth()+"/"+Day.getYear());
                LoginTime.setText(Integer.toString(T.getHours())+":"+Integer.toString(T.getMinutes())+":"+Integer.toString(T.getSeconds()));
                
            }
            else {
               JOptionPane JP = new JOptionPane();
               JP.showMessageDialog(null,"Userid not found","ERROR",0);
               System.exit(-1);
            }
        
            query = "select * from Administrator.myprofile where Administrator.myprofile.userid="+userid;
            Result=DBC.ExecuteQuery(query, S, true);
            
            if(Result.next()) {
                UserNameField.setText(MyUser);
                NameField.setText(Result.getString(2));
                AdressField.setText(Result.getString(3));
                PhoneField.setText(Result.getString(4));
                EmailField.setText(Result.getString(5));
                IDProof.setText(Result.getString(6));
            }
                
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminControlsView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        MainFrame = new javax.swing.JTabbedPane();
        ViewProfile = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        UserNameField = new javax.swing.JTextField();
        AdressField = new javax.swing.JTextField();
        PhoneField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        IDProof = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        DefaultAccountField = new javax.swing.JTextField();
        LoginDate = new javax.swing.JTextField();
        LoginTime = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        ViewProfileButton = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        ViewProfileUsername = new javax.swing.JTextField();
        UserType = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        AddressButton = new javax.swing.JButton();
        PhoneButton = new javax.swing.JButton();
        EmailButton = new javax.swing.JButton();
        Accounts = new javax.swing.JTabbedPane();
        BlockAccount = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BlockIDField = new javax.swing.JTextField();
        Reason = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        UnblockAccount = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        UnBlockIDField = new javax.swing.JTextField();
        Comment = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ResetPasswordPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        ProfileUsername = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        NewPassword = new javax.swing.JPasswordField();
        RetypePassword = new javax.swing.JPasswordField();
        ResetPassword = new javax.swing.JButton();
        ResetPasswordFields = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        TransactionUsername = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        NewTrPassword = new javax.swing.JPasswordField();
        RetypeTrPassword = new javax.swing.JPasswordField();
        ResetTrPassword = new javax.swing.JButton();
        ResetTPassField = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        AddBranch = new javax.swing.JTabbedPane();
        ChangeDraftStatus = new javax.swing.JTabbedPane();
        Help = new javax.swing.JTabbedPane();
        Utilities = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        AccountNum = new javax.swing.JTextField();
        TransactionID = new javax.swing.JTextField();
        SearchName = new javax.swing.JButton();
        SearchAccountNum = new javax.swing.JButton();
        SearchTransaction = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        QueryField = new javax.swing.JTextField();
        QueryType = new javax.swing.JComboBox();
        ExecuteQuery = new javax.swing.JButton();
        AccountType = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        MainFrame.setName("MainFrame"); // NOI18N

        ViewProfile.setName("ViewProfile"); // NOI18N
        ViewProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewProfileMouseClicked(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(admincontrols.AdminControlsApp.class).getContext().getResourceMap(AdminControlsView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        NameField.setEditable(false);
        NameField.setText(resourceMap.getString("NameField.text")); // NOI18N
        NameField.setName("NameField"); // NOI18N

        UserNameField.setEditable(false);
        UserNameField.setName("UserNameField"); // NOI18N

        AdressField.setName("AdressField"); // NOI18N

        PhoneField.setName("PhoneField"); // NOI18N

        EmailField.setName("EmailField"); // NOI18N

        IDProof.setEditable(false);
        IDProof.setName("IDProof"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        DefaultAccountField.setEditable(false);
        DefaultAccountField.setName("DefaultAccountField"); // NOI18N

        LoginDate.setEditable(false);
        LoginDate.setName("LoginDate"); // NOI18N

        LoginTime.setEditable(false);
        LoginTime.setName("LoginTime"); // NOI18N

        jLabel28.setFont(resourceMap.getFont("jLabel28.font")); // NOI18N
        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        ViewProfileButton.setText(resourceMap.getString("ViewProfileButton.text")); // NOI18N
        ViewProfileButton.setName("ViewProfileButton"); // NOI18N
        ViewProfileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ViewProfileButtonMouseReleased(evt);
            }
        });

        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        ViewProfileUsername.setText(resourceMap.getString("ViewProfileUsername.text")); // NOI18N
        ViewProfileUsername.setName("ViewProfileUsername"); // NOI18N

        UserType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Personal", "Coorporate" }));
        UserType.setName("UserType"); // NOI18N

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        AddressButton.setText(resourceMap.getString("AddressButton.text")); // NOI18N
        AddressButton.setName("AddressButton"); // NOI18N
        AddressButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AddressButtonMouseReleased(evt);
            }
        });

        PhoneButton.setText(resourceMap.getString("PhoneButton.text")); // NOI18N
        PhoneButton.setName("PhoneButton"); // NOI18N
        PhoneButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PhoneButtonMouseReleased(evt);
            }
        });

        EmailButton.setText(resourceMap.getString("EmailButton.text")); // NOI18N
        EmailButton.setName("EmailButton"); // NOI18N
        EmailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                EmailButtonMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout ViewProfileLayout = new javax.swing.GroupLayout(ViewProfile);
        ViewProfile.setLayout(ViewProfileLayout);
        ViewProfileLayout.setHorizontalGroup(
            ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewProfileLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewProfileLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefaultAccountField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ViewProfileLayout.createSequentialGroup()
                        .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(41, 41, 41)
                        .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ViewProfileLayout.createSequentialGroup()
                                .addComponent(AdressField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddressButton))
                            .addGroup(ViewProfileLayout.createSequentialGroup()
                                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UserNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PhoneButton)
                                    .addComponent(EmailButton))
                                .addGap(24, 24, 24)
                                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewProfileLayout.createSequentialGroup()
                                        .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(ViewProfileLayout.createSequentialGroup()
                                                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel29)
                                                    .addComponent(jLabel30)
                                                    .addComponent(jLabel7))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addComponent(jLabel8))
                                        .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(ViewProfileLayout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(ViewProfileButton)
                                                    .addComponent(UserType, 0, 103, Short.MAX_VALUE)
                                                    .addComponent(ViewProfileUsername)))
                                            .addGroup(ViewProfileLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(LoginTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(LoginDate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jLabel28)))
                            .addComponent(IDProof, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ViewProfileLayout.setVerticalGroup(
            ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewProfileLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(LoginDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UserNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(LoginTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(AdressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressButton))
                .addGap(18, 18, 18)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(PhoneButton))
                .addGap(18, 18, 18)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(ViewProfileUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmailButton))
                .addGap(18, 18, 18)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(IDProof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(UserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ViewProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(DefaultAccountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewProfileButton))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        MainFrame.addTab(resourceMap.getString("ViewProfile.TabConstraints.tabTitle"), ViewProfile); // NOI18N

        Accounts.setName("Accounts"); // NOI18N

        BlockAccount.setName("BlockAccount"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        BlockIDField.setText(resourceMap.getString("BlockIDField.text")); // NOI18N
        BlockIDField.setName("BlockIDField"); // NOI18N

        Reason.setText(resourceMap.getString("Reason.text")); // NOI18N
        Reason.setName("Reason"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout BlockAccountLayout = new javax.swing.GroupLayout(BlockAccount);
        BlockAccount.setLayout(BlockAccountLayout);
        BlockAccountLayout.setHorizontalGroup(
            BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockAccountLayout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(41, 41, 41)
                .addGroup(BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BlockAccountLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(Reason)
                    .addComponent(BlockIDField, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addGap(388, 388, 388))
        );
        BlockAccountLayout.setVerticalGroup(
            BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockAccountLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BlockIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(35, 35, 35)
                .addGroup(BlockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        Accounts.addTab(resourceMap.getString("BlockAccount.TabConstraints.tabTitle"), BlockAccount); // NOI18N

        UnblockAccount.setName("UnblockAccount"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        UnBlockIDField.setName("UnBlockIDField"); // NOI18N

        Comment.setName("Comment"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout UnblockAccountLayout = new javax.swing.GroupLayout(UnblockAccount);
        UnblockAccount.setLayout(UnblockAccountLayout);
        UnblockAccountLayout.setHorizontalGroup(
            UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UnblockAccountLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGroup(UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UnblockAccountLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(UnBlockIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, UnblockAccountLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UnblockAccountLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addComponent(Comment))))
                .addGap(383, 383, 383))
        );
        UnblockAccountLayout.setVerticalGroup(
            UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UnblockAccountLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(UnBlockIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Comment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(UnblockAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        Accounts.addTab(resourceMap.getString("UnblockAccount.TabConstraints.tabTitle"), UnblockAccount); // NOI18N

        ResetPasswordPanel.setName("ResetPasswordPanel"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        ProfileUsername.setText(resourceMap.getString("ProfileUsername.text")); // NOI18N
        ProfileUsername.setName("ProfileUsername"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        NewPassword.setText(resourceMap.getString("NewPassword.text")); // NOI18N
        NewPassword.setName("NewPassword"); // NOI18N

        RetypePassword.setText(resourceMap.getString("RetypePassword.text")); // NOI18N
        RetypePassword.setName("RetypePassword"); // NOI18N

        ResetPassword.setText(resourceMap.getString("ResetPassword.text")); // NOI18N
        ResetPassword.setName("ResetPassword"); // NOI18N
        ResetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResetPasswordMouseReleased(evt);
            }
        });

        ResetPasswordFields.setText(resourceMap.getString("ResetPasswordFields.text")); // NOI18N
        ResetPasswordFields.setName("ResetPasswordFields"); // NOI18N
        ResetPasswordFields.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResetPasswordFieldsMouseReleased(evt);
            }
        });

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        TransactionUsername.setName("TransactionUsername"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        NewTrPassword.setName("NewTrPassword"); // NOI18N

        RetypeTrPassword.setName("RetypeTrPassword"); // NOI18N

        ResetTrPassword.setText(resourceMap.getString("ResetTrPassword.text")); // NOI18N
        ResetTrPassword.setName("ResetTrPassword"); // NOI18N
        ResetTrPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResetTrPasswordMouseReleased(evt);
            }
        });

        ResetTPassField.setText(resourceMap.getString("ResetTPassField.text")); // NOI18N
        ResetTPassField.setName("ResetTPassField"); // NOI18N
        ResetTPassField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ResetTPassFieldMouseReleased(evt);
            }
        });

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        javax.swing.GroupLayout ResetPasswordPanelLayout = new javax.swing.GroupLayout(ResetPasswordPanel);
        ResetPasswordPanel.setLayout(ResetPasswordPanelLayout);
        ResetPasswordPanelLayout.setHorizontalGroup(
            ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResetPasswordPanelLayout.createSequentialGroup()
                .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ResetPasswordPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel14))
                                .addGap(28, 28, 28)
                                .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ProfileUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(NewPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(RetypePassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                            .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ResetPasswordFields)
                                .addGap(18, 18, 18)
                                .addComponent(ResetPassword)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE))
                    .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addGap(289, 289, 289)))
                .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResetPasswordPanelLayout.createSequentialGroup()
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(RetypeTrPassword)
                            .addComponent(TransactionUsername)
                            .addComponent(NewTrPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResetPasswordPanelLayout.createSequentialGroup()
                        .addComponent(ResetTPassField)
                        .addGap(16, 16, 16)
                        .addComponent(ResetTrPassword)
                        .addGap(54, 54, 54))))
        );

        ResetPasswordPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {NewPassword, NewTrPassword, ProfileUsername, RetypePassword, RetypeTrPassword, TransactionUsername});

        ResetPasswordPanelLayout.setVerticalGroup(
            ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(29, 29, 29)
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ProfileUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(NewTrPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RetypePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(RetypeTrPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(30, 30, 30)
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ResetPassword)
                            .addComponent(ResetPasswordFields)
                            .addComponent(ResetTrPassword)
                            .addComponent(ResetTPassField)))
                    .addGroup(ResetPasswordPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(ResetPasswordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TransactionUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        Accounts.addTab(resourceMap.getString("ResetPasswordPanel.TabConstraints.tabTitle"), ResetPasswordPanel); // NOI18N

        MainFrame.addTab(resourceMap.getString("Accounts.TabConstraints.tabTitle"), Accounts); // NOI18N

        AddBranch.setName("AddBranch"); // NOI18N
        MainFrame.addTab(resourceMap.getString("AddBranch.TabConstraints.tabTitle"), AddBranch); // NOI18N

        ChangeDraftStatus.setName("ChangeDraftStatus"); // NOI18N
        MainFrame.addTab(resourceMap.getString("ChangeDraftStatus.TabConstraints.tabTitle"), ChangeDraftStatus); // NOI18N

        Help.setName("Help"); // NOI18N
        MainFrame.addTab(resourceMap.getString("Help.TabConstraints.tabTitle"), Help); // NOI18N

        Utilities.setName("Utilities"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        Name.setText(resourceMap.getString("Name.text")); // NOI18N
        Name.setName("Name"); // NOI18N

        AccountNum.setText(resourceMap.getString("AccountNum.text")); // NOI18N
        AccountNum.setName("AccountNum"); // NOI18N

        TransactionID.setText(resourceMap.getString("TransactionID.text")); // NOI18N
        TransactionID.setName("TransactionID"); // NOI18N

        SearchName.setText(resourceMap.getString("SearchName.text")); // NOI18N
        SearchName.setName("SearchName"); // NOI18N
        SearchName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SearchNameMouseReleased(evt);
            }
        });

        SearchAccountNum.setText(resourceMap.getString("SearchAccountNum.text")); // NOI18N
        SearchAccountNum.setName("SearchAccountNum"); // NOI18N
        SearchAccountNum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SearchAccountNumMouseReleased(evt);
            }
        });

        SearchTransaction.setText(resourceMap.getString("SearchTransaction.text")); // NOI18N
        SearchTransaction.setName("SearchTransaction"); // NOI18N
        SearchTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SearchTransactionMouseReleased(evt);
            }
        });

        jLabel26.setFont(resourceMap.getFont("jLabel26.font")); // NOI18N
        jLabel26.setText(resourceMap.getString("jLabel26.text")); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        QueryField.setText(resourceMap.getString("QueryField.text")); // NOI18N
        QueryField.setName("QueryField"); // NOI18N

        QueryType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Search", "Update" }));
        QueryType.setName("QueryType"); // NOI18N

        ExecuteQuery.setText(resourceMap.getString("ExecuteQuery.text")); // NOI18N
        ExecuteQuery.setName("ExecuteQuery"); // NOI18N
        ExecuteQuery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExecuteQueryMouseReleased(evt);
            }
        });

        AccountType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Money Account", "Loan Account" }));
        AccountType.setName("AccountType"); // NOI18N

        javax.swing.GroupLayout UtilitiesLayout = new javax.swing.GroupLayout(Utilities);
        Utilities.setLayout(UtilitiesLayout);
        UtilitiesLayout.setHorizontalGroup(
            UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtilitiesLayout.createSequentialGroup()
                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UtilitiesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22))
                    .addGroup(UtilitiesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26))
                    .addGroup(UtilitiesLayout.createSequentialGroup()
                        .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UtilitiesLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)))
                            .addGroup(UtilitiesLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel27)))
                        .addGap(29, 29, 29)
                        .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QueryField, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(UtilitiesLayout.createSequentialGroup()
                                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TransactionID)
                                    .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addGroup(UtilitiesLayout.createSequentialGroup()
                                        .addComponent(AccountNum, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39)
                                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SearchAccountNum)
                                    .addComponent(SearchTransaction)
                                    .addComponent(SearchName)))
                            .addGroup(UtilitiesLayout.createSequentialGroup()
                                .addComponent(QueryType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(ExecuteQuery)))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        UtilitiesLayout.setVerticalGroup(
            UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UtilitiesLayout.createSequentialGroup()
                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UtilitiesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(SearchAccountNum)
                            .addComponent(AccountNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(TransactionID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchTransaction))
                        .addGap(42, 42, 42)
                        .addComponent(jLabel26))
                    .addGroup(UtilitiesLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(SearchName)))
                .addGap(41, 41, 41)
                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(QueryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UtilitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueryType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExecuteQuery))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        MainFrame.addTab(resourceMap.getString("Utilities.TabConstraints.tabTitle"), Utilities); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(admincontrols.AdminControlsApp.class).getContext().getActionMap(AdminControlsView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 674, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        // TODO add your handling code here:
        String userid = null;
        String reason = null;
        
        userid = BlockIDField.getText();
        reason= Reason.getText();
        
        BlockUnBlockAccount BUAcc = new BlockUnBlockAccount();
        BUAcc.BlockAccount(userid, reason);
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
        // TODO add your handling code here:
        String userid = null;
        String reason = null;
        
        userid = UnBlockIDField.getText();
        reason= Comment.getText();
        
        BlockUnBlockAccount BUAcc = new BlockUnBlockAccount();
        BUAcc.UnBlockAccount(userid, reason);
    }//GEN-LAST:event_jButton3MouseReleased

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        // TODO add your handling code here:
        UnBlockIDField.setText("");
        Comment.setText("");
    }//GEN-LAST:event_jButton4MouseReleased

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        // TODO add your handling code here:
        BlockIDField.setText("");
        Reason.setText("");
    }//GEN-LAST:event_jButton2MouseReleased

    private void ResetPasswordMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetPasswordMouseReleased
        // TODO add your handling code here:
        
        String userid = null;
        String PasswordFieldOne = null;
        String PasswordFieldTwo = null;
        
        userid = ProfileUsername.getText();
        PasswordFieldOne= NewPassword.getText();
        PasswordFieldTwo= RetypePassword.getText();
        if(PasswordFieldOne.equalsIgnoreCase(PasswordFieldTwo))
        {
            BlockUnBlockAccount BUAcc = new BlockUnBlockAccount();
            BUAcc.ResetProfilePassword(userid, PasswordFieldOne);
        }
        else
        {
            //error msg
        }
        
}//GEN-LAST:event_ResetPasswordMouseReleased

    private void ResetPasswordFieldsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetPasswordFieldsMouseReleased
        // TODO add your handling code here:
        
        ProfileUsername.setText("");
        NewPassword.setText("");
        RetypePassword.setText("");
}//GEN-LAST:event_ResetPasswordFieldsMouseReleased

    private void ResetTrPasswordMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetTrPasswordMouseReleased
        // TODO add your handling code here:
        
        String userid = null;
        String PasswordFieldOne = null;
        String PasswordFieldTwo = null;
        
        userid = TransactionUsername.getText();
        PasswordFieldOne= NewTrPassword.getText();
        PasswordFieldTwo= RetypeTrPassword.getText();
        if(PasswordFieldOne.equalsIgnoreCase(PasswordFieldTwo))
        {
            BlockUnBlockAccount BUAcc = new BlockUnBlockAccount();
            BUAcc.ResetTransactionPassword(userid, PasswordFieldOne);
        }
        else
        {
            //error msg
        }
}//GEN-LAST:event_ResetTrPasswordMouseReleased

    private void ResetTPassFieldMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetTPassFieldMouseReleased
        // TODO add your handling code here:
}//GEN-LAST:event_ResetTPassFieldMouseReleased

    private void SearchNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchNameMouseReleased
        // TODO add your handling code here:
        String name =Name.getText();
        if(name.equals(""))
        {
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(null,"Search String can not be empty !!","Error!",1);
        }
        else
        {
            Search SearchForName = new Search();
            SearchForName.SearchByName(name);
        }
    }//GEN-LAST:event_SearchNameMouseReleased

    private void SearchAccountNumMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchAccountNumMouseReleased
        // TODO add your handling code here:
        String name =AccountNum.getText();
        int Type = AccountType.getSelectedIndex();
        if(name.equals(""))
        {
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Search String can not be empty !!","Error",1);
        }
        else
        {
            Search AccountSearch = new Search();
            AccountSearch.SearchByAccounts(name,Type);
        }
    }//GEN-LAST:event_SearchAccountNumMouseReleased

    private void SearchTransactionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchTransactionMouseReleased
        // TODO add your handling code here:
        String Tid =TransactionID.getText();
        if(Tid.equals(""))
        {
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(null,"Search String can not be empty !!","Error!",1);
        }
        else
        {
            Search TransactionSearch = new Search();
            TransactionSearch.SearchByTransaction(Tid);
        }
    }//GEN-LAST:event_SearchTransactionMouseReleased

    private void ViewProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewProfileMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewProfileMouseClicked

    private void ExecuteQueryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExecuteQueryMouseReleased
        // TODO add your handling code here:
        String InputQuery;
        int Type;
        InputQuery=QueryField.getText();
        Type=QueryType.getSelectedIndex();
        
        if(InputQuery.equals("")){
            JOptionPane Message = new JOptionPane();
            JOptionPane.showMessageDialog(null,"Query String can not be empty !!","Error!",1);
        }
        else{
            Search QuerySearch = new Search();
            QuerySearch.QueryOutput(InputQuery,Type);
        }
    }//GEN-LAST:event_ExecuteQueryMouseReleased

    private void ViewProfileButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewProfileButtonMouseReleased
        // TODO add your handling code here:
        Username = ViewProfileUsername.getText();
        if(Username.equals("")){
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog(null,"Username field can not be empty !!","Error!",1);
        }
        else
        {
            int Type = UserType.getSelectedIndex();
            ViewProfile ViewUserProfile = new ViewProfile(Username);
            if( ViewUserProfile.SetMyProfile(Type) ==0)
                ViewUserProfile.setVisible(true);
        }
    }//GEN-LAST:event_ViewProfileButtonMouseReleased

    private void AddressButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddressButtonMouseReleased
        // TODO add your handling code here:
        String Address = AdressField.getText();
        String query = null;
        int userid;
        
        if(Address.equals("")){
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog(null,"Adress field can not be empty !!","Error!",1);
        }
        else{
            try {
                DBC = new DBConnect();
                DBConn = DBC.getDBConnection();
                DBStmt = DBC.CreateQueryStatement(DBConn);
                                
                query = "SELECT * FROM ADMINISTRATOR.ALOGININFO where Administrator.alogininfo.username='" + Username + "'";
                
                Result = DBC.ExecuteQuery(query, DBStmt, true);

                Result.next();
                userid = Result.getInt("userID");
                String Query = "UPDATE ADMINISTRATOR.MYPROFILE SET ADMINISTRATOR.MYPROFILE.MYADDRESS='"+ Address +"' WHERE ADMINISTRATOR.MYPROFILE.USERID=" + userid;
                
                DBConn.close();
                
                JOptionPane JP = new JOptionPane();
                JP.showMessageDialog(null,"Adress updated successfully\n It will be visible next time","Info",1);
                
            } catch (SQLException ex) {
                Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AddressButtonMouseReleased

    private void PhoneButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhoneButtonMouseReleased
        // TODO add your handling code here:
        String Phone = PhoneField.getText();
        String query = null;
        int userid;
        
        if(Phone.equals("")){
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog(null,"Adress field can not be empty !!","Error!",1);
        }
        else{
            try {
                DBC = new DBConnect();
                DBConn = DBC.getDBConnection();
                DBStmt = DBC.CreateQueryStatement(DBConn);
                
                query = "SELECT * FROM ADMINISTRATOR.ALOGININFO where Administrator.alogininfo.username='" + Username + "'";
                
                Result = DBC.ExecuteQuery(query, DBStmt, true);

                Result.next();
                userid = Result.getInt("userID");
                String Query = "UPDATE ADMINISTRATOR.MYPROFILE SET ADMINISTRATOR.MYPROFILE.myPhone='"+ Phone +"' WHERE ADMINISTRATOR.MYPROFILE.USERID=" + userid;
                
                DBConn.close();
                
                JOptionPane JP = new JOptionPane();
                JP.showMessageDialog(null,"Phone number updated successfully\n It will be visible next time","Info",1);
                
            } catch (SQLException ex) {
                Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_PhoneButtonMouseReleased

    private void EmailButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmailButtonMouseReleased
        // TODO add your handling code here:
        
        String Email = EmailField.getText();
        String query = null;
        int userid;
        
        if(Email.equals("")){
            JOptionPane pane = new JOptionPane();
            pane.showMessageDialog(null,"Adress field can not be empty !!","Error!",1);
        }
        else{
            try {
                DBC = new DBConnect();
                DBConn = DBC.getDBConnection();
                DBStmt = DBC.CreateQueryStatement(DBConn);
                
                query = "SELECT * FROM ADMINISTRATOR.CLOGININFO where Administrator.clogininfo.username='" + Username + "'";
               
                Result = DBC.ExecuteQuery(query, DBStmt, true);

                Result.next();
                userid = Result.getInt("userID");
                String Query = "UPDATE ADMINISTRATOR.MYPROFILE SET ADMINISTRATOR.MYPROFILE.mail='"+ Email +"' WHERE ADMINISTRATOR.MYPROFILE.USERID=" + userid;
                
                DBConn.close();
                
                JOptionPane JP = new JOptionPane();
                JP.showMessageDialog(null,"Email-ID updated successfully\n It will be visible next time","Info",1);
                
            } catch (SQLException ex) {
                Logger.getLogger(ViewProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_EmailButtonMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AccountNum;
    private javax.swing.JComboBox AccountType;
    private javax.swing.JTabbedPane Accounts;
    private javax.swing.JTabbedPane AddBranch;
    private javax.swing.JButton AddressButton;
    private javax.swing.JTextField AdressField;
    private javax.swing.JPanel BlockAccount;
    private javax.swing.JTextField BlockIDField;
    private javax.swing.JTabbedPane ChangeDraftStatus;
    private javax.swing.JTextField Comment;
    private javax.swing.JTextField DefaultAccountField;
    private javax.swing.JButton EmailButton;
    private javax.swing.JTextField EmailField;
    private javax.swing.JButton ExecuteQuery;
    private javax.swing.JTabbedPane Help;
    private javax.swing.JTextField IDProof;
    private javax.swing.JTextField LoginDate;
    private javax.swing.JTextField LoginTime;
    private javax.swing.JTabbedPane MainFrame;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField NameField;
    private javax.swing.JPasswordField NewPassword;
    private javax.swing.JPasswordField NewTrPassword;
    private javax.swing.JButton PhoneButton;
    private javax.swing.JTextField PhoneField;
    private javax.swing.JTextField ProfileUsername;
    private javax.swing.JTextField QueryField;
    private javax.swing.JComboBox QueryType;
    private javax.swing.JTextField Reason;
    private javax.swing.JButton ResetPassword;
    private javax.swing.JButton ResetPasswordFields;
    private javax.swing.JPanel ResetPasswordPanel;
    private javax.swing.JButton ResetTPassField;
    private javax.swing.JButton ResetTrPassword;
    private javax.swing.JPasswordField RetypePassword;
    private javax.swing.JPasswordField RetypeTrPassword;
    private javax.swing.JButton SearchAccountNum;
    private javax.swing.JButton SearchName;
    private javax.swing.JButton SearchTransaction;
    private javax.swing.JTextField TransactionID;
    private javax.swing.JTextField TransactionUsername;
    private javax.swing.JTextField UnBlockIDField;
    private javax.swing.JPanel UnblockAccount;
    private javax.swing.JTextField UserNameField;
    private javax.swing.JComboBox UserType;
    private javax.swing.JPanel Utilities;
    private javax.swing.JPanel ViewProfile;
    private javax.swing.JButton ViewProfileButton;
    private javax.swing.JTextField ViewProfileUsername;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
