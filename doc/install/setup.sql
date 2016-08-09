SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `contact_id` BIGINT       NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(40)  NOT NULL,
  `last_name`  VARCHAR(40)  NOT NULL,
  `birth_date` DATE                  DEFAULT NULL,
  `email`      VARCHAR(100) NOT NULL,
  `version`    INT(11)      NOT NULL DEFAULT '0',
  PRIMARY KEY (`contact_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

-- ----------------------------
-- Table structure for contact_hobby_ids
-- ----------------------------
DROP TABLE IF EXISTS `contact_hobby_ids`;
CREATE TABLE `contact_hobby_ids` (
  `contact_hobby_id` BIGINT NOT NULL AUTO_INCREMENT,
  `contact_id`       BIGINT NOT NULL,
  `hobby_id`         BIGINT NOT NULL,
  PRIMARY KEY (`contact_hobby_id`),
  KEY `fk_hobby_contact_id` (`contact_id`),
  KEY `fk_hobby_hobby_id` (`hobby_id`),
  CONSTRAINT `fk_hobby_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_hobby_hobby_id` FOREIGN KEY (`hobby_id`) REFERENCES `hobbies` (`hobby_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

-- ----------------------------
-- Table structure for contact_phones
-- ----------------------------
DROP TABLE IF EXISTS `contact_phones`;
CREATE TABLE `contact_phones` (
  `contact_phone_id` BIGINT      NOT NULL AUTO_INCREMENT,
  `contact_id`       BIGINT      NOT NULL,
  `tel_type`         VARCHAR(20) NOT NULL,
  `tel_number`       VARCHAR(20) NOT NULL,
  `version`          INT(11)     NOT NULL DEFAULT '0',
  PRIMARY KEY (`contact_phone_id`),
  KEY `fk_contact_phones_contact_id` (`contact_id`),
  CONSTRAINT `fk_contact_phones_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

-- ----------------------------
-- Table structure for hobbies
-- ----------------------------
DROP TABLE IF EXISTS `hobbies`;
CREATE TABLE `hobbies` (
  `hobby_id`    BIGINT      NOT NULL AUTO_INCREMENT,
  `hobby_title` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`hobby_id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

-- ----------------------------
-- View structure for v_contact_hobbies
-- ----------------------------
DROP VIEW IF EXISTS `v_contact_hobbies`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`root`@`localhost`
  SQL SECURITY DEFINER VIEW `v_contact_hobbies` AS
  SELECT
    `contacts`.`contact_id` AS `contact_id`,
    `contacts`.`first_name` AS `first_name`,
    `contacts`.`last_name`  AS `last_name`,
    `contacts`.`birth_date` AS `birth_date`,
    `contacts`.`email`      AS `email`,
    `hobbies`.`hobby_id`    AS `hobby_id`,
    `hobbies`.`hobby_title` AS `hobby_title`
  FROM ((`contacts`
    JOIN `contact_hobby_ids` ON ((`contacts`.`contact_id` = `contact_hobby_ids`.`contact_id`))) JOIN `hobbies`
      ON ((`contact_hobby_ids`.`hobby_id` = `hobbies`.`hobby_id`)))
  ORDER BY `contacts`.`contact_id`;

-- ----------------------------
-- View structure for v_contact_phones
-- ----------------------------
DROP VIEW IF EXISTS `v_contact_phones`;
CREATE ALGORITHM = UNDEFINED
  DEFINER =`root`@`localhost`
  SQL SECURITY DEFINER VIEW `v_contact_phones` AS
  SELECT
    `contacts`.`contact_id`             AS `contact_id`,
    `contacts`.`first_name`             AS `first_name`,
    `contacts`.`last_name`              AS `last_name`,
    `contacts`.`birth_date`             AS `birth_date`,
    `contacts`.`email`                  AS `email`,
    `contact_phones`.`tel_type`         AS `tel_type`,
    `contact_phones`.`tel_number`       AS `tel_number`,
    `contact_phones`.`contact_phone_id` AS `contact_phone_id`
  FROM (`contacts`
    JOIN `contact_phones` ON ((`contacts`.`contact_id` = `contact_phones`.`contact_id`)));


-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO contacts (first_name, last_name, birth_date, email, version)
VALUES ('Summer', 'Glass', '1977-09-15', 'nec@enimSuspendisse.ca', 0),
  ('Libby', 'Goff', '1964-07-27', 'lectus.ante.dictum@placeratvelit.ca', 0),
  ('Shad', 'Mason', '1963-09-28', 'nunc.ac.mattis@metusVivamuseuismod.net', 0),
  ('Aidan', 'Watkins', '1970-06-21', 'sit.amet.metus@Inmi.org', 0),
  ('Gareth', 'Montgomery', '1979-03-22', 'Cum.sociis@amifringilla.ca', 0),
  ('Cruz', 'Rogers', '1975-03-30', 'eget.laoreet.posuere@ornareIn.org', 0),
  ('Freya', 'Carson', '1982-07-26', 'facilisis@aodio.org', 0),
  ('Raymond', 'Hickman', '1968-02-24', 'Nam.nulla@pedenonummyut.edu', 0),
  ('Denise', 'Hull', '1958-10-17', 'euismod.in.dolor@ametultricies.com', 0),
  ('Xenos', 'Thompson', '1959-12-10', 'sit.amet.massa@elementumduiquis.ca', 0);
-- INSERT INTO contacts (first_name,last_name,birth_date,email,version) VALUES ('Quincy','Glenn','1974-11-23','dui.nec.urna@egestasAliquamfringilla.org',0),('Ferris','Barker','1980-04-16','pellentesque.a.facilisis@gravida.co.uk',0),('Armand','Armstrong','1976-06-27','hendrerit.neque.In@lectusquismassa.co.uk',0),('Alexis','Bradley','1981-04-26','et.magnis.dis@taciti.edu',0),('Kennan','George','1972-01-04','commodo.tincidunt@nuncIn.ca',0),('Sloane','Cochran','1972-03-02','nec.ligula.consectetuer@blanditcongue.com',0),('Nita','Dawson','1957-05-24','pretium@nonsapienmolestie.edu',0),('Brent','Harris','1973-09-10','eu.dui@velarcueu.com',0),('Thane','Padilla','1978-04-28','et@metusfacilisis.ca',0),('Caesar','Wiggins','1977-05-28','vulputate.nisi@necanteblandit.ca',0);
-- INSERT INTO contacts (first_name,last_name,birth_date,email,version) VALUES ('Wendy','Johnston','1967-04-21','ante.ipsum@convallis.net',0),('Sasha','Koch','1958-09-28','Pellentesque@feugiatmetussit.ca',0),('Zachary','Odonnell','1978-06-29','ullamcorper.Duis.at@posuere.edu',0),('Demetria','Whitney','1957-08-15','Fusce@ridiculus.net',0),('Walter','Lang','1966-01-11','pretium.aliquet.metus@rutrum.org',0),('Heidi','Hale','1972-09-26','Curabitur.egestas@Pellentesqueutipsum.ca',0),('Troy','Potter','1976-06-02','dolor@tristique.co.uk',0),('Ivy','Buck','1982-03-09','cursus@id.ca',0),('Keane','Steele','1968-04-22','commodo@ultriciessem.net',0),('Cullen','Hammond','1981-09-15','Duis.sit.amet@facilisisvitae.ca',0);
-- INSERT INTO contacts (first_name,last_name,birth_date,email,version) VALUES ('Freya','Stevens','1968-01-16','ut@molestiesodalesMauris.ca',0),('Ina','Parker','1967-03-28','quam.Pellentesque.habitant@Vestibulumante.net',0),('Berk','Hahn','1963-01-19','interdum.ligula.eu@imperdietornare.ca',0),('Nichole','Horn','1971-11-26','enim@lacusUtnec.com',0),('Ava','Gutierrez','1970-12-18','Cras@felisadipiscing.com',0),('Risa','Beck','1963-04-04','eget.ipsum.Suspendisse@Phasellusvitae.co.uk',0),('Ashton','Salazar','1968-08-13','purus.ac@velit.net',0),('Branden','Stephens','1967-12-14','id@elementum.edu',0),('Mariko','Zamora','1981-04-09','dolor.sit.amet@et.ca',0),('Jack','Rose','1968-07-20','mauris.blandit@dictum.co.uk',0);
-- INSERT INTO contacts (first_name,last_name,birth_date,email,version) VALUES ('Nell','Munoz','1964-08-28','eget@Inmi.org',0),('Libby','Dixon','1972-12-18','tempus.risus@nec.com',0),('Evelyn','Lyons','1970-08-12','Pellentesque.habitant@Donecatarcu.org',0),('Howard','Dunn','1983-01-25','a.scelerisque@acrisusMorbi.ca',0),('Stone','Parsons','1978-05-02','velit@aliquet.ca',0),('Paula','Alvarez','1959-01-22','tincidunt.Donec.vitae@ornare.com',0),('Teagan','Brewer','1963-02-06','nibh@mus.co.uk',0),('Avye','Cochran','1968-07-23','dignissim.pharetra@sapienAeneanmassa.ca',0),('Shelly','Mcneil','1964-06-08','magna.Cras.convallis@amagnaLorem.com',0),('Melinda','Baker','1960-10-21','Morbi.metus.Vivamus@montes.ca',0);


-- ----------------------------
-- Records of contact_hobby_ids
-- ----------------------------

INSERT INTO contact_hobby_ids (contact_id, hobby_id)
VALUES ('1', '1'), ('1', '2'), ('2', '3'), ('2', '4'), ('3', '5'), ('3', '1'), ('4', '2'), ('4', '3'), ('5', '4'),
  ('5', '5');
INSERT INTO contact_hobby_ids (contact_id, hobby_id)
VALUES ('6', '1'), ('6', '2'), ('7', '3'), ('7', '4'), ('8', '5'), ('8', '1'), ('9', '2'), ('9', '3'), ('10', '4'),
  ('10', '5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('11','1'),('11','2'),('12','3'),('12','4'),('13','5'),('13','1'),('14','2'),('14','3'),('15','4'),('15','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('16','1'),('16','2'),('17','3'),('17','4'),('18','5'),('18','1'),('19','2'),('19','3'),('20','4'),('20','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('21','1'),('21','2'),('22','3'),('22','4'),('23','5'),('23','1'),('24','2'),('24','3'),('25','4'),('25','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('26','1'),('26','2'),('27','3'),('27','4'),('28','5'),('28','1'),('29','2'),('29','3'),('30','4'),('30','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('31','1'),('31','2'),('32','3'),('32','4'),('33','5'),('33','1'),('34','2'),('34','3'),('35','4'),('35','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('36','1'),('36','2'),('37','3'),('37','4'),('38','5'),('38','1'),('39','2'),('39','3'),('40','4'),('40','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('41','1'),('41','2'),('42','3'),('42','4'),('43','5'),('43','1'),('44','2'),('44','3'),('45','4'),('45','5');
-- INSERT INTO contact_hobby_ids (contact_id,hobby_id) VALUES ('46','1'),('46','2'),('47','3'),('47','4'),('48','5'),('48','1'),('49','2'),('49','3'),('50','4'),('50','5');


-- ----------------------------
-- Records of contact_phones
-- ----------------------------
INSERT INTO contact_phones (contact_id, tel_type, tel_number)
VALUES ('1', 'Mobile', '1-113-753-8020'), ('1', 'Home', '1-996-507-0853'), ('2', 'Mobile', '1-407-100-1341'),
  ('2', 'Home', '1-285-981-2510'), ('3', 'Mobile', '1-274-311-9291'), ('3', 'Home', '1-499-112-9185'),
  ('4', 'Mobile', '1-234-628-6511'), ('4', 'Home', '1-560-178-3273'), ('5', 'Mobile', '1-430-941-9233'),
  ('5', 'Home', '1-271-831-8886');
INSERT INTO contact_phones (contact_id, tel_type, tel_number)
VALUES ('6', 'Mobile', '1-255-105-0103'), ('6', 'Home', '1-481-652-4155'), ('7', 'Mobile', '1-917-917-8478'),
  ('7', 'Home', '1-766-831-2271'), ('8', 'Mobile', '1-863-515-3218'), ('8', 'Home', '1-930-909-9849'),
  ('9', 'Mobile', '1-423-399-6903'), ('9', 'Home', '1-294-840-1996'), ('10', 'Mobile', '1-661-300-3848'),
  ('10', 'Home', '1-972-479-8970');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('11','Mobile','1-700-975-4810'),('11','Home','1-841-475-4950'),('12','Mobile','1-540-332-5929'),('12','Home','1-305-787-5001'),('13','Mobile','1-375-858-9683'),('13','Home','1-449-614-8027'),('14','Mobile','1-850-680-7774'),('14','Home','1-860-636-1523'),('15','Mobile','1-435-817-1311'),('15','Home','1-558-597-6941');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('16','Mobile','1-279-560-1242'),('16','Home','1-525-908-3449'),('17','Mobile','1-620-476-3968'),('17','Home','1-964-291-6520'),('18','Mobile','1-715-315-5189'),('18','Home','1-235-427-2071'),('19','Mobile','1-433-929-5046'),('19','Home','1-659-580-3903'),('20','Mobile','1-745-620-3943'),('20','Home','1-615-378-2289');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('21','Mobile','1-599-942-7672'),('21','Home','1-586-543-3663'),('22','Mobile','1-281-719-3781'),('22','Home','1-164-913-3867'),('23','Mobile','1-713-373-7813'),('23','Home','1-854-970-7530'),('24','Mobile','1-551-559-3227'),('24','Home','1-353-987-0030'),('25','Mobile','1-479-983-5533'),('25','Home','1-302-826-4182');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('26','Mobile','1-218-461-7454'),('26','Home','1-860-750-5575'),('27','Mobile','1-204-936-3074'),('27','Home','1-392-712-9284'),('28','Mobile','1-314-666-8132'),('28','Home','1-468-156-5733'),('29','Mobile','1-541-668-7686'),('29','Home','1-261-130-4105'),('30','Mobile','1-265-284-1978'),('30','Home','1-216-412-5566');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('31','Mobile','1-851-500-8601'),('31','Home','1-362-623-5415'),('32','Mobile','1-277-929-6170'),('32','Home','1-116-906-6099'),('33','Mobile','1-462-472-7433'),('33','Home','1-316-396-6314'),('34','Mobile','1-877-973-6758'),('34','Home','1-113-934-8108'),('35','Mobile','1-583-331-2033'),('35','Home','1-851-242-3442');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('36','Mobile','1-734-811-9586'),('36','Home','1-558-779-0757'),('37','Mobile','1-442-967-7381'),('37','Home','1-377-470-4176'),('38','Mobile','1-623-171-8898'),('38','Home','1-594-702-0689'),('39','Mobile','1-231-846-7503'),('39','Home','1-584-615-2296'),('40','Mobile','1-995-341-8582'),('40','Home','1-967-587-1129');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('41','Mobile','1-169-735-7048'),('41','Home','1-610-422-7955'),('42','Mobile','1-317-644-9689'),('42','Home','1-743-104-0964'),('43','Mobile','1-387-933-3362'),('43','Home','1-994-652-7710'),('44','Mobile','1-523-680-2976'),('44','Home','1-603-353-1877'),('45','Mobile','1-429-253-2308'),('45','Home','1-743-976-6151');
-- INSERT INTO contact_phones (contact_id,tel_type,tel_number) VALUES ('46','Mobile','1-561-354-9166'),('46','Home','1-337-248-0267'),('47','Mobile','1-540-262-9428'),('47','Home','1-187-969-5770'),('48','Mobile','1-196-635-8512'),('48','Home','1-785-584-9612'),('49','Mobile','1-405-719-1651'),('49','Home','1-838-192-7697'),('50','Mobile','1-652-578-3126'),('50','Home','1-768-778-7355');


-- ----------------------------
-- Records of hobby
-- ----------------------------
INSERT INTO hobbies (hobby_title) VALUES ('Jogging');
INSERT INTO hobbies (hobby_title) VALUES ('Movies');
INSERT INTO hobbies (hobby_title) VALUES ('Programming');
INSERT INTO hobbies (hobby_title) VALUES ('Reading');
INSERT INTO hobbies (hobby_title) VALUES ('Swimming');