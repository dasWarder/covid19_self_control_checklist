DROP TABLE IF EXISTS symptom_x_statistic;
DROP TABLE IF EXISTS symptom;
DROP TABLE IF EXISTS statistic;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE users
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    registered TIMESTAMP default now() NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX uniq_email_index ON users(email);

CREATE TABLE user_role
(
    user_id INTEGER NOT NULL,
    role VARCHAR,
    CONSTRAINT user_role_index UNIQUE(user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE symptom
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    description VARCHAR(255) NOT NULL
);

CREATE TABLE statistic
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id INTEGER NOT NULL,
    temperature DECIMAL NOT NULL,
    dateTime TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX statistic_user_index ON statistic (user_id, dateTime);

CREATE TABLE symptom_x_statistic
(
    statistic_id INTEGER,
    symptom_id INTEGER,
    FOREIGN KEY (statistic_id) REFERENCES statistic (id) ON DELETE CASCADE,
    FOREIGN KEY (symptom_id) REFERENCES symptom (id) ON DELETE CASCADE
);


