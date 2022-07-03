package com.dale.imageprocessor.panels;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;

import javax.swing.JPanel;

import com.dale.imageprocessor.data.MyData;

public class EdittedImagePanel extends JPanel {
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double ratio = 0.0;
        int w = 0;
        int h = 0;
        if(MyData.isLoaded) {
        	System.out.println(MyData.copiedImage.getWidth() + " " +MyData.copiedImage.getHeight());
        	if(MyData.copiedImage.getWidth()<MyData.copiedImage.getHeight()) {
        		ratio = ((double)EdittedImagePanel.this.getHeight())/((double)MyData.copiedImage.getHeight());
                w = (int)(MyData.copiedImage.getWidth() * ratio);
                h = (int)(MyData.copiedImage.getHeight() * ratio);
                Image resizeImage = MyData.copiedImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);  
                g2.drawImage(resizeImage,MyData.copiedImage.getWidth()/2 - w/2,0,this);   
                System.out.println(MyData.copiedImage.getWidth()/2 - w/2);
            }
        	else {
        		g2.drawImage(MyData.copiedImage, 0, 0, EdittedImagePanel.this.getWidth(), EdittedImagePanel.this.getHeight(), this);
        	}
        	
      }
      
	}
}
