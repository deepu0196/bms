package com.bms.controllertest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bms.controller.UserController;
import com.bms.dto.UserDto;
import com.bms.service.UserServiceImpl;

public class UserTest {
	
	private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CorsFilter())
                .build();
    }
    
    @Test
    public void test_get_all_success() throws Exception {
        List<UserDto> users = Arrays.asList(
                new UserDto(1, "dae@gmail.com","dae123$","7894561001"),
                new UserDto(2, "john@gmail.com","john123$","7898940003"));
        when(userService.fetchAllUser()).thenReturn(users);
        mockMvc.perform(get("/bms/allUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userid", is(1)))
                .andExpect(jsonPath("$[0].username", is("dae@gmail.com")))
                .andExpect(jsonPath("$[1].userid", is(2)))
                .andExpect(jsonPath("$[1].username", is("john@gmail.com")));
        verify(userService, times(1)).fetchAllUser();
        verifyNoMoreInteractions(userService);
    }
    
     

    
    
    
}
