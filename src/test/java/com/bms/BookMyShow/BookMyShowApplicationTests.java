package com.bms.BookMyShow;

import static org.hamcrest.CoreMatchers.is;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bms.controller.MovieController;
import com.bms.controller.UserController;
import com.bms.dto.MovieDto;
import com.bms.dto.UserDto;
import com.bms.service.MovieServiceImpl;
import com.bms.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMyShowApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;
    
    
    @Mock
	private MovieServiceImpl movieService;

	@InjectMocks
	private MovieController movieController;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController, movieController)
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
    
    

	@Test
	public void test_get_all_success2() throws Exception {
		List<MovieDto> movies = Arrays.asList(new MovieDto(1, "harrypotter", "hollywood", "fiction", "imagelink"),
				new MovieDto(2, "blackmail", "bollywood", "fiction", "imagelink"));
		when(movieService.fetchAllMovies()).thenReturn(movies);
		mockMvc.perform(get("/bms/allMovies")).andExpect(status().isOk()).andExpect(jsonPath("$[0].movieid", is(1)))
				.andExpect(jsonPath("$[0].moviename", is("harrypotter"))).andExpect(jsonPath("$[1].movieid", is(2)))
				.andExpect(jsonPath("$[1].moviename", is("blackmail")));
		verify(movieService, times(1)).fetchAllMovies();
		verifyNoMoreInteractions(movieService);
	}

	@Test
    public void test_get_bollywood_success() throws Exception {
        List<MovieDto> movies = Arrays.asList(
                new MovieDto(1, "harrypotter","bollywood","fiction","imagelink"),
                new MovieDto(2, "blackmail","bollywood","fiction","imagelink"));
        when(movieService.fetchAllBollywoodMovies()).thenReturn(movies);
        mockMvc.perform(get("/bms/bollywoodmovies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movieid", is(1)))
                .andExpect(jsonPath("$[0].moviename", is("harrypotter")))
                .andExpect(jsonPath("$[1].movieid", is(2)))
                .andExpect(jsonPath("$[1].moviename", is("blackmail")))
		        .andExpect(jsonPath("$[1].language", is("bollywood")))
		        .andExpect(jsonPath("$[1].genere", is("fiction")));
        verify(movieService, times(1)).fetchAllBollywoodMovies();
        verifyNoMoreInteractions(movieService);
    }


 


	
}
