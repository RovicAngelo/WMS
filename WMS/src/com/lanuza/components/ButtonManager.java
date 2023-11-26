package com.lanuza.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

public class ButtonManager extends JButton {
    private static final long serialVersionUID = 7024531932688928563L;
    private String iconName;
    private String tooltip;
    private ActionListener listener;
    private Rectangle bounds;
    private Color color;
    private Boolean focusPaint;
    private Border border;

    // Create button with icon, tooltiptext, action, bounds
    public ButtonManager(String iconName, Color color, String tooltip, ActionListener listener, Rectangle bounds,Boolean focusPaint,Border border) {
        super();      
        this.iconName = iconName;
        this.color = color;
        this.tooltip = tooltip;
        this.listener = listener;
        this.bounds = bounds;
        this.focusPaint = focusPaint;
        this.border = border;
        initializeButton();
    }

    // Create button with icon, tooltiptext, bounds
    public ButtonManager(String iconName,Color color, String tooltip, Rectangle bounds,Boolean focusPaint,Border border) {
        super();
        this.iconName = iconName;
        this.color = color;
        this.tooltip = tooltip;
        this.bounds = bounds;
        this.focusPaint = focusPaint;
        this.border = border;
        initializeButton();
    }

    // Create button with tooltiptext, bounds
    public ButtonManager(Color color,String tooltip, Rectangle bounds,Boolean focusPaint,Border border) {
        super();
        this.color = color;
        this.tooltip = tooltip;
        this.bounds = bounds;
        this.focusPaint = focusPaint;
        this.border = border;
        initializeButton();
    }

    private void initializeButton() {
        //set properties of the button  	
    	setIcon(new ImageIcon(getClass().getResource("/com/lanuza/icons/" + iconName)));
    	setBackground(color);
        setToolTipText(tooltip);
        setBounds(bounds);
        setFocusPainted(focusPaint);
        setBorder(border);
        // to add an action listener
        if (listener != null) {
            addActionListener(listener);
        }
    }
}
