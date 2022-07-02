package com.dale.imageprocessor;

import java.awt.Color;

import javax.swing.*;

public class MainFrame extends JFrame {
	JPanel mainPanel ;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem fileLoader;
	JMenuItem fileSaver;
	
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
	}
	
	private void run() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		
		menuBar = new JMenuBar();
		menu = new JMenu("파일 설정");
		fileLoader = new JMenuItem("파일 불러오기");
		fileSaver = new JMenuItem("파일 저장하기");
		
		menu.add(fileSaver);
		menu.add(fileLoader);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		this.add(mainPanel);
		this.setSize(1280, 720);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

}
