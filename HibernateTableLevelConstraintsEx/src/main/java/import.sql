alter table user add constraint check_constraint check(auction_start < auction_end AND user_name <=> 'admin');
create table user1(id int primary key auto_increment);