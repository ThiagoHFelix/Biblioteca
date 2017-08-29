
/* Banco de dados do projeto Biblioteca */

/*================================================================================*/

create table livro(

codlivro int, /* Gerar o código automaticamente */
autor varchar(50) not null,
colecao varchar(50),
titulo varchar(50) not null,
localizacao varchar(50),
editora varchar(50),

constraint PK_livro primary key (codlivro)

);

/*================================================================================*/

create table genero(

idgenero int,/* Gerar o código automaticamente */
nomegenero varchar(50) not null,

constraint PK_Genero primary key(idgenero)

);


/*================================================================================*/



/* Tabela da relação de n:n entre livro e genêro */
create table livrogenero(

codlivro int,
idgenero int,

constraint FK_Livrolivrogenero foreign key (codlivro) references livro(codlivro),
constraint FK_Generolivrogenero foreign key (idgenero) references genero(idgenero),
constraint PK_Livrogenero primary key (codlivro, idgenero)

);

/*================================================================================*/


create table itemlivro(

coditemlivro int,
dataaquisicao date not null,
status boolean not null,/* True quando o item estiver disponivel para emprestimo, e false para quando não estiver disponivel */
codlivro int not null,


constraint FK_livroItemlivro foreign key (codlivro) references livro(codlivro),
constraint PK_Itemlivro primary key(coditemlivro, codlivro)
);



/*================================================================================*/

create table mapa(

codmapa int,
titulo varchar(50) not null,

constraint PK_Mapa primary key(codmapa)

);

/*================================================================================*/

create table itemmapa(

coditemmapa int,
status boolean not null,
dataaquisicao date not null,
codmapa int not null,

constraint FK_Mapaitemmapa foreign key(codmapa) references mapa(codmapa),
constraint PK_Itemmapa primary key(coditemmapa, codmapa)


);

/*================================================================================*/

create table jogo(

codjogo int,
fabricante varchar(50) not null,
colecao varchar(50),
titulo varchar(50) not null,

constraint PK_Jogo primary key(codjogo)

);

/*================================================================================*/

/* Tipo não é uma entidade fraca */

create table tipo(

idtipo int,
nometipo varchar(25) not null,

constraint PK_Tipo primary key(idtipo)

);

/*================================================================================*/
/* Tabela da relação de n:n entre jogo e tipo */
create table jogotipo(

codjogo int,
idtipo int,

constraint FK_Jogojogotipo foreign key (codjogo) references jogo(codjogo),
constraint FK_Tipojogotipo foreign key (idtipo) references tipo(idtipo),
constraint PK_Jogotipo primary key (codjogo, idtipo)

);
/*================================================================================*/


create table itemjogo(

coditemjogo int,
dataaquisicao date not null,
status boolean not null,
codjogo int,

constraint FK_Itemjogojogo foreign key (codjogo) references jogo(codjogo),
constraint PK_Itemjogo primary key (coditemjogo,codjogo)

);

/*================================================================================*/

create table globo(

codglobo int,
tituloglobo varchar(50) not null,

constraint PK_Globo primary key (codglobo)

);

/*================================================================================*/
create table itemglobo(

coditemglobo int,
status boolean not null,
dataquisicao date not null,
codglobo int,

constraint FK_Itemgloboglobo foreign key (codglobo) references globo (codglobo),
constraint PK_Itemglobo primary key (coditemglobo,codglobo)


);

/*================================================================================*/
create table dicionario(

coddicionario int,
titulodicionario varchar(50) not null,

constraint PK_Dicionario primary key (coddicionario)

);

/*================================================================================*/
create table itemdicionario(

coditemdicionario int,
status boolean not null,
dataaquisicao date not null,
coddicionario int,

constraint FK_Itemdicionariodicionario foreign key (coddicionario) references dicionario(coddicionario),
constraint PK_Itemdicionario primary key (coditemdicionario, coddicionario)


);

/*================================================================================*/

create table enciclopedia(

codenciclopedia int,
tituloenciclopedia varchar(50) not null,

constraint PK_Enciclopedia primary key (codenciclopedia)

);

/*================================================================================*/
create table itemenciclopedia(

coditemenciclopedia int,
status boolean not null,
dataaquisicao date not null,
codenciclopedia int,

constraint FK_Itemenciclopediaenciclopedia foreign key (codenciclopedia) references enciclopedia(codenciclopedia),
constraint PK_Itemenciclopedia primary key (codenciclopedia, coditemenciclopedia)

);


/*================================================================================*/

/* Entidade fraca ( //  Telefone dos funcionarios )*/


create table cliente(

codcliente int,
email varchar(50),
periodo char(1),
turma char(1),
nascimento date not null,
sexo char(1) not null,
nomealuno varchar(50) not null,

constraint PK_Cliente primary key (codcliente)

);


/*================================================================================*/

create table telefone(

codcliente int,
fone int, 

constraint FK_Telefonefuncionario foreign key (codcliente) references cliente(codcliente),
constraint PK_Telefone primary key (codcliente, fone)

);


/*================================================================================*/

create table administrador(

email varchar(50),
nascimento date not null,
sexo char(1) not null, 
nomeadministrador varchar(50) not null,
codadministrador int,

constraint PK_Administrador primary key (codadministrador),
constraint CHK_Sexo check (sexo='m' or sexo='f')

);
/*================================================================================*/

create table login(

codadm int,
idlogin varchar(25) not null,
senha varchar(50) not null,

constraint FK_LoginAdministrador foreign key (codadm) references administrador(codadministrador),
constraint PK_Login primary key (codadm)

);

/* =================================================================== 
   
    Tabelas relacionadas ao empretimo de objetos =D

*/


/* // XXX Termina o codigo sql dos emprestimos */

create table emprestimo(

codEmprestimo int,
codadm int not null,
dataPrevistaEntrega date not null,
horarioRealEntrega time not null,
dataRealEntrega date,
dataRetirada date not null,
horarioRetirada time not null,
status boolean not null,    /* Status setado como false significa que o emprestimo já foi finalizado e os item devolvidos, caso contrario ainda estão em emprestimo */
codcliente int,

constraint FK_EmprestimoCliente foreign key (codcliente) references cliente(codcliente),
constraint PK_Emprestimo primary key (codEmprestimo)


);


/*===============================================================================================================*/

/* Tabelas da relação dos items com os emprestimos */ 
/* Firebird esta relatando erro ao adicionar a chave estrangeira dos itens objetos FIXIT arrumar isto depois  */
/*===============================================================================================================*/

create table emprestimoContemItemLivro(

coditemlivro int,
codEmprestimo int,

constraint FK_EmprestaEmprestimo foreign key (codEmprestimo) references emprestimo(codEmprestimo),
constraint FK_EmprestaItemLivroFK foreign key (coditemlivro) references itemlivro(coditemlivro),
constraint PK_EmprestaItemLivro primary key (coditemlivro, codEmprestimo)

);

/*================================================================================*/

create table emprestimoContemItemMapa(

codItemMapa int,
codEmprestimo int,

constraint FK_EmprestaEmprestimoMapa foreign key (codEmprestimo) references emprestimo(codEmprestimo),
constraint FK_EmprestaItemMapaFK foreign key (codItemMapa) references itemmapa (coditemmapa),
constraint PK_EmprestaItemMapa primary key (codItemMapa, codEmprestimo)

);

/*================================================================================*/

create table emprestimoContemItemJogo(

codItemJogo int,
codEmprestimo int,

constraint FK_EmprestaEmprestimoJogo foreign key (codEmprestimo) references emprestimo (codEmprestimo),
constraint FK_EmprestaItemJogoFK foreign key (codItemJogo) references itemjogo (coditemjogo),
constraint PK_EmprestaItemJogo primary key (codItemJogo, codEmprestimo)


);
/*================================================================================*/

create table emprestimoContemItemGlobo(

codItemGlobo int,
codEmprestimo int,

constraint FK_EmprestaEmprestimoGlobo foreign key (codEmprestimo)  references emprestimo(codEmprestimo),
constraint FK_EmprestaItemGloboFK foreign key (codItemGlobo) references itemglobo (coditemglobo),
constraint PK_EmprestaItemGlobo primary key (codItemGlobo, codEmprestimo)

);

/*================================================================================*/

create table emprestimoContemItemDicionario(

codItemDicionario int,
codEmprestimo int,


constraint FK_EmprestaEmprestimoDici foreign key (codEmprestimo) references emprestimo(codEmprestimo),
constraint FK_EmprestaItemDiciFK foreign key (codItemDicionario) references itemdicionario(coditemdicionario),
constraint PK_EmprestaItemDici primary key (codItemDicionario, codEmprestimo)

);




create table emprestiomContemItemEnci(

codItemEnciclopedia int,
codEmprestimo int,

constraint FK_EmprestaEmprestimoEnci foreign key (codEmprestimo) references emprestimo(codEmprestimo),
constraint FK_EmprestimoItemEnciFK foreign key (codItemEnciclopedia) references itemenciclopedia(coditemenciclopedia),
constraint PK_EmprestaItemEnci primary key (codEmprestimo, codItemEnciclopedia) 


);

