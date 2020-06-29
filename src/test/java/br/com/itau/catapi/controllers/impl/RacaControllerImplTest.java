package br.com.itau.catapi.controllers.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.controllers.impl.RacaControllerImpl;
import br.com.itau.catapi.services.RacaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = {RacaControllerImpl.class})
public class RacaControllerImplTest {

    static String API = "/racas";

    @MockBean
    private RacaService racaService;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by initFields methos below
    private JacksonTester<List<Raca>> jsonResult;
    private JacksonTester<List<Raca>> jsonResponse;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("Deve retornar as raças pelo temperamento")
    public void deveRetornarUmaListaRacaTest() throws Exception {
        // Cenário
        List<Raca> racas = retornarRacasParaMock().get();

        BDDMockito.given( racaService.buscarTodos() ).willReturn( racas );

        // Ação
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get(API)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        // Verificação
        assertThat( response.getStatus() ).isEqualTo(HttpStatus.OK.value());
        assertThat( response.getContentAsString() )
                .isEqualTo( jsonResponse.write( racas ).getJson() );

    }

    @Test
    @DisplayName("Não deve retornar uma raça")
    public void naoDeveRetornarUmaRacaTest() throws Exception {
        // Cenário
        BDDMockito.given( racaService.buscarTodos() ).willReturn( new ArrayList<Raca>());

        // Ação
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get(API)
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Verificação
        assertThat( response.getStatus() ).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    public Optional<List<Raca>> retornarRacasParaMock() {
        Raca raca1 = Raca.builder().id("abys").nome("Abyssinian").origem("Egypt").temperamento("Active, Energetic, Independent, Intelligent, Gentle").build();
        Raca raca2 = Raca.builder().id("beng").nome("Bengal").origem("United States").temperamento("Alert, Agile, Energetic, Demanding, Intelligent").build();
        Raca raca3 = Raca.builder().id("orie").nome("Oriental").origem("United States").temperamento("Energetic, Affectionate, Intelligent, Social, Playful, Curious").build();
        Raca raca4 = Raca.builder().id("siam").nome("Siamese").origem("Thailand").temperamento("Active, Agile, Clever, Sociable, Loving, Energetic").build();

        return Optional.of(Arrays.asList(raca1, raca2, raca3, raca4));
    }
}
