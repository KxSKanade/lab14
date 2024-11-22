package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.SpecialtyTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerTest {
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testFindSpecialtyKO() throws Exception {
        mockMvc.perform(get("/specialties/666"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateSpecialty() throws Exception {
        String NAME = "Neurologia";
        String OFFICE = "Trujillo";
        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(NAME);
        newSpecialtyTO.setOffice(OFFICE);
        newSpecialtyTO.setH_open("6");
        newSpecialtyTO.setH_close("10");
        mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.office", is(OFFICE)))
                .andExpect(jsonPath("$.h_open", is("6")))
                .andExpect(jsonPath("$.h_close", is("10")));
    }

    @Test
    public void testDeleteSpecialty() throws Exception {
        String NAME = "eloyxd";
        String OFFICE = "Huanchaco";
        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(NAME);
        newSpecialtyTO.setOffice(OFFICE);
        newSpecialtyTO.setH_open("5");
        newSpecialtyTO.setH_close("11");
        ResultActions mvcActions = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");
        mockMvc.perform(delete("/specialties/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteSpecialtyKO() throws Exception {
        mockMvc.perform(delete("/specialties/1000"))
                .andExpect(status().isNotFound());
    }
}
