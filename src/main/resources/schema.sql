DROP TABLE IF EXISTS PEDIDO_ITEM;
DROP TABLE IF EXISTS PEDIDO;
DROP TABLE IF EXISTS ITEM;

CREATE TABLE PEDIDO(
	id INT AUTO_INCREMENT PRIMARY KEY,
	pedido VARCHAR(32) NOT NULL,
	itens_aprovados INT DEFAULT NULL,
	valor_aprovado DECIMAL(10,2) DEFAULT NULL
);

CREATE TABLE ITEM(
	id INT AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(32) NOT NULL,
	preco_unitario DECIMAL(10,2) NOT NULL	
);

CREATE TABLE PEDIDO_ITEM(
	id INT AUTO_INCREMENT PRIMARY KEY,
	pedido_id INT NOT NULL,
	item_id INT NOT NULL,
	quantidade INT NOT NULL,
	FOREIGN KEY (item_id) REFERENCES item(id),
	FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);
