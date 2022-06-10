CREATE TABLE IF NOT EXISTS MEMBER
(
    ID            BIGSERIAL primary key,
    FIRST_NAME    VARCHAR(255) not null,
    IS_ENABLED    BOOLEAN      not null,
    LAST_NAME     VARCHAR(255) not null,
    MOBILE_NUMBER VARCHAR(255) not null,
    NATIONAL_CODE VARCHAR(255) not null,
    PASSWORD      VARCHAR(255) not null,
    IS_WORKING    BOOLEAN DEFAULT false,
    USERNAME      VARCHAR(255) unique
);


CREATE INDEX IF NOT EXISTS ix_member_id on MEMBER (ID);

