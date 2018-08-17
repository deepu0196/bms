package com.bms.BookMyShow;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.core.ParameterizedTypeReference;

import com.bms.BookMyShowApplication;
import com.bms.controller.MovieController;
import com.bms.controller.UserController;
import com.bms.dto.MovieDto;
import com.bms.dto.UserDto;
import com.bms.repository.UserRepository;
import com.bms.service.MovieServiceImpl;
import com.bms.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookMyShowApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@Import(BookMyShowApplication.class)
public class BookMyShowApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

	@MockBean
	UserRepository userRepository;
	
    @LocalServerPort
	Integer port;

	@Autowired
	private TestRestTemplate restTemplate;

	ModelMapper modelMapper = new ModelMapper();

	@Test
	public void fetchAllUserTest() {
		UserDto userDto1 = new UserDto();
		userDto1.setUserid(1);
		userDto1.setUsername("john");
		userDto1.setPassword("john123");

		List<UserDto> userList = new ArrayList<>();
		userList.add(userDto1);

		Mockito.when(userService.fetchAllUser()).thenReturn(userList);
		String url = "http://localhost:" + port + "/bms/allUsers";

		List<UserDto> response = restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
				}).getBody();

		assertNotNull(response);
		Assertions.assertThat(response.size()).isEqualTo(1);
		Assertions.assertThat(response.get(0).getUsername()).isEqualTo("john");
	}
	
	
	
	
}
