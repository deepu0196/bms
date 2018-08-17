package com.bms.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dto.MovieDto;
import com.bms.model.Movie;
import com.bms.repository.MovieRepository;

@Service
public class MovieServiceImpl implements IMovieService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private MovieRepository movieRepository;

	protected MovieDto movieDto;
	protected Movie mov;

	@Override
	public List<MovieDto> fetchAllMovies() {
		List<MovieDto> movieDtolist = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		for (Movie movie : movies) {
			movieDto = modelMapper.map(movie, MovieDto.class);
			movieDtolist.add(movieDto);
		}
		return movieDtolist;
	}

	@Override
	public List<MovieDto> fetchAllBollywoodMovies() {
		List<MovieDto> movieDtolist = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		for (Movie movie : movies) {
			movieDto = modelMapper.map(movie, MovieDto.class);
			if (movie.getLanguage().equalsIgnoreCase("BOLLYWOOD"))
				movieDtolist.add(movieDto);
		}
		return movieDtolist;
	}

	@Override
	public List<MovieDto> fetchAllHollywoodMovies() {
		List<MovieDto> movieDtolist = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		for (Movie movie : movies) {
			movieDto = modelMapper.map(movie, MovieDto.class);
			if (movie.getLanguage().equalsIgnoreCase("HOLLYWOOD"))
				movieDtolist.add(movieDto);
		}
		return movieDtolist;
	}

	@Override
	public List<MovieDto> fetchAllFictionMovies() {
		List<MovieDto> movieDtolist = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		for (Movie movie : movies) {
			movieDto = modelMapper.map(movie, MovieDto.class);
			if (movie.getGenere().equalsIgnoreCase("FICTION"))
				movieDtolist.add(movieDto);
		}
		return movieDtolist;
	}

	@Override
	public List<MovieDto> fetchAllHorrorMovies() {
		List<MovieDto> movieDtolist = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		for (Movie movie : movies) {
			movieDto = modelMapper.map(movie, MovieDto.class);
			if (movie.getGenere().equalsIgnoreCase("HORROR"))
				movieDtolist.add(movieDto);
		}
		return movieDtolist;
	}

	@Override
	public List<MovieDto> fetchTypeGenreMovies(String language, String genere ) {
		List<MovieDto> movieDtolist = new ArrayList<>();
		List<Movie> movies = movieRepository.findAll();
		for (Movie movie : movies) {
			movieDto = modelMapper.map(movie, MovieDto.class);
			if (movieDto.getLanguage().equalsIgnoreCase(language)) {
				if (genere.equals(null))
					movieDtolist.add(movieDto);
				else if (movieDto.getGenere().equalsIgnoreCase(genere))
					movieDtolist.add(movieDto);
			}
		} 
		return movieDtolist;
	}

}
