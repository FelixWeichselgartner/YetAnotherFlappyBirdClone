package com.example.flappybird;

/**
 * Player class
 * keeps track of score and name
 */
public class Player {
    private int score;
    private String name = "player1";

    public Player() {
        score = 0;
    }

    public void setScore(int s) {
        score = s;
    }

    public void setName(String n) {
        name = n;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
