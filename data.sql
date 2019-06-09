insert into address values (101, '42', '89', 'Vilnius', 'LT-35264', 'Konstitucijos pr.');
insert into address values (102, '1', '12', 'Vilnius', 'LT-35225', 'Ukmergės g.');
insert into address values (103, '35', '45', 'Vilnius', 'LT-35525', 'Linkmenų g.');
insert into address values (104, '50', '89', 'Vilnius', 'LT-35264', 'Konstitucijos pr.');
insert into address values (105, '52', '89', 'Vilnius', 'LT-35264', 'Konstitucijos pr.');

insert into organization values (201, '301463552', 'SDG group', 'UAB', 101);
insert into organization values (202, '301463546', 'Didžiausiais renginių organizatorius', 'UAB', 102);
insert into organization values (203, '301463857', 'Įrangos tiekėjas', 'UAB', 103);
insert into organization values (204, '301463777', 'Universitetas', 'VŠĮ', 105);
insert into organization values (205, '301463154', 'Super puper Mega Sponsor', 'AB', 103);

insert into user values (301, 'petras@viko.lt', 'Petras', 'Petraitis', '+3708956451', 201);
insert into user values (302, 'ana@viko.lt', 'Ana', 'Anaitė', '+37085664531', 202);
insert into user values (303, 'john@viko.lt', 'Jonas', 'Jonaitis', '+3708956547', 202);
insert into user values (304, 'leia@viko.lt', 'Leia', 'Vader', '+370895745', 203);
insert into user (id, email, first_name, last_name, phone) values (305, 'agne@viko.lt', 'Agnė', 'Bagnė', '+370895888');
insert into user values (306, 'darius@viko.lt', 'Darius', 'Profesoraitis', '+370895888', 204);
insert into user values (307, 'evelina@viko.lt', 'Evelina', 'Docentaitė', '+370895745', 204);

insert into user values (308, null, 'Orkestras-pukestras', null , null, null);
insert into user values (309, null, 'Grupė "Grojam garsiausiai"', null, null, null);
insert into user values (310, null, 'Grupė "Opa-pa"', null, null, null);

insert into user_details values (401, '$2a$10$enFAzLEFBgVylW4o8m0AVeP//9VXrWYs/EygnoKxZFvTX/k3cuskC', 'ORGANISATOR', 'petras', 301);
insert into user_details values (402, '$2a$10$KVQiE7GlgN1rbdle28yx1.7cvXzjR.8wWFSBscpLKYWxGZQkszawC', 'ORGANISATOR','ana', 302);
insert into user_details values (403, '$2a$10$bqU.N7ZDm3Npx35h8zhYrO4nKhT89l2f9ijANMeGRu5gP8njb5NSy', 'ORGANISATOR','jonas', 303);
insert into user_details values (404, '$2a$10$hOrBOKPBbxH4Jtj37FS/gO2JsYlfo8lyLQK.D.ejPU0ykHfP2MLau', 'PARTICIPANT', 'agne', 305);
insert into user_details values (405, '$2a$10$CiHxFJnKe7CWHeojUWGQde6U9ppxQqVeng6Obr.vNRfrt67jiS/2O', 'LECTURER', 'darius', 306);
insert into user_details values (406, '$2a$10$8MPi6NDQayInV0LH17JIget.n5W.B3uUYW5.P2huDmxaYcsfD6KBe', 'LECTURER','evelina', 307);

insert into event values (501, 5, 'Paroda', 'Vilnius', 'Interaktyvių robotų paroda patiks ir vaikams ir jų tėvams', '2019-07-01', FALSE, 'Robotų paroda', '2019-07-30');
insert into event values (502, 0, 'Šventė', 'Klaipėda', 'Linksma miesto šventė visai šeimai', '2019-08-05', TRUE, 'Jūros šventė', '2019-08-05');
insert into event values (503, 0, 'Šventė', 'Vilnius', 'Koncertai po atviru dangumi visą naktį', '2019-07-12', TRUE, 'Muzikos naktis', '2019-07-12');
insert into event values (504, 10, 'Mokymai', 'Vilnius', 'Paskaitos apie programavimą', '2019-07-11', FALSE, 'Clean Code konferencija', '2019-07-10');
insert into event values (505, 25, 'Mokymai', 'Vilnius', 'Workshop for Docker practices', '2019-01-15', FALSE, 'Docker - why do we need it', '2019-01-15');
insert into event values (506, 15, 'Mokymai', 'Vilnius', 'Konferencija apie web servisų kūrimą', '2019-02-01', FALSE, 'REST vs SOAP', '2019-02-01');

insert into event_organisators values(501, 302);
insert into event_organisators values(502, 303);
insert into event_organisators values(503, 301);
insert into event_organisators values(504, 302);

insert into event_sponsors values(501, 205);
insert into event_sponsors values(504, 205);

insert into agenda_point values(705, '2019-06-05', 'Šventinė eisėna miesto gatvėmis', 'Šventinė eisėna', '12:00:00', null, 502);
insert into agenda_point values(706, '2019-06-05', 'Šventinė mugė miesto gatvėse', 'Mugė', '14:00:00', null, 502);
insert into agenda_point values(707, '2019-06-05', 'Berniukų-kiškiukų choro koncertas', 'Koncertas', '17:00:00', null, 502);

insert into agenda_point values(708, '2019-07-12', 'Bugnų koncertas Oslo gatvėje', 'Bugnų šou', '20:00:00', null, 503);
insert into agenda_point values(709, '2019-07-12', 'Gitarų koncertas Asiliukų aikštėje', 'Gitarų ritmu', '22:00:00', null, 503);
insert into agenda_point values(710, '2019-07-12', 'Akordeono koncertas Pionų kiemelyje', 'Grok akordeone', '24:00:00', null, 503);

insert into agenda_point values(701, '2019-07-10', 'Clean Java code best practices', 'Java', '10:00:00', 104, 504);
insert into agenda_point values(702, '2019-07-10', 'Debugging Javascript', 'Javascript', '12:00:00', 105, 504);
insert into agenda_point values(703, '2019-07-11', 'Let''s make our hands dirty', 'Swift', '11:00:00', 104, 504);
insert into agenda_point values(704, '2019-07-11', 'Spring security and how it can help you', 'Spring', '14:00:00', 105, 504);

insert into agenda_point_presenters values(701, 306);
insert into agenda_point_presenters values(702, 307);
insert into agenda_point_presenters values(703, 306);
insert into agenda_point_presenters values(704, 307);

insert into agenda_point_presenters values(707, 310);
insert into agenda_point_presenters values(708, 308);
insert into agenda_point_presenters values(709, 309);
insert into agenda_point_presenters values(710, 310);

insert into equipment values (601, 'Projektorius', 50, 1, 'Biuro technika', 203);
insert into equipment values (602, 'Nešiojamas kompiuteris', 120, 1, 'Biuro technika', 203);
insert into equipment values (603, 'Bowl of mud', 12, 30, 'Speciali įranga', 203);

insert into agenda_point_equipment values(701, 601);
insert into agenda_point_equipment values(701, 602);
insert into agenda_point_equipment values(702, 601);
insert into agenda_point_equipment values(702, 602);
insert into agenda_point_equipment values(703, 603);

insert into ticket values(801, 25, 505, 305);
insert into ticket values(802, 15, 506, 305);