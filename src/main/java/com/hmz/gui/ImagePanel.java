package com.hmz.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/** PANEL HOLDING AN IMAGE 
 * 
 * @author hamza
 *
 */
public class ImagePanel extends Panel {
	// PANEL USED TO PAINT AN IMAGE TO A DIALOG OR A FRAME
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private String path;
	
	// CONSTRUCT SECTION ----------------------------------------------------------------------------------------------
	
	public ImagePanel(String path) {
		this.path = path;
		try {
			image = ImageIO.read(new File(this.path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// GET SECTION ----------------------------------------------------------------------------------------------
	
	public BufferedImage getImage() {
		return image;
	}
	
	public String getPath() {
		return path;
	}
	
	// SET SECTION ----------------------------------------------------------------------------------------------
	
	public void setImage() {
		try {
			image = ImageIO.read(new File(this.path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	public void setImage(String path) {
		setPath(path);
		setImage();
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	// FINAL SECTION ----------------------------------------------------------------------------------------------

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	

}
