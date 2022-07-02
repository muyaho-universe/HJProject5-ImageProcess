package com.dale.imageprocessor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.dale.imageprocessor.data.MyData;
import com.dale.imageprocessor.panels.*;

public class MainFrame extends JFrame {
	private static final String TITLE = "이미지 편집기";
	static int monitorWidth;
	static int monitorHeight;
	
	JPanel mainPanel ;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem fileLoader;
	JMenuItem fileSaver;
	JFileChooser chooser;
	ImagePanel imagePanel;
	EdittedImagePanel edittedImagePanel;
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
	}
	MainFrame(){
		super(TITLE);
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		
		monitorWidth = resolution.width;
		monitorHeight = resolution.height;
		
		this.setSize(monitorWidth * 9 / 10, monitorHeight * 9 / 10);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	private void run() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		
		menuBar = new JMenuBar();
		menu = new JMenu("파일 탐색");
		fileLoader = new JMenuItem("파일 열기");
		fileSaver = new JMenuItem("파일 저장");
		
		imagePanel = new ImagePanel();
		imagePanel.setBounds(15, 150, monitorWidth * 81 / 200 , monitorHeight * 81 / 200);
		imagePanel.setBackground(Color.BLUE);
		
		edittedImagePanel = new EdittedImagePanel();
		edittedImagePanel.setBounds(monitorWidth * 81 / 200 + 40, 150, monitorWidth * 81 / 200 , monitorHeight * 81 / 200);
		edittedImagePanel.setBackground(Color.BLACK);
		
		fileLoader.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
				chooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
				
				chooser.setMultiSelectionEnabled(false);
				chooser.setVisible(true);
				int result = chooser.showOpenDialog(MainFrame.this);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						MyData.loadedBufferedImage = ImageIO.read(chooser.getSelectedFile());
						MyData.isLoaded = true;
						imagePanel.repaint();
						edittedImagePanel.repaint();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				    System.out.println(chooser.getSelectedFile());
				}
			}
		});
		
		fileSaver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("jpeg", "jpeg"));
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
				
				chooser.setMultiSelectionEnabled(false);
				chooser.setVisible(true);
				int result = chooser.showSaveDialog(MainFrame.this);
				
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = chooser.getSelectedFile();
				    
				    System.out.println(selectedFile);
				}
			}				
		});
		
		menu.add(fileSaver);
		menu.add(fileLoader);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		mainPanel.add(imagePanel);
		mainPanel.add(edittedImagePanel);
		
		this.add(mainPanel);
		this.setVisible(true);
	}

}
