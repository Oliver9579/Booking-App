CREATE TABLE IF NOT EXISTS verification_tokens
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    token_value varchar(255) NOT NULL,
    created_at  BIGINT NOT NULL,
    user_id   INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE users
    ADD COLUMN enabled BOOLEAN DEFAULT false;

UPDATE users
SET enabled = TRUE;