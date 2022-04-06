package me.Input;

import me.RandomInt;

public class One {
    @RandomInt(min = 10, max = 30)
    private int value;

    private int getValue() {
        return value;
    }
}
