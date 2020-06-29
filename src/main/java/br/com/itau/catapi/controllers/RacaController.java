package br.com.itau.catapi.controllers;

import br.com.itau.catapi.beans.Raca;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("racas")
public interface RacaController {

    @GetMapping
    ResponseEntity<List<Raca>> listarTodasRacas();

}
