db info: dbname-FamilyMap username FamilyMapApp password-FamilyMapApp

SET UP:
After setting up MYSQL server, create a databse called FamilyMap. Download Flywaydb to handle migrations. Download from (https://flywaydb.org) or just use brew package manager -- brew install flywaydb

** set up config file in directory from which you will call "flyway migrate" **

example config: 

flyway.url=jdbc:mysql://localhost/FamilyMap
flyway.user=FamilyMapApp
flyway.password=FamilyMapApp
flyway.locations=filesystem:/Users/kittykatt/Desktop/devShelf/BYU/cs240/familyMap/WebService/DB/migrati
ons/flyway/mysql

EXECUTION:

flyway migrate
