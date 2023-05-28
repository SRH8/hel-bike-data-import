CREATE TABLE IF NOT EXISTS `city_bike_app`.`stations` (
  `fid` INT NOT NULL,
  `id` INT NOT NULL,
  `nimi` VARCHAR(100) NOT NULL,
  `namn` VARCHAR(100) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `osoite` VARCHAR(100) NOT NULL,
  `adress` VARCHAR(100) NOT NULL,
  `kaupunki` VARCHAR(45) NULL,
  `stad` VARCHAR(45) NULL,
  `operaattor` VARCHAR(45) NULL,
  `kapasiteet` INT NULL,
  `x` DOUBLE NOT NULL,
  `y` DOUBLE NOT NULL,
  PRIMARY KEY (`fid`));

CREATE TABLE IF NOT EXISTS `city_bike_app`.`journeys` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departure_date` DATETIME NOT NULL,
  `return_date` DATETIME NOT NULL,
  `departure_station_id` INT NOT NULL,
  `departure_station_name` VARCHAR(45) NOT NULL,
  `return_station_id` INT NOT NULL,
  `return_station_name` VARCHAR(45) NOT NULL,
  `distance_coveredm` INT NOT NULL,
  `durations` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `city_bike_app`.`journeys_seq` (
  `next_val` INT NOT NULL,
  PRIMARY KEY (`next_val`));

CREATE TABLE `city_bike_app`.`stations_seq` (
  `next_val` INT NOT NULL,
  PRIMARY KEY (`next_val`));
