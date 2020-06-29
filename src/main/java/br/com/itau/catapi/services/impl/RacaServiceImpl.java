package br.com.itau.catapi.services.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacaServiceImpl implements RacaService {

    private RacaRepository racaRepository;

    @Autowired
    RacaServiceImpl(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    public List<Raca> buscarTodos() {
        return this.racaRepository.findAll();
    }

}
