package fr.ezzud.defaultlauncher;

import java.awt.Color;
import java.awt.Component;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private static Frame instance;
	private Panel lPanel;
	
	public Frame() throws URISyntaxException {
		instance = this;
		this.setTitle(functions.getLauncherName());
		this.setSize(650, 631);
	    this.setDefaultCloseOperation(3);
	    this.setResizable(false);
	    this.setTitle(functions.getLauncherName());
	    this.setContentPane(lPanel = new Panel());
	    this.setLocationRelativeTo((Component)null);
	    this.setUndecorated(true);
	    this.setIconImage(Swinger.getResource("icon.png"));
	    this.setBackground(new Color(0, 0, 0, 0));
	    this.getContentPane().setBackground(new Color(0, 0, 0, 0));
	    this.setVisible(true);
	      
	    WindowMover n = new WindowMover(this);
	    this.addMouseListener(n);
	    this.addMouseMotionListener(n);
		
		this.setVisible(true);
	}
	
	public static CrashReporter errorUtil;
	
	
	public static void main(String[] args) {
		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/fr/ezzud/defaultlauncher/resources");
		Main.MC_CRASHES_DIR.mkdirs();
		errorUtil = new CrashReporter(functions.getLauncherName(), Main.MC_CRASHES_DIR);
		
		try {
			instance = new Frame();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static Frame getInstance() {
		return instance;
	}
	
	public Panel getPanel() {
		return this.lPanel;
	}
	
	public static CrashReporter getErrorUtil() {
		return errorUtil;
	}
}
