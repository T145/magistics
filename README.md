<p align="center"><img src="http://i1064.photobucket.com/albums/u370/MegaT145/Magistics/logo.png"/></p>
***

Logistical magic!

---
**_Table of Contents_**

1. [Dependencies](https://github.com/T145/magistics#dependencies)
2. [Workspace Setup](https://github.com/T145/magistics#workspace-setup)
3. [Development](https://github.com/T145/magistics#development)
4. [License](https://github.com/T145/magistics#development)
5. [Contributing](https://github.com/T145/magistics#contributing)

---

## Dependencies

In order to get started with Minecraft mod development in this workspace, a few prerequisites are required:

1. [Git](https://git-scm.com/downloads)
2. [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (JDK)
3. *(Optional)* [Gradle](http://gradle.org/gradle-download/)

Each of the listed requirements contains a hyperlink that should take you directly to the correspondant download page. Just download and install what is compatible with your OS. The reason Gradle is optional is because this workspace includes a Gradle wrapper, so when executing commands that begin with `gradle`, such a prefix can be replaced with `gradlew` and function normally.

---

## Workspace Setup

To begin coding, start by executing the following command:
```
gradle getDependencies setupWorkspace eclipse
```
It may take a while to complete, depending on your internet connection and the processing power of your machine. On average it takes about 10 minutes for most people. Again, if you don't have Gradle installed, then just replace the `gradle` in that command with `gradlew`. Once it completes, just open up the generated `eclipse` directory in your IDE of choice. If you're using IntelliJ's IDEA, there should be an Eclipse plugin that's automatically installed with it from version 13 and on for compatibility.

---

## Development

When run in the workspace for the first time, CodeChickenCore will prompt you for access to the MCP source mappings. These can be found at:
```
(User)\.gradle\caches\minecraft\net\minecraftforge\forge\(MinecraftForge Version)\unpacked\conf
```
It will remember this path for consecutive executions, so you're all set for here on out.

---

## License [![Apache License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Please consult the license at the provided link if you wish to use the source code. You may contact [myself](https://github.com/T145) for permission to use any of the assets.

---

## Contributing

This project is made specifically for the community and mainstream use! If you see something wrong with it or want to add a new feature, then please fork it and make a contribution.

*Enjoy!*
