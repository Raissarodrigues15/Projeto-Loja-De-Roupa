DROP DATABASE IF EXISTS lojaderoupas;
CREATE SCHEMA IF NOT EXISTS lojaderoupas DEFAULT CHARACTER SET utf8 ;
USE lojaderoupas ;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(11) UNIQUE,
  `nome` VARCHAR(45),
  `email` VARCHAR(45) NOT NULL UNIQUE,
  `senha` VARCHAR(20) NOT NULL,
  `cargo` VARCHAR(20) NOT NULL,
  `saldo` DOUBLE,
  
  PRIMARY KEY (`idUsuario`));

-- -----------------------------------------------------
-- Table `mydb`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Endereco` (
  `idEndereco` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(5) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idEndereco`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Endereco_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES lojaderoupas.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `mydb`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Compra` (
  `idCompra` INT NOT NULL AUTO_INCREMENT,
  `valor` VARCHAR(45) NOT NULL,
  `data` DATE NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idCompra`),
  CONSTRAINT `fk_Compra_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES lojaderoupas.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `mydb`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `qtd_disponivel` INT NULL,
  `tecido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProduto`));

-- -----------------------------------------------------
-- Table `mydb`.`Compra_has_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Compra_has_Produto` (
  `Compra_idCompra` INT NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  PRIMARY KEY (`Compra_idCompra`, `Produto_idProduto`),
  CONSTRAINT `fk_Compra_has_Produto_Compra1`
    FOREIGN KEY (`Compra_idCompra`)
    REFERENCES lojaderoupas.`Compra` (`idCompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Produto_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES lojaderoupas.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `mydb`.`Carrinho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Carrinho` (
  `idCarrinho` INT NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idCarrinho`),
  CONSTRAINT `fk_Carrinho_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES lojaderoupas.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `mydb`.`Carrinho_has_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS lojaderoupas.`Carrinho_has_Produto` (
  `Carrinho_idCarrinho` INT NOT NULL,
  `Produto_idProduto` INT NOT NULL,
  PRIMARY KEY (`Carrinho_idCarrinho`, `Produto_idProduto`),
  CONSTRAINT `fk_Carrinho_has_Produto_Carrinho1`
    FOREIGN KEY (`Carrinho_idCarrinho`)
    REFERENCES lojaderoupas.`Carrinho` (`idCarrinho`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Carrinho_has_Produto_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES lojaderoupas.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
INSERT INTO Produto (nome, valor, qtd_disponivel, tecido) VALUES
("Blusa", 200, 50, "Moleton"),
("Calça", 300, 17, "Jeans"),
("Short", 150, 5, "pano"),
("Camiseta", 50, 4, "Algodão");
