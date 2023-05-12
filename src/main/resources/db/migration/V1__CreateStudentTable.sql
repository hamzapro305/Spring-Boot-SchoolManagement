CREATE TABLE IF NOT EXISTS student (
    studentId UUID PRIMARY KEY NOT NULL,
    userName VARCHAR(100) NOT NULL UNIQUE,
    fullName VARCHAR(100) NOT NULL,
    age int NOT NULL CHECK(age > 0 AND age <= 30),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    roles VARCHAR(100) NOT NULL,
    gender VARCHAR(6) NOT NULL
        CHECK(
            gender = 'MALE' OR gender = 'FEMALE'
        )
);