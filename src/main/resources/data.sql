INSERT INTO patients (
    city, complement, neighborhood, state, street, zip_code,
    address_number, birth_date, cpf, created_at, email, name,
    status, telephone, updated_at
) VALUES
      ('São Paulo', 'Ap 21', 'Centro', 'SP', 'Av. Paulista', '01311000', '1578', '1985-05-14', '222.222.222-22', '2025-01-10', 'joao.santos@gmail.com', 'João Santos', 'Ativo', '11 99888-1122', now()),
      ('Campinas', '', 'Jardim das Flores', 'SP', 'Rua das Acácias', '13050000', '42', '1992-03-09', '333.333.333-33', '2025-01-15', 'maria.alves@gmail.com', 'Maria Alves', 'Inativo', '19 98888-5566', now()),
      ('Ribeirão Preto', NULL, 'Campos Elíseos', 'SP', 'Av. Independência', '14020000', '305', '1990-12-22', '444.444.444-44', '2025-01-20', 'carlos.pereira@gmail.com', 'Carlos Pereira', 'Ativo', '16 98765-1234', now()),
      ('Bauru', '', 'Vila Universitária', 'SP', 'Rua da Liberdade', '17012190', '99', '1980-08-01', '555.555.555-55', '2025-01-25', 'ana.menezes@gmail.com', 'Ana Menezes', 'Ativo', '14 99999-9999', now()),
      ('Sorocaba', '', 'Jardim América', 'SP', 'Rua Rio Grande', '18050000', '100', '1995-09-21', '666.666.666-66', '2025-02-01', 'paulo.souza@gmail.com', 'Paulo Souza', 'Ativo', '15 98888-0000', now()),
      ('Santos', NULL, 'Gonzaga', 'SP', 'Av. Ana Costa', '11060001', '125', '1983-07-17', '777.777.777-77', '2025-02-05', 'juliana.freitas@gmail.com', 'Juliana Freitas', 'Inativo', '13 99777-8899', now()),
      ('Barretos', '', 'Centro', 'SP', 'Rua 20', '14780000', '70', '2001-11-12', '888.888.888-88', '2025-02-10', 'leandro.batista@gmail.com', 'Leandro Batista', 'Ativo', '17 97654-3210', now()),
      ('Franca', 'Casa', 'Vila Nova', 'SP', 'Rua das Palmeiras', '14400100', '12', '1977-04-03', '999.999.999-99', '2025-02-15', 'simone.lima@gmail.com', 'Simone Lima', 'Ativo', '16 96543-2100', now()),
      ('Araçatuba', NULL, 'Jardim Paulista', 'SP', 'Rua São João', '16015230', '8', '1999-10-28', '000.000.000-00', '2025-02-20', 'eduardo.machado@gmail.com', 'Eduardo Machado', 'Ativo', '18 95432-1000', now()),
      ('Marília', NULL, 'Palmital', 'SP', 'Rua das Oliveiras', '17500000', '45', '1993-06-11', '123.456.789-01', '2025-02-25', 'fernanda.silva@gmail.com', 'Fernanda Silva', 'Ativo', '14 91122-3344', now()),
      ('Jaú', '', 'Jardim Bela Vista', 'SP', 'Rua São Pedro', '17200000', '33', '1986-01-29', '234.567.890-12', '2025-03-01', 'rodrigo.souza@gmail.com', 'Rodrigo Souza', 'Inativo', '14 92233-4455', now()),
      ('Lins', 'Casa 1', 'Vila Faria', 'SP', 'Av. Tiradentes', '16400000', '120', '1991-05-05', '345.678.901-23', '2025-03-05', 'patricia.oliveira@gmail.com', 'Patrícia Oliveira', 'Ativo', '14 93344-5566', now()),
      ('Itu', NULL, 'Centro', 'SP', 'Rua dos Andradas', '13300000', '10', '1979-12-30', '456.789.012-34', '2025-03-10', 'julio.cesar@gmail.com', 'Julio Cesar', 'Ativo', '11 94455-6677', now()),
      ('Americana', '', 'Jardim São Paulo', 'SP', 'Rua Ipiranga', '13465000', '88', '1988-09-19', '567.890.123-45', '2025-03-15', 'luciana.moraes@gmail.com', 'Luciana Moraes', 'Inativo', '19 95566-7788', now()),
      ('Araraquara', NULL, 'Vila Xavier', 'SP', 'Av. Portugal', '14801000', '112', '1996-11-25', '678.901.234-56', '2025-03-20', 'ricardo.gomes@gmail.com', 'Ricardo Gomes', 'Ativo', '16 96677-8899', now()),
      ('Jundiaí', '', 'Anhangabaú', 'SP', 'Rua do Rosário', '13201000', '76', '2000-02-20', '789.012.345-67', '2025-03-25', 'vanessa.rocha@gmail.com', 'Vanessa Rocha', 'Ativo', '11 97788-9900', now()),
      ('Piracicaba', 'Casa', 'Vila Rezende', 'SP', 'Rua Bom Jesus', '13400000', '53', '1982-03-18', '890.123.456-78', '2025-04-01', 'eduarda.pinto@gmail.com', 'Eduarda Pinto', 'Ativo', '19 98899-0011', now()),
      ('Itapetininga', NULL, 'Jardim Europa', 'SP', 'Rua Bélgica', '18200000', '34', '1994-07-23', '901.234.567-89', '2025-04-05', 'anderson.lima@gmail.com', 'Anderson Lima', 'Ativo', '15 99900-1122', now()),
      ('Votuporanga', '', 'Centro', 'SP', 'Rua Amazonas', '15500000', '65', '1975-04-16', '012.345.678-90', '2025-04-10', 'daniela.costa@gmail.com', 'Daniela Costa', 'Inativo', '17 90011-2233', now()),
      ('Ourinhos', NULL, 'Vila Margarida', 'SP', 'Av. Domingos Camerlingo', '19900000', '82', '1989-06-01', '321.654.987-01', '2025-04-15', 'thiago.martins@gmail.com', 'Thiago Martins', 'Ativo', '14 91122-3344', now()),
      ('Assis', '', 'Jardim Paraná', 'SP', 'Rua Brasil', '19800000', '11', '1997-10-12', '432.765.098-12', '2025-04-20', 'aline.santos@gmail.com', 'Aline Santos', 'Ativo', '18 92233-4455', now()),
      ('Tupã', 'Ap 302', 'Centro', 'SP', 'Rua Japão', '17600000', '77', '1981-01-07', '543.876.109-23', '2025-04-25', 'fabio.ramos@gmail.com', 'Fábio Ramos', 'Ativo', '14 93344-5566', now());


INSERT INTO specialties (name, created_at) VALUES
                                               ('Neurologia', now()),
                                               ('Pediatria', now()),
                                               ('Ginecologia', now()),
                                               ('Oftalmologia', now()),
                                               ('Psiquiatria', now()),
                                               ('Endocrinologia', now()),
                                               ('Oncologia', now()),
                                               ('Radiologia', now()),
                                               ('Urologia', now()),
                                               ('Gastroenterologia', now()),
                                               ('Reumatologia', now()),
                                               ('Anestesiologia', now()),
                                               ('Nefrologia', now()),
                                               ('Infectologia', now()),
                                               ('Otorrinolaringologia', now()),
                                               ('Medicina de Família e Comunidade', now()),
                                               ('Cirurgia Geral', now());


INSERT INTO doctors (
    created_at, crm, email, name, telephone, updated_at, specialties_id
) VALUES
      ('2025-04-23 04:32:57.297853', '345.678-SP', 'doutorpedro.rodrigues@gmail.com', 'Pedro Rodrigues', '11 98234-1122', now(), 1),
      ('2025-04-23 04:33:57.297853', '456.789-SP', 'dr.claudiamartins@gmail.com', 'Cláudia Martins', '16 98345-2233', now(), 2),
      ('2025-04-23 04:34:57.297853', '567.890-SP', 'dr.joaosilva@gmail.com', 'João Silva', '15 98456-3344', now(), 3),
      ('2025-04-23 04:35:57.297853', '678.901-SP', 'drcarlostavares@gmail.com', 'Carlos Tavares', '19 98567-4455', now(), 4),
      ('2025-04-23 04:36:57.297853', '789.012-SP', 'dr.marceloalves@gmail.com', 'Marcelo Alves', '14 98678-5566', now(), 5),
      ('2025-04-23 04:37:57.297853', '890.123-SP', 'dr.julianasantos@gmail.com', 'Juliana Santos', '13 98789-6677', now(), 6),
      ('2025-04-23 04:38:57.297853', '901.234-SP', 'dr.ricardosilveira@gmail.com', 'Ricardo Silveira', '12 98890-7788', now(), 7),
      ('2025-04-23 04:39:57.297853', '012.345-SP', 'dr.marcelomorais@gmail.com', 'Marcelo Morais', '16 98901-8899', now(), 8),
      ('2025-04-23 04:40:57.297853', '123.456-SP', 'dr.anapaulacampos@gmail.com', 'Ana Paula Campos', '18 98012-9900', now(), 9),
      ('2025-04-23 04:41:57.297853', '234.568-SP', 'dr.renatocosta@gmail.com', 'Renato Costa', '17 98123-1010', now(), 10),
      ('2025-04-23 04:42:57.297853', '345.679-SP', 'dr.isabellalima@gmail.com', 'Isabella Lima', '15 98234-2121', now(), 11),
      ('2025-04-23 04:43:57.297853', '456.780-SP', 'dr.fernandoribeiro@gmail.com', 'Fernando Ribeiro', '16 98345-3232', now(), 12),
      ('2025-04-23 04:44:57.297853', '567.891-SP', 'dr.luizfernando@gmail.com', 'Luiz Fernando', '14 98456-4343', now(), 13),
      ('2025-04-23 04:45:57.297853', '678.902-SP', 'dr.martagomes@gmail.com', 'Marta Gomes', '19 98567-5454', now(), 14),
      ('2025-04-23 04:46:57.297853', '789.013-SP', 'dr.juliotavares@gmail.com', 'Julio Tavares', '13 98678-6565', now(), 15),
      ('2025-04-23 04:47:57.297853', '890.124-SP', 'dr.alinepinto@gmail.com', 'Aline Pinto', '15 98789-7676', now(), 16),
      ('2025-04-23 04:48:57.297853', '901.235-SP', 'dr.robertoalves@gmail.com', 'Roberto Alves', '14 98890-8787', now(), 17),
      ('2025-04-23 04:49:57.297853', '012.346-SP', 'dr.gustavomartins@gmail.com', 'Gustavo Martins', '12 98901-9898', now(), 1),
      ('2025-04-23 04:50:57.297853', '123.457-SP', 'dr.danielribeiro@gmail.com', 'Daniel Ribeiro', '17 98012-1000', now(), 2);


INSERT INTO consultations (
    created_at, date, observations, status, time, updated_at, doctor_id, patient_id
) VALUES
      ('2025-04-23 05:24:45.303242', '2025-06-23', 'Retorno pós-operatório', 'Pendente', '10:30:00', now(), 1, 8),
      ('2025-04-23 05:25:12.303242', '2025-06-24', 'Consulta de rotina', 'Confirmada', '14:00:00', now(), 2, 7),
      ('2025-04-23 05:25:44.303242', '2025-06-25', 'Dor abdominal persistente', 'Pendente', '09:00:00', now(), 3, 6),
      ('2025-04-23 05:26:15.303242', '2025-06-26', 'Exames pré-operatórios', 'Cancelada', '16:30:00', now(), 4, 4),
      ('2025-04-23 05:26:45.303242', '2025-06-27', 'Primeira consulta', 'Pendente', '08:15:00', now(), 5, 5),
      ('2025-04-23 05:27:10.303242', '2025-06-28', 'Retorno com exames', 'Confirmada', '11:45:00', now(), 6, 9),
      ('2025-04-23 05:27:40.303242', '2025-06-29', 'Consulta dermatológica', 'Pendente', '15:00:00', now(), 7, 10),
      ('2025-04-23 05:28:08.303242', '2025-06-30', 'Revisão anual', 'Confirmada', '13:30:00', now(), 8, 2),
      ('2025-04-23 05:28:38.303242', '2025-07-01', 'Acompanhamento hipertensão', 'Pendente', '10:00:00', now(), 9, 1),
      ('2025-04-23 05:29:02.303242', '2025-07-02', 'Consulta de avaliação geral', 'Confirmada', '09:30:00', now(), 10, 3);



