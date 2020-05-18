create table rules_table (
    rule_id bigint not null auto_increment,
    rule_name varchar(50) not null unique,
    rule_string varchar(32000) not null,
    primary key (rule_id),
    unique key rules_table_unique_key (rule_name)
);