# jmazegen (*archived*)

A maze generator written in Java 17. The project contains several experimental
implementations of various algorithms to generate mazes.

## Folder structure

All the source code can be found in `src/com.tom/` folder. This folder has two 
sub-folders / packages:
- `maze/` contains the implementation of the following algorithms
  ([Recursive backtracker](https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search),
  [Prim's](https://en.wikipedia.org/wiki/Prim%27s_algorithm),
  [Kruskal's](https://en.wikipedia.org/wiki/Kruskal%27s_algorithm),
  [Hunt-and-kill](http://www.astrolog.org/labyrnth/algrithm.htm),
  [Eller's](http://www.neocomputer.org/projects/eller.html),
  [Backterial growth / Growing tree](https://weblog.jamisbuck.org/2011/1/27/maze-generation-growing-tree-algorithm.html)).
- `maze2/` contains less algorithm, and is a slight modification of the code above. 
It implements a custom `hashCode` to optimise performance. 

There are various implementations of the maze generator. Each variation of the 
algorithms has its own file. 

## How to install and run the project

Both packages contain their own `Main.java` file. Locate these files to run the 
project. 

## How to use the project

> **Note** *This project is archived - as written above, it includes all the experiments with the algorithms
that were considered during the development of the project. A clean and final version of the project can be
found [here](). You are strongly recommended using that one.*

### Example

To generate a maze using the Recursive Division algorithm, run the following code
```
grid = new Grid(10, 10);
RecursiveDivisionMazeBuilder division = new RecursiveDivisionMazeBuilder(grid);
division.buildMaze();
```

### Result

You can show the generated maze typing the following
```
presenter = new MazePresenter(grid, "Recursive division");
presenter.showImage();
```
or the resulting maze can be printed to the console with
```
presenter.printToConsole();
```

## Algo optimisation

<p align="center">
    <img src="images/comparison_all.png">
</p>

<p align="center">
    <strong> Figure 1:</strong> Comparison of all the implemented algorithm
</p>


<p align="center" width="100%">
    <img width="47%" src="images/comparison-btree.png">
    <img width="47%" src="images/comparison-ellers.png"> 
</p>
<p align="center" width="100%">
    <img width="47%" src="images/comparison-huntkill.png"> 
    <img width="47%" src="images/comparison-kruskals.png"> 

</p>
<p align="center" width="100%">
    <img width="47%" src="images/comparison-prims.png"> 
    <img width="47%" src="images/comparison-sidewinder.png"> 
</p>

<p align="center">
    <strong> Figure 2:</strong> Algorithms optimised
    <br>(from left to right and top to bottom - 
    <em>BTree</em>, <em>Eller's</em>, <em>Hunt and kill</em>, <em>Kruskal's</em>, 
    <em>Prim's</em>, <em>Sidewinder</em>)
</p>

## License

[MIT License](LICENSE)

