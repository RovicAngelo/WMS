package com.lanuza.wms.Main;

import javax.swing.SwingUtilities;

import com.lanuza.wms.ui.form.LoginForm;

public class Main {
  
    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ManageAllForm().setVisible(true);
            	//new Dashboard();
            	new LoginForm();
            }
        });
    }
}

