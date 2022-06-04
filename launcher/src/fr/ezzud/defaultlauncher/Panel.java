package fr.ezzud.defaultlauncher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.openlauncherlib.util.ramselector.RamSelector;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import fr.theshark34.swinger.textured.STexturedProgressBar;

@SuppressWarnings("serial")
public class Panel extends JPanel implements SwingerEventListener {

	private Image background = Swinger.getResource("login.png");
		
	private Saver saver = new Saver(new File(Main.MC_DIR, "launcher.properties"));
	
	private JTextField username = new JTextField(saver.get("username"));
	private JPasswordField password = new JPasswordField();
	
	private STexturedButton playButton = new STexturedButton(Swinger.getResource("bouton-play.png"));
	private STexturedButton quitButton = new STexturedButton(Swinger.getResource("bouton-quit.png"));
	private STexturedButton optionButton = new STexturedButton(Swinger.getResource("bouton-options.png"));
	
	private RamSelector ramSelector = new RamSelector(new File(Main.MC_DIR, "ram.txt"));
	
	private STexturedProgressBar progressBar = new STexturedProgressBar(Swinger.getResource("bar-empty.png"), Swinger.getResource("bar-full.png"));
	private JLabel infoLabel = new JLabel(" ");
	
	
	public Panel() throws URISyntaxException {
		this.setLayout(null);
			
	    username.setForeground(Color.black);
	    username.setFont(username.getFont().deriveFont(24.0F));
	    username.setCaretColor(Color.white);
	    username.setOpaque(false);
	    username.setBorder(null);
	    username.setBounds(215, 243, 194, 30); 
	    this.add(username);
	      
	    password.setForeground(Color.black);
	    password.setFont(username.getFont().deriveFont(24.0F));
	    password.setCaretColor(Color.WHITE);
	    password.setOpaque(false);
	    password.setBorder(null);
	    password.setBounds(215, 366, 194, 30);
	    this.add(password);
	    	    
		playButton.setBounds(220, 467);
		playButton.addEventListener(this);
		this.add(playButton);
		
		quitButton.setBounds(449, 59);
		quitButton.addEventListener(this);
		this.add(quitButton);
		
		progressBar.setBounds(19, 113, 42, 288);
		progressBar.setVertical(true);
		progressBar.setBackgroundTexture(Swinger.getResource("bar-empty.png"));
		progressBar.setForegroundTexture(Swinger.getResource("bar-full.png"));
		progressBar.setMaximum(5000);
		this.add(progressBar);
		
		infoLabel.setBounds(30, 114, 369, 36);
		infoLabel.setFont(username.getFont().deriveFont(12.0F));
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(infoLabel);
		
		this.optionButton.addEventListener(this);
		this.optionButton.setBounds(93, 534);
		this.add(this.optionButton);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEvent(SwingerEvent e) {
		if(e.getSource() == playButton) {
			setFieldsEnabled(false);
			
			if(username.getText().replaceAll(" ", "").length() == 0 || password.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, functions.getMessage("MissingEntry"));
				setFieldsEnabled(true);
				return;
			}
			Thread t = new Thread() {
				@Override
				public void run() {
					Frame.getInstance().getPanel().setInfoText(functions.getMessage("ConnectionToServer"));
					if(functions.getAuthMethod().equals("mojang")) {
						try {
							Main.auth(username.getText(), password.getText());
						} catch (AuthenticationException e) {
							JOptionPane.showMessageDialog(Panel.this, functions.getMessage("ConnectionError") + e.getMessage());
							setInfoText(" ");
							setFieldsEnabled(true);
							return;
						}
					}
					if(functions.getAuthMethod().equals("microsoft")) {
						try {
							Main.microsoftAuth(username.getText(), password.getText());
						} catch (MicrosoftAuthenticationException e) {
							JOptionPane.showMessageDialog(Panel.this, functions.getMessage("ConnectionError") + e.getMessage());
							setInfoText(" ");
							setFieldsEnabled(true);
							return;
						}
					}
					
					
					System.out.println(functions.getMessage("Connected"));
					((Saver) saver).set("username", username.getText());
					ramSelector.save();
					try {
						Main.update();
					} catch (Exception e) {
						Main.interrupThread();
						System.out.println(e.toString());
						setFieldsEnabled(true);
						setInfoText(" ");
						return;
					}
					
					System.out.println("Updated!");
					
					try 
					{
						Main.launch();
					} 
					catch (LaunchException e) {
						Main.interrupThread();
						Frame.getErrorUtil().catchError(e, functions.getMessage("GameNotStarting"));
						System.out.println(e.toString());
						setFieldsEnabled(true);
						setInfoText(" ");
					}
					System.out.println("Starting...");
				}
			};
			t.start();
			
		} else if(e.getSource() == quitButton) {
			System.exit(0);
		} else if(e.getSource() == this.optionButton) {
			ramSelector.display();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Swinger.drawFullsizedImage(g, this, background);
	}
	
	private void setFieldsEnabled(boolean enabled) {
		username.setEnabled(enabled);
		password.setEnabled(enabled);
		playButton.setEnabled(enabled);
		optionButton.setEnabled(enabled);
	}
	
	public STexturedProgressBar getProgressBar() {
		return progressBar;
	}

	public void setInfoText(String text) {
		infoLabel.setText(text);
	}
	
	public RamSelector getRamSelector() {
		return ramSelector;
	}

}

