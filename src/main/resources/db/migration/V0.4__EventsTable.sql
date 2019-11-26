DROP TABLE IF EXISTS users;
Create table IF NOT EXISTS users (
  id INT NOT NULL Auto_Increment PRIMARY KEY,
  firstname VARCHAR(100),
  lastname VARCHAR(100),
  vkid INT,
  profilepicture VARCHAR(100)
  );

DROP TABLE IF EXISTS events;
Create table IF NOT EXISTS events (
  id INT NOT NULL Auto_Increment PRIMARY KEY,
  name VARCHAR(100),
  eventdate VARCHAR(100)
  );