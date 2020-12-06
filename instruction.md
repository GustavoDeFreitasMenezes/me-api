Este projeto foi criado com o STS 3.8.4

Foi utilizado o banco de dados H2, 
salvando num arquivo chamado meapi
localizado na raiz do projeto

As informacoes sobre credenciais do banco de dados estao contidas no arquivo "application.properties"
Neste arquivo deverão ser alterados usuario, senha e o local onde será gravado o arquivo que representa o banco de dados da aplicacao



Foi incorporado ao projeto o Swagger para documentar os resources da API estando disponivel em http://localhost:8080/swagger-ui.html

Os resources do projeto sao executados a partir do contexto: http://localhost:8080



O projeto foi configurado para criar o database e popular as tabelas toda vez que iniciar o projeto no STS (Spring Tool Suite)
Para iniciar o projeto abra o STS (Spring Tool Suite)
Na guia "Package Explorer" clique com o botão direito do mouse
Escolha a opção "Import"
Procure a opção "Existing Maven Projects" e clique em "Next"
No campo "Root Directory" escolha o local (no linux ou windows) onde foi salvo o projeto usando o botão "Browser". Ex: no windows: D:\curso\me-api
Na caixa de texto "Projects" aparecerá o arquivo pom.xml, selecione-o e clique em "Finish"
O STS irá fazer o download das dependências se não houver

Após a conclusao procure a classe java MeApiApplication
Ela está no seguinte local do projeto: src/main/java , no pacote com.me.api
Abra a classe clicando 2x no arquivo
Clique com o botao direito na classe
No menu suspenso escolha a opcao "Run As" e "Java Application"



Para testar o endpoint de status deverá seguir os passos:

Executar o endpoint do pedido: Um POST com o payload do desafio proposto
Executar o endpoint do status: os 6 requests do status proposto no desafio 
