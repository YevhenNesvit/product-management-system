create table if not exists roles
(
	role_id UUID PRIMARY KEY,
    role_name VARCHAR(200) NOT NULL
);

alter table roles owner to postgres;

create table if not exists users
(
	user_id UUID PRIMARY KEY,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    role_id UUID,
        FOREIGN KEY (role_id)
            REFERENCES roles
            ON DELETE CASCADE
);

alter table roles owner to postgres;