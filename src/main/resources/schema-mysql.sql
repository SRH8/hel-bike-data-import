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
  `x` DOUBLE NULL,
  `y` DOUBLE NULL,
  PRIMARY KEY (`fid`));