package com.jeff.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void getContactsWithoutParameterShouldGetUserNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

	@Test
	void getContactsWithBothParameters() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=1&username=Bret"))
		.andExpect(content().json("{'id':1,'email':'Sincere@april.biz','phone':'1-770-736-8031 x56442'}'"));
	}

	@Test
	void getContactsWithBothParametersButNotMatch() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=1&username=Brett"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}


	@Test
	void getContactsByValidId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=1"))
		.andExpect(content().json("{'id':1,'email':'Sincere@april.biz','phone':'1-770-736-8031 x56442'}'"));
	}

	@Test
	void getContactsByInValidId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=x"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

	@Test
	void getContactsByIdNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=999"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

	@Test
	void getContactsByValidUsername() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?username=Bret"))
		.andExpect(content().json("{'id':1,'email':'Sincere@april.biz','phone':'1-770-736-8031 x56442'}'"));
	}

	@Test
	void getContactsByUsernameNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?username=Brett"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

}
