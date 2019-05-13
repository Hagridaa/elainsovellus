-- auto_increment kasvattaa id:tä automaattisesti
CREATE TABLE elain(
id int auto_increment not null,
laji varchar(255) not null,
nimi varchar(255) not null,
kuvaus varchar(255) not null,
hinta decimal(30,20) not null,
primary key(id)
);