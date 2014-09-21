FORGE_VERSION=1.7.10-10.13.1.1217

curl -L -O -k http://files.minecraftforge.net/maven/net/minecraftforge/forge/$FORGE_VERSION/forge-$FORGE_VERSION-src.zip
mkdir forge
unzip forge-$FORGE_VERSION-src.zip -d forge
cp -rf forge/eclipse/.metadata eclipse/.metadata
rm -rf forge && rm -rf forge-$FORGE_VERSION-src.zip
gradle setupDecompWorkspace && gradle eclipse