CREATE DATABASE finance_tracker_data;
CREATE USER 'rest_user'@'localhost' IDENTIFIED BY 'test123';
GRANT ALL PRIVILEGES ON finance_tracker_data.* TO 'rest_user'@'localhost';