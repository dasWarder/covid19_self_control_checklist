DELETE FROM symptom_x_statistic;
DELETE FROM symptom;
DELETE FROM statistic;
DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (name, password, email) VALUES
('Alex', 'password', 'a@gmail.com'),
('Jack', 'admin', 'j@gmail.com');

INSERT INTO user_role (user_id, role) VALUES
(1, 'USER'),
(2, 'ADMIN');

INSERT INTO symptom (description) VALUES
('snuffle'),
('cough'),
('headache'),
('diarrhea'),
('chills');

INSERT INTO statistic (user_id, temperature, datetime) VALUES
(1, 37.2, '2020-10-30 10:00:00'),
(1, 37.4, '2020-10-30 15:00:00'),
(1, 36.8, '2020-10-31 12:00:00'),
(2, 36.4, '2020-11-01 10:20:00');

INSERT INTO symptom_x_statistic (statistic_id, symptom_id) VALUES
(8, 3),
(8, 5),
(9, 3),
(9, 4),
(9, 5),
(9, 6),
(10, 7)


