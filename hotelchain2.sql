insert into hotel values (2,3,'reservas@jardimbotanico.com','Jardim Botânico','81977776666',110,160,50,60);
insert into hotel values (3,5,'booking@atlantico.com','Mar Atlântico','81955554444',100,220,40,150);
insert into hotel values (1,4,'contato@grupofloaroma.com','Parque das Flores','81999998888',80,110,80,90);

-- INSERINDO DADOS NA TABELA GUEST
insert into guest values (10,'1990-09-09','Rafa_The_Boss@qualitreta.com','Rafael Duarte','1234','FIDELITY','81987654321');
insert into guest values (11,'1987-10-09','1515@qualitreta.com','Thiago Santos','5678','REGULAR','81984928631');
insert into guest values (12,'1993-05-17','DroidQueen@qualitreta.com','Mirela Lima','4121','REGULAR','81995867283');

-- INSERINDO DADOS NA TABELA RESERVATION
insert into reservation values (1,'2020-10-09','2020-10-12',0,12,2);
insert into reservation values (2,'2020-10-29','2020-11-02',0,10,3);
insert into reservation values (3,'2020-12-25','2020-01-02',0,11,1);



select * from hotel;
select * from guest;
select * from reservation;