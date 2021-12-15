package com.bae.playlist.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.playlist.domain.Songs;
import com.fasterxml.jackson.databind.ObjectMapper;

// boots the entire context - random port to avoid collisions (two apps running
// on the same port)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up MockMVC object
@Sql(scripts = { "classpath:playlist-schema.sql",
		"classpath:playlist-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class SongsControllerIntergrationTest {

	@Autowired // pulls the MockMVC object from the context
	private MockMvc mvc; // class that performs the request (like what postman does)

	@Autowired
	private ObjectMapper mapper; // java to JSON converter that spring uses

	@Test
	void testCreate() throws Exception {
		Songs testSong = new Songs(null, "oasis", "wonderwall", "whats the story morning glory", "indie rock",
				LocalDate.of(1995, 5, 7));
		String testSongAsJSON = this.mapper.writeValueAsString(testSong);
		RequestBuilder req = post("/createSong").content(testSongAsJSON).contentType(MediaType.APPLICATION_JSON);

		Songs testCreatedSong = new Songs(4, "oasis", "wonderwall", "whats the story morning glory", "indie rock",
				LocalDate.of(1995, 5, 7));
		String testCreatedSongAsJSON = this.mapper.writeValueAsString(testCreatedSong);
		ResultMatcher checkStatus = status().isCreated(); // this is checking the status code
		ResultMatcher checkBody = content().json(testCreatedSongAsJSON); // this is checking the body

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // mvc sends the request -
																			// first expect checks the status
																			// second expect checks the body
	}

	@Test
	void testCreate2() throws Exception {
		Songs testSongs = new Songs(null, "oasis", "wonderwall", "whats the story morning glory", "indie rock",
				LocalDate.of(1995, 5, 7));

		String testSongsAsJSON = this.mapper.writeValueAsString(testSongs);
		RequestBuilder req = post("/createSong").content(testSongsAsJSON).contentType(MediaType.APPLICATION_JSON);

		Songs testCreatedSongs = new Songs(4, "oasis", "wonderwall", "whats the story morning glory", "indie rock",
				LocalDate.of(1995, 5, 7));

		String testCreatedSongsAsJSON = this.mapper.writeValueAsString(testCreatedSongs);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedSongsAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testReplace() throws Exception {
		Songs testSongs = new Songs(null, "ACDC", "back in black", "back in black", "hard rock",
				LocalDate.of(1980, 7, 25));

		String testSongsAsJSON = this.mapper.writeValueAsString(testSongs);
		RequestBuilder req = put("/replaceSong/1").content(testSongsAsJSON).contentType(MediaType.APPLICATION_JSON);

		Songs testUpdatedSongs = new Songs(1, "ACDC", "back in black", "back in black", "hard rock",
				LocalDate.of(1980, 7, 25));

		String testUpdatedSongsAsJSON = this.mapper.writeValueAsString(testUpdatedSongs);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedSongsAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		List<Songs> testSongs = List.of(
				new Songs(1, "oasis", "wonderwall", "whats the story morning glory", "indie rock",
						LocalDate.of(1995, 5, 7)),

				new Songs(2, "ACDC", "back in black", "back in black", "hard rock", LocalDate.of(1980, 7, 25)),

				new Songs(3, "Tupac", "changes", "changes", "hip hop", LocalDate.of(1998, 9, 13)));

		String json = this.mapper.writeValueAsString(testSongs);

		RequestBuilder req = get("/getAllSongs");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByTitle() throws Exception {
		List<Songs> testSongs = List.of(new Songs(1, "oasis", "wonderwall", "whats the story morning glory",
				"indie rock", LocalDate.of(1995, 5, 7)));
		String json = this.mapper.writeValueAsString(testSongs);

		RequestBuilder req = get("/getByTitle/wonderwall");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByAlbum() throws Exception {
		List<Songs> testSongs = List.of(new Songs(1, "oasis", "wonderwall", "whats the story morning glory",
				"indie rock", LocalDate.of(1995, 5, 7)));
		String json = this.mapper.writeValueAsString(testSongs);

		RequestBuilder req = get("/getByAlbum/whats the story morning glory");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByGenre() throws Exception {
		List<Songs> testSongs = List.of(new Songs(1, "oasis", "wonderwall", "whats the story morning glory",
				"indie rock", LocalDate.of(1995, 5, 7)));
		String json = this.mapper.writeValueAsString(testSongs);

		RequestBuilder req = get("/getByGenre/indie rock");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetByReleaseDate() throws Exception {
		List<Songs> testSongs = List.of(new Songs(1, "oasis", "wonderwall", "whats the story morning glory",
				"indie rock", LocalDate.of(1995, 5, 7)));
		String json = this.mapper.writeValueAsString(testSongs);

		RequestBuilder req = get("/getByReleaseDate/1995-05-07");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetSongById() throws Exception {
		Songs testSongs = new Songs(1, "oasis", "wonderwall", "whats the story morning glory", "indie rock",
				LocalDate.of(1995, 5, 7));
		String json = this.mapper.writeValueAsString(testSongs);

		RequestBuilder req = get("/getSongById/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testRemove() throws Exception {
		RequestBuilder req = delete("/removeSong/1");
		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(req).andExpect(checkStatus);
	}
}
