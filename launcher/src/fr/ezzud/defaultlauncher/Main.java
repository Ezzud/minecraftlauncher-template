package fr.ezzud.defaultlauncher;


import java.io.File;
import java.util.Arrays;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.openlauncherlib.util.ProcessLogManager;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.swinger.Swinger;

public class Main{

	public static final GameVersion MC_VERSION = new GameVersion("1.7.10", GameType.V1_7_10);
	public static final GameInfos MC_INFOS = new GameInfos(functions.getAppdata(), MC_VERSION, null);
	public static final File MC_DIR = MC_INFOS.getGameDir();
	public static final GameFolder MC_FOLDER = new GameFolder("assets", "libs", "natives", "minecraft.jar");
	public static final File MC_CRASHES_DIR = new File(MC_DIR, "crashes");
	
	private static AuthInfos authInfos;
	private static Thread updateThread;
	
	public static void auth(String username, String password) throws AuthenticationException {
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
		AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, username, password, "");
		authInfos = new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(), response.getSelectedProfile().getId());
	}
	
	public static void microsoftAuth(String username, String password) throws MicrosoftAuthenticationException {
		MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
		MicrosoftAuthResult result = authenticator.loginWithCredentials(username, password);
		authInfos = new AuthInfos(result.getProfile().getName(), result.getAccessToken(), result.getProfile().getId());
	}
	

	public static void update() throws Exception {
		Frame.getInstance().getPanel().setInfoText(functions.getMessage("ConnectionToServer"));
		SUpdate su = new SUpdate(functions.getSUpdate(), MC_DIR);
		su.addApplication(new FileDeleter());

		updateThread = new Thread() {
			private int val = 0;
			private int max = 0;
			
			@Override
			public void run() {
				
				Frame.getInstance().getPanel().setInfoText(functions.getMessage("ConnectionToServer"));
				while(!this.isInterrupted()) {
					val = (int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000);
					max = (int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000);
					Frame.getInstance().getPanel().getProgressBar().setMaximum(max);
					Frame.getInstance().getPanel().getProgressBar().setValue(val);
					
					if(BarAPI.getNumberOfFileToDownload() == 0) {
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("Updating"));
					} else if(val == max) {
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("Verifiying"));
					} else if(val > max) {
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("Verifiying"));
					} else {
					Frame.getInstance().getPanel().setInfoText(functions.getMessage("Updating") +
						BarAPI.getNumberOfDownloadedFiles() + "/" + BarAPI.getNumberOfFileToDownload() + " " +
							Swinger.percentage(val, max) + "%");
					Frame.getInstance().getPanel().getProgressBar().setString(Swinger.percentage(val, max) + "%");
					}
				}
			}
		};
		updateThread.start();
		
		su.start();
		su.setServerUrl(functions.getSUpdate().split("index.php")[0]);
		updateThread.interrupt();
	}
	
	public static void launch() throws LaunchException {
		
		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(MC_INFOS, GameFolder.BASIC, authInfos);
		profile.getVmArgs().addAll(Arrays.asList(Frame.getInstance().getPanel().getRamSelector().getRamArguments()));
		ExternalLauncher launcher = new ExternalLauncher(profile);

		
		Process pp = (Process) launcher.launch();
		
		ProcessLogManager manager = new ProcessLogManager(pp.getInputStream(), new File(MC_DIR, "logs.txt"));
		manager.start();
		
		Frame.getInstance().setVisible(false);
		
		try {
			Thread.sleep(5000L);
			pp.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.exit(0);
	}
	
	public static void interrupThread() {
		updateThread.interrupt();
	}
	
}
