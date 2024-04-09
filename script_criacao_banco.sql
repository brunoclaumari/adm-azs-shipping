/*CRIA AS TABELAS NECESSÁRIAS NO BANCO*/
create table if not exists tb_cliente (tipo_carga smallint check (tipo_carga between 0 and 1), id bigserial not null, documento varchar(255) unique, email varchar(255) unique, nome_razao_social varchar(255), telefone varchar(255), primary key (id));
create table if not exists tb_cubagem (altura float(53), comprimento float(53), largura float(53), quantidade integer, id bigserial not null, descricao varchar(255), primary key (id));
create table if not exists tb_endereco (numero integer, tipo_endereco smallint check (tipo_endereco between 0 and 1), id bigserial not null, tipo_endereco_disc varchar(31) not null, cep varchar(255), documento varchar(255), endereco_completo varchar(255), nome_dono_endereco varchar(255), primary key (id));
create table if not exists tb_frete (cliente_id bigint not null, data_entrega TIMESTAMP WITHOUT TIME ZONE, destino_id bigint unique, id bigserial not null, origem_id bigint unique, tipo_carga varchar(31) not null, primary key (id));
create table if not exists tb_frete_cubagem (cubagem_id bigint not null, frete_id bigint not null, primary key (cubagem_id, frete_id));
create table if not exists tb_frete_peso (frete_id bigint not null, peso_id bigint not null, primary key (frete_id, peso_id));
create table if not exists tb_peso (peso float(53), quantidade integer, id bigserial not null, descricao varchar(255), primary key (id));

/*ADICIONA AS CONSTRAINTS NO BANCO COM OS NOMES*/
alter table if exists tb_frete add constraint fk_cliente_frete foreign key (cliente_id) references tb_cliente;
alter table if exists tb_frete add constraint fk_destino_frete foreign key (destino_id) references tb_endereco;
alter table if exists tb_frete add constraint fk_origem_frete foreign key (origem_id) references tb_endereco;
alter table if exists tb_frete_cubagem add constraint fk_cubagem foreign key (cubagem_id) references tb_cubagem;
alter table if exists tb_frete_cubagem add constraint fk_cubagem_frete foreign key (frete_id) references tb_frete;
alter table if exists tb_frete_peso add constraint fk_peso foreign key (peso_id) references tb_peso;
alter table if exists tb_frete_peso add constraint fk_peso_frete foreign key (frete_id) references tb_frete;

/*FAZ UM PEQUENO SEED HIPOTÉTICO DE CLIENTES - o FRETE SÓ PODE SER CADASTRADO SE O CLIENTE EXISTIR NO BANCO
Cada cliente tem um tipo de carga específico, como se fosse um contrato. 
É diferenciado na coluna 'TIPO_CARGA', que é referente a um Enum que está no backend.
TIPO_CARGA = 0 > TIPO CUBAGEM (leva em consideração "Largura", "Comprimento" e "Altura" em metros)
TIPO_PESO = 1  > TIPO PESO (leva em consideração o "Peso" em kg)
Ambos os tipos de carga possuem "Descrição" e "Quantidade".

*/

--Inserindo Clientes
INSERT INTO TB_CLIENTE (TIPO_CARGA, DOCUMENTO, EMAIL, NOME_RAZAO_SOCIAL,TELEFONE ) VALUES (0,'94.236.136/0001-49','transportes-caixas@gmail.com', 'Cliente 1 Transportes Caixas', '11 11111-1111');
INSERT INTO TB_CLIENTE (TIPO_CARGA, DOCUMENTO, EMAIL, NOME_RAZAO_SOCIAL,TELEFONE ) VALUES (0,'43.227.700/0001-26','cliente2paletes@gmail.com', 'Cliente 2 Entregas paletes', '11 22222-2222');
INSERT INTO TB_CLIENTE (TIPO_CARGA, DOCUMENTO, EMAIL, NOME_RAZAO_SOCIAL,TELEFONE ) VALUES (1,'91.945.283/0001-08','cliente3sacoscafe@gmail.com', 'Cliente 3 Sacos de café', '11 33333-3333');
INSERT INTO TB_CLIENTE (TIPO_CARGA, DOCUMENTO, EMAIL, NOME_RAZAO_SOCIAL,TELEFONE ) VALUES (1,'29.452.999/0001-91','cliente4materiais@gmail.com', 'Cliente 4 Areia de construção ', '11 44444-4444');
INSERT INTO TB_CLIENTE (TIPO_CARGA, DOCUMENTO, EMAIL, NOME_RAZAO_SOCIAL,TELEFONE ) VALUES (1,'69.446.812/0001-61','cliente5construcoes@gmail.com', 'Cliente 5 Sacos de Cimento', '11 55555-5555');
