package com.bms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer movieid;
	
	@NotBlank(message="Movie Name cannot be Blank")
	@Column(name = "moviename")
	private String moviename;
	
	@NotBlank(message="Language should be specified")
	@Column(name = "language")
	private String language;
	
	@NotBlank(message= "Genere should be specified")
	@Column(name = "genere")
	private String genere;
	
	@NotBlank(message = "Image must be displayed")
	@Column(name = "image")
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getMovieid() {
		return movieid;
	}
	public void setMovieid(Integer movieid) {
		this.movieid = movieid;
	}
	
	@Override
	public String toString() {
		return "Movie [movieid=" + movieid + ", moviename=" + moviename + ", language=" + language + ", genere="
				+ genere + "]";
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
}
