package com.bae.playlist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bae.playlist.domain.Playlist;
import com.bae.playlist.service.PlaylistService;

public class PlaylistController {

	private PlaylistService service;

	@Autowired // dependancy injection
	public PlaylistController(PlaylistService service) {
		super();
		this.service = service;
	}

	private List<Playlist> song = new ArrayList<>();

//	CRUD Functionality

	@PostMapping("/create") // CREATING method = 201 status code
	public ResponseEntity<Playlist> createSong(@RequestBody Playlist song) {
		Playlist created = this.service.createSong(song);
		ResponseEntity<Playlist> response = new ResponseEntity<Playlist>(created, HttpStatus.CREATED); // status code of
																										// 201
		return response; // This returns the new song and a response code of 201
	}

	@GetMapping("/getAll") // 200 - don't need to change these as the default is 200 anyway
	public ResponseEntity<List<Playlist>> getAllSongs() {
		return ResponseEntity.ok(this.service.getAllSongs()); // This will return all the Playlist in the playlist
	}

	@PutMapping("/replace/{id}") // put because we are replacing the whole record (UPDATING)
	public ResponseEntity<Playlist> replaceSong(@PathVariable Integer id, @RequestBody Playlist newSong) {
//@pathvariable is so you can pull id from above
//@requestbody so you can get new song details
		Playlist body = this.service.replaceSong(id, newSong);
		ResponseEntity<Playlist> response = new ResponseEntity<Playlist>(body, HttpStatus.ACCEPTED); // 202

		return response;
	}

	@DeleteMapping("/remove/{id}") // DELETING
	public ResponseEntity<Playlist> removeSong(@PathVariable Integer id) {
		this.service.removeSong(id);

		return new ResponseEntity<Playlist>(HttpStatus.NO_CONTENT); // 204
	}
}
