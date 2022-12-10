create table roles
(
	role_id UUID PRIMARY KEY,
    role_name VARCHAR(200) NOT NULL
);

alter table roles owner to postgres;

create table users
(
	user_id UUID PRIMARY KEY,
    email VARCHAR(200) NOT NULL,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200)
);

alter table roles owner to postgres;