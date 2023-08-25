CREATE DATABASE social_media;

\c social_media;

CREATE TABLE account (
    id  SERIAL PRIMARY KEY,
    first_name  VARCHAR(200) NOT NULL,
    last_name   VARCHAR(200) NOT NULL,
    nickname    VARCHAR(200),
    birthday    DATE NOT NULL,
    gender      CHAR NOT NULL,
    email       VARCHAR(200) NOT NULL UNIQUE,
    password    VARCHAR(200) NOT NULL,
    profile_picture VARCHAR(200) DEFAULT 'default.png'
);


CREATE TABLE message (
    id_account_sender   INT REFERENCES account(id),
    id_account_receiver INT REFERENCES account(id),
    message_datetime    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    message_content     TEXT CHECK (message_content <> ''),
    seen_datetime       TIMESTAMP DEFAULT now(),
    CHECK (seen_datetime >= message_datetime)
);

CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    posting_date DATE DEFAULT CURRENT_DATE,
    posting_time TIME DEFAULT CURRENT_TIME,
    post_content TEXT,
    post_photo VARCHAR(100) DEFAULT null,
    id_account   INT REFERENCES account(id)
);

CREATE TABLE react_post (
    id_account        INT REFERENCES account(id),
    id_post           INT REFERENCES post(id),
    reaction_type     VARCHAR(20) NOT NULL DEFAULT 'like',
    reaction_datetime TIMESTAMP DEFAULT NOW()
);
