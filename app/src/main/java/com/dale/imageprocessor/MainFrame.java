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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.dale.imageprocessor.buttons.RoundButton;
import com.dale.imageprocessor.data.MyData;
import com.dale.imageprocessor.panels.*;

public class MainFrame extends JFrame {
	private static final String TITLE = "이미지 편집기";
	static int monitorWidth;
	static int monitorHeight;
	
	private boolean isBrightControlPressed = false;
	
	JPanel mainPanel ;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem fileLoader;
	JMenuItem fileSaver;
	JFileChooser chooser;
	ImagePanel imagePanel;
	EdittedImagePanel edittedImagePanel;
	ButtonPanel buttonPanel;
	File loadedLoad;
	JSlider slider = new JSlider(JSlider.HORIZONTAL, -255, 255, 0);
	
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
		menu = new JMenu("파일 설정");
		fileLoader = new JMenuItem("파일 불러오기");
		fileSaver = new JMenuItem("파일 저장하기");
		
		buttonPanel = new ButtonPanel();
		buttonPanel.setBounds(15,15,monitorWidth * 81 / 100 , 130) ;
		buttonPanel.setBackground(Color.WHITE);
		
		buttonPanel.getGrayScale().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int y = 0; y < MyData.loadedBufferedImage.getHeight(); y++) {
					   for(int x = 0; x < MyData.loadedBufferedImage.getWidth(); x++) {
					       Color colour = new Color(MyData.loadedBufferedImage.getRGB(x, y));
					       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
					       MyData.copiedImage.setRGB(x, y, new Color(Y, Y, Y).getRGB());
					   }
				}
				edittedImagePanel.repaint();
			}
		});
		
		buttonPanel.getGetOrigin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MyData.copiedImage = ImageIO.read(loadedLoad);
					
					edittedImagePanel.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		buttonPanel.getBrightnessControl().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isBrightControlPressed) {
					isBrightControlPressed = false;
					buttonPanel.getBrightnessControl().setColor("밝기조절", new Color(61,205,91));
					slider.setVisible(false);
				}
				else {
					isBrightControlPressed = true;
					buttonPanel.getBrightnessControl().setColor("밝기조절", Color.GRAY);
					slider.setVisible(true);
				}
			}
		});
		
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
				loadedLoad = chooser.getSelectedFile();
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						MyData.loadedBufferedImage = ImageIO.read(chooser.getSelectedFile());
						
						MyData.copiedImage = ImageIO.read(chooser.getSelectedFile());
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
		
		slider.setBounds(150, 80, monitorWidth/2, 50);
		slider.setPaintLabels(true);
	    slider.setPaintTicks(true);
	    slider.setPaintTrack(true);
	    slider.setMajorTickSpacing(25);
	    slider.setMinorTickSpacing(25);
	    slider.setVisible(false);
	    
	    slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				for (int y = 0; y < MyData.loadedBufferedImage.getHeight(); y++) {
		            for (int x = 0; x < MyData.loadedBufferedImage.getWidth(); x++) {

		               int pixel = MyData.loadedBufferedImage.getRGB(x,y);
		               Color color = new Color(pixel, true);
		               
		               int red = color.getRed();
		               if(red+slider.getValue()>=255) {
		            	   red = 255;
		               }
		               else if(red+slider.getValue()<=0) {
		            	   red = 0;
		               }
		               else {
		            	   red+= slider.getValue();
		               }
		               
		               int green = color.getGreen();
		               if(green+slider.getValue()>=255) {
		            	   green = 255;
		               }
		               else if(green+slider.getValue()<=0) {
		            	   green = 0;
		               }
		               else {
		            	   green+=slider.getValue();
		               }
		               
		               int blue = color.getBlue();
		               if(blue+slider.getValue()>=255) {
		            	   blue = 255;
		               }
		               else if(blue+slider.getValue()<=0) {
		            	   blue = 0;
		               }
		               else {
		            	   blue+=slider.getValue();
		               }

		               color = new Color(red, green, blue);
		               //Setting new Color object to the image
		               MyData.copiedImage.setRGB(x, y, color.getRGB());
		               edittedImagePanel.repaint();
		            }
		         }
		   }

				
			
	    });
		
		menu.add(fileSaver);
		menu.add(fileLoader);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		mainPanel.add(imagePanel);
		mainPanel.add(edittedImagePanel);
		
		this.add(slider);
		this.add(buttonPanel);
		this.add(mainPanel);
		this.setVisible(true);
	}

}
