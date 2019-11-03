INSERT INTO produto (id, criado_em, nome, preco, tempo_preparo, ativo)
VALUES (1, NOW(), 'Pizza', '20.00', 30, TRUE);

INSERT INTO adicional_categoria (id, criado_em, produto_id, nome, ativo, minimo, maximo)
VALUES (1, NOW(), 1, 'Tamanho', TRUE, 1, 1);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (1, NOW(), 1, 'Pequena', '0.00', 0, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (2, NOW(), 1, 'Média', '5.00', 5, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (3, NOW(), 1, 'Grande', '0.00', 10, TRUE);


INSERT INTO adicional_categoria (id, criado_em, produto_id, nome, ativo, minimo, maximo)
VALUES (2, NOW(), 1, 'Sabor', TRUE, 1, 1);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (4, NOW(), 2, 'Calabresa', '0.00', 0, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (5, NOW(), 2, 'Portuguesa', '5.00', 5, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (6, NOW(), 2, 'Quatro Queijos', '0.00', 5, TRUE);

INSERT INTO adicional_categoria (id, criado_em, produto_id, nome, ativo, minimo, maximo)
VALUES (3, NOW(), 1, 'Adicional', TRUE, 0, 100);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (7, NOW(), 3, 'Bacon', '3.00', 2, TRUE);

INSERT INTO adicional (id, criado_em, categoria_id, nome, preco, tempo_preparo, ativo)
VALUES (8, NOW(), 3, 'Sem cebola', '0.00', 0, TRUE);