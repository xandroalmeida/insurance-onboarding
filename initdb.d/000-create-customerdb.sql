CREATE USER customer WITH ENCRYPTED PASSWORD 'customer';
CREATE DATABASE customer OWNER customer;
GRANT ALL PRIVILEGES ON DATABASE customer TO customer;
GRANT ALL ON schema public TO customer	;