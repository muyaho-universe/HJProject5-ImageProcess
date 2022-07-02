package com.dale.imageprocessor;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Image;
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
	
	private void run() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		
		menuBar = new JMenuBar();
		menu = new JMenu("파일 설정");
		fileLoader = new JMenuItem("파일 불러오기");
		fileSaver = new JMenuItem("파일 저장하기");
		
		imagePanel = new ImagePanel();
		imagePanel.setBounds(15, 150, 640, 360);
		imagePanel.setBackground(Color.BLUE);
		
		edittedImagePanel = new EdittedImagePanel();
		edittedImagePanel.setBounds(670, 150, 640, 360);
		edittedImagePanel.setBackground(Color.BLACK);
		
		fileLoader.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("jpeg", "jpeg"));
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
				
				chooser.setMultiSelectionEnabled(false);
				chooser.setVisible(true);
				int result = chooser.showOpenDialog(MainFrame.this);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					//선택한 파일의 경로 반환
					try {
						MyData.loadedImage = ImageIO.read(chooser.getSelectedFile());
						MyData.isLoaded = true;
						imagePanel.repaint();
						edittedImagePanel.repaint();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
//				    경로 출력
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
					//선택한 파일의 경로 반환
				    File selectedFile = chooser.getSelectedFile();
				    
				    //경로 출력
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
		this.setSize(1366, 768);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

}
