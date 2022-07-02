package com.dale.imageprocessor.panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.dale.imageprocessor.data.MyData;

public class EdittedImagePanel extends JPanel {
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(MyData.isLoaded) {
			g.drawImage(MyData.loadedImage, 0, 0, 640, 360, this);
		}
	}

}
