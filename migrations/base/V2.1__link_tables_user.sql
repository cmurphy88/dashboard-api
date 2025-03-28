CREATE TABLE weight
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    weight DECIMAL,
    date TIMESTAMP DEFAULT now(),
    user_id UUID
);

ALTER TABLE todo
    ADD COLUMN user_id UUID;

ALTER TABLE todo
    ADD CONSTRAINT fk_todo_user_user_id
        FOREIGN KEY (user_id)
            REFERENCES "user" (id);

ALTER TABLE payment
    ADD COLUMN user_id UUID;

ALTER TABLE payment
    ADD CONSTRAINT fk_payment_user_user_id
        FOREIGN KEY (user_id)
            REFERENCES "user" (id);

