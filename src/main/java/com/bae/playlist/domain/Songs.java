package com.bae.playlist.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // flags class as a table to spring data
public class Songs {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sets auto increment for ID
	private Integer id;
	@Column
	private String artistName;
	@Column
	private String title;
	@Column
	private String albumName;
	@Column
	private String genre;
	@Column
	private LocalDate releaseDate;

	// Constructor with an ID
	public Songs(Integer id, String artistName, String title, String albumName, String genre, LocalDate date) {
		super();
		this.id = id;
		this.artistName = artistName;
		this.title = title;
		this.albumName = albumName;
		this.genre = genre;
		this.releaseDate = date;
	}

	// Constructor without an ID
	public Songs(String artistName, String title, String albumName, String genre, LocalDate releaseDate) {
		super();
		this.artistName = artistName;
		this.title = title;
		this.albumName = albumName;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}

	// Default constructor
	public Songs() {
		super();
	}

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	// toString method
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", artistName=" + artistName + ", title=" + title + ", albumName=" + albumName
				+ ", genre=" + genre + ", releaseDate=" + releaseDate + "]";
	}

}
