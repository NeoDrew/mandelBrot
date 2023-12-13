# mandelBrot

A simple Mandelbrot set generator

### Set Preview with Default Settings
<p align="left">
  <img src="generation1.png" width="400" alt="Set Preview with Default Settings">
</p>

### How to use

1. Tweak the following settings in `Mandel.java` to achieve your desired illustration. 

```java
    final double POINTSIZE = 0.3; //Domain of depends on graphPrecision
    final double windowSize = 500; //Adjust the window size
    final double scale = 1.4; //Adjust scale for graph: scale = a ---> x : [-a,a], y : [-a,a]
    final double graphPrecision = 0.3; //Precision of points. 1/graphPrecision = # of points on unit line
```
2. Compile & run with the following commands

```bash
export PATH_TO_FX=javafx-sdk-21.0.1/lib
javac --module-path $PATH_TO_FX --add-modules javafx.controls Mandel.java
java --module-path $PATH_TO_FX --add-modules javafx.controls Mandel
```

Feel free to Use .vscode/settings.json for intelliCode.

### Wikipedia 📕
To learn more about the mandelbrot set, [click here](https://en.wikipedia.org/wiki/Mandelbrot_set).

### Future Plans 🚀
* Optimse, optimise, optimise.
* Iteractable GUI to allow new set generations without re-compilation.
* An iterative zoom, allowing users to pick a point and zoom in on it.
