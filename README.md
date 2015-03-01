![logo](http://i1064.photobucket.com/albums/u370/MegaT145/Magistics/magistics_banner.png)
***

**_Adding some logistics to Thaumcraft!_**

<a href="https://codeship.com/projects/52617" target="_blank"><img src="https://codeship.com/projects/49546e30-64a5-0132-1a9a-1a1c11ba4c94/status?branch=master"/></a>

## Workspace Setup
Simply follow these steps and you too can be working on Magistics like a boss!
1) Plop your deobfuscated copy of Thaumcraft into the lib directory
2) Make a directory named "eclipse"
3) Run these commands:
``` bash
gradle setupWorkspace
gradle setupDecompWorkspace eclipse
```
If your using the Gradle wrapper, then just replace `gradle` with the wrapper executable that's commpatible with your OS.

## License
All of the wonderful license information is located in the project's license file. Please consult the license if you wish to use the source code and [myself](https://github.com/T145) for any of the assets.

## Contributing
1. Fork it
2. Add or remove some stuff
3. Commit your changes (`git commit -am "My awesome change!"`)
4. Create a new pull request!

### Parameters

#### Proper format
The main thing that I ask when making a new pull request is that none of the project's general formatting be affected. This is so that other formats do not conflict w/ what is existing, and avoids giant blocks of highlighted code in Git commits. However, it's also because the code has a great appearance. Code isn't meant to just be functional, it's meant to look good too!

#### Keep a majority of the variables accessible
Whenever new variables, classes, etc. are added to code, please be sure that most are public. This is so that Magistics can maintain a modular atmosphere, and other mods can override things with ease. In general, I only make things private that serve to help not type as much, e.g. `private static BlockStoneApparatus.Types = types;`, or for the application of getter and setter methods.
