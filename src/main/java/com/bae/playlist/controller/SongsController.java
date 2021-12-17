package com.bae.playlist.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.playlist.domain.Songs;
import com.bae.playlist.service.SongsService;

@RestController
@CrossOrigin
public class SongsController {

	private SongsService service;

	@Autowired // dependancy injection
	public SongsController(SongsService service) {
		super();
		this.service = service;
	}

	private List<Songs> song = new ArrayList<>();

//	CRUD Functionality

	@PostMapping("/createSong") // CREATING method = 201 status code
	public ResponseEntity<Songs> createSong(@RequestBody Songs song) {
		Songs created = this.service.createSong(song);
		ResponseEntity<Songs> response = new ResponseEntity<Songs>(created, HttpStatus.CREATED); // status code of
																									// 201
		return response; // This returns the new song and a response code of 201
	}

	@GetMapping("/getAllSongs") // 200 - don't need to change these as the default is 200 anyway
	public ResponseEntity<List<Songs>> getAllSongs() {
		return ResponseEntity.ok(this.service.getAllSongs()); // This will return all the Playlist in the playlist
	}

	@PutMapping("/replaceSong/{id}") // put because we are replacing the whole record (UPDATING)
	public ResponseEntity<Songs> replaceSong(@PathVariable Integer id, @RequestBody Songs newSong) {
//@pathvariable is so you can pull id from above
//@requestbody so you can get new song details
		Songs body = this.service.replaceSong(id, newSong);
		ResponseEntity<Songs> response = new ResponseEntity<Songs>(body, HttpStatus.ACCEPTED); // 202

		return response;
	}

	@DeleteMapping("/removeSong/{id}") // DELETING
	public ResponseEntity<Songs> removeSong(@PathVariable Integer id) {
		this.service.removeSong(id);

		return new ResponseEntity<Songs>(HttpStatus.NO_CONTENT); // 204
	}

	@GetMapping("/getByTitle/{title}")
	public ResponseEntity<List<Songs>> getAllSongByTitle(@PathVariable String title) {
		List<Songs> found = this.service.getAllSongsByTitleIgnoreCase(title);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByAlbum/{albumName}")
	public ResponseEntity<List<Songs>> getAllSongsByAlbumName(@PathVariable String albumName) {
		List<Songs> found = this.service.getAllSongsByAlbumNameIgnoreCase(albumName);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByGenre/{genre}")
	public ResponseEntity<List<Songs>> getAllSongsByGenre(@PathVariable String genre) {
		List<Songs> found = this.service.getAllSongsByGenreIgnoreCase(genre);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByReleaseDate/{releaseDate}")
	public ResponseEntity<List<Songs>> getAllSongsByReleaseDate(@PathVariable String releaseDate) {
		List<Songs> found = this.service.getAllSongsByReleaseDate(LocalDate.parse(releaseDate));
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getSongById/{id}") // 200 - don't need to change these as the default is 200 anyway
	public Songs getSongById(@PathVariable Integer id) {
		return this.service.getSongById(id);
	}
}
