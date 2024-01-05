package com.cifrascontable.cifrasbackend.api.controller;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.controller.EntidadFinancieraController;
import com.cifrascontable.cifrasbackend.service.EntidadFinancieraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.transform.Result;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = EntidadFinancieraController.class)
@AutoConfigureMockMvc(addFilters = false) // Evitar Spring Security para no preocuparse por tokens
@ExtendWith(MockitoExtension.class)
public class EntidadFinancieraControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntidadFinancieraService entidadFinancieraService;

    private EntidadFinancieraRequestDTO entidadFinancieraRequestDTO;
    private EntidadFinancieraResponseDTO entidadFinancieraResponseDTO;

    @BeforeEach
    public void init() {
        entidadFinancieraRequestDTO = EntidadFinancieraRequestDTO.builder()
            .nombre("Entidad Test")
            .tipo("Test")
            .numeroCuenta("123456/789")
            .cuit("44-556677-0")
            .cbu("123456789")
            .alias("entidad.test")
            .cuitEmpresa("00-11222333-0")
            .build();

        entidadFinancieraResponseDTO = EntidadFinancieraResponseDTO.builder()
                .id(Long.valueOf(1))
                .nombre("Entidad Test")
                .tipo("Test")
                .numeroCuenta("123456/789")
                .cuit("44-556677-0")
                .cbu("123456789")
                .alias("entidad.test")
                .cuitEmpresa("00-11222333-0")
                .build();
    }

    @Test
    public void EntidadFinancieraController_CrearEntidadFinanciera_ReturnCreated() throws Exception {
        // Mocking the service behavior
        when(entidadFinancieraService.crearEntidadFinanciera(ArgumentMatchers.any())).thenReturn(entidadFinancieraResponseDTO);

        // Perform HTTP Post request
        ResultActions response = mockMvc.perform(post("/v1/entidades_financieras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(entidadFinancieraRequestDTO)));

        // Asserting the response
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", CoreMatchers.is(entidadFinancieraRequestDTO.nombre())));
    }

    @Test
    public void EntidadFinancieraController_GetEntidadFinanciera_ReturnResponseDTO() throws Exception {
        Long id = Long.valueOf(1);
        when(entidadFinancieraService.getEntidadFinanciera(id)).thenReturn(entidadFinancieraResponseDTO);

        ResultActions response = mockMvc.perform(get("/v1/entidades_financieras/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre", CoreMatchers.is(entidadFinancieraResponseDTO.nombre())));
    }

}
