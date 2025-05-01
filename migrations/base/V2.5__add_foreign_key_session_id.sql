ALTER TABLE exercise_set
    ADD CONSTRAINT fk_exercise_set_session_session_id
        FOREIGN KEY (session_id)
            REFERENCES "session" (id);