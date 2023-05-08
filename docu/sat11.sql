--------------------------------------------------------------------------------
-- 
--
--
--
--------------------------------------------------------------------------------

-- CMD에서 계정 등록
--create user sat11 identified by "0000";
--grant dba to sat11;
--exit;
--sqlplus sat11/0000;


-- 테이블 생성
create table member(
    seq     number primary key,
    mid     varchar2(20),
    mpw     varchar2(20),
    mname   varchar2(20),
    mgubun  varchar2(20),
    regdate date default sysdate
);


-- 테이블 인덱스 추가
create sequence member_seq
start with 1
increment by 1;


-- 테이블 변수 추가
insert into member values (member_seq.nextval, 'kim', '111', '김씨', 'ROLL_USER', sysdate);
insert into member values (member_seq.nextval, 'admin', '111', '관리자', 'ROLL_ADMIN', sysdate);


-- 테이블 저장
commit;


-- 테이블 조회
select * from member;
select * from member where mid='kim' and mpw='111';




-- 게시판

drop table BOARD;
CREATE TABLE BOARD
   (	BSEQ NUMBER primary key, 
	TITLE VARCHAR2(100 BYTE), 
	CONTENT VARCHAR2(1000 BYTE), 
	REGID VARCHAR2(20 BYTE), 
	REGDATE DATE DEFAULT sysdate, 
	FILEPATH VARCHAR2(100 BYTE), 
	FILENAME VARCHAR2(20 BYTE)
);

create table board_reply (
rseq number primary key,
reply varchar2(100),
bseq number,
regid varchar2(20),
regdate date default sysdate
);

create sequence board_seq
start with 1 
increment by 1;

create sequence board_reply_seq
start with 1 
increment by 1;

insert into board(bseq, title, content, regid, regdate)
values(board_seq.nextval, '제목1', '내용1', 'lee', sysdate);

insert into board(bseq, title, content, regid, regdate)
values(board_seq.nextval, '제목2', '내용2', 'kim', sysdate);

insert into board_reply(rseq, reply, bseq, regid)
values(board_reply_seq.nextval, '댓글1', 1, 'park');

insert into board_reply(rseq, reply, bseq, regid)
values(board_reply_seq.nextval, '댓글2', 1, 'lee');
commit;

select * from board_reply
select * from BOARD
