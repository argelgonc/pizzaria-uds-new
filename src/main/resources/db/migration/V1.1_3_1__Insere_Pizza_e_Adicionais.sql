INSERT INTO produto (id, criado_em, nome, preco, tempo_preparo, ativo)
VALUES (1, NOW(), 'Pizza', '0.00', 0, TRUE);

INSERT INTO adicional_categoria (id, criado_em, produto_id, nome, ativo, minimo, maximo)
VALUES (1, NOW(), 1, 'Tamanho', TRUE, 1, 1);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (1, NOW(), 1, 'Pequena', '20.00', 15, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (2, NOW(), 1, 'Média', '30.00', 20, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (3, NOW(), 1, 'Grande', '40.00', 25, TRUE);


INSERT INTO adicional_categoria (id, criado_em, produto_id, nome, ativo, minimo, maximo)
VALUES (2, NOW(), 1, 'Sabor', TRUE, 1, 1);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (4, NOW(), 2, 'Calabresa', '0.00', 0, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (5, NOW(), 2, 'Portuguesa', '0.00', 5, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (6, NOW(), 2, 'Marguerita', '0.00', 0, TRUE);


INSERT INTO adicional_categoria (id, criado_em, produto_id, nome, ativo, minimo, maximo)
VALUES (3, NOW(), 1, 'Adicional', TRUE, 0, 100);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (7, NOW(), 3, 'Extra Bacon', '3.00', 0, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (8, NOW(), 3, 'Sem Cebola', '0.00', 0, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (9, NOW(), 3, 'Borda Recheada', '5.00', 5, TRUE);