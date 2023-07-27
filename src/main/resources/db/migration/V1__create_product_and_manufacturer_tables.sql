create table if not exists manufacturers
(
	manufacturer_id UUID PRIMARY KEY,
    manufacturer_name VARCHAR(200) NOT NULL UNIQUE
);

alter table manufacturers owner to postgresql_kcma_user;

create table if not exists products
(
	product_id UUID PRIMARY KEY,
	product_name VARCHAR(200) NOT NULL,
	price DECIMAL,
	manufacturer_id UUID,
    	FOREIGN KEY (manufacturer_id)
            REFERENCES manufacturers
            ON DELETE CASCADE
);

alter table products owner to postgresql_kcma_user;
