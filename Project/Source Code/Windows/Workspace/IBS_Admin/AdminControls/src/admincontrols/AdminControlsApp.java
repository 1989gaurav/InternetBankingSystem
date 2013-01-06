/*
 * AdminControlsApp.java
 */

package admincontrols;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class AdminControlsApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new AdminControlsView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of AdminControlsApp
     */
    public static AdminControlsApp getApplication() {
        return Application.getInstance(AdminControlsApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        LoginScreen AdminLogin = new LoginScreen();
        //AdminLogin.setVisible(true);
        //if(AdminLogin.)
        launch(AdminControlsApp.class, args);
    }
}
