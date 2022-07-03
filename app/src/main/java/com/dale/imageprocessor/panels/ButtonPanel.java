package com.dale.imageprocessor.panels;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;

import com.dale.imageprocessor.buttons.RoundButton;

public class ButtonPanel extends JPanel {
	private Font normalFont = new Font("",Font.BOLD, 30);
	
	private RoundButton grayScale = new RoundButton("��鸸���");
	private RoundButton invert = new RoundButton("�� ����");
	private RoundButton brightnessControl = new RoundButton("�������");
	private RoundButton getEdge = new RoundButton("�׵θ� ����");
	private RoundButton getOrigin = new RoundButton("���� ����");
	
	public ButtonPanel() {
		setLayout(new FlowLayout());
		grayScale.setFont(normalFont);
		invert.setFont(normalFont);
		brightnessControl.setFont(normalFont);
		getEdge.setFont(normalFont);
		getOrigin.setFont(normalFont);
		
		add(grayScale);
		add(invert);
		add(brightnessControl);
		add(getEdge);
		add(getOrigin);
	}
	
	public RoundButton getGrayScale() {
		return grayScale;
	}
	public void setGrayScale(RoundButton grayScale) {
		this.grayScale = grayScale;
	}
	public RoundButton getInvert() {
		return invert;
	}
	public void setInvert(RoundButton invert) {
		this.invert = invert;
	}
	public RoundButton getBrightnessControl() {
		return brightnessControl;
	}
	public void setBrightnessControl(RoundButton brightnessControl) {
		this.brightnessControl = brightnessControl;
	}
	public RoundButton getGetEdge() {
		return getEdge;
	}
	public void setGetEdge(RoundButton getEdge) {
		this.getEdge = getEdge;
	}
	
	public RoundButton getGetOrigin() {
		return getOrigin;
	}

	public void setGetOrigin(RoundButton getOrigin) {
		this.getOrigin = getOrigin;
	}
}
