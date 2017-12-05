create table if not exists `task_tracker`.`company` (
  id varchar(36) not null,
  name varchar(45) not null,
  primary key(id)
);

create table if not exists `task_tracker`.`command` (
  id varchar(36) not null,
  name varchar(45) not null,
  company_id varchar(36) not null,
  primary key (id),
  index `fk_command_company_idx` (company_id asc),
  constraint `fk_command_company`
    foreign key(company_id)
    references `task_tracker`.`company`(id)
    on delete no action
    on update no action
);

drop table if exists `task_tracker`.`user_in_project`;

alter table project add command_id varchar(36);
alter table project add
  constraint fk_command_project
  foreign key (`command_id`) references `command` (`id`);
alter table project add index fk_command_project_idx (`command_id` asc);

alter table user add company_id varchar(36) not null;
alter table user add
  constraint fk_user_company
  foreign key (company_id) references `company` (`id`);
alter table user add index fk_user_company_idx (`company_id` asc);

alter table user add command_id varchar(36);
alter table user add
  constraint fk_user_command
  foreign key (command_id) references command (id);
alter table user add index fk_user_command_idx (command_id asc);

start transaction;
  insert into role (id, name) values (uuid(), 'director');
commit;
