package br.com.itau.catapi.services.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import br.com.itau.catapi.services.RacaService;
import br.com.itau.catapi.services.impl.RacaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RacaServiceImplTest {

    private RacaService racaService;

    @MockBean
    private RacaRepository repository;

    @BeforeEach
    public void setUp() {
        this.racaService = new RacaServiceImpl(repository);
    }


    @Test
    @DisplayName("Deve buscar as raças cadastradas")
    public void deveBuscarTodasAsRacasTest() {
        // Cenário
        List<Raca> racas = retornarRacasParaMock().get();
        BDDMockito.when( repository.findAll() ).thenReturn( racas );

        // Ação
        List<Raca> returnedRacas = racaService.buscarTodos();

        // Verificação
        assertThat( returnedRacas.isEmpty() ).isFalse();
        assertThat( returnedRacas.size() ).isEqualTo( racas.size() );

        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar uma lista pois não encontrou dados no Banco de Dados")
    public void deveRetornarListaVazia() {
        // Cenário
        BDDMockito.when( repository.findAll() ).thenReturn( new ArrayList<Raca>() );

        // Ação
        List<Raca> returnedListRacas = racaService.buscarTodos();

        // Verificação
        assertThat( returnedListRacas ).isEmpty();
        Mockito.verify(repository, times(1)).findAll();
    }

    private Optional<List<Raca>> retornarRacasParaMock() {
        Raca raca1 = Raca.builder().id("abys").nome("Abyssinian").origem("Egypt").temperamento("Active, Energetic, Independent, Intelligent, Gentle").build();
        Raca raca2 = Raca.builder().id("beng").nome("Bengal").origem("United States").temperamento("Alert, Agile, Energetic, Demanding, Intelligent").build();
        Raca raca3 = Raca.builder().id("orie").nome("Oriental").origem("United States").temperamento("Energetic, Affectionate, Intelligent, Social, Playful, Curious").build();
        Raca raca4 = Raca.builder().id("siam").nome("Siamese").origem("Thailand").temperamento("Active, Agile, Clever, Sociable, Loving, Energetic").build();

        return Optional.of(Arrays.asList(raca1, raca2, raca3, raca4));
    }

}
