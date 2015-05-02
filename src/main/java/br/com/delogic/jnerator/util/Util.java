package br.com.delogic.jnerator.util;

import java.util.Random;

public class Util {

    private static final Random random = new Random();

    public static final int validIndex(int index, int size) {
        return index % size;
    }

    public static final int nextInt(int limit){
        return random.nextInt(limit);
    }

    public static final long nextLong(){
        return random.nextLong();
    }

    public static final boolean nextBoolean(){
        return random.nextBoolean();
    }

}
