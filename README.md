<p align="center"><img src="http://i1064.photobucket.com/albums/u370/MegaT145/Magistics/logo.png"/></p>
***

**_Adding some logistics to Thaumcraft!_**

<a href="https://codeship.com/projects/52617" target="_blank"><img src="https://codeship.com/projects/49546e30-64a5-0132-1a9a-1a1c11ba4c94/status?branch=master"/></a>

## Workspace Setup
Simply run the following commands and you too can be working on Magistics like a boss!
``` bash
gradle setupWorkspace
gradle setupDecompWorkspace eclipse
```
If your using the Gradle wrapper, then just replace `gradle` with the wrapper executable that's commpatible with your OS.

## License
All of the wonderful license information is located in the project's license file. Please consult the license if you wish to use the source code and [myself](https://github.com/T145) for any of the assets.

## Contributing [![Stories in Ready](https://badge.waffle.io/t145/magistics.svg?label=ready&title=Ready)](http://waffle.io/t145/magistics) 
1. Fork it
2. Add or remove some stuff
3. Commit your changes (`git commit -am "My awesome change!"`)
4. Create a new pull request!

### Contribution Parameters
***

#### Code

##### Proper format
The main thing that I ask when making a new pull request is that none of the project's general formatting be affected. This is so that other formats do not conflict w/ what is existing, and avoids giant blocks of highlighted code in Git commits. However, it's also because the code has a great appearance. Code isn't meant to just be functional, it's meant to look good too!

##### Keep a majority of the variables accessible
Whenever new variables, classes, etc. are added to code, please be sure that most are public. This is so that Magistics can maintain a modular atmosphere, and other mods can override things with ease. In general, I only make things private that serve to help not type as much, e.g. `private static BlockStoneApparatus.Types = types;`, or for the application of getter and setter methods.

#### Resources

##### Consistent style
Just be certain that the image style is consistent with the rest of the mod; it is the only proper requirement.
