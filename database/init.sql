CREATE DATABASE IF NOT EXISTS log_analyzer;

USE log_analyzer;

CREATE TABLE IF NOT EXISTS access_log_file(
    id BIGINT AUTO_INCREMENT,
    file_path VARCHAR(100) not null,
    size INTEGER not null,
    start_date DATETIME not null,
    finish_date DATETIME not null,
    CONSTRAINT pk_access_log_file_id PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS access_log(
    id BIGINT AUTO_INCREMENT,
    request_date DATETIME not null,
    ip VARCHAR(20) not null,
    request VARCHAR(100) not null,
    user_agent VARCHAR(256) not null,
    id_access_log_file BIGINT not null,
    CONSTRAINT pk_access_log_id PRIMARY KEY(id),
    CONSTRAINT fk_access_log_access_log_file_id FOREIGN KEY(id_access_log_file) REFERENCES access_log_file(id)
);