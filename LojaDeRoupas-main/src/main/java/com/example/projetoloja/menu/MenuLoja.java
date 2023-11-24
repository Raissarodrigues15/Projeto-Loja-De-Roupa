package com.example.projetoloja.menu;

import com.example.projetoloja.Controle.*;
import com.example.projetoloja.Exception.RegistroError;
import com.example.projetoloja.Exception.SaldoError;
import com.example.projetoloja.Exception.PasswordError;
import com.example.projetoloja.models.Compra;
import com.example.projetoloja.models.Produto;
import com.example.projetoloja.models.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public interface MenuLoja {

    static MenuAuxiliar menuCliente(Usuario u) {
        // Instanciando
        Scanner in = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CompraDAO compraDAO = new CompraDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        Carrinho_has_ProdutoDAO carrinhoHasProdutoDAO = new Carrinho_has_ProdutoDAO();
        Compra_has_ProdutoDAO compraHasProdutoDAO = new Compra_has_ProdutoDAO();
        MenuAuxiliar menuAuxiliar = new MenuAuxiliar(true, true);

        int opcao; // Controle do switch
        int idCarrinho = carrinhoDAO.selectCarrinhoID(u.getIdUsuario()); // Pega o ID do carrinho referente ao usuario
        String email = u.getEmail();
        String senha = u.getSenha();

        //Imprime a lista de opção
        System.out.println("Seu saldo é de: R$ " + String.format("%.2f", u.getSaldo()));
        System.out.println("------------------------------------------------");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Listar Produtos");
        System.out.println("2 - Adicionar Produto ao Carrinho");
        System.out.println("3 - Ver carrinho");
        System.out.println("4 - Remover Produto do Carrinho");
        System.out.println("5 - Finalizar Pedido");
        System.out.println("6 - Fazer um depósito");
        System.out.println("7 - Ver compras");
        System.out.println("8 - Fazer logout");
        System.out.println("9 - Encerrar");
        opcao = in.nextInt();
        switch (opcao) {
            case 1:
                produtoDAO.selectProduto(); // Listar todos os produtos
                break;
            case 2:
                System.out.print("Adicionar produto ao carrinho: ");
                int idproduto = in.nextInt();
                carrinhoHasProdutoDAO.insertCarrinho_has_Produto(idCarrinho, idproduto); // Adicionar um produto ao carrinho
                break;
            case 3:
                carrinhoHasProdutoDAO.exibirCarrinho(u.getIdUsuario()); // Mostrar todos os produtos no carrinho
                break;
            case 4:
                System.out.print("Informe o id do Produto: ");
                int idprodutoAux = in.nextInt();
                carrinhoHasProdutoDAO.deleteCarrinho_Has_Produto(idCarrinho, idprodutoAux); // Remover um produto do carrinho
                break;
            case 5:
                double valorCompra = carrinhoHasProdutoDAO.exibirCarrinho(u.getIdUsuario()); // Mostrar o carrinho
                System.out.println("Digite sua a senha para concluir a compra: ");
                in.nextLine();
                senha = in.nextLine();
                try {
                    if (usuarioDAO.selectUsuarioLogin(email, senha) == null) // Verifica se a senha está correta
                        throw new PasswordError();
                    else {
                        if (valorCompra <= u.getSaldo()) { // Verifica se tem saldo suficiente para a compra
                            Compra compra = new Compra(valorCompra, LocalDate.now().toString(), u.getIdUsuario());
                            compraDAO.insertCompra(compra); // Insere a compra no banco de dados
                            // Pega todos os produtos e passa para um arraylist
                            ArrayList<Integer> idProdutos = carrinhoHasProdutoDAO.selectProdutosCarrinho(idCarrinho);
                            int idCompra = compraDAO.selectUltimaCompraID(u.getIdUsuario()); // Pega o ID da compra
                            for (int produto : idProdutos) { // Percorre o arraylist
                                compraHasProdutoDAO.insertCompra_has_Produto(idCompra, produto); // Insere a compra e produto na tabela N-M
                                produtoDAO.diminuirEstoque(produto); // Diminui 1 unidade no estoque do produto
                            }
                            carrinhoHasProdutoDAO.removerItensCarrinho(idCarrinho); // Remove os itens do carrinho
                            usuarioDAO.removerSaldo(u.getIdUsuario(), valorCompra); // Deduz o valor da compra do saldo
                        } else {
                            new SaldoError();
                        }
                    }
                } catch (PasswordError e) {
                }
                break;
            case 6:
                System.out.print("Digite o valor do deposito: R$ ");
                double valor = in.nextDouble();
                System.out.println("Digite sua senha para confirmar o depósito: ");
                in.nextLine();
                senha = in.nextLine();
                try {
                    if (usuarioDAO.selectUsuarioLogin(email, senha) == null) // Verifica se a senha está correta
                        throw new PasswordError();
                    else
                        usuarioDAO.depositar(u.getIdUsuario(), valor); // Deposita o valor na conta
                } catch (PasswordError e) {
                }
                break;
            case 7:
                compraDAO.verCompras(u.getIdUsuario()); // Exibe todas as compras do usuário
                break;
            case 8:
                System.out.println("Saindo da conta...");
                menuAuxiliar.setSucesso(false); // Quebra o loop do menu e volta para o login
                break;
            case 9:
                // Quebra ambos os loops e encerra o programa
                menuAuxiliar.setFlag(false);
                menuAuxiliar.setSucesso(false);
                break;
        }
        return menuAuxiliar;
    }

    static MenuAuxiliar menuADM() {
        // Instanciando as classes
        Scanner in = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Carrinho_has_ProdutoDAO carrinhoHasProdutoDAO = new Carrinho_has_ProdutoDAO();
        MenuAuxiliar menuAuxiliar = new MenuAuxiliar(true, true);

        System.out.println("------------------------------------------------");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Adicionar um novo Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Atualizar um Produto");
        System.out.println("4 - Remover um Produto da Loja");
        System.out.println("5 - Listar Usuários");
        System.out.println("6 - Criar um novo Administrador");
        System.out.println("7 - Fazer logout");
        System.out.println("8 - Encerrar");

        int opcao; // Controle do switch
        opcao = in.nextInt();
        in.nextLine();

        switch (opcao) {

            case 1:
                System.out.println("Novo Produto: ");
                System.out.print("Nome: ");
                String nome = in.nextLine();
                System.out.print("Valor: ");
                double valor = in.nextDouble();
                System.out.print("Quantidade disponível: ");
                int qtd_disponivel = in.nextInt();
                in.nextLine();
                System.out.print("Tecido: ");
                String tecido = in.nextLine();

                Produto produto= new Produto(nome,valor,qtd_disponivel,tecido);
                produtoDAO.insertProduto(produto); // Adiciona um novo produto
                break;

            case 2:
                produtoDAO.selectProduto(); // Mostra os produtos cadastrados
                break;

            case 3:
                System.out.print("Informe o ID do produto que quer atualizar: ");
                int id_p=in.nextInt();
                if(produtoDAO.selectProdutoEspecifico(id_p) != null) { // Verifica se o produto está cadastrado
                    in.nextLine();
                    System.out.print("Novo nome: ");
                    String nome_p = in.nextLine();
                    System.out.print("Novo valor: ");
                    double valor_p = in.nextDouble();
                    System.out.print("Nova quantidade disponível: ");
                    int qtd_disponivel_p = in.nextInt();
                    in.nextLine();
                    System.out.print("Novo tecido: ");
                    String tecido_t = in.nextLine();

                    Produto produto1 = new Produto(nome_p, valor_p, qtd_disponivel_p, tecido_t);
                    produtoDAO.updateProduto(id_p, produto1); // Atualiza as informações do produto
                } else {
                    System.out.println("O produto " + id_p + " não existe");
                }
                break;

            case 4:
                System.out.print("Informe o id do Produto: ");
                int idprodutoAux = in.nextInt();
                carrinhoHasProdutoDAO.removerCarrinhoProdutoExcluido(idprodutoAux); // Remove do carrinho de todos que tem o produto
                produtoDAO.deleteProduto(idprodutoAux); // Exclui o produto do banco de dados
                System.out.println("Produto removido da Loja");
                break;
            case 5:
                usuarioDAO.selectUsuario(); // Mostra todos os usuários registrados
                break;
            case 6:
                try {
                    MenuLogin.registrarADM(); // Registra um novo usuário de administrador
                } catch (RegistroError e) {
                    throw new RuntimeException(e);
                }
                break;
            case 7:
                System.out.println("Saindo da conta...");
                menuAuxiliar.setSucesso(false); // Quebra o loop do menu e volta para o login
                break;
            case 8:
                // Quebra ambos os loops e encerra o programa
                menuAuxiliar.setFlag(false);
                menuAuxiliar.setSucesso(false);
                break;
        }

        return menuAuxiliar;

    }

}
