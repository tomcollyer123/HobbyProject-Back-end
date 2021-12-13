package com.bae.playlist.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.playlist.domain.Playlist;

@Repository
public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {

	List<Playlist> findByArtistNameIgnoreCase(String artistName);

	List<Playlist> findByTitleIgnoreCase(String title);

	List<Playlist> findByAlbumNameIgnoreCase(String albumName);

	List<Playlist> findByGenreIgnoreCase(String genre);

	List<Playlist> findByReleaseDateIgnoreCase(Date releaseDate);

}
