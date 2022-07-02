package com.dale.imageprocessor.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.dale.imageprocessor.data.MyData;

public class ImagePanel extends JPanel {
	public ImagePanel() {
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double ratio = 0.0;
        int w = 0;
        int h = 0;
        if(MyData.loadedBufferedImage != null) {
        	if(ImageProcessingFrame.image.getWidth()>ImageProcessingFrame.image.getHeight()) {
                ratio = ((double)ImageProcessingFrame.WIDTH/2)/((double)ImageProcessingFrame.image.getWidth());
                w = (int)(ImageProcessingFrame.image.getWidth() * ratio);
                h = (int)(ImageProcessingFrame.image.getHeight() * ratio);
             }else {
                ratio = ((double)ImageProcessingFrame.HEIGHT-100)/((double)ImageProcessingFrame.image.getHeight());
                w = (int)(ImageProcessingFrame.image.getWidth() * ratio);
                h = (int)(ImageProcessingFrame.image.getHeight() * ratio);
             }
             Image resizeImage = ImageProcessingFrame.image.getScaledInstance(w, h, Image.SCALE_SMOOTH);      
     		g.drawImage(resizeImage, 0, ((ImageProcessingFrame.HEIGHT-100)/2) - h/2, this);
        }

	}
}
