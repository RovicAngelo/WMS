package com.lanuza.wms.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverEffect extends MouseAdapter {
    private final JComponent component;
    private final Color originalBackground;
    private final Color hoverBackground;

    public HoverEffect(JComponent component,Color hoverColor, Color originalColor) {
        this.component = component;
        this.originalBackground = originalColor;
        this.hoverBackground = hoverColor;
        component.addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(hoverBackground);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(originalBackground);
    }
}

