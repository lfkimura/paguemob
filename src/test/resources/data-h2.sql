insert into company(name, cnpj, telephone, website,industry) VALUES ('Kimura Sistemas', '51.855.786/0001-60', '1150557468','www.kimurasistemas.com','Bussiness Services');
insert into company( name, cnpj, telephone, website,industry) values ('Google', '88.946.074/0001-66', null,'www.google.com','Software'); 
insert into company( name, cnpj, telephone, website,industry) values ('Dell', '11.946.074/0001-66', '1199886622','www.dell.com','Hardware');

insert into employee(name_title,first_name,last_name,gender, email, cpf, employer,job_title) values('Mr','Luis Fernando','Kimura', 'male','lfkimura@kimurasistemas.com','222.255.568.-47',1,'Fuckin Developer');
insert into employee(name_title,first_name,last_name,gender, email, cpf, employer,job_title) values('Mis','Gisele ','de Souza', 'female','gisele@kimurasistemas.com','222.888.999.-47',1,'Sales Analyst');
insert into employee(name_title,first_name,last_name,gender, email, cpf, employer,job_title) values('Mis','Joana','Dark', 'female','joan@bol.com','222.111.333.-47',1,'Product Owner');

commit;
