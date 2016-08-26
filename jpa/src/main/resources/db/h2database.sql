INSERT INTO contacts (first_name, last_name, birth_date, email, version)
VALUES ('Summer', 'Glass', '1968-08-05', 'vitae@egestasadui.net', 0),
  ('Mikayla', 'Church', '1975-04-03', 'lobortis.Class@aliquam.org', 0),
  ('Shaine', 'Brooks', '1971-08-24', 'vel.pede@metusVivamuseuismod.edu', 0),
  ('Robin', 'Sullivan', '1961-09-09', 'purus.gravida@necleo.edu', 0),
  ('Xantha', 'Kim', '1960-08-25', 'risus.Duis.a@velnisl.ca', 0),
  ('Barry', 'Kirk', '1982-03-27', 'blandit.at@Maurisblanditenim.com', 0),
  ('Tad', 'Robellaboy', '1972-08-08', 'In.lorem.Donec@Vivamusnisi.org', 0),
  ('Finn', 'Robertorobo', '1974-05-27', 'aliquet@ornare.net', 0),
  ('Ali', 'Calhoun', '1976-11-30', 'fermentum@nulla.co.uk', 0),
  ('Alexandra', 'Hendricks', '1973-07-05', 'at.auctor@pellentesquemassalobortis.edu', 0);


INSERT INTO contact_phones (contact_id,phone_type,phone_number) VALUES ('1','Mobile','1-113-753-8020'),('1','Home','1-996-507-0853'),('2','Mobile','1-407-100-1341'),('2','Home','1-285-981-2510'),('3','Mobile','1-274-311-9291'),('3','Home','1-499-112-9185'),('4','Mobile','1-234-628-6511'),('4','Home','1-560-178-3273'),('5','Mobile','1-430-941-9233'),('5','Home','1-271-831-8886');
INSERT INTO contact_phones (contact_id,phone_type,phone_number) VALUES ('6','Mobile','1-255-105-0103'),('6','Home','1-481-652-4155'),('7','Mobile','1-917-917-8478'),('7','Home','1-766-831-2271'),('8','Mobile','1-863-515-3218'),('8','Home','1-930-909-9849'),('9','Mobile','1-423-399-6903'),('9','Home','1-294-840-1996'),('10','Mobile','1-661-300-3848'),('10','Home','1-972-479-8970');

INSERT INTO hobbies (hobby_title) VALUES ('Jogging');
INSERT INTO hobbies (hobby_title) VALUES ('Movies');
INSERT INTO hobbies (hobby_title) VALUES ('Programming');
INSERT INTO hobbies (hobby_title) VALUES ('Reading');
INSERT INTO hobbies (hobby_title) VALUES ('Swimming');

INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('1','1'),('1','2'),('2','3'),('2','4'),('3','5'),('3','1'),('4','2'),('4','3'),('5','4'),('5','5');
INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('6','1'),('6','2'),('7','3'),('7','4'),('8','5'),('8','1'),('9','2'),('9','3'),('10','4'),('10','5');
