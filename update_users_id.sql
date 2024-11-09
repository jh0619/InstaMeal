USE InstaMeal;
# Need to remove all foreign key constraints before we can change the column
ALTER TABLE Reshares 
	DROP FOREIGN KEY fk_Reshares_UserId;
ALTER TABLE Reviews
	DROP FOREIGN KEY fk_Reviews_UserId;
ALTER TABLE Userstocks
	DROP FOREIGN KEY fk_UserStocks_UserId;
ALTER TABLE USERS CHANGE COLUMN UserId UserId INT NOT NULL AUTO_INCREMENT;

# Set the AUTO INCREMENT Value START VALUE, 
# need to be larger than largest value in your TABLE
# change it based on your table 
ALTER TABLE USERS AUTO_INCREMENT = 2002372707;
SHOW CREATE TABLE USERS;

# ADD back all foreign key constraints
ALTER TABLE Reshares
	ADD CONSTRAINT fk_Reshares_UserId
		FOREIGN KEY (UserId)
        REFERENCES Users(UserId)
        ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE Reviews 
	ADD CONSTRAINT fk_Reviews_UserId 
		FOREIGN KEY (UserId)
        REFERENCES Users(UserId)
        ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE Userstocks
	ADD CONSTRAINT fk_UserStocks_UserId
		FOREIGN KEY (UserId)
        REFERENCES Users(UserId)
        ON UPDATE CASCADE ON DELETE CASCADE;