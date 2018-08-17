package com.bms.controllertest;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.core.ParameterizedTypeReference;

import org.assertj.core.api.Assertions;

import com.bms.BookMyShowApplication;
import com.bms.BookMyShow.BookMyShowApplicationTests;
import com.bms.dto.UserDto;
import com.bms.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookMyShowApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(BookMyShowApplication.class)
public class UserControllerTest {
	 

		@Autowired
		private TestRestTemplate restTemplate;

		@LocalServerPort
		Integer port;

		@MockBean
		IUserService userService;

	org.jboss.logging.Logger logger = LoggerFactory.logger(BookMyShowApplicationTests.class);
		

		@Test
		public void retrieveAllUsersTest1() {

			Integer uid = 12;
			String username = "dips123@gmail.com";
			String password = "dips123$";
			String mobileno = "9874561230";
			 
			UserDto userDto = new UserDto();
			userDto.setUserid(uid);
			userDto.setPassword(password);
			userDto.setUsername(username);
			userDto.setMobileno(mobileno);
			 
			List<UserDto> userDtoList = new ArrayList<>();
			userDtoList.add(userDto);

			Mockito.when(userService.fetchAllUser()).thenReturn(userDtoList);
			String url = "http://localhost:" + port + "/bms/allUsers";
			List<UserDto> userDto1 = restTemplate
					.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
					}).getBody();
			Assertions.assertThat(userDto1.get(1).getUsername()).isEqualTo(username);
			Assertions.assertThat(userDto1.get(1).getUsername()).isEqualTo("dips123@gmail.com");
			logger.info("Test Run Successful");

	}

	 

}
