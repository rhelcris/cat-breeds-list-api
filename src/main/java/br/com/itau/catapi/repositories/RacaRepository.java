package br.com.itau.catapi.repositories;

import br.com.itau.catapi.beans.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacaRepository extends JpaRepository<Raca, String> {
}
