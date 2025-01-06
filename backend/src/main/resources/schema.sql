CREATE TABLE clients (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        contact_person VARCHAR(255),
                        email VARCHAR(255),
                        phone VARCHAR(255)
);

CREATE TABLE jobs (
                     id BIGSERIAL PRIMARY KEY,
                     client_id BIGINT NOT NULL,
                     address VARCHAR(255),
                     booking_date DATE,
                     photography_date DATE,
                     delivery_date DATE,
                     number_of_photos INT,
                     editor_names VARCHAR(255),
                     number_of_edited_photos INT,
                     is_weekend_work BOOLEAN,
                     is_night_work BOOLEAN,
                     car_fee DECIMAL(10, 2),
                     extra_fee DECIMAL(10, 2),
                     status VARCHAR(255),
                     FOREIGN KEY (client_id) REFERENCES clients(id)
);
