package com.iths.lab2.springlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {

    private CharRepository charRepository;

    @Autowired
    public Controller(CharRepository charRepository) { this.charRepository = charRepository;}

    @GetMapping("/batman")
    public List<Moviecharacters> batman(){

        // charRepository.save(new Registry( "Batman","Batman Begins"));

        return charRepository.findAllById(1);
    }
    //CREATE
    @PostMapping("/character")
    @ResponseStatus(HttpStatus.CREATED)     //Gives a 201 CREATED response instead of simply 200 OK.
    public ResponseEntity<?> createCharacter(@RequestBody Moviecharacters character) {
        if (charRepository.existsById(character.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }else
            System.out.println("New movie character added to database");
        charRepository.save(character);
        return ResponseEntity.ok("New entry to database:\n" + character.getId() + "\n" + character.getCharacter() +
                "\n" + character.getMovie() + "\nhas been created.");
    }

    //GET
    @GetMapping("/all")
    public List<Moviecharacters> listAllCharacters(){

        // charRepository.save(new Registry( "character","movie",number));

        return charRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Moviecharacters findMovieCharacterById(@PathVariable int id) {
        if(!charRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else
            return charRepository.findById(id)
                    .orElse(new Moviecharacters());
    }

    @GetMapping("/character/{Character}")
    public List<Moviecharacters> findAllByCharacter(@PathVariable String Character) {
        if (charRepository.findAllByCharacter(Character).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else
            return charRepository.findAllByCharacter(Character);
    }

    @GetMapping("/movie/{Movie}")
    public List<Moviecharacters> findAllByMovie(@PathVariable String Movie) {
        if (charRepository.findAllByMovie(Movie).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else
            return charRepository.findAllByCharacter(Movie);
    }

    //UPDATE
    @PutMapping("update")
    public ResponseEntity<?> updateMoviecharacters(@RequestBody Moviecharacters Character) {
        if (!CharRepository.existsById(Character.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }else
            CharRepository.save(Character);
        System.out.println("List of Movie Characters has been updated");
        return ResponseEntity.ok("Updated to \n" + Character.getId() + "\n" + Character.getCharacter() + "\n" + Character.getMovie());
    }

    //DELETE
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteMovieCharacters(@RequestBody Moviecharacters Character) {
        if(CharRepository.existsById(Character.getId())) {
            CharRepository.delete(Character);
            System.out.println("Guitarist deleted.");
            return ResponseEntity.ok("Deleted entry with ID: " + Character.getId());
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
