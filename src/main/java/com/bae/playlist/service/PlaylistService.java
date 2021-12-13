package com.bae.playlist.service;

import java.util.List;

import com.bae.playlist.domain.Playlist;

public interface PlaylistService {

// CRUD functions

	Playlist createSong(Playlist song);

	List<Playlist> getAllSongs();

	List<Playlist> getAllSongsByArtistName(String artistName);

	List<Playlist> getAllSongsByTitle(String title);

	List<Playlist> getAllSongsByAlbumName(String albumName);

	List<Playlist> getAllSongsByGenre(String genre);

	Playlist getSong(Integer id);

	Playlist replaceSong(Integer id, Playlist newSong);

	void removeSong(Integer id);

}
