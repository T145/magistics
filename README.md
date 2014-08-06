![logo](http://i1064.photobucket.com/albums/u370/MegaT145/Magistics/magistics_banner.png)
---

**_Adding some logistics to Thaumcraft!_**

## Workspace Setup
Luckily most of this has been taken care of! Simply run one of the scripts contained in the "scripts" directory (that's catered to your OS) from the project's root directory and you should be all set!

## License Stuff
The license and all of that wonderful information is located in the project's license file. All of the project source code falls under this license. *As for the assets (images and such), while they're included, they are being outlined as separate content!* They belong to the original creators, and require their explicit permission for usage outside of this project.

## Contributing
1. Fork it
2. Commit your changes (`git commit -m "My awesome change!"`)
3. Create a new pull request

### Parameters

#### Format properly
The main thing that I ask when making a new pull request is that none of the project's general formatting be affected. This is so that other formats do not conflict w/ what is existing, and avoids giant blocks of highlighted code in Git commits. However, it's also because it has a great appearance! Code isn't meant to just be functional; it's meant to look good too!

#### Public, public, public!
Whenever new variables, classes, etc. are added to code, please make sure that they are not private. This is so that Magistics can be 100% modular, and other mods can override various things w/ ease! The only time I ever make anything private is whenever I'm certain that it doesn't serve any purpose other than for one specific function that's usually geared to help not type as much, e.g. `private static BlockStoneApparatus.Types = types`;