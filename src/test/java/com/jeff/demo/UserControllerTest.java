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
	void getContacsWithoutParameterShouldGetUserNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

	@Test
	void getContacsWithBothParameters() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=1&username=Bret"))
		.andExpect(content().json("{'id':1,'email':'Sincere@april.biz','phone':'1-770-736-8031 x56442'}'"));
	}

	@Test
	void getContacsWithBothParametersButNotMatch() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=1&username=Brett"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}


	@Test
	void getContacsByValidId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=1"))
		.andExpect(content().json("{'id':1,'email':'Sincere@april.biz','phone':'1-770-736-8031 x56442'}'"));
	}

	@Test
	void getContacsByInValidId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=x"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

	@Test
	void getContacsByIdNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?id=999"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

	@Test
	void getContacsByValidUsername() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?username=Bret"))
		.andExpect(content().json("{'id':1,'email':'Sincere@april.biz','phone':'1-770-736-8031 x56442'}'"));
	}

	@Test
	void getContacsByUsernameNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/getusercontacts?username=Brett"))
		.andExpect(content().json("{'id':-1,'email':null,'phone':null}"));
	}

}
