package com.bms.dto;

public class MovieDto {

	private Integer movieid;
	private String moviename;
	private String language;
	private String genere;
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
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	
	public MovieDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MovieDto(Integer movieid, String moviename, String language, String genere, String img) {
		super();
		this.movieid = movieid;
		this.moviename = moviename;
		this.language = language;
		this.genere = genere;
		this.img = img;
	}
	@Override
	public String toString() {
		return "MovieDto [movieid=" + movieid + ", moviename=" + moviename + ", language=" + language + ", genere="
				+ genere + ", img=" + img + "]";
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
