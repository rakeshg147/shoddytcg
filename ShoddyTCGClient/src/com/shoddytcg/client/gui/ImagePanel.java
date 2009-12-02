package com.shoddytcg.client.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
	private Image backgroundImage;

    public ImagePanel(Image backgroundImage) {
        super();
        this.backgroundImage = backgroundImage;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        g.drawImage(this.backgroundImage, 0, 0, null);
    }
}