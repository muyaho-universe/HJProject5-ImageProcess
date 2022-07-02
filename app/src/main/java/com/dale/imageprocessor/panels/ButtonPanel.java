package com.dale.imageprocessor.panels;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;

import com.dale.imageprocessor.buttons.RoundButton;

public class ButtonPanel extends JPanel {
	private Font normalFont = new Font("",Font.BOLD, 30);
	
	private RoundButton grayScale = new RoundButton("흑백만들기");
	private RoundButton zoomInZoomOut = new RoundButton("확대/축소");
	private RoundButton brightnessControl = new RoundButton("밝기조절");
	private RoundButton getEdge = new RoundButton("테두리 추출");
	private RoundButton getOrigin = new RoundButton("원본 보기");
	
	public ButtonPanel() {
		setLayout(new FlowLayout());
		grayScale.setFont(normalFont);
		zoomInZoomOut.setFont(normalFont);
		brightnessControl.setFont(normalFont);
		getEdge.setFont(normalFont);
		getOrigin.setFont(normalFont);
		
		add(grayScale);
		add(zoomInZoomOut);
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
	public RoundButton getZoomInZoomOut() {
		return zoomInZoomOut;
	}
	public void setZoomInZoomOut(RoundButton zoomInZoomOut) {
		this.zoomInZoomOut = zoomInZoomOut;
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
