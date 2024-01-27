package com.lanuza.wms.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PlaceholderHandler {

    private Font mainFont;

    public PlaceholderHandler(Font mainFont) {
        this.mainFont = mainFont;
    }

    public void addPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    public void addPlaceholder(JPasswordField passwordField, String placeholder) {
        passwordField.setForeground(Color.GRAY);
        passwordField.setText(placeholder);
        passwordField.setEchoChar((char) 0); // Show actual characters

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] password = passwordField.getPassword();
                String passwordText = new String(password);

                if (passwordText.equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setEchoChar('●'); // Set the password echo character
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setFont(mainFont);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] password = passwordField.getPassword();
                String passwordText = new String(password);

                if (passwordText.isEmpty()) {
                    passwordField.setEchoChar((char) 0); // Show actual characters
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText(placeholder);
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char[] password = passwordField.getPassword();
                String passwordText = new String(password);

                if (passwordText.equals(placeholder)) {
                    passwordField.setEchoChar('●'); // Set the password echo character
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setFont(mainFont);
                }
            }
        });
    }
}

