alter table project add company_id varchar(36) not null;
alter table project add
  constraint fk_project_company
  foreign key (company_id) references company (id);
alter table project add index fk_project_company_idx (company_id asc);