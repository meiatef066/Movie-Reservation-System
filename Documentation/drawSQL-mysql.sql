CREATE TABLE `User`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('') NOT NULL,
    `create at` TIMESTAMP NOT NULL
);
CREATE TABLE `Movie`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `image_url` VARCHAR(255) NOT NULL
);
CREATE TABLE `Show time`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `movie id` BIGINT NOT NULL,
    `start_time` TIMESTAMP NOT NULL,
    `hall_id` BIGINT NOT NULL,
    `create at` TIMESTAMP NOT NULL
);
CREATE TABLE `seats`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `seat_number` BIGINT NOT NULL,
    `row` CHAR(255) NOT NULL,
    `hall_id` BIGINT NOT NULL
);
CREATE TABLE `Reservation`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user id` BIGINT NOT NULL,
    `showtime id` BIGINT NOT NULL,
    `status` ENUM('ACTIVE', 'CANCELED') NOT NULL,
    `reservation_time` TIMESTAMP NOT NULL
);
CREATE TABLE `ReservedSeat`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reservation id` BIGINT NOT NULL,
    `showTime id` BIGINT NOT NULL,
    `seat id` BIGINT NOT NULL
);
CREATE TABLE `Halls`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `total-_rows` VARCHAR(255) NOT NULL,
    `total_column` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Show time` ADD CONSTRAINT `show time_movie id_foreign` FOREIGN KEY(`movie id`) REFERENCES `Movie`(`id`);
ALTER TABLE
    `ReservedSeat` ADD CONSTRAINT `reservedseat_reservation id_foreign` FOREIGN KEY(`reservation id`) REFERENCES `Reservation`(`id`);
ALTER TABLE
    `Show time` ADD CONSTRAINT `show time_hall_id_foreign` FOREIGN KEY(`hall_id`) REFERENCES `Halls`(`id`);
ALTER TABLE
    `Reservation` ADD CONSTRAINT `reservation_user id_foreign` FOREIGN KEY(`user id`) REFERENCES `User`(`id`);
ALTER TABLE
    `ReservedSeat` ADD CONSTRAINT `reservedseat_seat id_foreign` FOREIGN KEY(`seat id`) REFERENCES `seats`(`id`);
ALTER TABLE
    `seats` ADD CONSTRAINT `seats_seat_number_foreign` FOREIGN KEY(`seat_number`) REFERENCES `Halls`(`id`);
ALTER TABLE
    `ReservedSeat` ADD CONSTRAINT `reservedseat_showtime id_foreign` FOREIGN KEY(`showTime id`) REFERENCES `Show time`(`id`);
ALTER TABLE
    `Reservation` ADD CONSTRAINT `reservation_showtime id_foreign` FOREIGN KEY(`showtime id`) REFERENCES `Show time`(`id`);