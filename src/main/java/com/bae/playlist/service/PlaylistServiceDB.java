package com.bae.playlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.playlist.domain.Playlist;
import com.bae.playlist.repo.PlaylistRepo;

@Service
@Primary // this allows me to have the @service in this class and the serviceList
// class but this means that I want spring to use this class not the other

public class PlaylistServiceDB implements PlaylistService {
	private PlaylistRepo repo;

	@Autowired
	public PlaylistServiceDB(PlaylistRepo repo) {
		super();
		this.repo = repo;

	}

	@Override
	public Playlist createSong(Playlist song) {
		Playlist created = this.repo.save(song); // INSERT INTO
		return created;
	}

	@Override
	public List<Playlist> getAllSongs() {

		return this.repo.findAll(); // SELECT * FROM...
	}

	@Override
	public List<Playlist> getAllSongsByArtistName(String artistName) {
		List<Playlist> found = this.repo.findByArtistNameIgnoreCase(artistName);
		return found;
	}

	@Override
	public List<Playlist> getAllSongsByTitle(String title) {
		List<Playlist> found = this.repo.findByTitleIgnoreCase(title);
		return found;
	}

	@Override
	public List<Playlist> getAllSongsByAlbumName(String albumName) {
		List<Playlist> found = this.repo.findByAlbumNameIgnoreCase(albumName);
		return found;
	}

	@Override
	public List<Playlist> getAllSongsByGenre(String genre) {
		List<Playlist> found = this.repo.findByGenreIgnoreCase(genre);
		return found;
	}

	@Override
	public Playlist getSong(Integer id) {
		Optional<Playlist> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Playlist replaceSong(Integer id, Playlist newSong) {
		Playlist existing = this.repo.getById(id);
		existing.setArtistName(newSong.getArtistName());
		existing.setTitle(newSong.getTitle());
		existing.setAlbumName(newSong.getAlbumName());
		existing.setGenre(newSong.getGenre());
		existing.setReleaseDate(newSong.getReleaseDate());
		Playlist updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void removeSong(Integer id) {
		this.repo.deleteById(id);

	}

}
