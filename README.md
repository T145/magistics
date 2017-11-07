<p align="center"><img src="https://github.com/T145/magistics/blob/1.11.2/src/main/resources/logo.png"/></p>

***

Logistical magic!

[![DownloadCount](http://cf.way2muchnoise.eu/magistics.svg)](https://minecraft.curseforge.com/projects/magistics)
[![SupportedVersions](http://cf.way2muchnoise.eu/versions/For%20MC%20_magistics_all.svg)](https://minecraft.curseforge.com/projects/magistics)

---
**_Table of Contents_**

1. [Dependencies](https://github.com/T145/magistics#dependencies)
2. [Workspace Setup](https://github.com/T145/magistics#workspace-setup)
3. [License](https://github.com/T145/magistics#development)
4. [Contributing](https://github.com/T145/magistics#contributing)
5. [Support](https://github.com/T145/magistics#support)

---

## Dependencies

In order to get started with Minecraft mod development in this workspace, a few prerequisites are required:

1. [Git](https://git-scm.com/downloads)
2. [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (JDK 8)
3. *(Optional)* [Gradle](http://gradle.org/gradle-download/)

Each of the listed requirements contains a hyperlink that should take you directly to the correspondant download page. Just download and install what is compatible with your OS. Gradle is optional is because this workspace includes a Gradle wrapper, so when executing commands that begin with `gradle`, execute them with `gradlew` instead and everything will function normally.

If you're using OSX, I highly recommend using [Homebrew](https://brew.sh/), and installing everything by executing the following commands:
```
brew cask install java
brew install gradle
```
If you don't have Apple's Command Line Utilities installed before installing Homebrew, Hombrew will install them automatically.

---

## Workspace Setup

Executing the following commands to prepare the workspace:
```
gradle setupWorkspace
gradle eclipse
gradle build
```
Depending on your internet connection and the processing power of your machine, it may take a while to build. For most people it takes about 10 minutes. Again, if you don't have Gradle installed, then just replace the `gradle` in those commands with `gradlew`. Once it completes, just open up the generated `eclipse` directory in your IDE of choice. If you're using IntelliJ's IDEA, there should be an Eclipse plugin that's automatically installed with it from version 13 and on for compatibility.

---

## License [![OfficialLicense](http://img.shields.io/badge/official%20license-Apache--2-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Please consult the license if you wish to use the source code. You may contact [myself](https://github.com/T145) for permission to use any of the assets.

---

## Contributing

This project is made specifically for the community and mainstream use! If you see something wrong with it or want to add a new feature, then please fork it and make a contribution.

---

## Support

If you like my work and are interested in supporting me, go check out [Patreon](https://www.patreon.com/user?u=152139).
