package fr.ezzud.defaultbootstrap;

public class functions {
	public static String getSUpdateLauncher() {
		return "https://yourwebsite.com/supdate/bootstrap/";
	}
	public static String getWebsite() {
		return "https://ezzud.fr";
	}
	public static String getLauncherName() {
		return "Minecraft Bootstrap by Ezzud";
	}
	public static String getAppdata() {
		return "defaultlauncher";
	}
	public static String getMessage(String id) {
		if(id == null) return "null";
		switch(id) {
			case "ConnectionToServer":
				return "Checking for Update...";
			case "Connected":
				return "Successfully connected to the server...";
			case "ConnectionError":
				return "Erreur! Unable to connect: ";
			case "LauncherNotStarting":
				return "Unable to start Launcher, this error may appear if you have a too recent java version, note that this launcher works only with Java 8";
			case "LauncherNotUpdating":
				return "Unable to update Launcher, this error may appear if you have badly written S-Update URL or the Update server is offline/unfinished";
			case "startUpdate":
				return "Starting Update...";
			case "Updating":
				return "Updating Launcher Data...";
			case "Updated":
				return "Launcher Updated!";
			case "Verifying":
				return "Checking launcher data...";
		}
		return "null";
		
	}
}
