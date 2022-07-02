package com.dale.imageprocessor;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
	}
	
	private void run() {
		this.setSize(1280, 720);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

}
