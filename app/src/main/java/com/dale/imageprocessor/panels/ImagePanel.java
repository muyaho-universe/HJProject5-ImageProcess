package com.dale.imageprocessor.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.dale.imageprocessor.data.MyData;

public class ImagePanel extends JPanel {
	
	
	public ImagePanel() {
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double ratio = 0.0;
        int w = 0;
        int h = 0;
        if(MyData.isLoaded) {
        	System.out.println(MyData.loadedBufferedImage.getWidth() + " " +MyData.loadedBufferedImage.getHeight());
        	if(MyData.loadedBufferedImage.getWidth()<MyData.loadedBufferedImage.getHeight()) {
        		ratio = ((double)ImagePanel.this.getHeight())/((double)MyData.loadedBufferedImage.getHeight());
                w = (int)(MyData.loadedBufferedImage.getWidth() * ratio);
                h = (int)(MyData.loadedBufferedImage.getHeight() * ratio);
                Image resizeImage = MyData.loadedBufferedImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);  
                g2.drawImage(resizeImage,MyData.loadedBufferedImage.getWidth()/2 - w/2,0,this);   
                System.out.println(MyData.loadedBufferedImage.getWidth()/2 - w/2);
            }
        	else {
        		g2.drawImage(MyData.loadedBufferedImage, 0, 0, ImagePanel.this.getWidth(), ImagePanel.this.getHeight(), this);
        	}
        	    
        }

	}
}
