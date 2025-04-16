CREATE TABLE session
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT,
    date TIMESTAMP DEFAULT now(),
    user_id UUID
);

CREATE TABLE exercise
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT,
    session_id UUID
);

CREATE TABLE set
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "order" INT,
    weight DECIMAL(10, 2),
    reps INT,
    exercise_id UUID
);

ALTER TABLE session
    ADD CONSTRAINT fk_session_user_user_id
        FOREIGN KEY (user_id)
            REFERENCES "user" (id);

ALTER TABLE exercise
    ADD CONSTRAINT fk_exercise_session_session_id
        FOREIGN KEY (session_id)
            REFERENCES "session" (id);

ALTER TABLE set
    ADD CONSTRAINT fk_set_exercise_exercise_id
        FOREIGN KEY (exercise_id)
            REFERENCES "exercise" (id);
