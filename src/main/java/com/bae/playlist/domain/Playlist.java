package com.bae.playlist.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // flags class as a table to spring data
public class Playlist {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // sets auto increment for ID
	private Integer id;
	@Column(nullable = false)
	private String artistName;
	@Column(nullable = false)
	private String songName;
	@Column(nullable = false)
	private String albumName;
	@Column(nullable = false)
	private String genre;
	@Column(nullable = false)
	private Date releaseDate;

}
