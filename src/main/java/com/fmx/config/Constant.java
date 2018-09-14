package com.fmx.config;

import java.util.HashSet;
import java.util.Set;

public class Constant {
    public static final int squareSize = 10;
    public static final int fieldWidth = 1200;
    public static final int fieldHeight = 800;
    public static final int widthCount = Constant.fieldWidth / Constant.squareSize;
    public static final int heighCount = Constant.fieldHeight / Constant.squareSize;
    public static final int random_size = 50;
    public static final Set<Integer> aliveCondition = new HashSet<Integer>() {{
        add(2);
        add(3);
    }};
}
