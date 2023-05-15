CREATE TABLE IF NOT EXISTS student (
    student_id UUID PRIMARY KEY NOT NULL,
    user_name VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    photo_url VARCHAR(150),
    age int NOT NULL CHECK(age > 0 AND age <= 30),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    roles VARCHAR(100) NOT NULL,
    gender VARCHAR(6) NOT NULL
        CHECK(
            gender = 'MALE' OR gender = 'FEMALE'
        )
);