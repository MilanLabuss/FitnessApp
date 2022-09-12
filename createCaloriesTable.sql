CREATE TABLE `Calories` (
  CalorieLogID int not null auto_increment primary key,
  ID int not null,
  FOREIGN KEY (ID) REFERENCES Users(ID),
  `Calories` smallint(20) NOT NULL,
  `date` DATETIME NOT NULL
  )