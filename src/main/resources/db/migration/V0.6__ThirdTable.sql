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
  eventdate date
  );

DROP TABLE IF EXISTS usersevents;
CREATE TABLE IF NOT EXISTS usersevents (
    user_id INT NOT NULL,
    event_id INT NOT Null,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);