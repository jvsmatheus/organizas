CREATE TABLE verification_token(
    id         UUID PRIMARY KEY,
    token      VARCHAR(255),
    user_id    UUID      NOT NULL,
    expires_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_verification_token_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);