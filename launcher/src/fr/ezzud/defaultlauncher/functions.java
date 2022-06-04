package fr.ezzud.defaultlauncher;

public class functions {
	public static String getSUpdate() {
		return "https://ezzud.fr/supdate/index.php/";
	}
	public static String getWebsite() {
		return "YOUR WEBSITE";
	}
	public static String getAuthMethod() {
		return "microsoft"; // Available: microsoft, mojang
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
			case "GameNotStarting":
				return "Impossible de lancer le jeu";
			case "Updating":
				return "Mise à jour...";
			case "Verifying":
				return "Verification...";
		}
		return "null";
		
	}
}
