CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE app_user(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    role VARCHAR(5) NOT NULL
);