CREATE TABLE USERS
(
    ID       VARCHAR PRIMARY KEY,
    USERNAME VARCHAR NOT NULL,
    EMAIL    VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL
);

CREATE TABLE USERS_ROLES
(
    USERS_ID     VARCHAR NOT NULL ,
    ROLE        VARCHAR NOT NULL ,
    CONSTRAINT user_roles_idx UNIQUE (USERS_ID, ROLE),
    FOREIGN KEY (USERS_ID) REFERENCES USERS(ID) ON DELETE CASCADE

);