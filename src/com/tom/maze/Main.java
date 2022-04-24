package com.tom.maze;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        MazePresenter presenter;
        TimeIt timeIt_;
        Grid grid;

//        // Binary Tree algo
//        timeIt_ = new TimeIt(new BinaryTreeMazeBuilder(), 20, 20);
//        System.out.println("Binary Tree algo: " +  Arrays.toString(timeIt_.timeIt(2000, 5)));
//
//        grid = new Grid(10, 10);
//        BinaryTreeMazeBuilder binary = new BinaryTreeMazeBuilder(grid);
//        binary.buildMaze();
//        showMaze(grid);
//
//        // Recursive Backtracker algo
//        timeIt_ = new TimeIt(new RecursiveBacktrackerMazeBuilder(), 20, 20);
//        System.out.println("Recursive Backtracker algo: " +  Arrays.toString(timeIt_.timeIt(2000, 5)));
//
//        grid = new Grid(10, 10);
//        RecursiveBacktrackerMazeBuilder backtracker = new RecursiveBacktrackerMazeBuilder(grid);
//        backtracker.buildMaze();
//        showMaze(grid);
//
//        // Prim's algo
//        timeIt_ = new TimeIt(new PrimsMazeBuilder(), 20, 20);
//        System.out.println("Prim's algo: " +  Arrays.toString(timeIt_.timeIt(2000, 5)));
//
//        grid = new Grid(10, 10);
//        PrimsMazeBuilder prims = new PrimsMazeBuilder(grid);
//        prims.buildMaze();
//
//        presenter = new MazePresenter(grid, "Prim's");
//        presenter.saveImage("prims.png");
//
//        // Aldous-Broder algo
//        timeIt_ = new TimeIt(new AldousBroderMazeBuilder(), 20, 20);
//        System.out.println("Aldous-Broder algo: " +  Arrays.toString(timeIt_.timeIt(2000, 5)));
//
//        grid = new Grid(10, 10);
//        AldousBroderMazeBuilder aldousBroder = new AldousBroderMazeBuilder(grid);
//        aldousBroder.buildMaze();
//        showMaze(grid);
//
//        presenter = new MazePresenter(grid, "Aldous-Broder");
//        presenter.render();
//        presenter.saveImage("aldousbroder.png");
//
//        // Sidewinder algo
//        timeIt_ = new TimeIt(new SidewinderMazeBuilder(), 20, 20);
//        System.out.println("Sidewinder algo: " +  Arrays.toString(timeIt_.timeIt(2000, 5)));
//
//        grid = new Grid(10, 10);
//        SidewinderMazeBuilder sidewinder = new SidewinderMazeBuilder(grid);
//        sidewinder.buildMaze();
//        showMaze(grid);
//
//        presenter = new ImagePresenter(grid, "Sidewinder");
//        presenter.saveImage("sidewinder.png");
//
//        // Kruskal's algo
//        timeIt_ = new TimeIt(new Old5KruskalsMazeBuilder(), 20, 20);
//        System.out.println("Kruskal's algo: " +  Arrays.toString(timeIt_.timeIt(100, 5)));
//
        grid = new Grid(10, 10);
        KruskalsMazeBuilder kruskals = new KruskalsMazeBuilder(grid);
        kruskals.buildMaze();

        presenter = new MazePresenter(grid, "Kruskal's");
        presenter.showImage();
//
//        // Eller's algo
//        timeIt_ = new TimeIt(new EllersMazeBuilder(), 20, 20);
//        System.out.println("Eller's algo: " +  Arrays.toString(timeIt_.timeIt(1000, 5)));
//
//        grid = new Grid(10, 10);
//        EllersMazeBuilder ellers = new EllersMazeBuilder(grid);
//        ellers.buildMaze();
//
//        presenter = new MazePresenter(grid, "Eller's");
//        presenter.showImage();
//
//        // Hunt and Kill algo
//        timeIt_ = new TimeIt(new HuntAndKillMazeBuilder(), 20, 20);
//        System.out.println("Hunt and Kill algo: " +  Arrays.toString(timeIt_.timeIt(1000, 5)));
//
        grid = new Grid(10, 10);
        HuntAndKillMazeBuilder huntkill = new HuntAndKillMazeBuilder(grid);
        huntkill.buildMaze();

        presenter = new MazePresenter(grid, "Hunt and Kill");
        presenter.showImage();
//
//        // Wilson's algo
//        timeIt_ = new TimeIt(new WilsonsMazeBuilder(), 20, 20);
//        System.out.println("Wilson's algo: " +  Arrays.toString(timeIt_.timeIt(1000, 5)));
//
//        grid = new Grid(10, 10);
//        WilsonsMazeBuilder wilsons = new WilsonsMazeBuilder(grid);
//        wilsons.buildMaze();
//
//        presenter = new MazePresenter(grid, "Wilson's");
//        presenter.saveImage("wilsons.png");
//
//        // Recursive division algo
//        timeIt_ = new TimeIt(new RecursiveDivisionMazeBuilder(), 20, 20);
//        System.out.println("Recursive division algo: " +  Arrays.toString(timeIt_.timeIt(1000, 5)));
//
//        grid = new Grid(10, 10);
//        RecursiveDivisionMazeBuilder division = new RecursiveDivisionMazeBuilder(grid);
//        division.buildMaze();
//
//        presenter = new MazePresenter(grid, "Recursive division");
//        presenter.showImage();
//        presenter.printToConsole();

        Map<String, MazeBuilder> algos = new HashMap<>() {{
            put("Binary Tree", new BinaryTreeMazeBuilder());
            put("Sidewinder", new SidewinderMazeBuilder());
            put("Recursive Backtracker", new RecursiveBacktrackerMazeBuilder());
            put("Prim's", new PrimsMazeBuilder());
            put("Kruskal's", new KruskalsMazeBuilder());
            put("Eller's", new EllersMazeBuilder());
            put("Hunt and Kill", new HuntAndKillMazeBuilder());
            put("Aldous-Broder", new AldousBroderMazeBuilder());
            put("Wilson's", new WilsonsMazeBuilder());
            put("Recursive Division", new RecursiveDivisionMazeBuilder());
        }};
        StatisticGenerator stats = new StatisticGenerator(algos);
//        stats.writeToFile("stats_kruskals423.txt");
    }
}
