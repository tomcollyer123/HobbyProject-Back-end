DROP TABLE IF EXISTS `playlist` CASCADE;

CREATE TABLE `playlist`(
		`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
		`artist_name`VARCHAR(100) NOT NULL,
		`song_name`VARCHAR(100) NOT NULL,
		`album_name` VARCHAR(100) NOT NULL,
		`genre` VARCHAR(100) NOT NULL,
		`release_date` date NOT NULL
);