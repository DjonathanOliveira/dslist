package com.devsuperior.dslist.controllers;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.exceptions.GameNotFoundException;
import com.devsuperior.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll() {
        List<GameMinDTO> result = gameService.findAll();
        return result;
    }

    @GetMapping(value = "/{gameId}")
    public ResponseEntity<GameDTO> findById(@PathVariable Long gameId) {
        GameDTO result = gameService.findById(gameId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<String> handleGameNotFoundException(GameNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
