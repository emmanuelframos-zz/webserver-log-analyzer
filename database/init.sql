SET GLOBAL max_allowed_packet=1073741824;

CREATE DATABASE IF NOT EXISTS log_analyzer;

USE log_analyzer;

CREATE TABLE IF NOT EXISTS access_log(
    id BIGINT not null,
    request_date DATETIME not null,
    ip VARCHAR(20) not null,
    status VARCHAR(3) not null,
    request VARCHAR(100) not null,
    user_agent VARCHAR(256) not null,
    CONSTRAINT pk_access_log_id PRIMARY KEY(id)
);