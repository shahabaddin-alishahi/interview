CREATE TABLE IF NOT EXISTS MEMBER_AUTHORITIES
(
    MEMBER_ID    BIGINT not null,
    AUTHORITY_ID BIGINT not null,
    constraint member_user_authority_pkey primary key (MEMBER_ID, AUTHORITY_ID),
    constraint fk_member foreign key (MEMBER_ID) references MEMBER (ID),
    constraint fk_authority foreign key (AUTHORITY_ID) references AUTHORITY (ID)
);

CREATE INDEX IF NOT EXISTS ix_member_id_member_authority on MEMBER_AUTHORITIES (MEMBER_ID);

