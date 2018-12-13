SET GLOBAL max_allowed_packet=1073741824;

CREATE DATABASE IF NOT EXISTS log_analyzer;

USE log_analyzer;

CREATE TABLE IF NOT EXISTS access_log_file(
    id BIGINT AUTO_INCREMENT,
    file_path VARCHAR(100) not null,
    size INTEGER not null,
    CONSTRAINT pk_access_log_file_id PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS access_log(
    id BIGINT AUTO_INCREMENT,
    request_date DATETIME not null,
    ip VARCHAR(20) not null,
    status VARCHAR(3) not null,
    request VARCHAR(100) not null,
    user_agent VARCHAR(256) not null,
    access_log_file_id BIGINT not null,
    CONSTRAINT pk_access_log_id PRIMARY KEY(id),
    CONSTRAINT fk_access_log_access_log_file_id FOREIGN KEY(access_log_file_id) REFERENCES access_log_file(id)
);