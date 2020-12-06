INSERT INTO item (descricao, preco_unitario) VALUES ('Item 1', 15);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 2', 20);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 3', 25);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 4', 35);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 5', 40);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 6', 50);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 7', 45);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item 8', 30);

INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201204203000', 3, 20.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201204224200', 3, 10.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201205090000', 4, 21.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201205113000', 2, 20.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201205134300', 0, 0.00);

INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (1, 1, 4);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (2, 2, 5);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (2, 3, 6);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (3, 2, 3);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (3, 1, 3);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (3, 3, 1);
