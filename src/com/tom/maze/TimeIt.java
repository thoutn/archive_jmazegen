package com.tom.maze;

import java.time.Duration;
import java.time.Instant;

//public class TimeIt<T extends MazeBuilder> {
//    private final Constructor<? extends T> constructor;
//    private T builder;
//
//    int width;
//    int height;
//
//    public TimeIt(Class<? extends T> implemented) throws NoSuchMethodException {
//        this.constructor = implemented.getConstructor();
//
//        this.width = 20;
//        this.height = 20;
//        }
//
//    public float timeIt(int number) throws Exception {
//        Instant start = Instant.now();
//
//        for (int i = 0; i < number; i++) {
//            Grid grid = new Grid(width, height);
////            BinaryTreeMazeBuilder binary = new BinaryTreeMazeBuilder(grid);
//            builder = constructor.newInstance();
//            builder.buildMaze();
//        }
//
//        Instant stop = Instant.now();
//        return Duration.between(start, stop).toMillis() / 1000.0f;
//    }
//
//    public float[] timeIt(int number, int repeat) throws Exception {
//        float[] times = new float[repeat];
//
//        for (int i = 0; i < repeat; i++) {
//            times[i] = timeIt(number);
//        }
//
//        return times;
//    }
//}

public class TimeIt {
    MazeBuilder builder;
    int width;
    int height;
//    private final float baseTime;

    public TimeIt(MazeBuilder builder, int width, int height) {
        this.builder = builder;
        this.width = width;
        this.height = height;

//        Instant start = Instant.now();
//        int x = 0;
//        for (int i = 0; i < 10000; i++) {
//            for (int j = 0; j < 10000; j++) {
//                x = (x + 2 - 1) * 3;
//            }
//        }
//        Instant stop = Instant.now();
//        this.baseTime = Duration.between(start, stop).toNanos() / 1000000000.0f; //.toMillis() / 1000.0f;
    }

    public float timeIt(int number) {
        Instant start = Instant.now();

        for (int i = 0; i < number; i++) {
            builder.setGrid(new Grid(width, height));
            builder.buildMaze();
        }

        Instant stop = Instant.now();
        return Duration.between(start, stop).toNanos() / 1000000000.0f; //.toMillis() / 1000.0f;
    }

    public float[] timeIt(int number, int repeat) {
        float[] times = new float[repeat];

        for (int i = 0; i < repeat; i++) {
            times[i] = timeIt(number); // / baseTime;
        }

        return times;
    }
}