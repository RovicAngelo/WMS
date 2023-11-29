package com.lanuza.wms.ui.components;

import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {
    private static final long serialVersionUID = 7024531932688928563L;
    private String text,tooltip;
    private ActionListener listener;
    private Rectangle bounds;
    private Color bgColor,fgColor;
    private Boolean focusPaint;
    private Border border;

    // Create button with text,icon,bgColor, tooltiptext, action, bounds, focus paint
    public CustomButton(String text, Color bgColor,Color fgColor, String tooltip, ActionListener listener, Rectangle bounds,Boolean focusPaint) {
        super();      
        this.text = text;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.tooltip = tooltip;
        this.listener = listener;
        this.bounds = bounds;
        this.focusPaint = focusPaint;
        initializeButton();
    }
    
    // Create button with icon,icon,bgColor, tooltiptext, action, bounds, focus paint, border
    public CustomButton( Color bgColor, String tooltip, ActionListener listener, Rectangle bounds,Boolean focusPaint,Border border) {
        super();      
      // this.iconName = iconName;
        this.bgColor = bgColor;
        this.tooltip = tooltip;
        this.listener = listener;
        this.bounds = bounds;
        this.focusPaint = focusPaint;
        this.border = border;
        initializeButton();
    }
    // Create button with icon, tooltiptext, bounds parameters
    public CustomButton(String text,Color bgColor,Color fgColor, String tooltip, Rectangle bounds,Boolean focusPaint) {
        super();
        this.text = text;      
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.tooltip = tooltip;
        this.bounds = bounds;
        this.focusPaint = focusPaint;

        initializeButton();
      //this.iconName = iconName;
    }

    //Create buttons with no default parameter
    public CustomButton() {
        super();
        initializeButton();
    }


    private void initializeButton() {
        //set properties of the button  	
    	setText(text);
    	//setIcon(new ImageIcon(getClass().getResource("/com/lanuza/wms/ui/resources/icons/" + iconName)));
    	setBackground(bgColor);
    	setForeground(fgColor);
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
