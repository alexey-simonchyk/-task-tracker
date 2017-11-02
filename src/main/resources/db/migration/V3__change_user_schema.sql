alter table user add unique (email);
alter table user add column nick varchar(20) not null;