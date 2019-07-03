# WexPrePag
Aplicação do desafio WEX

Desafio : Desenvolver uma aplicação utilizando para controle de "cartões de credito" com duas funcionalidades "Emissão de Cartão" e "Autorização de Venda".
	Pre - Requisitos : JAVA e Spring Boot
	
	Tempo de análise da solução :  1 hora. (BrainStorm de Arquitetura e estrategia para as funcionalidades)
	Tempo de desenvolvimento : 6 horas (Codificação, controle de versão e documentação)
	 
Arquitetura Escolhida : (Foi utilizado o conjunto do framwork Spring, por agilidade na integração e facilidade de iniciar projeto base, utilizando a ferramenta Spring Initializr)

	Spring Boot
	Spring REST
	Spring JPA
	HSQL DB (Devido a aplicação possuir apenas uma entidade persistente e por facilidade no deploy e testes da aplicação)
	POSTMAN Extension (Utilizado para testar a Aplicação)
	
A Estrutura da aplicação:
	
	1 Controller (Responsavel por Mapear a aplicação REST)
	1 Model(Cartão- Unica entidade persistente)
	3 Data transfer Objects (Responsaveis pelo transporte das informações serializaveis)
	1 Repositorio (Conexão com a base de dados)
	1 Classe de Negocio (Reponsavel por todas as regras de negocio da aplicação)
	1 SQL (Import inicial de cartões)
	
API REST: 

	URL : /cartoes
		GET : Lista todos os cartões existentes da base de dados. 
		POST : Funcionalidade de Emissão de Cartão (Requer JSON como parametro)
		PATCH : Funcionalidade de Autorização de Venda (Requer JSON como Parametro)
		
Execução:
	Há duas maneiras de executar a aplicação 
		Executando o arquivo WexPrePag.jar disponivel na raiz do projeto
		Utilizando uma IDE executando a classe PrePagApplication.java como "java Application" (Neste caso há execução do import.sql)
		
Casos de Teste: 

	Caso 1: Acessar Lista de cartões
		Utilizando a extensão PostMan selecionar o metodo GET e acessar a URL "localhost:8080/cartoes".
		
	Caso 2: Emissão de Cartão
		Utilizando a extensão PostMan selecionar o metodo POST e acessar a URL "localhost:8080/cartoes"
			passando um JSON como parametro no "body" (raw) da requisição
			ex ..     {
        					"nome": "Diego",
        					"saldo": 1000
    					}
	
			Obs : Lembrar de setar o meta type para JSON(application/json) na ferramenta PostMan.
			
	Caso 3: Autorização de Venda
		Utilizando a extensão PostMan selecionar o metodo PATCH e acessar a URL "localhost:8080/cartoes"
			passando um JSON como parametro no "body" (raw) da requisição
			ex ..			{
        						"cartao": {
        							"numero": 5109223241667258,
        							"validade": "07/21",
        							"senha": "8158",
        							"cvv": "311"
        							},
        						"estabelecimento": "Shop Salvador - Loja 01",
        						"valor": 10
    						}
	
			Obs : Lembrar de setar o meta type para JSON(application/json) na ferramenta PostMan.			
