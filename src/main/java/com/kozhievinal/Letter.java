package com.kozhievinal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Letter {
    public char letter;
    public int count;

    public Letter() {

    }

    public Letter(char letter, int count) {
        this.letter = letter;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "letter=" + letter +
                ", count=" + count +
                '}';
    }
}
