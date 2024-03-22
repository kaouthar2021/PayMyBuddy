BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "user" (
	"user_id"	integer,
	"email"	varchar(255),
	"first_name"	varchar(255),
	"last_name"	varchar(255),
	"password"	varchar(255),
	"solde"	float,
	PRIMARY KEY("user_id")
);
CREATE TABLE IF NOT EXISTS "friends" (
	"user_id"	integer NOT NULL,
	"friend_id"	integer NOT NULL
);
CREATE TABLE IF NOT EXISTS "transactions" (
	"id"	integer,
	"amount"	float,
	"description"	varchar(255),
	"user_id_receiver"	integer,
	"user_id_sender"	integer,
	"transaction_date"	timestamp,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "bank_account" (
	"id_count_bank"	integer,
	"iban"	varchar(255),
	"user_id"	integer,
	"description"	varchar(255),
	PRIMARY KEY("id_count_bank")
);
INSERT INTO "user" VALUES (4,'youssefBaroudi@gmail.com','Youssef','baroudi','$2a$10$V5kedT98bH/RehlGUiUsqedYL6LzLOfESZGZmMr9ur75uEwlOSnmS',200.0);
INSERT INTO "user" VALUES (5,'adambaroudi@hotmail.fr','Adam','baroudi','$2a$10$v1//Knd5z5myGv9tvSGzvOW5aeIXMr3g5ky62RJC8fpKLxPQ0Wcpi',570.0);
INSERT INTO "user" VALUES (6,'touijerkaouthar@hotmail.fr','kaouthar','BAROUDI','$2a$10$OhRnzeKM7dhsYxEN3Gi8xO3xRDDYRCoSLjuB2NgBMtuwspTPHfN4O',997.4949);
INSERT INTO "user" VALUES (7,'johnBoyd@gmail.com','john','boyd','$2a$10$ZRA7qeFYFkhlubRl9fjIyeSu5JKcwptglOnRJVolPOjp6DcrSC3nO',321.988);
INSERT INTO "user" VALUES (8,'tenz@email.com','Tenley','boyd','$2a$10$WqgYBTzWtTjyNC8.wojnLOo0W2EgBjgcpnj4LhQ8jMC/t4UGqXFM6',160.0);
INSERT INTO "friends" VALUES (7,8);
INSERT INTO "friends" VALUES (7,5);
INSERT INTO "friends" VALUES (7,6);
INSERT INTO "friends" VALUES (5,8);
INSERT INTO "friends" VALUES (5,6);
INSERT INTO "friends" VALUES (5,7);
INSERT INTO "friends" VALUES (4,7);
INSERT INTO "friends" VALUES (4,6);
INSERT INTO "friends" VALUES (4,8);
INSERT INTO "friends" VALUES (4,5);
INSERT INTO "friends" VALUES (6,7);
INSERT INTO "friends" VALUES (6,8);
INSERT INTO "friends" VALUES (6,4);
INSERT INTO "transactions" VALUES (1,100.0,'trip money',8,7,1704984730329);
INSERT INTO "transactions" VALUES (2,50.0,'transfert ',5,7,1705552565960);
INSERT INTO "transactions" VALUES (3,20.0,'trip money',8,7,1705661923430);
INSERT INTO "transactions" VALUES (4,20.0,'transfert ',7,6,1705821123741);
INSERT INTO "transactions" VALUES (5,20.0,'trip money',5,7,1705821403819);
INSERT INTO "transactions" VALUES (6,50.0,'trip money',6,7,1705821763924);
INSERT INTO "transactions" VALUES (7,10.0,'trip money',7,6,1706038873779);
INSERT INTO "transactions" VALUES (8,20.0,'trip money',7,6,1707571169840);
INSERT INTO "transactions" VALUES (9,12.0,'trip money',7,6,1707571945935);
INSERT INTO "transactions" VALUES (10,40.0,'add ',8,6,1707572655511);
INSERT INTO "transactions" VALUES (11,-100.0,'Transfer to my IBAN Account 123',6,6,NULL);
INSERT INTO "bank_account" VALUES (1,'123',7,'trip money');
INSERT INTO "bank_account" VALUES (2,'123',6,'trip money');
INSERT INTO "bank_account" VALUES (3,'456',4,'transfert ');
COMMIT;
