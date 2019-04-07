/*QUERIES*/
/*1 visi vartotojai*/
select * from user;

/*2 vartotojo el. pastas*/
select email from user where user.id=301;

/*3 vartotojo role*/
select role from user_details where user_id=301;

/*4 vartotojai kuriu role - lektorius*/
select user.first_name, user.last_name from user 
join user_details on user.id=user_details.user_id where user_details.role='LECTURER';

/*5 vartotojo prisijungimo vardas or slaptažodis*/
select user_details.username, user_details.password from user_details where user_id=302;

/*6 kokiai organizacijai priklauso vartotojas*/
select * from organization where id=(select organisation_id from user where user.id=301);

/*7 organizacijos adresas*/
select * from address where id=(select address_id from organization where name='Didžiausiais renginių organizatorius');

/*8 visi renginiai*/
select * from event;

/*9 renginių kategorijos*/
select distinct event.category from event;

/*10 daline pajieska pagal renginio pavadinima*/ 
select * from event where event.name like '%svent%';

/*11 visi renigniai kurie bus po kovo 30d.*/
select * from event where event.start_date > '2019-03-30';

/*12 visi renigniai kurie buvo iki šiandienos*/
select * from event where event.start_date < now();

/*13 visi nemokami renginiai*/
select * from event where event.is_free=1;

/*14 visi nemokami renginiai vilniuje*/
select * from event where event.is_free=1 and event.city='Vilnius';

/*15 mokymai ir sventes vilniuje*/
select * from event where event.city='Vilnius' and (event.category='Mokymai' or event.category='Šventė');

/*16 info apie renginiu organizatorius*/
select * from user join user_details on user.id=user_details.user_id where upper(role)='organisator';

/*17 info apie tam tikro renginio organizatorius*/
select user.phone, user.first_name, user.last_name from user 
join event_organisators on user.id=event_organisators.organisators_id 
join event on event.id=event_organisators.event_id
where event.id=503;

/*18 renginio tvarkaraštis*/
select * from agenda_point where event_id=503;

/*19 renginio tvarkaraštis su lektoriais*/
select * from agenda_point 
join agenda_point_presenters on agenda_point.id=agenda_point_presenters.agenda_point_id 
join user on agenda_point_presenters.presenters_id=user.id where event_id=504;

/*20 renginio tvarkaraštis su reikalinga įranga*/
select agenda_point.description, equipment.name, equipment.price_per_unit, equipment.type, equipment.supplier_id from agenda_point
join agenda_point_equipment on agenda_point.id=agenda_point_equipment.agenda_point_id
join equipment on agenda_point_equipment.equipment_id=equipment.id
where event_id=504;

/*21 visos užregistruotos organizacijos*/
select type, name, code from organization;

/*22 info apie organiacija (įrangos tiekėją)*/
select * from organization where id=203;

/*23 Esami įrangos tipai*/
select distinct equipment.type from equipment;

/*24 daline pajieska pagal organizacijos pavadinima*/ 
select * from organization where organization.name like '%organ%';

/*25 vartotojo bilietai*/
select * from ticket where user_id=305;

/*26 vartotojo bilietai į buvusius renginius*/
select * from ticket 
join event on ticket.event_id=event.id 
where user_id=305 and event.start_date < now();

/*27 visi vartotojai nusipirke bilietus i tam tikra renginį*/
select user.first_name, user.last_name from user
join ticket on user.id=ticket.user_id where ticket.event_id=505;

/*28 renginio įnešimas į vartotojo įsimintus renginius*/
insert into user_favourite_events values (305,505);

/*29 vartotojo įsiminti renginiai*/
select * from event 
join user_favourite_events on event.id=user_favourite_events.favourite_events_id 
join user on user_favourite_events.user_id=user.id
where user.id=305;

/*30 paieska tarp vartotojo įsimintų renginių pagal miesta*/
select * from event 
join user_favourite_events on event.id=user_favourite_events.favourite_events_id 
join user on user_favourite_events.user_id=user.id
where user.id=305 and event.city='Vilnius';

/*31 renginio pašalinimas iš vartotojo įsimintų renginių */
delete from user_favourite_events where user_id=305 and favourite_events_id=505;

/*32 renginio įvertinimų įrašymas*/
insert into feedback values (900, 'Geras renginys!', 5, 505, 305);
insert into feedback values (901, 'Blogas renginys!', 2, 505, 304);

/*33 renginio įvertinimų peržiūra*/
select * from feedback where event_id=505;

/*34 įvertinimų peržiųra pagal juos parašiusį vartotoją*/
select * from feedback where user_id=305;

/*35 Vidutinio renginio reitingo apskaičiavimas pagal paliktus įvertinimus*/
select avg(rating) as 'Average event rating' from feedback where event_id=505;

/*36 renginio sponsoriai*/
select organization.name as 'Sponsor name' from organization 
join event_sponsors on organization.id=event_sponsors.sponsors_id
where event_sponsors.event_id=504;

/*37 renginio aprašymas*/
select description from event where id=504;

/*38 renginių surušiavimas pagal datą*/
select event.name, event.start_date from event order by event.start_date desc;

/*39 renginio programos įvykių adresai*/
select agenda_point.description, address.city, address.street, address.building, address.apartment from address 
join agenda_point on address.id=agenda_point.address_id where agenda_point.event_id=504;

/*40 info apie vartotoją, palikusį blogą atsiliepimą apie renginį(kerštui)*/
select user.first_name, user.last_name, user.email from feedback
join user on feedback.user_id=user.id where feedback.rating <3 and feedback.event_id=505;

/*41 kiek pinigų reikia sumokėti už įrangą tiekėjui*/
select sum(equipment.price_per_unit*equipment.quantity) as 'Įrangos suma tiekėjui' from equipment where supplier_id=203;

/*42 kiek pinigų reikia sumokėti už įrangą tiekėjui už tam tikrą renginį*/
select sum(equipment.price_per_unit*equipment.quantity) as 'Įrangos suma už renginį' from equipment
left join agenda_point_equipment on agenda_point_equipment.equipment_id=equipment.id
left join agenda_point on agenda_point.id=agenda_point_equipment.agenda_point_id
where supplier_id=203 and agenda_point.event_id=504;

/*43 kiek parduota bilietų*/
select count(*) as 'Viso parduota bilietų į renginį' from ticket where ticket.event_id=505;

/*44 kiek už bilietus gauta pinigų*/
select count(*)*ticket.price as 'Viso gauta Eur uz bilietus į renginį' from ticket where ticket.event_id=505;

/*45 ar vartotojas paliko atsiliepima apie renginį į kurį nupirko biletą*/
select ticket.user_id, count(ticket.id), count(feedback.id) from feedback 
join ticket on ticket.user_id=feedback.user_id
join event on event.id=ticket.event_id where event.id=505;
