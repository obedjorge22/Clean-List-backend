CREATE TABLE sessions (

                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          user_id BIGINT NOT NULL,

                          token_id VARCHAR(255) NOT NULL,

                          token VARCHAR(255),

                          user_agent VARCHAR(500),

                          expires_at DATETIME NOT NULL,

                          last_used_at DATETIME,

                          revoked BOOLEAN NOT NULL DEFAULT FALSE,

                          created_at DATETIME NOT NULL,

                          updated_at DATETIME,

                          deleted_at DATETIME,

                          CONSTRAINT fk_session_user
                              FOREIGN KEY(user_id)
                                  REFERENCES users(id)
);