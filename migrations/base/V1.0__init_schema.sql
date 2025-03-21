CREATE TABLE todo
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP DEFAULT now(),
    due_date TIMESTAMP,
    content TEXT,
    completed BOOLEAN
);

CREATE TABLE payment
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT,
    amount DECIMAL(10, 2),
    month TEXT,
    is_income BOOLEAN
)