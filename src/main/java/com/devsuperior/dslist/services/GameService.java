package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.exceptions.GameNotFoundException;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        var result = gameRepository.findAll();
        List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList(); //transformamos uma lista de Game em uma lista de GameMinDTO trazedo apenas alguns atributos

        return dto;
        //We could return this as well:
        //return result.stream().map(x -> new GameDTO(x)).toList(); //transformamos uma lista de Game em uma lista de GameDTO trazedo apenas alguns atributos
    }

    /*FindById method returns all attributes of a game object*/
    /*In case the specified gameId doesn't exist, it'll return gameNotFoundException*/
    @Transactional(readOnly = true)
    public GameDTO findById(Long gameId) {
        Game result = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game not found with id " + gameId));
        GameDTO dto = new GameDTO(result);
        return dto;
//      return new GameDTO(result);
    }


    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        var result = gameRepository.searchByList(listId);
        List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();

        return dto;
    }

}
