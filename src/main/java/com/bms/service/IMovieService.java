package com.bms.service;

import java.util.List;

import com.bms.dto.MovieDto;

public  interface IMovieService {

	public List<MovieDto> fetchAllMovies();
	
	public List<MovieDto> fetchAllBollywoodMovies();
	
	public List<MovieDto> fetchAllHollywoodMovies();
	
	public List<MovieDto> fetchAllFictionMovies();
	
	public List<MovieDto> fetchAllHorrorMovies();
	
	public List<MovieDto> fetchTypeGenreMovies(String language,String genere );
}
