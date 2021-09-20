package fr.ezzud.defaultbootstrap;

public class functions {
	public static String getSUpdateLauncher() {
		return "YOUR S-UPDATE BOOTSTRAP FOLDER";
	}
	public static String getWebsite() {
		return "YOUR WEBSITE";
	}
	public static String getLauncherName() {
		return "Minecraft Launcher";
	}
	public static String getAppdata() {
		return "minecrafttestlauncher";
	}
	public static String getMessage(String id) {
		if(id == null) return "null";
		switch(id) {
			case "ConnectionToServer":
				return "Connexion au serveur...";
			case "Connected":
				return "Connexion effectuée...";
			case "ConnectionError":
				return "Erreur! Connexion impossible: ";
			case "MissingEntry":
				return "Erreur! L'un des champs de connexion est vide";
			case "LauncherNotStarting":
				return "Impossible de lancer le launcher";
			case "LauncherNotUpdating":
				return "Impossible de mettre à jour le launcher";
			case "Updating":
				return "Mise à jour...";
			case "Updated":
				return "Mise à jour effectuée!";
			case "Verifying":
				return "Verification...";
		}
		return "null";
		
	}
}
