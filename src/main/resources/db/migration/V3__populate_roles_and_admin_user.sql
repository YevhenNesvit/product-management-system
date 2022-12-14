CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO roles (role_id, role_name) VALUES
(uuid_generate_v4(), 'ROLE_ADMIN'),
(uuid_generate_v4(), 'ROLE_USER');

INSERT INTO users (user_id, email, password, first_name, last_name, role_id)
VALUES (uuid_generate_v4(), 'admin@admin.com', '$2a$04$.k6KNYoiPDDKwjibIY8igOSMT1O0.Bvvmp2r.U7wBTktTI97Z/p2e', 'admin', 'admin', (SELECT role_id FROM roles where role_name = 'ROLE_ADMIN'))