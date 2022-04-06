package me.Input;

import me.RandomInt;

public class Two {
    @RandomInt(max = 10)
    private int value;

    private int getValue() {
        return value;
    }
}
