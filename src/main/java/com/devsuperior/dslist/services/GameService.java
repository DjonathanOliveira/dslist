package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameDTO> findAll() {
        var result = gameRepository.findAll();
        List<GameDTO> dto = result.stream().map(x -> new GameDTO(x)).toList(); //transformamos uma lista de Game em uma lista de GameDTO trazedo apenas alguns atributos

        return dto;

        //Poderíamos retornar isso também:
        //return result.stream().map(x -> new GameDTO(x)).toList(); //transformamos uma lista de Game em uma lista de GameDTO trazedo apenas alguns atributos
    }
}
