package com.deepsingh44.utility;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Util {

	public static ImageIcon resize(ImageIcon image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D) bi.getGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(image.getImage(), 0, 0, width, height, null);
		gd.dispose();
		return new ImageIcon(bi);
	}

	public static void errorMessage(Component cmp, String msg) {
		JOptionPane.showMessageDialog(cmp, msg, "Error-Message", JOptionPane.ERROR_MESSAGE);
	}

	public static void warningMessage(Component cmp, String msg) {
		JOptionPane.showMessageDialog(cmp, msg, "Warning-Message", JOptionPane.WARNING_MESSAGE);
	}

	public static void normalMessage(Component cmp, String msg) {
		JOptionPane.showMessageDialog(cmp, msg);
	}

}
