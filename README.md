# Alexei

## Overview
Alexei is a type-safe interface of image processing algorithms.

## Screenshots

# Demo

I'll soon post the Google Play demo here.

## Setup
I'll soon post the aar link here.

## Usage

The basic API declaration is quite simple:

``` java
Alexei.analize(image)
      .perform(calculus)
      .andGiveMe(answer);
```

It's like saying: *"Alexei, analize this image, perform some calculus
and give me the answer!"*

### Example

``` java
Alexei.analize(imageView)
      .perform(ImageProcessingThing.DOMINANT_COLOR)
      .andGiveMe(new Answer<Color>(){
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
[ImageProcessingThing](). You check them below:

* [Dominant Color]()
* [Color Palette]()
* [Average RGB]()

### Custom Calculus

Create a custom calculus and implement a method called **theCalculation**, returning
the object you expect to calculate. The Answer generic type must match your custom calculus generic type.

``` java

Alexei.analize(image)
        .perform(new Calculus<YourObject>() {
            @Override
            protected YourObject theCalculation(Bitmap image) {

                // do your bizarre stuff here!

                return new YourObject();
            }
        })
        .andGiveMe(new Answer<YourObject>() {
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
4. Add a flag to the new calculus in [ImageProcessingThing]()
5. Create a fragment in the demo app with the new calculations being applied in an image.
6. Do your pull-request.

### THE IMAGES OF THE DEMO APP MUST BE HOT BLOND GIRLS.


## TODO
* Figure-out a way to let Alexei perform synchronous AND/OR asynchronous calculus.
* Make it thread-safe. This way I won't need to wrap Alexei inside AsyncTasks or Handlers.
* Feed Alexei with more calculus.
* Thing


## Who the fu#!% is Alexei?
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
