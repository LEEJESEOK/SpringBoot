drop table tbl_user CASCADE CONSTRAINTS purge;
CREATE TABLE tbl_user (
    email    VARCHAR2(255),
    password VARCHAR2(255) NOT NULL,
    name     VARCHAR2(255) NOT NULL,
    regdate  DATE DEFAULT sysdate,
    social   NUMBER(1),
    CONSTRAINT pk_user PRIMARY KEY ( email )
);

drop table tbl_user_role_set CASCADE CONSTRAINTS purge;
CREATE TABLE tbl_user_role_set (
    user_email VARCHAR2(255) NOT NULL,
    role_set   VARCHAR2(255),
    CONSTRAINT fk_role_set FOREIGN KEY ( user_email )
        REFERENCES tbl_user ( email )
            ON DELETE CASCADE
);

drop table tbl_board CASCADE CONSTRAINTS purge;
create table tbl_board (
    bno         number          primary key,
    bname	    varchar2(20)    not null,
    btitle      varchar2(100)   not null,
    bcontent    clob      	    not null,
    bregdate    date      	  	default sysdate,
    breadcount  number(5) 	    default 0
);

drop sequence seq_board;
create sequence seq_board nocache;