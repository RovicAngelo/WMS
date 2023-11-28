package com.lanuza.wms.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class PanelManager extends JPanel{
	private static final long serialVersionUID = 1L;
	private Color bgColor;
	private Rectangle bounds;
	private LayoutManager layout;
	private Boolean isOpaque;

	//
	public PanelManager(Color bgColor,Rectangle bounds,LayoutManager layout,Boolean isOpaque) {
		super();
		this.bgColor = bgColor;
		this.bounds = bounds;
		this.layout = layout;
		this.isOpaque = isOpaque;
		initializaPanel();
	}
	public PanelManager() {
		super();
		initializaPanel();
	}
	
	
	private void initializaPanel() {
		setBackground(bgColor);
		setBounds(bounds);
		setLayout(layout);
		setOpaque(isOpaque);
		setPreferredSize(new Dimension(bounds.width, bounds.height));
	}
}
