# Hexawatch

_An artistic watch face for Android Wear and Gear S2_


![hexawatch][]


## How to tell the time? (hint: it's easy once you know how)

• The 12 triangles around the outside of the clock indicate hours (from 1 to 12) in the same position as in a conventional clock

• The 6 large triangles at each side of the inner hexagon indicate the first digit of the minutes (0X, 1X, 2X, 3X, 4X, 5X)

• The digit displayed at the center of the hexagon is the last digit of the minutes (0 to 9)


## Build information

### Generate Java model from protobuf file

```sh
protoc common/src/main/proto/config.proto --java_out=common/src/main/java/
```


[hexawatch]: https://raw.githubusercontent.com/Nilhcem/hexawatch/master/assets/screenshot.jpg
