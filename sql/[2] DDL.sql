CREATE TABLE tbl_user (
    email    VARCHAR2(255),
    password VARCHAR2(255) NOT NULL,
    name     VARCHAR2(20) NOT NULL,
    regdate  DATE DEFAULT sysdate,
    social   NUMBER(1),
    CONSTRAINT pk_user PRIMARY KEY ( email )
);

CREATE TABLE tbl_user_role_set (
    user_email VARCHAR2(255) NOT NULL,
    role_set   VARCHAR2(255),
    CONSTRAINT fk_role_set FOREIGN KEY ( user_email )
        REFERENCES tbl_user ( email )
            ON DELETE CASCADE
);