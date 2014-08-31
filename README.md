# Alexei

A type-safe interface of image processing algorithms.

## Screenshots
![](https://github.com/thiagokimo/Alexei/blob/master/screenshots/dominant-color.png)
![](https://github.com/thiagokimo/Alexei/blob/master/screenshots/blur.png)
![](https://github.com/thiagokimo/Alexei/blob/master/screenshots/palette.png)

# Demo

The sample application (the source is in the **app** folder) has been published on Google Play to facilitate the access:

[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](https://play.google.com/store/apps/details?id=com.kimo.examples.alexei)

## Setup

Gradle:

``` groovy
dependencies {
    compile 'com.github.thiagokimo:alexei-library:1.+'
}
```

Maven:

``` xml
<dependency>
    <groupId>com.github.thiagokimo</groupId>
    <artifactId>alexei-library</artifactId>
    <version>1.1</version>
</dependency>
```

## Usage

The basic API declaration is quite simple:

``` java
Alexei.with(context)
      .analize(image)
      .perform(calculus)
      .showMe(answer);
```

It's like saying: *"Alexei, analize this image, perform some calculus
and give me the answer!"*

### Example

``` java
Alexei.with(context)
      .analize(imageView)
      .perform(ImageProcessingThing.DOMINANT_COLOR)
      .showMe(new Answer<Color>(){
        @Override
        public void beforeExecution() {
          // anything you want to define before the calculation
        }

        @Override
        public void afterExecution(Color answer, long elapsedTime) {
            // your usual things after the calculation
        }

        @Override
        public void ifFails(Exception error) {
          // when shit happens, do your stuff here!
        }
      });
```

### Predefined Calculus
Alexei has some predefined image processing calculus that are accessible from the class
[ImageProcessingThing](https://github.com/thiagokimo/Alexei/blob/master/library/src/main/java/com/kimo/lib/alexei/ImageProcessingThing.java). You check them below:

* [Dominant Color](https://github.com/thiagokimo/Alexei/blob/master/library/src/main/java/com/kimo/lib/alexei/calculus/DominantColor.java)
* [Color Palette](https://github.com/thiagokimo/Alexei/blob/master/library/src/main/java/com/kimo/lib/alexei/calculus/ColorPalette.java)
* [Average RGB](https://github.com/thiagokimo/Alexei/blob/master/library/src/main/java/com/kimo/lib/alexei/calculus/AverageColor.java)
* [Blur](https://github.com/thiagokimo/Alexei/blob/master/library/src/main/java/com/kimo/lib/alexei/calculus/BlurCalculus.java)

### Custom Calculus

Create a custom calculus and implement a method called **theCalculation**, returning
the object you expect to calculate. The Answer generic type must match your custom calculus generic type.

``` java

Alexei.with(context)
        .analize(image)
        .perform(new Calculus<YourObject>() {
            @Override
            protected YourObject theCalculation(Bitmap image) {

                // do your bizarre stuff here!

                return new YourObject();
            }
        })
        .showMe(new Answer<YourObject>() {
            @Override
            public void beforeExecution() {}

            @Override
            public void afterExecution(YourObject answer, long elapsedTime) {}

            @Override
            public void ifFails(Exception error) {}
        });

```

## Contribuiting

To suggest a new feature/bug-fix:

1. Fork it
2. Create your feature/bug-fix branch(`git checkout -b my-new-feature-or-fix`)
3. Commit your changes (`git commit -am 'Add some feature/fix'`)
4. Do your pull-request

To add a new predefined calculus:

1. Fork it
2. Create your feature/bug-fix branch(`git checkout -b my-new-feature-or-fix`)
3. Create your calculus inside `calculus` package.
4. Add a flag to the new calculus in [ImageProcessingThing](https://github.com/thiagokimo/Alexei/blob/master/library/src/main/java/com/kimo/lib/alexei/ImageProcessingThing.java)
5. Create a fragment in the demo app with the new calculations being applied in an image.
6. Do your pull-request.

### THE IMAGES OF THE DEMO APP MUST BE HOT BLOND GIRLS.


## TODO
* Figure-out a way to let Alexei perform synchronous AND/OR asynchronous calculus.
* Feed Alexei with more predefined calculus.


## Who the fu#% is Alexei?
[Alexei](http://buscatextual.cnpq.br/buscatextual/visualizacv.do?metodo=apresentar&id=K4784376J9)
was one of my favorite professors in college. He's well known for its studies in
 **Image Processing** and **Computer Vision** fields.
It was almost impossible to not think in his class while I was writting this library :P

## License

    Copyright 2011, 2012 Thiago Rocha

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
