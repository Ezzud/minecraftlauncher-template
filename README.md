<h1><a href="https://github.com/Ezzud/minecraftlauncher-template"><img src="https://github.com/Ezzud/minecraftlauncher-template/raw/main/launcher/src/fr/ezzud/defaultlauncher/resources/icon.png" width="34px" height="34px"/> Ezzud's Minecraft Launcher Template</h1>

<p>
- <a href="https://github.com/Ezzud/minecraftlauncher-template/tree/main#-requirements">Requirements</a><br />
- <a href="https://github.com/Ezzud/minecraftlauncher-template/tree/main#i-install-the-workspace">I Setup Workspace</a><br />
- <a href="https://github.com/Ezzud/minecraftlauncher-template/tree/main#ii-setup-launcher">II Setup Launcher</a><br />
- <a href="https://github.com/Ezzud/minecraftlauncher-template/tree/main#iii-setup-the-s-update-server">III Setup S-Update</a><br />
- <a href="https://github.com/Ezzud/minecraftlauncher-template/tree/main#iv-setup-the-bootstrap">IV Setup Bootstrap</a><br />
- <a href="https://github.com/Ezzud/minecraftlauncher-template/tree/main#-for-any-help-join-my-discord-at-httpsezzudfrdiscord">Support</a><br />
</p>


<h1>DEMO</h1>

Launcher Demo:<br/>
![image](https://user-images.githubusercontent.com/44119886/188251148-1d898248-5af0-4d96-801d-9c5c328ff515.png)<br/>

Bootstrap Demo:<br/>
![image](https://user-images.githubusercontent.com/44119886/188251161-403a879a-3083-4237-b76f-8f8e442f9ca1.png)<br/>


<h1>✨ Features</h1>

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

<h1>🛑 Requirements</h1>

<p>

> **FOR LAUNCHER ONLY** [OpenAuth v1.1.2](https://ezzud.fr/download/openauth-1.1.2.jar)<br />
> **FOR LAUNCHER ONLY** [OpenLauncherLib v3.0.5](https://ezzud.fr/download/openlauncherlib-3.0.5.jar)<br />
> **FOR LAUNCHER ONLY** [Fernet v1.5.0](https://ezzud.fr/download/fernet-java8-1.5.0.jar)<br />
> **FOR LAUNCHER ONLY** [Apache Commons Codec v1.15](https://ezzud.fr/download/commons-codec-1.15.jar)<br />
> **FOR BOOTSTRAP ONLY** [OpenLauncherLib v2.1](https://ezzud.fr/download/openlauncherlib-2.1-SNAPSHOT.jar)<br />
> [S-Update v3.1.0](https://ezzud.fr/download/s-update-3.1.0-BETA.jar)<br />
> [Swinger v1.0.0](https://ezzud.fr/download/swinger-1.0.0-BETA.jar)<br />

> Any Web Hoster (with php incuded)<br />
> **WEBHOSTER** [S-Update server-side v3.1.0](https://github.com/Litarvan/S-Update-Server/releases/download/3.1.0-BETA/s-update-server-3.1.0.zip)<br />


</p>

<h1>📜 Installation</h1>

<h2>I Install the workspace</h2>
<p>

1.0 - Download all requirements from the requirement section <br />
1.1 - Open your Java IDE (Eclipse, IntelliJ)<br />
1.2 - Optional: With Eclipse IDE, open the ".project" file on launcher & bootstrap (Easier)<br />
1.3 - Create a JavaProject for Launcher, AND ANOTHER for Bootstrap (⚠ Launcher & Bootstrap are 2 different projects)<br />
1.4 - Import respective launcher & bootstrap files into each project<br />
1.5 - Import required Libraries<br />


</p>

<h2>II Setup Launcher</h2>

<p>

2.0 - (IDE) Open the `src/fr.ezzud.defaultlauncher/functions.java` file<br />
2.1 - (IDE) Edit your website url, appdata name, launcher name etc...<br />
2.2 - (IDE) Edit messages as you want<br />
2.3 - (IDE) Replace `https://yourwebsite.com/supdate/` by your website url where the s-update server will be hosted (Example: https://site.com/supdate/)<br />
💡 You can store s-update server in a folder on your website, so add the folder in your URL<br />
2.4 - (MISC) Change textures in `fr.ezzud.defaultlauncher.resources` if you want to edit textures<br />
2.5 - (IDE) Open the `src/fr.ezzud.defaultlauncher/Main.java` file<br /><br />
2.6 - (IDE) At line 30, Change your Minecraft Game version:<br />
📢 **FOR 1.7.10**: ![image](https://user-images.githubusercontent.com/44119886/188249478-bf22ceeb-5f90-47e4-9dcc-dc2720e6d366.png)<br />
📢 **FOR 1.8 TO 1.12**: ![image](https://user-images.githubusercontent.com/44119886/188249487-4c8b31fe-f704-43d7-931e-ae2d28b5ad55.png)<br />
📢 **FOR 1.13 TO 1.16**: ![image](https://user-images.githubusercontent.com/44119886/188249508-81200964-2a38-4fa3-8439-e8e4c09de230.png)<br /><br />
📢 **IF YOU WANT TO REMOVE FORGE FROM THE LAUNCHER**: <br />
        <p>
> (IDE) At line 31, Remove `new GameTweak[] { GameTweak.FORGE }` by "null"<br />
        ![image](https://user-images.githubusercontent.com/44119886/188250349-318d708c-bda2-4099-8a0d-afac735e8678.png)<br /><br />
</p>


2.7 - (IDE) Extract your project as a Executable JAR and name it **launcher.jar** (Name is important)<br />

</p>

<h2>III Setup the S-Update server</h2>

<p>

3.0 - (MISC) Download the s-update server zip from the requirement section<br />
3.1 - (FTP) Upload the content of the ZIP file in a folder named **supdate** in your web hoster<br />
3.2 - Wait for upload<br />
3.3 - (WEB) Access to the S-Update panel with `https://your-url.com/supdate`<br />
  ⚠ If you get 404 error, go on `https://your-url.com/supdate/index.php/`<br />
  ⚠ If after registering, you didn't get redirected, verify permission (Debian: `chmod -R 777 /var/www/html/supdate`)<br />
  ⚠ If you cannot setup password, go on <a href="https://ezzud.fr/download/server.json">this link</a> and paste content into `https://your-url.com/supdate/config/server.json`<br />
3.4 - (WEB) Create admin credentials (to reset, delete file **server.json** at update/config/)<br />
3.5 - (WEB) Enable S-Update by clicking on the circle (if it is already green, he is already activated) <br />
3.6 - (MISC) Go on your **Appdata/Roaming/.minecraft/version** folder<br />
3.7 - (MISC) Go on the folder with the name of the version you chosen (NOT FORGE)<br />
3.8 - (MISC) Take the yourversion.jar file, copy and rename it by `minecraft.jar`<br />
3.9- (FTP) Go back to your WebHoster FTP<br />
3.10 - (FTP) Access to the `files` folder (or create it)<br />
3.11 - (FTP) Select all these files:<br>
  - `minecraft.jar` that you got before
  - `assets` folder from your .minecraft appdata folder
  - `libraries` folder from your .minecraft appdata folder (💡TIP: On Windows, go to "libraries" folder and type in search bar "*.jar", then press CTRL+A to select all libraries)
  - `bin` folder from your .minecraft appdata folder (💡TIP: On Windows, go to "bin" folder and type in search bar "*.jar", then press CTRL+A to select all libraries)


⚠ **I recommend you to launch Minecraft in your desired version in another appdata folder to get ONLY the assets, libraries & natives for the version you want**<br />
⚠ **YOU NEED TO RENAME "libraries" FOLDER TO "libs"**<br />
⚠ **YOU NEED TO RENAME "bin" FOLDER TO "natives"**<br />
⚠ **If you don't have "bin" folder**, try to delete the bin folder and launch your Minecraft with the correct forge version<br />
Also you can download 1.12.2 Minecraft natives <a href="https://ezzud.fr/download/1.12.2-natives.zip">here</a><br /><br />
  
3.12 - (FTP) Place all selected files into the **files** folder in the S-UPDATE

3.13 - (FTP) Add other folders like "mods" folder or anything your launcher need (mods folder, config folder, every folder that is normally on .minecraft folder<br />
3.14 - (FTP) Open the `supdate/config/ignore.list` file and add every folder and file you want not to be **deleted/replaced by original version** during launcher updates<br />
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
3.15 - (FTP) Every time you change the `files` folder in your FTP, when launcher starts it will update the new files & folders<br />
3.16 - (FTP) Save the file and now your launcher will work when you launch `launcher.jar`<br />

<h4>🎤 If you don't want to add a launcher updater, you are free to stop reading because the next part will talk about setting-up the bootstrap</h4>

</p>


<h2>IV Setup the Bootstrap</h2>

<p>

4.0 - (IDE) Open or Import **bootstrap** project in your Java IDE <br />
4.1 - (IDE) Open the `src/fr.ezzud.defaultbootstrap/functions.java` file<br />
4.2 - (IDE) Edit the file with your informations<br />
4.3 - (IDE) Replace supdate URL by your supdate url with **/bootstrap** after the url<br />(Example: https://yourwebsite.com/supdate/bootstrap/)<br>
  ⚠ If you get 404 error, go on `https://example.com/supdate/bootstrap/index.php/`<br />
  ⚠ If after registering, you didn't get redirected, verify permission (Debian: `chmod -R 777 /var/www/html/supdate`)<br />
  ⚠ If you cannot setup password, go on <a href="https://ezzud.fr/download/server.json">this link</a> and paste content into `https://your-url.com/supdate/bootstrap/config/server.json`<br />
4.4 - (IDE) Extract the project as a JAR Executable and name it `bootstrap.jar` (not important)<br />

4.5 - (FTP) **In the S-Update folder**, create a new folder named `bootstrap`<br />
4.6 - (FTP) Duplicate __the entire__ supdate folder ⚠ except **bootstrap** folder and **files** folder<br />
4.7 - (FTP) In the freshly created bootstrap folder, create a `files` folder<br />
4.8 - (FTP) Upload your launcher.jar (the JAR of the launcher from the Part 2)<br />
4.9 - (FTP) Create a `Libs` folder (with the cap) and upload all libraries used in the `launcher project`<br />
4.10 - Now when you start `bootstrap.jar` it will check if launcher is updated and if so the launcher will start<br />

</p>

<h1>❓ For any help, join my discord at https://ezzud.fr/discord</h1>
