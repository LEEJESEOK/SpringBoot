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
    bno         number          not null,
    uemail      varchar2(255)   not null,
    bname       varchar2(255)    not null,
    btitle      varchar2(100)   not null,
    bcontent    clob             not null,
    bregdate    date              default sysdate,
    breadcount  number(5)        default 0,
    
    CONSTRAINT pk_board PRIMARY KEY (bno)
);
drop sequence seq_board;
create sequence seq_board nocache;


drop table tbl_file CASCADE CONSTRAINTS purge;
create table tbl_file (
    fno number primary key,
    bno number not null,
    fname varchar2(300) not null,
    ffolder varchar2(300) not null,
    uuid varchar2(300) not null
);

drop sequence seq_file;
create sequence seq_file nocache;