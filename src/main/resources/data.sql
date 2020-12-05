	INSERT INTO item (descricao, preco_unitario) VALUES ('Item A', 10);
	INSERT INTO item (descricao, preco_unitario) VALUES ('Item B', 5);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item C', 15);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item D', 20);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item E', 25);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item F', 35);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item G', 40);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item H', 50);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item I', 45);
INSERT INTO item (descricao, preco_unitario) VALUES ('Item J', 30);

	INSERT INTO pedido (pedido) VALUES ('123456');
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201204203000', 3, 20.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201204224200', 3, 10.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201205090000', 4, 21.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201205113000', 2, 20.00);
INSERT INTO pedido (pedido, itens_aprovados, valor_aprovado) VALUES ('20201205134300', 0, 0.00);

	INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (1, 1, 1);
	INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (1, 2, 2);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (2, 3, 4);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (6, 4, 5);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (6, 5, 6);
INSERT INTO pedido_item (pedido_id, item_id, quantidade) VALUES (6, 6, 3);
