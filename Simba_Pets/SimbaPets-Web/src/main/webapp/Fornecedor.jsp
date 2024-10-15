<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="author" content="TaylorSz" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TabsFornecedor</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
    <!-- Stylesheet -->
    <link rel="stylesheet" href="css/tabsFornecedor.css" />
  </head>
  <body>
    <div class="container">
      <div class="tabs">
        <h3 class="tab-link active" data-tab="tab1">Cadastrar Fornecedor</h3>
        <h3 class="tab-link" data-tab="tab2">Lista de Fornecedores</h3>
      </div>
      <div class="tab-content">
        <div id="tab1" class="tab-content-item active">
          <div class="fundo">
            <form method="post" action="fornecedores" class="product-form">
              <div class="row">
                <div class="form-control">
                  <label for="nome">Nome do fornecedor</label>
                  <input type="text" name="nomeFornecedor" id="nome" placeholder="Digite o nome do fornecedor"/>
                </div>
                <div class="form-control">
                  <label for="cnpj">CNPJ</label>
                  <input type="text" name="cnpj" id="cnpj" placeholder="Digite o cnpj da empresa"/>
                </div>
                <div class="form-control">
                  <label for="rua">Rua</label>
                  <input type="text" name="rua" id="rua" placeholder="Digite a rua da empresa"/>
                </div>
                <div class="form-control">
                  <label for="bairro">Bairro</label>
                  <input type="text" name="bairro" id="bairro" placeholder="Digite o bairro da empresa"/>
                </div>
                <div class="form-control numero-control">
                  <label for="numero">Número</label>
                  <input type="number" name="numero" id="numero" placeholder="Informe o número"/>
                </div>
                <div class="form-control">
                  <label for="cidade">Cidade</label>
                  <input type="text" name="cidade" id="cidade" placeholder="Informe a cidade da empresa"/>
                </div>
                <div class="form-control">
                  <label for="estado">Estado</label>
                  <input type="text" name="estado" id="estado" placeholder="Informe o estado onde está localizada"/>
                </div>
                <div class="form-control">
                  <label for="nomeVendedor">Nome do vendedor</label>
                  <input type="text" name="nomeVendedor" id="nomeVendedor" placeholder="Digite o nome do Vendedor"/>
                </div>
                <div class="form-control">
                  <label for="telefone">Telefone</label>
                  <input type="text" name="telefone" id="telefone" placeholder="Informe o telefone da empresa"/>
                </div>
                <div class="form-control">
                  <label for="email">Email</label>
                  <input type="email" name="email" id="email" placeholder="Digite o email de contato"/>
                </div>
              </div>
              <div class="row">
                <div class="form-control">
                  <label for="produtos-fornecidos">Produtos fornecidos</label>
                  <div class="select-container">
                    <select name="produtos-fornecidos" id="produtos-fornecidos">
                      <c:forEach var="produtoFornecedor" items="${produtosFonecedor}">
                        <option value="${produtoFornecedor.codigo}" data-preco="${produtoFornecedor.precoRevenda}">
                          ${produtoFornecedor.nome}
                        </option>
                      </c:forEach>
                    </select>
                    <div class="btn-container">
                      <button type="button" class="btn-produtos" onclick="adicionarProduto()">Adicionar</button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="form-control">
                  <table id="tabela-produtos" class="produtos">
                    <thead>
                      <tr>
                        <th>Codigo</th>
                        <th>Produto</th>
                        <th>Preço</th>
                        <th>Ação</th>
                      </tr>
                    </thead>
                    <tbody>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="form-buttons">
                <button type="submit" class="save-button"><img src="img/registro.png">Cadastrar</button>
                <button type="reset" class="cancel-button" id="cancelar"><img src="img/cancelar.png">Cancelar</button>
              </div>
            </form>
          </div>
        </div>
        <!-- Tab 2 Content -->
        <div id="tab2" class="tab-content-item">
          <div class="fundo">
          <button class="voltar" id="voltar"><img src="img/voltar.png">Voltar</button>
            <table class="lista-produto">
              <thead>
                <tr>
                  <th>Nome da empresa</th>
                  <th>CNPJ</th>
                  <th>Telefone</th>
                  <th>Email</th>
                  <th>Produto Fornecidos</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="fornecedor" items="${fornecedores}">
                  <tr>
                    <td>${fornecedor.getNomeEmpresa()}</td>
                    <td>${fornecedor.getCnpj()}</td>
                    <td>${fornecedor.getTelefone()}</td>
                    <td>${fornecedor.getEmail()}</td>
                    <td>${fornecedor.getProdutosFornecidos()}</td>
                    <td>
                      <button class="btn-ação" id="view" data-nomeEmpresa="${fornecedor.getNomeEmpresa()}" data-cnpj="${fornecedor.getCnpj()}" data-rua="${fornecedor.getRua()}"
                        data-bairro="${fornecedor.getBairro()}" data-num="${fornecedor.getNumero()}" data-cidade="${fornecedor.getCidade()}" data-estado="${fornecedor.getEstado()}"
                        data-nomeVendedor="${fornecedor.getNomeVendedor()}" data-telefone="${fornecedor.getTelefone()}" data-email="${fornecedor.getEmail()}"
                        data-produtos="${fornecedor.getProdutosFornecidos()}" >
                        <img src="img/olho.png" />
                      </button>
                      <button class="btn-ação" id="editar" data-id="${fornecedor.getId()}" data-nomeEmpresa="${fornecedor.getNomeEmpresa()}" data-cnpj="${fornecedor.getCnpj()}" data-rua="${fornecedor.getRua()}"
                       data-bairro="${fornecedor.getBairro()}" data-num="${fornecedor.getNumero()}" data-cidade="${fornecedor.getCidade()}" data-estado="${fornecedor.getEstado()}"
                       data-nomeVendedor="${fornecedor.getNomeVendedor()}" data-telefone="${fornecedor.getTelefone()}" data-email="${fornecedor.getEmail()}" data-produtos="${pr}">
                        <img src="img/editar.png" />
                      </button>
                      <button class="btn-ação" onclick="window.location.href='fornecedores?action=delete&nomeEmpresa=${fornecedor.getNomeEmpresa()}'">
                        <img src="img/excluir.png"/>
                      </button>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        <div id="conteiner"></div>
      </div>
    </div>
    <!-- JavaScript -->
    <script src="js/tabsFornecedor.js"></script>
  </body>
</html>
