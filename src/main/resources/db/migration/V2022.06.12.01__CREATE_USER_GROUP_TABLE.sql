CREATE TABLE IF NOT EXISTS USER_GROUP
(
    ID          BIGSERIAL primary key,
    CREATE_DATE TIMESTAMP without time zone,
    UPDATE_DATE TIMESTAMP without time zone,
    TITLE VARCHAR(1024) not null
);
