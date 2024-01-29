package com.lanuza.wms.ui.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.border.Border;

public class HoverEffect extends MouseAdapter {
    private final JComponent component;
    private Color originalBackground;
    private Color hoverBackground;
    
    private Border originalBorder;   
    private Border hoverBorder;

    public HoverEffect(JComponent component, Color hoverBackground, Color originalBackground) {
        this.component = component;
        this.hoverBackground = hoverBackground;
        this.originalBackground = originalBackground;
        component.addMouseListener(this);
    }
    
    public HoverEffect(JComponent component, Border originalBorder, Border hoverBorder) {
        this.component = component;
        this.originalBorder = originalBorder;
        this.hoverBorder = hoverBorder;
        component.addMouseListener(this);
    }      

    @Override
    public void mouseEntered(MouseEvent e) {
        if (hoverBackground != null) {
            component.setBackground(hoverBackground);
        }
        if (hoverBorder != null) {
            component.setBorder(hoverBorder);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (originalBackground != null) {
            component.setBackground(originalBackground);
        }
        if (originalBorder != null) {
            component.setBorder(originalBorder);
        }
    }
}
