
ALTER TABLE "exercise"
DROP COLUMN "session_id";

ALTER TABLE "exercise_set"
ADD COLUMN session_id UUID;