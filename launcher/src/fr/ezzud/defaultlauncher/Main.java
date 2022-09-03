package fr.ezzud.defaultlauncher;


import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.openlauncherlib.util.ProcessLogManager;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.swinger.Swinger;

public class Main{

	public static final GameVersion MC_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
	public static final GameInfos MC_INFOS = new GameInfos(functions.getAppdata() + "/game", MC_VERSION, new GameTweak[] { GameTweak.FORGE });
	public static final File MC_DIR = MC_INFOS.getGameDir();
	public static final GameFolder MC_FOLDER = new GameFolder("assets", "libs", "natives", "minecraft.jar");
	public static final File MC_CRASHES_DIR = new File(MC_DIR, "crashes");
	
	private static AuthInfos authInfos;
	private static Thread updateThread;
	
	
	public static void microsoftAuth(String username, String password) throws MicrosoftAuthenticationException {
		MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
		MicrosoftAuthResult result = authenticator.loginWithCredentials(username, password);
		authInfos = new AuthInfos(result.getProfile().getName(), result.getAccessToken(), result.getProfile().getId());
	}
	

	public static void update() throws Exception {
		Frame.getInstance().getPanel().setInfoText(functions.getMessage("ConnectionToServer"));
		SUpdate su = new SUpdate(functions.getSUpdate(), MC_DIR);
		su.getServerRequester().setRewriteEnabled(true);
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
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("startUpdate"));
					} else if(val == max) {
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("Verifying"));
					} else if(val > max) {
						Frame.getInstance().getPanel().setInfoText(functions.getMessage("Verifying"));
					} else {
					Frame.getInstance().getPanel().setInfoText(functions.getMessage("Updating") + "  (" +
							Swinger.percentage(val, max) + "%)");
					Frame.getInstance().getPanel().setSizeText(bts(BarAPI.getNumberOfTotalDownloadedBytes()) + "/" + bts(BarAPI.getNumberOfTotalBytesToDownload()));
					Frame.getInstance().getPanel().getProgressBar().setString(Swinger.percentage(val, max) + "%");
					}
				}
				
				Frame.getInstance().getPanel().getProgressBar().setValue(max);
				Frame.getInstance().getPanel().getProgressBar().setString("100%");
				Frame.getInstance().getPanel().setSizeText(" ");
				Frame.getInstance().getPanel().setInfoText(functions.getMessage("Verifying"));
			}
		};
		updateThread.start();
		su.start();
		updateThread.interrupt();
		Frame.getInstance().getPanel().setInfoText(functions.getMessage("Verifying"));
	}
	
	public static void launch() throws LaunchException {
		
		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(MC_INFOS, MC_FOLDER, authInfos);
		ArrayList<String> arguments = new ArrayList<String>();
		arguments.addAll(Arrays.asList(Frame.getInstance().getPanel().getRamSelector().getRamArguments()));
		arguments.add("-XX:+IgnoreUnrecognizedVMOptions");
		profile.getVmArgs().addAll(arguments);
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
	
	public static String bts(long bytes) {
	    ArrayList<String> sizes = new ArrayList<String>();
	    sizes.add("o");
	    sizes.add("Ko");
	    sizes.add("Mo");
	    sizes.add("Go");
	    sizes.add("To");
	    if (bytes == 0) {
	    	return "0";
	    }
	    double i = Math.floor(Math.log(bytes) / Math.log(1024));
	    if (i == 0) {
	    	return bytes + " " + sizes.get((int) Double.parseDouble(String.valueOf(i)));
	    }
	    DecimalFormat decimalFormat = new DecimalFormat("0.##");
	    return String.valueOf(decimalFormat.format((bytes / (Math.pow(1024, i))))) + " " + sizes.get((int) Double.parseDouble(String.valueOf(i)));
	}
	
}
