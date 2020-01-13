INSERT INTO CATEGORY (id, name) values (1, 'Lazer');
INSERT INTO CATEGORY (id, name) values (2, 'Alimentação');
INSERT INTO CATEGORY (id, name) values (3, 'Supermercado');
INSERT INTO CATEGORY (id, name) values (4, 'Farmácia');
INSERT INTO CATEGORY (id, name) values (5, 'Outros');

INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (1, 'João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (2, 'Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (3, 'Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (4, 'Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (5, 'Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (6, 'Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (7, 'Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (8, 'Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (9, 'Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO PERSON (id, name, street, number_address, complement, neighborhood, zip_code, city, state, active) values (10, 'Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);

INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (1, 'Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (2, 'Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (3, 'Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (4, 'CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (5, 'DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (6, 'Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 6);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (7, 'Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 7);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (8, 'Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 8);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (9, 'Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (10, 'Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 10);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (11, 'Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 5);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (12, 'Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (13, 'Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (14, 'Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO APPOINTMENT (id, description, due_date, pay_date, cost, observation, appointment_type, category_id, person_id) values (15, 'Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);

INSERT INTO USERS (id, name, email, password) values (1, 'Administrador', 'admin@testapi.com', '$2a$10$dufCpQClVReFAnikyPeJ/eCNYwIjtvL3tSG8DOkOesHyc99zxQbN.');
INSERT INTO USERS (id, name, email, password) values (2, 'Julia', 'julia@testapi.com', '$2a$10$tg7da4UIkJPQUcs3FFbkx.EIyxSt18ybRAqX9u5ySJJXXytfXNesW');

INSERT INTO PERMISSION (id, description) values (1, 'ROLE_CREATE_CATEGORY');
INSERT INTO PERMISSION (id, description) values (2, 'ROLE_FIND_CATEGORY');

INSERT INTO PERMISSION (id, description) values (3, 'ROLE_CREATE_PERSON');
INSERT INTO PERMISSION (id, description) values (4, 'ROLE_REMOVE_PERSON');
INSERT INTO PERMISSION (id, description) values (5, 'ROLE_FIND_PERSON');

INSERT INTO PERMISSION (id, description) values (6, 'ROLE_CREATE_APPOINTMENT');
INSERT INTO PERMISSION (id, description) values (7, 'ROLE_REMOVE_APPOINTMENT');
INSERT INTO PERMISSION (id, description) values (8, 'ROLE_FIND_APPOINTMENT');

INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 1);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 2);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 3);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 4);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 5);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 6);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 7);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (1, 8);

INSERT INTO USERS_PERMISSION (user_id, permission_id) values (2, 2);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (2, 5);
INSERT INTO USERS_PERMISSION (user_id, permission_id) values (2, 8);
