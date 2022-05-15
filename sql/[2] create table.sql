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