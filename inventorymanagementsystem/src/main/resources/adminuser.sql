/* execute this line in your sql first before running the app */

INSERT INTO ca_v1.user(id, role, first_name, last_name, password, username)
VALUES (1,0, "Donald",	"Trump", "$2a$10$DyBHv4zxnqH/lsh2F/f9Tu5vSEZ9zAuew.zLnl43XwIvW.rh8ac.e", "donaldtrump");

/* donaldtrump, password123 */

/*SUPPLIERS*/
INSERT INTO ca_v1.supplier(id, address, email, name, phone_number)
VALUES(1, "8 Jalan Lembah Kallang #01-01, Min Ghee Building, Singapore 339564", "mingheeauto@minghee.com", "Min Ghee Auto Pte Ltd", "62983888");

INSERT INTO ca_v1.supplier(id, address, email, name, phone_number)
VALUES(2, "6 Tannery Lane #01-05B Sindo Building, Singapore 347805", "transgloberauto@trans.com", "Transglober Auto Pte Ltd", "	68423393");

INSERT INTO ca_v1.supplier(id, address, email, name, phone_number)
VALUES(3, "7 Soon Lee Street #05-05 iSpace, Singapore 627608", "maximusauto@maximus.com", "Maximus Auto Services Pte Ltd", "62501198");

INSERT INTO ca_v1.supplier(id, address, email, name, phone_number)
VALUES(4, "50 Serangoon North Avenue 4 #02-19 First Centre, Singapore 555856", "evergreengroup@evergreen.com", "	Evergreen Group NC", "62983888");

INSERT INTO ca_v1.supplier(id, address, email, name, phone_number)
VALUES(5, "48 Toh Guan Road East #04-126 Enterprise Hub, Singapore 608586", "micracle@workz.com", "Miracle Workz Pte Ltd", "64683096");

INSERT INTO ca_v1.supplier(id, address, email, name, phone_number)
VALUES(6, "47 Sungei Kadut Loop Singapore 729512", "kheng@kheng.com", "Kheng Keng Auto Pte Ltd", "68443333");