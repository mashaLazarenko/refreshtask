create table Linux ( 
Id number primary key, 
ParentId number, 
Name varchar2(50) 
);

insert into Linux values (1, 0, 'debian');

insert into Linux values (2, 1, 'ubuntu');

insert into Linux values (3, 2, 'kubuntu');

insert into Linux values (4, 2, 'lubuntu');

insert into Linux values (5, 2, 'linux mint');

insert into Linux values (6, 0, 'slackware');

insert into Linux values (7, 6, 'slax');

insert into Linux values (8, 7, 'wolvix');

insert into Linux values (9, 7, 'slampp');

insert into Linux values (10, 7, 'dnalinux');

insert into Linux values (11, 6, 'suse');

insert into Linux values (12, 11, 'linkat');

insert into Linux values (13, 11, 'opensuse');

insert into Linux values (14, 0, 'redhat');

insert into Linux values (15, 14, 'fedora core');

insert into Linux values (16, 15, 'sailfish os');

insert into Linux values (17, 15, 'fedora');

