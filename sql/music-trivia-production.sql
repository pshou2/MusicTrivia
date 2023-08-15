drop database if exists music_trivia;
create database music_trivia;
use music_trivia;

-- DDL creating tables
create table player (
	player_id int primary key auto_increment,
    gamer_tag varchar(50) not null,
    tagline varchar(50)
);

create table high_scores (
	high_scores_id int primary key auto_increment,
    score int not null,
    `date` date not null,
    `time` time not null,
    player_id int not null,
    constraint fk_high_scores_player_id
		foreign key (player_id)
        references player(player_id)
);

-- DML inserting into tables

insert into player(gamer_tag)
	values ('FILO');
    
insert into high_scores (score, `date`, `time`, player_id)
	values (5,'2023-08-15','01:25:32',1);
