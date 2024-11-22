package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.PetTO;
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
public class PetControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void testFindPetKO() throws Exception {

		mockMvc.perform(get("/pets/666"))
				.andExpect(status().isNotFound());

	}

	@Test
	public void testCreatePet() throws Exception {

		String NAME_PET = "Beethoven";
		int TYPE_ID = 1;
		int OWNER_ID = 1;
		String BIRTH_DATE = "2020-05-20";

		PetTO newPetTO = new PetTO();
		newPetTO.setName(NAME_PET);
		newPetTO.setTypeId(TYPE_ID);
		newPetTO.setOwnerId(OWNER_ID);
		newPetTO.setBirthDate(BIRTH_DATE);

		mockMvc.perform(post("/pets")
						.content(om.writeValueAsString(newPetTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				//.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(NAME_PET)))
				.andExpect(jsonPath("$.typeId", is(TYPE_ID)))
				.andExpect(jsonPath("$.ownerId", is(OWNER_ID)))
				.andExpect(jsonPath("$.birthDate", is(BIRTH_DATE)));

	}

	@Test
	public void testDeletePet() throws Exception {

		String NAME_PET = "Beethoven3";
		int TYPE_ID = 1;
		int OWNER_ID = 1;
		String BIRTH_DATE = "2020-05-20";

		PetTO newPetTO = new PetTO();
		newPetTO.setName(NAME_PET);
		newPetTO.setTypeId(TYPE_ID);
		newPetTO.setOwnerId(OWNER_ID);
		newPetTO.setBirthDate(BIRTH_DATE);

		ResultActions mvcActions = mockMvc.perform(post("/pets")
						.content(om.writeValueAsString(newPetTO))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());

		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

		mockMvc.perform(delete("/pets/" + id ))
				/*.andDo(print())*/
				.andExpect(status().isOk());
	}


	@Test
	public void testDeletePetKO() throws Exception {

		mockMvc.perform(delete("/pets/" + "1000" ))
				/*.andDo(print())*/
				.andExpect(status().isNotFound());
	}
}
    