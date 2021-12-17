package com.bae.playlist.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.playlist.domain.Songs;

@Repository
public interface SongsRepo extends JpaRepository<Songs, Integer> {

	List<Songs> findByArtistNameIgnoreCase(String artistName);

	List<Songs> findByTitleIgnoreCase(String title);

	List<Songs> findByAlbumNameIgnoreCase(String albumName);

	List<Songs> findByGenreIgnoreCase(String genre);

	List<Songs> findByReleaseDate(LocalDate releaseDate);

}
