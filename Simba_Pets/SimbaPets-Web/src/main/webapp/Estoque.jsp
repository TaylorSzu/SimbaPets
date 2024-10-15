<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="author" content="TaylorSz" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TabsEstoque</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet"/>
    <!-- Stylesheet -->
    <link rel="stylesheet" href="css/tabsEstoque.css" />
  </head>
  <body>
    <div class="container">
      <div class="tabs">
        <h3 class="tab-link active" data-tab="tab1">Atualizar Estoque</h3>
      </div>
      <div class="tab-content">
        <div id="tab1" class="tab-content-item active">
          <div class="fundo">
            <form method="post" class="product-form" action="estoque">
              <div class="row">
                <div class="venda-form">
                  <div class="quantidade-valor">
                    <div class="form-control">
                      <label for="produto">Produto</label>
                       <select name="produtos" id="produtos">
                         <c:forEach var="produto" items="${produtos}">
                          <option value="${produto.codigo}" data-preco="${produto.precoFornecimento}"  data-precoVenda="${produto.precoRevenda}" data-estoque="${produto.estoque}">
                           ${produto.nome}
                          </option>
                         </c:forEach>
                       </select>
                    </div>
                    <div class="form-control">
                      <label for="quantidade">Quantidade</label>
                      <input type="number" name="quantidade" id="quantidade" value="0" />
                    </div>
                  </div>
                  <div class="btn-container">
                    <button type="button" class="btn-produtos">Adicionar</button>
                  </div>
                  <h3>Produtos adicionados</h3>
                  <table id="tabela-produtos" class="produtos">
                    <thead>
                      <tr>
                        <th>Código</th>
                        <th>Produto</th>
                        <th>Estoque Atual</th>
                        <th>Preço</th>
                        <th>Ação</th>
                      </tr>
                    </thead>
                    <tbody>
                    </tbody>
                  </table>
                </div>
                <div class="venda-info">
                  <div class="info-box">
                    <p>Valor Investido</p>
                    <h2 id="valor-investido">R$ 0,00</h2>
                  </div>
                  <div class="info-box">
                    <p>Lucro de Venda do produto</p>
                    <h2 id="lucro-venda">R$ 0,00</h2>
                  </div>
                  <div class="info-box">
                    <p>Unidades Vendidas</p>
                    <h2>0</h2>
                  </div>
                  <div class="form-buttons">
                    <button class="save-button">
                      <img src="img/botao-atualizar.png" /><strong>Atualizar</strong>
                    </button>
                    <button type="button" class="cancel-button" id="cancelar">
                      <img src="img/cancelar.png" /><strong>Cancelar</strong>
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Script -->
    <script src="js/tabsEstoque.js"></script>
  </body>
</html>
