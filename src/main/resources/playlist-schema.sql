DROP TABLE IF EXISTS `songs` CASCADE;

CREATE TABLE `songs`(
		`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
		`artist_name`VARCHAR(100) NOT NULL,
		`title`VARCHAR(100) NOT NULL,
		`album_name` VARCHAR(100) NOT NULL,
		`genre` VARCHAR(100) NOT NULL,
		`release_date` date NOT NULL
);