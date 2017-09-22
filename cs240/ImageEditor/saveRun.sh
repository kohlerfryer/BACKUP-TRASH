clear
rm src/*.class
javac src/ImageEditor.java
java src/ImageEditor assets/pictures/slctemple.ppm  /Users/kittykatt/Desktop/devShelf/BYU/cs240/ImageEditor/assets/picturesEdited/slcTempleGrey.ppm grayscale
java src/ImageEditor assets/pictures/slctemple.ppm  /Users/kittykatt/Desktop/devShelf/BYU/cs240/ImageEditor/assets/picturesEdited/slcTempleInverted.ppm invert
java src/ImageEditor assets/pictures/slctemple.ppm  /Users/kittykatt/Desktop/devShelf/BYU/cs240/ImageEditor/assets/picturesEdited/slcTempleEmbossed.ppm emboss


open /Users/kittykatt/Desktop/devShelf/BYU/cs240/ImageEditor/assets/picturesEdited/slcTempleEmbossed.ppm