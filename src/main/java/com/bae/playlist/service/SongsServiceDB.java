package com.bae.playlist.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.playlist.domain.Songs;
import com.bae.playlist.repo.SongsRepo;

@Service
@Primary // this allows me to have the @service in this class and the serviceList
// class but this means that I want spring to use this class not the other

public class SongsServiceDB implements SongsService {
	private SongsRepo repo;

	@Autowired
	public SongsServiceDB(SongsRepo repo) {
		super();
		this.repo = repo;

	}

	@Override
	public Songs createSong(Songs song) {
		Songs created = this.repo.save(song); // INSERT INTO
		return created;
	}

	@Override
	public List<Songs> getAllSongs() {

		return this.repo.findAll(); // SELECT * FROM...
	}

	@Override
	public List<Songs> getAllSongsByArtistNameIgnoreCase(String artistName) {
		List<Songs> found = this.repo.findByArtistNameIgnoreCase(artistName);
		return found;
	}

	@Override
	public List<Songs> getAllSongsByTitleIgnoreCase(String title) {
		List<Songs> found = this.repo.findByTitleIgnoreCase(title);
		return found;
	}

	@Override
	public List<Songs> getAllSongsByAlbumNameIgnoreCase(String albumName) {
		List<Songs> found = this.repo.findByAlbumNameIgnoreCase(albumName);
		return found;
	}

	@Override
	public List<Songs> getAllSongsByGenreIgnoreCase(String genre) {
		List<Songs> found = this.repo.findByGenreIgnoreCase(genre);
		return found;
	}

	@Override
	public List<Songs> getAllSongsByReleaseDate(LocalDate releaseDate) {
		List<Songs> found = this.repo.findByReleaseDate(releaseDate);
		return found;
	}

	@Override
	public Songs getSongById(Integer id) {
		Optional<Songs> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Songs replaceSong(Integer id, Songs newSong) {
		Songs existing = this.repo.getById(id);
		existing.setArtistName(newSong.getArtistName());
		existing.setTitle(newSong.getTitle());
		existing.setAlbumName(newSong.getAlbumName());
		existing.setGenre(newSong.getGenre());
		existing.setReleaseDate(newSong.getReleaseDate());
		Songs updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void removeSong(Integer id) {
		this.repo.deleteById(id);

	}

}
