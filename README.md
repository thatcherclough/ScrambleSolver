# ScrambleSolver
ScrambleSolver is a simple word scramble solver.

## Features
ScrambleSolver can unscramble and solve word scrambles.

To do this, ScrambleSolver:
- Permutes a given word to find all possible character combinations.
- Cross references each combination with the 479k most common english words. (See [credits](https://github.com/thatcherclough/ScrambleSolver#credits))

## Demo
<a href="https://asciinema.org/a/DQ4A5FEbWC0zuDtATXFOpXRy5" target="_blank"><img src="https://asciinema.org/a/DQ4A5FEbWC0zuDtATXFOpXRy5.svg" width="600"/></a>

## Requirements
- A Java JDK distribution >=8 must be installed and added to PATH.

## Compatibility
ScrambleSolver is compatible with Windows, Mac, and Linux.

## Installation
```
# clone ScrambleSolver
git clone https://github.com/thatcherclough/ScrambleSolver.git

# change the working directory to ScrambleSolver
cd ScrambleSolver

# build ScrambleSolver with Maven
# for Windows run
mvnw.cmd clean package

# for Linux or Mac run
sh mvnw clean package
```

Alternatively, you can download the jar from the [release page](https://github.com/thatcherclough/ScrambleSolver/releases).

## Usage
```
java -jar scramblesolver.jar
ScrambleSolver: A simple word scramble solver (1.1.0)

Usage:
        java -jar scramblesolver.jar [-h] [-v] [-w WORD]

Arguments:
        -h, --help      Display this message.
        -v, --version   Display current version.
        -w, --word      Specify scramble to solve.
```

## Credits 
- [DWYL](https://github.com/dwyl) for the list of 479k english words. That repository can be found [here](https://github.com/dwyl/english-words). 

## License
- [MIT](https://choosealicense.com/licenses/mit/)
- Copyright 2020 © Thatcher Clough.
