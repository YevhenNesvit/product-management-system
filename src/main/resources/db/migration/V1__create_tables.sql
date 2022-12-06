create table manufacturers
(
	manufacturer_id VARCHAR(200) PRIMARY KEY,
    manufacturer_name VARCHAR(200) NOT NULL UNIQUE
);

alter table manufacturers owner to postgres;

create table products
(
	product_id VARCHAR(200) PRIMARY KEY,
	product_name VARCHAR(200) NOT NULL,
	price DECIMAL,
	manufacturer_name VARCHAR(200),
    	FOREIGN KEY (manufacturer_name)
            REFERENCES manufacturers
);

alter table products owner to postgres;