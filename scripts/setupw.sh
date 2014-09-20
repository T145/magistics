FORGE_VERSION=1.7.10-10.13.1.1217

curl -L -O -k http://files.minecraftforge.net/maven/net/minecraftforge/forge/$FORGE_VERSION/forge-$FORGE_VERSION-src.zip
mkdir forge && mkdir eclipse/.metadata
unzip forge-$FORGE_VERSION-src.zip -d forge
mv forge/eclipse/.metadata eclipse/.metadata
rm -rf forge && rm -rf forge-$FORGE_VERSION-src.zip
bash gradlew setupDecompWorkspace && bash gradlew eclipse