
    create table EMAIL_TEMPLATE_ITEM (
        ID bigint not null,
        LAST_MODIFIED timestamp not null,
        OWNER varchar(255) not null,
        SUBJECT clob(255) not null,
        MESSAGE clob(255) not null,
        TEMPLATE_KEY varchar(255) not null,
        TEMPLATE_LOCALE varchar(255),
        defaultType varchar(255),
        primary key (ID)
    );

    create index email_templ_owner on EMAIL_TEMPLATE_ITEM (OWNER);

    create index email_templ_key on EMAIL_TEMPLATE_ITEM (TEMPLATE_KEY);

    create table hibernate_unique_key (
         next_hi integer 
    );

    insert into hibernate_unique_key values ( 0 );
