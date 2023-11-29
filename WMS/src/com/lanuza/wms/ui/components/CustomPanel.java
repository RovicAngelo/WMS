package com.lanuza.wms.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class CustomPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Color bgColor;
	private Rectangle bounds;
	private LayoutManager layout;
	private Boolean isOpaque;
	private Boolean isVisible;
	private Border border;

	//
	public CustomPanel(Color bgColor,Rectangle bounds,LayoutManager layout,Boolean isOpaque,Boolean isVisible,Border border) {
		super();
		this.bgColor = bgColor;
		this.bounds = bounds;
		this.layout = layout;
		this.isOpaque = isOpaque;
		this.isVisible = isVisible;
		this.border = border;
		initializaPanel();
	}
	public CustomPanel() {
		super();
		initializaPanel();
	}
	
	
	private void initializaPanel() {
		setBackground(bgColor);
		setBounds(bounds);
		setLayout(layout);
		setOpaque(isOpaque);
		setVisible(isVisible);
		setPreferredSize(new Dimension(bounds.width, bounds.height));
		setBorder(border);
	}
}
