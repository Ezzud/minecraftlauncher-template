package fr.ezzud.defaultlauncher;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.macasaet.fernet.Key;

import fr.ezzud.defaultlauncher.addons.CustomTextField;
import fr.ezzud.defaultlauncher.addons.Encrypter;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.openlauncherlib.util.ramselector.RamSelector;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.colored.SColoredBar;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

@SuppressWarnings("serial")
public class Panel extends JPanel implements SwingerEventListener {

	private Image background = Swinger.getResource("background.png");
		
	private Saver saver = new Saver(new File(Main.MC_DIR, "launcher.properties"));
	private Saver firstLaunch = new Saver(new File(Main.MC_DIR, "FIRSTLAUNCH"));
	private CustomTextField username = new CustomTextField();
	private CustomTextField password = new CustomTextField();
	
	private STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png"));
	private STexturedButton quitButton = new STexturedButton(Swinger.getResource("exit.png"));
	private STexturedButton optionButton = new STexturedButton(Swinger.getResource("settings.png"));
	private STexturedButton websiteButton = new STexturedButton(Swinger.getResource("website.png"));
	private STexturedButton hideButton = new STexturedButton(Swinger.getResource("hide.png"));
	private STexturedButton resetButton = new STexturedButton(Swinger.getResource("reset.png"));
	
	private STexturedButton showHideEmailButton = new STexturedButton(Swinger.getResource("hideE.png"));
	private STexturedButton showHidePasswordButton = new STexturedButton(Swinger.getResource("showP.png"));
	
	private RamSelector ramSelector = new RamSelector(new File(Main.MC_DIR, "ram.txt"));
	
	private SColoredBar progressBar = new SColoredBar(Swinger.getTransparentWhite(50), Swinger.getTransparentWhite(75));
	private JLabel infoLabel = new JLabel(" ");
	private JLabel sizeLabel = new JLabel(" ");
	
	public Panel() throws URISyntaxException {
		
		username.setEchoChar('*');
		password.setEchoChar('*');
		
		try {
			File f = new File(Main.MC_DIR, "ram.txt");
			if(!f.exists()) {
				f.createNewFile();
				PrintWriter writer = new PrintWriter(f.getPath(), "UTF-8");
				writer.println("5");
				writer.close();
			} 
			new File(Main.MC_DIR, "launcher.properties").createNewFile();
			new File(Main.MC_DIR, "FIRSTLAUNCH").createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.setLayout(null);	
		
		
		String pE = saver.get("emailHidden");
		
		System.out.println("Email Hidden is set to: " + pE);
		
		if(pE == null) {
			saver.set("emailHidden", "false");
			saver.save();
			username.setEchoChar((char)0);
			showHideEmailButton.setTexture(Swinger.getResource("hideE.png"));
			showHideEmailButton.setTextureHover(Swinger.getResource("hideE.png"));
		} else if(pE.equals("true")) {
			username.setEchoChar('*');
			showHideEmailButton.setTexture(Swinger.getResource("showE.png"));
			showHideEmailButton.setTextureHover(Swinger.getResource("showE.png"));
		} else {
			username.setEchoChar((char)0);
			showHideEmailButton.setTexture(Swinger.getResource("hideE.png"));
			showHideEmailButton.setTextureHover(Swinger.getResource("hideE.png"));
		}
		
	    username.setForeground(Color.white);
	    username.setFont(username.getFont().deriveFont(18.0F));
	    username.setCaretColor(Color.white);
	    username.setOpaque(false);
	    username.setBorder(null);
	    username.setBounds(653, 189, 221, 18);
	    String savedEmail = saver.get("email");
	    if(savedEmail != null) {
	    	username.setText(savedEmail);
	    }
	    
	    username.addKeyListener(new KeyAdapter() {
            
			@Override
			public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiersEx() > 0) {
                    	username.transferFocusBackward();
                    } else {
                    	username.transferFocus();
                    }
                    e.consume();
                }
            }
        });
	    
	    
	    
	    
	    this.add(username);
	      
	    String pH = saver.get("passwordHidden");
	    System.out.println("Password Hidden is set to: " + pH);
	    
	    
	    if(pH == null) {
			saver.set("passwordHidden", "true");
			saver.save();
			password.setEchoChar('*');
			showHidePasswordButton.setTexture(Swinger.getResource("showP.png"));
			showHidePasswordButton.setTextureHover(Swinger.getResource("showP.png"));
		} else if(pH.equals("true")) {
			password.setEchoChar('*');
			showHidePasswordButton.setTexture(Swinger.getResource("showP.png"));
			showHidePasswordButton.setTextureHover(Swinger.getResource("showP.png"));
		} else {
			password.setEchoChar((char)0);
			showHidePasswordButton.setTexture(Swinger.getResource("hideP.png"));
			showHidePasswordButton.setTextureHover(Swinger.getResource("hideP.png"));
		}
	    
	    
	    password.setForeground(Color.white);
	    password.setFont(username.getFont().deriveFont(18.0F));
	    password.setCaretColor(Color.WHITE);
	    password.setOpaque(false);
	    password.setBorder(null);
	    password.setBounds(653, 288, 221, 18);
	    
	    String launch = firstLaunch.get("key");
	    
	    if(launch == null) {
	    	Key newKey = Key.generateKey();
	    	firstLaunch.set("key", newKey.serialise());
	    	firstLaunch.save();
	    	
	    	saver.set("key" ,"");
	    	saver.save();
	    } else {
	    	String encrypted = saver.get("key");
	    	if(encrypted != null) {  		
	    		String decryptedPassword = Encrypter.decrypt(launch, encrypted);
				password.setText(decryptedPassword);
	    	}
	    }
	    
	    this.add(password);
	    	    
		playButton.setBounds(658, 326);
		playButton.addEventListener(this);
		this.add(playButton);
		
		
		showHideEmailButton.setBounds(621, 187);
		showHideEmailButton.addEventListener(this);
		this.add(showHideEmailButton);
		
		showHidePasswordButton.setBounds(621, 286);
		showHidePasswordButton.addEventListener(this);
		this.add(showHidePasswordButton);
		
		quitButton.setBounds(944, 3);
		quitButton.addEventListener(this);
		this.add(quitButton);
		
		resetButton.setBounds(3, 3);
		resetButton.addEventListener(this);
		this.add(resetButton);
		
		websiteButton.setBounds(39, 3);
		websiteButton.addEventListener(this);
		this.add(websiteButton);
		
		hideButton.setBounds(908, 3);
		hideButton.addEventListener(this);
		this.add(hideButton);
		
		progressBar.setBounds(0, 462, 981, 7);
		progressBar.setVisible(false);
		this.add(progressBar);
		
		infoLabel.setBounds(290, 434, 350, 36);
		infoLabel.setFont(username.getFont().deriveFont(12.0F));
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(infoLabel);
		
		sizeLabel.setBounds(10, 434, 150, 36);
		sizeLabel.setFont(username.getFont().deriveFont(12.0F));
		sizeLabel.setForeground(Color.WHITE);
		sizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(sizeLabel);
		
		this.optionButton.addEventListener(this);
		this.optionButton.setBounds(872, 3);
		this.add(this.optionButton);
		
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEvent(SwingerEvent e) {
		if(e.getSource() == websiteButton) {
			if(functions.getWebsite() != null) {
				if(functions.getWebsite().length() < 2) return;
				
				Desktop desktop = java.awt.Desktop.getDesktop();
				try {
					URI website = new URI(functions.getWebsite());
					desktop.browse(website);
				} catch (IOException e1) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(this, functions.getMessage("errorURL"));
				} catch (URISyntaxException e1) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(this, functions.getMessage("invalidURL"));
				}
			}
		}
		if(e.getSource() == showHidePasswordButton) {
			
			if(saver.get("passwordHidden").equals("false")) {
				saver.set("passwordHidden", "true");
				saver.save();
				showHidePasswordButton.setTexture(Swinger.getResource("showP.png"));
				showHidePasswordButton.setTextureHover(Swinger.getResource("showP.png"));
				password.setEchoChar('*');
			} else {
				saver.set("passwordHidden", "false");
				saver.save();
				
				showHidePasswordButton.setTexture(Swinger.getResource("hideP.png"));
				showHidePasswordButton.setTextureHover(Swinger.getResource("hideP.png"));
				password.setEchoChar((char)0);
			}
			this.add(showHidePasswordButton);
		}
		
		if(e.getSource() == showHideEmailButton) {
			if(saver.get("emailHidden").equals("false")) {
				saver.set("emailHidden", "true");
				saver.save();
				showHideEmailButton.setTexture(Swinger.getResource("showE.png"));
				showHideEmailButton.setTextureHover(Swinger.getResource("showE.png"));
				username.setEchoChar('*');
			} else {
				saver.set("emailHidden", "false");
				saver.save();
				showHideEmailButton.setTexture(Swinger.getResource("hideE.png"));
				showHideEmailButton.setTextureHover(Swinger.getResource("hideE.png"));
				username.setEchoChar((char)0);
			}
		}
		
		if(e.getSource() == resetButton) {
			Toolkit.getDefaultToolkit().beep();
			setFieldsEnabled(false);
			int choice = JOptionPane.showConfirmDialog(Panel.this, functions.getMessage("confirmReset"));
			if(choice == 0) {
				File f1 = ramSelector.getFile();
				File f2 = new File(Main.MC_DIR, "launcher.properties");
				File f3 = new File(Main.MC_DIR, "FIRSTLAUNCH");
				f1.delete();
				f2.delete();
				f3.delete();
				username.setText("");
				password.setText("");
				try {
					File f = new File(Main.MC_DIR, "ram.txt");
					if(!f.exists()) {
						f.createNewFile();
						PrintWriter writer = new PrintWriter(f.getPath(), "UTF-8");
						writer.println("5");
						writer.close();
					}
					new File(Main.MC_DIR, "launcher.properties").createNewFile();
					new File(Main.MC_DIR, "FIRSTLAUNCH").createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setFieldsEnabled(true);
				
			} else {
				setFieldsEnabled(true);
				return;
			}
		}
		
		if(e.getSource() == playButton) {
			setFieldsEnabled(false);
			
			if(username.getText().replaceAll(" ", "").length() == 0 || password.getText().length() == 0) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(this, functions.getMessage("MissingEntry"));
				setFieldsEnabled(true);
				return;
			}
			Thread t = new Thread() {
				@Override
				public void run() {
					Frame.getInstance().getPanel().setInfoText(functions.getMessage("MicrosoftAuth"));
						try {
							Main.microsoftAuth(username.getText(), password.getText());
						} catch (MicrosoftAuthenticationException e) {
							Frame.getInstance().getPanel().setInfoText(" ");
							Toolkit.getDefaultToolkit().beep();
							JOptionPane.showMessageDialog(Panel.this, functions.getMessage("ConnectionError") + e.getMessage());
							setInfoText(" ");
							setFieldsEnabled(true);
							password.setText(null);
							saver.set("key" ,"");
					    	saver.save();
							return;
						}
					
					Frame.getInstance().getPanel().setInfoText(functions.getMessage("Connected"));
						
					String key = firstLaunch.get("key");
					String encryptedPassword = Encrypter.encrypt(key, password.getText());
					
					System.out.println("Password Successfully encrypted");	
					
					saver.set("email", username.getText());
					saver.set("key", encryptedPassword);
					saver.save();
					ramSelector.save();
					try {
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("ConnectionToServer"));
						progressBar.setVisible(true);
						Main.update();
					} catch (Exception e) {
						Main.interrupThread();
						Toolkit.getDefaultToolkit().beep();
						progressBar.setVisible(false);
						JOptionPane.showMessageDialog(Panel.this, e.toString());
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
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(Panel.this, e.toString());
						System.out.println(e.toString());
						progressBar.setVisible(false);
						setFieldsEnabled(true);
						setInfoText(" ");
					}
					System.out.println("Starting...");
				}
			};
			t.start();
			
		} else if(e.getSource() == quitButton) {
			System.exit(0);
		} else if(e.getSource() == hideButton) {
			Frame.getInstance().setState(JFrame.ICONIFIED);
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
		showHideEmailButton.setEnabled(enabled);
		showHidePasswordButton.setEnabled(enabled);
		resetButton.setEnabled(enabled);
	}
	
	public SColoredBar getProgressBar() {
		return progressBar;
	}

	public void setInfoText(String text) {
		infoLabel.setText(text);
	}
	
	public void setSizeText(String text) {
		sizeLabel.setText(text);
	}
	
	public RamSelector getRamSelector() {
		return ramSelector;
	}

}

