# Minecraft Launcher Template

<h2>✨ Features:</h2>

<p>

🎈 CUSTOM APPDATA<br />
🎈 CUSTOM DESIGN<br />

⌛ SELF-HOSTED AUTO UPDATE<br />
⌛ SELF-HOSTED LAUNCHER UPDATE<br />
⌛ MICROSOFT AUTH<br />

✅ MCP COMPATIBLE<br />
✅ FORGE OR VANILLA COMPATIBLE<br />
✅ **1.7.10 -> 1.13.2** Tested

⭐ EMAIL SAVER<br />
⭐ PASSWORD ENCRYPTED SAVER<br />
⭐ RAM SELECTOR<br />
⭐ EMERGENCY BUTTON<br />
⭐ SHOW/HIDE EMAIL OR PASSWORD FOR STREAMING<br />
⭐ EASY TO CONFIGURE<br />

</p>

<h2>🛑 Requirements:</h2>

<p>

> **FOR LAUNCHER ONLY** [OpenLauncherLib v3.0.5](https://ezzud.fr/others/openlauncherlib-3.0.5.jar)<br />
> **FOR LAUNCHER ONLY** [Fernet v1.5.0](https://ezzud.fr/others/fernet-java8-1.5.0.jar)<br />
> **FOR LAUNCHER ONLY** [Apache Commons Codec v1.15](commons-codec-1.15.jar)<br />
> **FOR BOOTSTRAP ONLY** [OpenLauncherLib v2.1](https://ezzud.fr/others/openlauncherlib-2.1-SNAPSHOT.jar)<br />
> [S-Update v3.1.0](https://ezzud.fr/others/s-update-3.1.0-BETA.jar)<br />
> [Swinger v1.0.0](https://ezzud.fr/others/swinger-1.0.0-BETA.jar)<br />

> Any Web Hoster (with php incuded)<br />
> **WEBHOSTER** [S-Update server-side v3.1.0](https://github.com/Litarvan/S-Update-Server/releases/download/3.1.0-BETA/s-update-server-3.1.0.zip)<br />


</p>

<h2>📜 Installation:</h2>

<h3>I Install the workspace</h3>
<p>

- Download all requirements from the requirement section 
- Open your Java IDE (Eclipse, IntelliJ)
- Optional: With Eclipse IDE, open the ".project" file on launcher & bootstrap
- Create a JavaProject for Launcher, AND ANOTHER for Bootstrap (⚠ Launcher & Bootstrap are 2 different programs)
- Import respective launcher & bootstrap files into each project
- Import required Libraries


</p>

<h3>II Edit your launcher</h3>

<p>

- Open the `src/fr.ezzud.defaultlauncher/functions.java` file
- Edit your website url, appdata name, launcher name etc...
- Edit messages as you want
- Replace `"https://yourwebsite.com/supdate/` by your website url where the s-update server will be hosted (Example: https://site.com/supdate/) <br>
💡 You can store s-update server in a folder on your website, so add the folder in your URL
- Change textures in `fr.ezzud.defaultlauncher.resources` if you want to edit textures
- Open the `src/fr.ezzud.defaultlauncher/Main.java` file
- At line 30, Change your Minecraft Game version:<br>

📢 **FOR 1.7.10**: ![image](https://user-images.githubusercontent.com/44119886/188249478-bf22ceeb-5f90-47e4-9dcc-dc2720e6d366.png)

📢 **FOR 1.8 TO 1.12**: ![image](https://user-images.githubusercontent.com/44119886/188249487-4c8b31fe-f704-43d7-931e-ae2d28b5ad55.png)

📢 **FOR 1.13 TO 1.16**: ![image](https://user-images.githubusercontent.com/44119886/188249508-81200964-2a38-4fa3-8439-e8e4c09de230.png)

📢 **TO REMOVE FORGE FROM THE LAUNCHER**: 
- At line 31, Remove `new GameTweak[] { GameTweak.FORGE }` by "null"
![image](https://user-images.githubusercontent.com/44119886/188250349-318d708c-bda2-4099-8a0d-afac735e8678.png)


- Extract your project as a Executable JAR and name it **launcher.jar** (Name is important)

</p>

<h3>III Setup the S-Update server</h3>

<p>

- Download the s-update server zip from the requirement section
- Upload the content of the ZIP file in a folder named **supdate** in your web hoster
- Wait for upload
- Access to the S-Update panel with `https://your-url.com/supdate`<br />
  ⚠ If you get 404 error, go on `https://your-url.com/supdate/index.php/`<br />
  ⚠ If after registering, you didn't get redirected, verify permission (Debian: `chmod -R 777 /var/www/html/supdate`)<br />
  ⚠ If you cannot setup password, go on <a href="https://ezzud.fr/download/server.json">this link</a> and paste content into `https://your-url.com/supdate/config/server.json`
- Create admin credentials (to reset, delete file **server.json** at update/config/)
- Enable S-Update by clicking on the circle (if it is already green, he is already activated) 
- Go on your **Appdata/Roaming/.minecraft/version** folder
- Go on the folder with the name of the version you chosen (NOT FORGE)
- Take the yourversion.jar file, copy and rename it by `minecraft.jar`
- Go back to your WebHoster FTP
- Access to the `files` folder (or create it)
- Select all these files:<br>
  - `minecraft.jar` that you got before
  - `assets` folder from your .minecraft appdata folder
  - `libraries` folder from your .minecraft appdata folder (💡TIP: On Windows, go to "libraries" folder and type in search bar "*.jar", then press CTRL+A to select all libraries)
  - `bin` folder from your .minecraft appdata folder (💡TIP: On Windows, go to "bin" folder and type in search bar "*.jar", then press CTRL+A to select all libraries)


⚠ **I recommend you to launch Minecraft in your desired version in another appdata folder to get ONLY the assets, libraries & natives for the version you want**<br />
⚠ **YOU NEED TO RENAME "libraries" FOLDER TO "libs"**<br />
⚠ **YOU NEED TO RENAME "bin" FOLDER TO "natives"**<br />
⚠ **If you don't have "bin" folder**, try to delete the bin folder and launch your Minecraft with the correct forge version<br />
Also you can download 1.12.2 Minecraft natives <a href="https://ezzud.fr/download/1.12.2-natives.zip">here</a>
  
- Place all selected files into the **files** folder in the S-UPDATE

- Add other folders like "mods" folder or anything your launcher need
- Open the `supdate/config/ignore.list` file and add every folder and file you want not to be deleted during launcher updates<br>
💡 Recommended list:<br>
  ```
  options.txt
  saves/
  resourcepacks/
  resources/
  shaderpacks/
  logs/
  crash-reports/
  screenshots/
  launcher.properties
  ram.txt
  launcher/
  game/
  journeymap/
  ```
- Every time you change the `files` folder in your FTP, when launcher starts it will update the new files & folders
- Save the file and now your launcher will work when you launch `launcher.jar`

<h4>🎤 If you don't want to add a launcher updater, you are free to stop reading because the next part will talk about setting-up the bootstrap</h4>

</p>


<h3>IV Setup the Bootstrap</h3>

<p>

- Open or Import **bootstrap** project in your Java IDE 
- Open the `src/fr.ezzud.defaultbootstrap/functions.java` file
- Edit the file with your informations
- Replace supdate URL by your supdate url with **/bootstrap** after (Example: https://yourwebsite.com/supdate/bootstrap/)<br>
Example: "https://example.com/update/bootstrap"<br />
  ⚠ If you get 404 error, go on `https://example.com/supdate/bootstrap/index.php/`<br />
  ⚠ If after registering, you didn't get redirected, verify permission (Debian: `chmod -R 777 /var/www/html/supdate`)<br />
  ⚠ If you cannot setup password, go on <a href="https://ezzud.fr/download/server.json">this link</a> and paste content into `https://your-url.com/supdate/bootstrap/config/server.json`
- Extract the project as a JAR Executable and name it `bootstrap.jar` (not important)

- **In the S-Update folder**, create a new folder named `bootstrap`
- Duplicate __the entire__ supdate folder except **bootstrap** folder and **files** folder
- In the freshly created bootstrap folder, create a `files` folder
- Upload the your launcher.jar
- Create a `Libs` folder (with the cap) and upload all libraries used in the `launcher project`
- Now when you start `bootstrap.jar` it will check if launcher is updated and if so the launcher will start

</p>

<h4>❓ For any help, join my discord at https://ezzud.fr/discord</h4>
