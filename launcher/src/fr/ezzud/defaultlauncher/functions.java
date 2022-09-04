package fr.ezzud.defaultlauncher;

public class functions {
	public static String getSUpdate() {
		return "https://yourwebsite.com/supdate/";
	}
	public static String getWebsite() {
		return "https://ezzud.fr";
	}
	
	public static String getLauncherName() {
		return "Minecraft Launcher by Ezzud";
	}
	public static String getAppdata() {
		return "defaultlauncher";
	}
	public static String getMessage(String id) {
		if(id == null) return "null";
		switch(id) {
			case "MicrosoftAuth":
			return "Trying to connect to Microsoft...";
			case "ConnectionToServer":
				return "Checking available update...";
			case "Connected":
				return "Connected to the server!";
			case "startUpdate":
				return "Starting Update...";
			case "ConnectionError":
				return "Unable to connect: ";
			case "MissingEntry":
				return "Error: One of the field is empty";
			case "GameNotStarting":
				return "Unable to launch the game";
			case "Updating":
				return "Updating game data... ";
			case "Verifying":
				return "Verifying the game data... ";
			case "confirmReset":
				return "Do you realy want to delete all cached data? (credentials, ram, encryption key)";
			case "errorURL":
				return "Error while opening URL";
			case "invalidURL":
				return "Error: Invalid URL";
		}
		return "null";
		
	}
}
