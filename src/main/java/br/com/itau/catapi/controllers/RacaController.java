package br.com.itau.catapi.controllers;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("racas")
public class RacaController {

    private RacaService racaService;

    @Autowired
    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @GetMapping
    public ResponseEntity<List<Raca>> listarTodasRacas() {
        List<Raca> racas = racaService.buscarTodos();
        return ResponseEntity.ok(racas);
    }


}
