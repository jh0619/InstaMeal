# InstaMeal

## Extra Steps required to update Users table

- The table was loaded with existing userId value.
- To save the trouble of finding number not yet inserted into the table,
- run the update_users_id sql script. This will change the userId in Users table to INT NOT NULL AUTO INCREMENT.

## To the Java program on eclipse.

- Update the mysql-connector jar file to the one in your computer. refer [here](https://docs.google.com/presentation/d/1P2U70XpVZDO1ZGCh3JobZRrybO5W79gSQRTPOdLj3Lw/edit#slide=id.gbec82a2f6_0_14)
- You may need to delete the mysql-connector jar set by the other.
- go to src/main/java/instameal/dal ConnectionManager.java class, change the user and password for your MySQL database.
