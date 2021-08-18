package com.iths.lab2.springlab;

import javax.persistence.*;

@Entity
@Table(name= "Moviecharacters")
public class Moviecharacters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String character;
    private String movie;


    public Moviecharacters(int id, String character, String movie) {
        this.id = id;
        this.character = character;
        this.movie = movie;
    }

    public Moviecharacters() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
