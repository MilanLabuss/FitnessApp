CREATE TABLE `Weight` (
  WeightLogID int not null auto_increment primary key,
  ID int not null,
  FOREIGN KEY (ID) REFERENCES Users(ID),
  `weight` smallint(20) NOT NULL,
  `bodyfat` tinyint(20) NOT NULL,
  `date` DATETIME NOT NULL
  )