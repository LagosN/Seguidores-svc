package cl.duoc.jv0101.caso12.seguidores;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import cl.duoc.jv0101.caso12.seguidores.model.Seguimiento;
import cl.duoc.jv0101.caso12.seguidores.service.SeguimientoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SeguimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SeguimientoService service;

    private Seguimiento recurso(Long id) {
        Seguimiento r = new Seguimiento();
        r.setId(id);
        r.setNombre("Demo");
        r.setSeguidor("valor");
        r.setSeguido("valor");
        return r;
    }

    private String body() throws Exception {
        return objectMapper.writeValueAsString(recurso(null));
    }

    @Test
    void listarDevuelve200YLista() throws Exception {
        when(service.findAll()).thenReturn(List.of(recurso(1L)));
        mockMvc.perform(get("/api/seguimientos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void obtenerExistenteDevuelve200() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(recurso(1L)));
        mockMvc.perform(get("/api/seguimientos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Demo"));
    }

    @Test
    void obtenerInexistenteDevuelve404() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/seguimientos/1")).andExpect(status().isNotFound());
    }

    @Test
    void crearValidoDevuelve201() throws Exception {
        when(service.create(any())).thenReturn(recurso(1L));
        mockMvc.perform(post("/api/seguimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void crearInvalidoDevuelve400() throws Exception {
        mockMvc.perform(post("/api/seguimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarExistenteDevuelve200() throws Exception {
        Seguimiento actualizado = recurso(1L);
        actualizado.setNombre("Actualizado");
        when(service.update(any(), any())).thenReturn(Optional.of(actualizado));
        mockMvc.perform(put("/api/seguimientos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Actualizado"));
    }

    @Test
    void actualizarInexistenteDevuelve404() throws Exception {
        when(service.update(any(), any())).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/seguimientos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body()))
            .andExpect(status().isNotFound());
    }

    @Test
    void eliminarExistenteDevuelve204() throws Exception {
        when(service.delete(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/seguimientos/1")).andExpect(status().isNoContent());
    }

    @Test
    void eliminarInexistenteDevuelve404() throws Exception {
        when(service.delete(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/seguimientos/1")).andExpect(status().isNotFound());
    }
}
