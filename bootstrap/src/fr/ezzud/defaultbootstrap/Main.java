package fr.ezzud.defaultbootstrap;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import fr.theshark34.openlauncherlib.bootstrap.Bootstrap;
import fr.theshark34.openlauncherlib.bootstrap.LauncherClasspath;
import fr.theshark34.openlauncherlib.bootstrap.LauncherInfos;
import fr.theshark34.openlauncherlib.util.GameDir;
import fr.theshark34.openlauncherlib.util.SplashScreen;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.colored.SColoredBar;

public class Main {

	
	private static SplashScreen splash;
	private static SColoredBar bar = new SColoredBar(Swinger.getTransparentWhite(50), Swinger.getTransparentWhite(75));
	private static JLabel infoLabel = new JLabel(" ");
	private static Thread barThread;
	
	private static final LauncherInfos MC_B_INFOS = new LauncherInfos(functions.getLauncherName(), "fr.ezzud.defaultlauncher.Frame");
	private static final File MC_DIR = GameDir.createGameDir(functions.getAppdata());
	private static final LauncherClasspath MC_B_CP = new LauncherClasspath(new File(MC_DIR, "launcher/launcher.jar"), new File(MC_DIR, "launcher/libs"));
	
	public static void main(String[] args) {

		Swinger.setSystemLookNFeel();
		Swinger.setResourcePath("/fr/ezzud/defaultbootstrap/resources/");
		displaySplash();
		infoLabel.setText(functions.getMessage("ConnectionToServer"));
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try
		{
		doUpdate();
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(splash.getContentPane(), functions.getMessage("LauncherNotUpdating"));
			infoLabel.setText(" ");
			barThread.interrupt();
			System.exit(0);
			return;
		}
		try {
			launchLauncher();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(splash.getContentPane(), functions.getMessage("LauncherNotStarting"));
			infoLabel.setText(" ");
			System.exit(0);
			return;
		}
	}
	private static void displaySplash() {
		splash = new SplashScreen(functions.getLauncherName(), Swinger.getResource("bg.png"));
		splash.setBackground(new Color(0, 0, 0, 0));
		splash.setLayout(null);
		splash.setIconImage(Swinger.getResource("icon.png"));
		
		infoLabel.setBounds(0, 562, 484, 12);
		infoLabel.setFont(infoLabel.getFont().deriveFont(12.0F));
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setText(" ");
		splash.add(infoLabel);
		
		
		
		bar.setBounds(0, 554, 484, 30);
		splash.add(bar);
		splash.setVisible(true);
	}

	private static void doUpdate() throws Exception {
		infoLabel.setText(functions.getMessage("startUpdate"));
		SUpdate su = new SUpdate(functions.getSUpdateLauncher(), new File(MC_DIR, "launcher"));
		su.getServerRequester().setRewriteEnabled(true);
	
		barThread = new Thread() {
			private int val = 0;
			private int max = 0;		
			@Override
			public void run() {

				infoLabel.setText(functions.getMessage("Connected"));
				while(!this.isInterrupted()) {
					infoLabel.setText(functions.getMessage("Updating"));
					val = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000);
					max = (int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000);
					bar.setValue(val); 
					bar.setMaximum(max); 
				}
				
			}
			
		};
		barThread.start();
		infoLabel.setText(functions.getMessage("ConnectionToServer"));
		su.start();
		infoLabel.setText(functions.getMessage("Updated"));
		barThread.interrupt();
	}
	
	private static void launchLauncher() throws IOException {
		Bootstrap bootstrap = new Bootstrap(MC_B_CP, MC_B_INFOS);
		Process p = bootstrap.launch();
		splash.setVisible(false);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			
		}
		System.exit(0);
	}
	


	
}
