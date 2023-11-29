package com.lanuza.wms.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class CustomLabel extends JLabel{
	private static final long serialVersionUID = 1L;
	private String text;
	private Color fgColor;
	private Font font;
	private Rectangle bound;
	private int horizontalAlignment;


    // Create label with text, fgcolor, font, bounds, horizontalAlignment parameters
    public CustomLabel(String text,Color fgColor,Font font, Rectangle bound, int horizontalAlignment) {
        super();
        this.text=text;
    	this.fgColor = fgColor;
    	this.font = font;
    	this.bound= bound;
    	this.horizontalAlignment = horizontalAlignment;    
        initializeLabel();
    }
    //create label with no defalut parameter
    public CustomLabel() {
        super();      
        initializeLabel();
    }
    
    private void initializeLabel() {
        //set properties of the label  
    	setText(text);
    	setForeground(fgColor);
    	setFont(font);
    	setBounds(bound);
    	setHorizontalAlignment(horizontalAlignment);//SwingConstant.CENTER as an example
    }
}
