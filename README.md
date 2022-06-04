# Minecraft Launcher Template

<h2>✨ Features:</h2>

<p>

- 🎈 Custom appdata name
- 🎈 Custom window
- ⌛ Auto .appdata update from a hosted website
- ⌛ Mojang auth check
- ⌛ Auto launcher update from a hosted website
- ✅ MCP Compatibility
- ✅ Full appdata customization
- ✨Easily Configurable

</p>

<h2>🛑 Requirements:</h2>

<p>

- [OpenAuth v1.1.2](https://ezzud.fr/others/openauth-1.1.2.jar)
- **FOR LAUNCHER ONLY** [OpenLauncherLib v3.0.5](https://ezzud.fr/others/openlauncherlib-3.0.5.jar)
- **FOR BOOTSTRAP ONLY** [OpenLauncherLib v2.1](https://ezzud.fr/others/openlauncherlib-2.1-SNAPSHOT.jar)
- [S-Update v3.1.0](https://ezzud.fr/others/s-update-3.1.0-BETA.jar)
- [Swinger v1.0.0](https://ezzud.fr/others/swinger-1.0.0-BETA.jar)
- Any Web Hoster (with php incuded)
- [S-Update server-side v3.1.0](https://github.com/Litarvan/S-Update-Server/releases)

</p>

<h2>📜 Installation:</h2>

<h3>I Install the workspace</h3>
<p>

- Download all requirements from the requirement section 
- Open your Java IDE (Eclipse, IntelliJ)
- Import the **launcher** folder as a java project


</p>

<h3>II Edit your launcher</h3>

<p>

- Open the `src/fr.ezzud.defaultlauncher/functions.java` file
- Edit your website url, appdata name, launcher name etc...
- Edit messages as you want
- Select between "microsoft" and "mojang" authentication (Microsoft is highly recommended)
- Replace *S-UPDATE URL* by your website url where the s-update server will be hosted <br>
💡 You can store s-update server in a folder on your website, so add the folder in your URL
- Change textures in `fr.ezzud.defaultlauncher.resources` if you have textures...
- Open the `src/fr.ezzud.defaultlauncher/Main.java` file
- At line 29, Change your Minecraft Game version:<br>
📢 For example, if you want to make a 1.8-1.12 launcher, replace by ```new GameVersion("1.12.2 or any version between 1.8 and 1.12", GameType.V1_8_HIGHER);```<br>
📢 If you want to make a 1.13+ launcher, replace by ```new GameVersion("1.13.2 or any version higher than 1.13", GameType.V1_13_HIGHER_FORGE);```
- Extract your project as a Executable JAR and name it **launcher.jar** (Name is important)

</p>

<h3>III Setup the S-Update server</h3>

<p>

- Download the s-update server zip from the releases section
- Upload the content of the ZIP file in a folder named **update** in your web hoster
- Wait for upload
- Access to the S-Update panel with `https://your-url.com/update`<br />
  ⚠ If you get 404 error, go on `https://your-url.com/update/index.php/`<br />
  ⚠ If after registering, you didn't get redirected, verify permission (Debian: `chmod -R 777 /var/www/html/update`)<br />
- Create admin credentials (to reset, delete file **server.json** at update/config/)
- Enable S-Update by clicking on the circle (if it is already green, he is already activated) 
- Go on your **Appdata/Roaming/.minecraft/version** folder
- Go on the folder with the name of the version you chosen
- Take the yourversion.jar file and rename it by `minecraft.jar`
- Refresh your Web Hoster FTP
- Access to the `files` folder (or create it)
- Drop:<br>
  - `minecraft.jar`
  - `assets` folder from your minecraft appdata
  - `libs` folder from your minecraft appdata"
  in the **files** folder

⚠ **I recommend you to launch Minecraft in your desired version in another appdata folder to get ONLY the assets & libraries for the version you want**
- Add other folder like "mods" folder or anything your launcher need
- Open the `supdate/config/ignore.list` file and add every folder and file you want not to be deleted during launcher updates<br>
💡 Recommended list:<br>
  ```
  options.txt
  optionsof.txt
  saves/
  resourcepacks/
  resources/
  shaderpacks/
  logs/
  crash-reports/
  screenshots/
  username.txt
  ram.txt
  launcher/
  journeymap/
  ```
- Every time you change the `files` folder in your FTP, when launcher starts it will update the new files & folders
- Save the file and now your launcher will work when you launch `launcher.jar`, but how can you update your launcher?? (you will see after)

</p>


<h3>IV Setup the Bootstrap</h3>

<p>

- Import the **bootstrap** source folder in your Java IDE 
- Open the `src/fr.ezzud.defaultbootstrap/functions.java` file
- Edit the file with your informations
- Replace *YOUR S-UPDATE BOOTSTRAP FOLDER* by your supdate url with **/bootstrap**<br>
Example: "https://example.com/update/bootstrap"<br />
  ⚠ If you get 404 error, go on `https://example.com/update/bootstrap/index.php/`<br />
  ⚠ If after registering, you didn't get redirected, verify permission (Debian: `chmod -R 777 /var/www/html/update`)<br />
- Extract the project as a JAR Executable and name it `bootstrap.jar` (not important)
- **In the S-Update folder**, create a new folder named `bootstrap`
- Duplicate __the entire__ supdate folder except **bootstrap** folder and **files** folder
- In the bootstrap folder, create a `files` folder
- Upload the your launcher.jar
- Create a `Libs` folder (with the cap) and upload all libraries in him
- Now when you start `bootstrap.jar` it will check if launcher is updated and if so the launcher will start

</p>

<h4>❓ For any help, join my discord at https://ezzud.fr/discord</h4>
