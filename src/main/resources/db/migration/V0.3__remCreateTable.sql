DROP TABLE IF EXISTS users;
Create table IF NOT EXISTS users (
  id INT NOT NULL Auto_Increment PRIMARY KEY,
  firstname VARCHAR(100),
  lastname VARCHAR(100),
  vkid INT,
  profilepicture VARCHAR(100)
  );