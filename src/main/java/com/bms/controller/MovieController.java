package com.bms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bms.dto.MovieDto;
import com.bms.model.Movie;
import com.bms.model.User;
import com.bms.repository.MovieRepository;
import com.bms.service.IMovieService;
 
@RestController("movieController")
@RequestMapping("/bms")
public class MovieController {
	
	Logger logger = LoggerFactory.getLogger(Movie.class);
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private IMovieService movieService;
	
	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping(value = "/allMovies", method = RequestMethod.GET)
	public List<MovieDto> displayMovies(HttpServletRequest request) {
		return  movieService.fetchAllMovies();
	}
	
	@RequestMapping(value = "/bollywoodmovies", method = RequestMethod.GET)
	public List<MovieDto> displayBollyMovies(HttpServletRequest request) {
		return  movieService.fetchAllBollywoodMovies();
	}
	

	@RequestMapping(value = "/hollywoodmovies", method = RequestMethod.GET)
	public List<MovieDto> displayHollyMovies(HttpServletRequest request) {
		return movieService.fetchAllHollywoodMovies();
	}
	
	@RequestMapping(value = "/fictionmovies", method = RequestMethod.GET)
	public List<MovieDto> displayFictionMovies(HttpServletRequest request) {
		return   movieService.fetchAllFictionMovies();
	}
	
	@RequestMapping(value = "/horrormovies", method = RequestMethod.GET)
	public List<MovieDto> displayHorrorMovies(HttpServletRequest request) {
		return  movieService.fetchAllHorrorMovies();
	}
	
/*	@RequestMapping(value = "/{language}/{genere}", method = RequestMethod.GET)
	public List<MovieDto> retrieveCategoryAuthorsBlogs(@PathVariable("language") String language, @PathVariable("genere") String genere){
		return movieService.fetchTypeGenreMovies(language, genere);
	
		
	}*/
	
}
