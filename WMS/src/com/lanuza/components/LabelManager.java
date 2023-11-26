package com.lanuza.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;

public class LabelManager extends JLabel{
	private static final long serialVersionUID = 1L;
	private Color fgColor;
	private Font font;
	private Rectangle bound;
	private int horizontalAlignment;


    // Create label with fgcolor, font, bounds, horizontalAlignment parameters
    public LabelManager(Color fgColor,Font font, Rectangle bound, int horizontalAlignment) {
        super();
    	this.fgColor = fgColor;
    	this.font = font;
    	this.bound= bound;
    	this.horizontalAlignment = horizontalAlignment;    
        initializeLabel();
    }
    //create label with no defalut parameter
    public LabelManager() {
        super();      
        initializeLabel();
    }
    
    private void initializeLabel() {
        //set properties of the label  	
    	setForeground(fgColor);
    	setFont(font);
    	setBounds(bound);
    	setHorizontalAlignment(horizontalAlignment);//SwingConstant.CENTER as an example
    }
}
