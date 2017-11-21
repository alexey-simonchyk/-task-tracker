create table image (
  `id` varchar(36) not null,
  `path` VARCHAR(255) not null,
  PRIMARY KEY (`id`)
);

alter table user add image_id varchar(36) not null;
alter table user add constraint fk_image_id foreign key (`image_id`) references `image`(`id`);
