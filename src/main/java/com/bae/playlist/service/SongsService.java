package com.bae.playlist.service;

import java.time.LocalDate;
import java.util.List;

import com.bae.playlist.domain.Songs;

public interface SongsService {

// CRUD functions

	Songs createSong(Songs song);

	List<Songs> getAllSongs();

	List<Songs> getAllSongsByArtistNameIgnoreCase(String artistName);

	List<Songs> getAllSongsByTitleIgnoreCase(String title);

	List<Songs> getAllSongsByAlbumNameIgnoreCase(String albumName);

	List<Songs> getAllSongsByGenreIgnoreCase(String genre);

	List<Songs> getAllSongsByReleaseDate(LocalDate releaseDate);

	Songs getSongById(Integer id);

	Songs replaceSong(Integer id, Songs newSong);

	void removeSong(Integer id);

}
