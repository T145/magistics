<p align="center"><img src="http://i1064.photobucket.com/albums/u370/MegaT145/Magistics/logo.png"/></p>
***

Adding some logistics to Thaumcraft!

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
gradle setupWorkspace eclipse
```
It may take a while to complete, depending on your internet connection and the processing power of your machine. On average it takes about 10 minutes for most people. Again, if you don't have Gradle installed, then just replace the `gradle` in that command with `gradlew`. Once it completes, just open up the generated `eclipse` directory in your IDE of choice. If you're using IntelliJ's IDEA, there should be an Eclipse plugin that's automatically installed with it from version 13 and on for compatibility.

---

## Development

To edit how this workspace functions for your own purposes, the only two files you need interact with are [custom.gradle](https://github.com/T145/ForgeWorkspace/blob/1.7.10/custom.gradle) and [gradle.properties](https://github.com/T145/ForgeWorkspace/blob/1.7.10/gradle.properties). The custom.gradle is where all of your normal build.gradle edits would go. In it is an example prompt that shows a message whenever the build command is issued (which is all the time), showing that any edits here will affect the build process properly. Keeping the build file and custom build setup separate allows for an extra degree of organization. The gradle.properties file is where all of the basic workspace information is set, such as the project name and which Forge version to use. Check here and edit it before building. Once both of those are in order you'll be all set to be begin programming!

## License [![Apache License](http://img.shields.io/badge/license-Apache--2-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Please consult the license at the provided link if you wish to use the source code. You may contact [myself](https://github.com/T145) for permission to use any of the assets.

---

## Contributing

This project is made specifically for the community and mainstream use! If you see something wrong with it or want to add a new feature then please fork it and make a contribution. If this actually helps you out, then tell your friends! For those of you that prefer lists and the standard `CONTRIBUTING.md` format, then glance below:

1. Fork it
2. Make changes
3. Commit changes
4. Make a pull request

Enjoy!
