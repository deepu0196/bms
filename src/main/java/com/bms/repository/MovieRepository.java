package com.bms.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.model.Movie;


public  interface  MovieRepository extends JpaRepository<Movie, Integer>{

}
