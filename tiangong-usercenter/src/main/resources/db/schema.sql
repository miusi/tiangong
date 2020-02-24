
create table if not  exists base_user(
    id int(11) not null auto_increment comment '自增Id',
    user_name varchar(64) comment '用户名',
    password varchar(64) comment '密码',
    created_time timestamp default current_timestamp  comment '创建时间',
    modified_time timestamp default current_timestamp comment '更新时间',
    PRIMARY KEY (`id`)
);

create table if not  exists base_role(
     id int(11) not null auto_increment,
    role_name varchar(64) not null comment '角色名' ,
    created_time timestamp default current_timestamp comment '创建时间' ,
    modified_time timestamp default current_timestamp comment '更新时间',
      PRIMARY KEY (`id`)
);

create  table if not  exists base_user_role(
    id int(11) not null auto_increment comment '自增id',
    user_id int not null comment '用户id',
    role_id int not null comment '角色id',
      PRIMARY KEY (`id`)
);

create table if not  exists base_resource(
    id int(11) not null auto_increment comment '自增id',
    categore varchar(32) not null  comment '类型：application、menu',
    created_time timestamp default current_timestamp comment '创建时间' ,
    modified_time timestamp default current_timestamp comment '创建时间',
      PRIMARY KEY (`id`)
);