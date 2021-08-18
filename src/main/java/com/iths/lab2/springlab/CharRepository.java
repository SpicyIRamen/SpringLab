package com.iths.lab2.springlab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharRepository extends JpaRepository<Moviecharacters, Integer> {

    List<Moviecharacters> findAllById(int id);

    List<Moviecharacters> findAllByCharacter(String Character);

    List<Moviecharacters> findAllByMovie(String Movie);



}

