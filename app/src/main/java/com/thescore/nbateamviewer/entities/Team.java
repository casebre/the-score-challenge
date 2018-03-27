package com.thescore.nbateamviewer.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rafael on 26/03/18.
 */

public class Team implements Serializable{

    private int id;
    private int wins;
    private int losses;
    private String full_name;
    private ArrayList<Player> players;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
