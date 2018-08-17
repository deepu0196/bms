package com.bms.controllertest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.bms.controller.MovieController;
import com.bms.dto.MovieDto;
import com.bms.service.MovieServiceImpl;
 

public class MovieTest {

	private MockMvc mockMvc;

    @Mock
    private MovieServiceImpl movieService;

    @InjectMocks
    private MovieController movieController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(movieController)
                .addFilters(new CorsFilter())
                .build();
    }
    
    @Test
    public void test_get_all_success() throws Exception {
        List<MovieDto> movies = Arrays.asList(
                new MovieDto(1, "harrypotter","hollywood","fiction","imagelink"),
                new MovieDto(2, "blackmail","bollywood","fiction","imagelink"));
        when(movieService.fetchAllMovies()).thenReturn(movies);
        mockMvc.perform(get("/bms/allMovies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movieid", is(1)))
                .andExpect(jsonPath("$[0].moviename", is("harrypotter")))
                .andExpect(jsonPath("$[1].movieid", is(2)))
                .andExpect(jsonPath("$[1].moviename", is("blackmail")));
        verify(movieService, times(1)).fetchAllMovies();
        verifyNoMoreInteractions(movieService);
    }
    
    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(movieService.fetchAllBollywoodMovies()).thenReturn(null);
        mockMvc.perform(get("/bms/bollywoodmovies"))
                .andExpect(status().isNotFound());
        verify(movieService, times(1)).fetchAllBollywoodMovies();
        verifyNoMoreInteractions(movieService);
    }
    
    
    
}
