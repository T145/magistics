<p align="center"><img src="https://github.com/T145/magistics/blob/1.11.2/src/main/resources/logo.png"/></p>

***

Logistical magic!

[![DownloadCount](http://cf.way2muchnoise.eu/magistics.svg)](https://minecraft.curseforge.com/projects/magistics)
[![SupportedVersions](http://cf.way2muchnoise.eu/versions/For%20MC%20_magistics_all.svg)](https://minecraft.curseforge.com/projects/magistics)
[![Discord](https://user-images.githubusercontent.com/7288322/34429152-141689f8-ecb9-11e7-8003-b5a10a5fcb29.png)](https://discord.gg/UqUZn3U)

---
**_Table of Contents_**

1. [Dependencies](https://github.com/T145/magistics#dependencies)
2. [Workspace Setup](https://github.com/T145/magistics#workspace-setup)
3. [License](https://github.com/T145/magistics#development)
4. [Dev Support](https://github.com/T145/magistics#support)
5. [Contributing](https://github.com/T145/magistics/blob/1.11.2/.github/CONTRIBUTING.md)

---

## Dependencies

In order to get started with Minecraft mod development in this workspace, a few prerequisites are required:

1. [Git](https://git-scm.com/downloads)
2. [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (JDK 8)
3. *(Optional)* [Gradle](http://gradle.org/gradle-download/)

Each of the listed requirements contains a hyperlink that should take you directly to the correspondant download page.
Just download and install what is compatible with your OS.
Gradle is optional is because this workspace includes a Gradle wrapper,
so when executing commands that begin with `gradle`,
execute them with `gradlew` instead and everything will function normally.

If you're using OSX, I highly recommend using [Homebrew](https://brew.sh/),
and installing everything by executing the following commands:
```bash
brew cask install java
brew install gradle
```
If you don't have Apple's Command Line Utilities installed before installing Homebrew, Hombrew will install them automatically.

---

## Workspace Setup

Execute a file in the `scripts` from the project directory to build automatically.
```bash
./scripts/build
```
If you don't have Gradle installed, just run the script that corresponds to your OS and ends with a `w`.
Else just use a regular script.
Depending on your internet connection and the processing power of your machine, it may take a while to build.
For most people it takes about 10 minutes.
Once it completes, just open up the generated `eclipse` directory in your IDE of choice.
If you're using IntelliJ's IDEA,
there should be an Eclipse plugin that's automatically installed with it from version 13 and on for compatibility.
After the project loads, be sure to rename the project in the IDE to `magistics`.
This will likely force any installed Git plugins to reload, as is the case with Eclipse.

---

## License

Please consult the [official license](http://www.apache.org/licenses/LICENSE-2.0) if you wish to use the source code.
To use any of the assets, you may contatct [myself](https://github.com/T145) or the original artist for permission.

---

## Support

If you like my work and are interested in supporting me, go check out [Patreon](https://www.patreon.com/user?u=152139).
