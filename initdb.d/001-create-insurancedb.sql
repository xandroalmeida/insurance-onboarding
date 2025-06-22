CREATE USER insurance WITH ENCRYPTED PASSWORD 'insurance';
CREATE DATABASE insurance OWNER insurance;
GRANT ALL PRIVILEGES ON DATABASE insurance TO insurance;
GRANT ALL ON schema public TO insurance	;