CREATE DATABASE finance_tracker_data;

SET GLOBAL event_scheduler = ON;

CREATE USER 'rest_user'@'localhost' IDENTIFIED BY 'test123';
GRANT ALL PRIVILEGES ON finance_tracker_data.* TO 'rest_user'@'localhost';

USE finance_tracker_data;

CREATE EVENT IF NOT EXISTS clean_up_expired_jwts
ON SCHEDULE
EVERY 1 DAY
COMMENT 'Clean up the expried jwts. No need to keep these around.'
DO
DELETE FROM blacklisted_jwt WHERE expires_at < NOW();