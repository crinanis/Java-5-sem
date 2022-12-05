create sequence hibernate_sequence start 1 increment 1;

create table contacts_users (
                                userid int8 not null,
                                activation_code varchar(255),
                                active boolean not null,
                                cuser_login varchar(255) not null,
                                cuser_password varchar(255) not null,
                                cuser_name varchar(255),
                                email varchar(255),
                                primary key (userid)
);

create table contacts_list (
                         contactid int8 not null,
                         contact_name varchar(255),
                         contact_number varchar(255) not null,
                         filename varchar(255),
                         userid int8,
                         primary key (contactid)
);

create table contacts_roles (
                           userid int8 not null,
                           roles varchar(255)
);



alter table if exists contacts_list
    add constraint contacts_list_user_fk
    foreign key (userid) references contacts_users;

alter table if exists contacts_roles
    add constraint contacts_roles_user_fk
    foreign key (userid) references contacts_users;
