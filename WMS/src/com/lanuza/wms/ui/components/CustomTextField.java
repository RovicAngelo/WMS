package com.lanuza.wms.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JTextField;

public class CustomTextField extends JTextField{
	private static final long serialVersionUID = 1L;
		private Font font;
	    private Rectangle bounds;
	    private Color bgcolor,fgcolor;
	    private String tooltip;
	    private int column;
	    private Cursor cursor;
	    private Boolean isOpaque;
	    private Boolean isEditable;

	    // Create textfields with font, bounds, bgcolor, fbcolor,tooltip,column,cursor,opaque,editable parameters
	    public CustomTextField(Font font,Rectangle bounds, Color bgcolor,Color fgcolor, String tooltip,int column,Cursor cursor,Boolean isOpaque,Boolean isEditable) {
	        super();      
	        this.font = font;
	        this.bounds = bounds;
	        this.bgcolor = bgcolor;
	        this.fgcolor = fgcolor;	
	        this.tooltip = tooltip;
	        this.column = column;
	        this.cursor = cursor;
	        this.isOpaque = isOpaque;
	        this.isEditable = isEditable;
	        initializeTextField();
	    }
	    
	    // Create textfields with font, bounds, bgcolor, fbcolor,tooltip,column,cursor parameters
	    public CustomTextField(Font font,Rectangle bounds, Color bgcolor,Color fgcolor, String tooltip,int column,Cursor cursor) {
	        super();      
	        this.font = font;
	        this.bounds = bounds;
	        this.bgcolor = bgcolor;
	        this.fgcolor = fgcolor;	
	        this.tooltip = tooltip;
	        this.column = column;
	        this.cursor = cursor;
	        initializeTextField();
	    }
	    //create textfield with no defalut parameter
	    public CustomTextField() {
	        super();      
	        initializeTextField();
	    }
	    
	    private void initializeTextField() {
	        //set properties of the textfields  	
	    	setFont(font);
	    	setBounds(bounds);
	    	setBackground(bgcolor);
	    	setForeground(fgcolor);
	        setToolTipText(tooltip);
	        setColumns(column);
	        setCursor(cursor);
	        setOpaque(isOpaque);
	        setEditable(isEditable);
	    }
}
