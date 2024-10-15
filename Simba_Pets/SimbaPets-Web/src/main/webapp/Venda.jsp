<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="author" content="TaylorSz" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TabsVenda</title>
    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
      rel="stylesheet"
    />
    <!-- Stylesheet -->
    <link rel="stylesheet" href="css/tabsVenda.css" />
  </head>
  <body>
    <div class="container">
      <div class="tabs">
        <h3 class="tab-link active" data-tab="tab1">Registrar Venda</h3>
        <h3 class="tab-link" data-tab="tab2">Histórico de Vendas</h3>
      </div>
      <div class="tab-content">
        <div id="tab1" class="tab-content-item active">
          <div class="fundo">
            <form method="post" action="vendas" class="product-form">
              <div class="row">
                <div class="venda-form">
                  <div class="input-group">
                    <div class="form-control produto-container">
                      <label for="produto">Produto</label>
                      <div class="produto-input-buttons">
                        <select name="produtosVenda" id="produto-venda" class="seleção">
                          <c:forEach var="produto" items="${produtosVenda}">
                            <option value="${produto.getCodigo()}" data-preco="${produto.getPrecoFornecimento()}"
                             data-granel="${produto.isVendaGranel()}">
                                ${produto.nome}
                            </option>
                          </c:forEach>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="quantidade-valor">
                    <div class="form-control">
                      <label for="quantidade">Quantidade</label>
                      <input type="number" id="quantidade" name="quantidade" value="0" />
                    </div>
                    <div class="form-control">
                      <label for="valor-pago">Valor pago</label>
                      <input type="number" id="valor-pago" name="valor" placeholder="R$0,00" step="0.01"/>
                    </div>
                    <div class="form-control">
                      <label for="forma-pagamento">Forma de pagamento</label>
                      <select name="formaPagamento" id="forma-pagamento">
                        <option value="Pix">Pix</option>
                        <option value="Dinheiro">Dinheiro</option>
                        <option value="Debito">Débito</option>
                        <option value="Credito">Crédito</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-buttons-inline">
                    <button type="button" class="btn-produtos" onclick="adicionarProduto()">Adicionar</button>
                  </div>
                  </br>
                  <h3>Produtos adicionados</h3>
                  <table class="produtos" id="tabela-produto">
                    <thead>
                      <tr>
                        <th>Código</th>
                        <th>Produto</th>
                        <th>Quantidade</th>
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
                    <p>Valor Total</p>
                    <h2 class="valor-total">R$ 0,00</h2>
                  </div>
                  <div class="info-box">
                    <p>Valor Pago</p>
                    <h2  class="valor-pago-display">R$ 0,00</h2>
                  </div>
                  <div class="info-box">
                    <p>Troco</p>
                    <h2 class="troco">R$ 0,00</h2>
                  </div>
                  <div class="form-buttons">
                    <button type="submit" class="save-button">
                      <img src="img/carrinho-de-compras.png" /><strong>Vender</strong>
                    </button>
                    <button type="button" class="cancel-button" id="cancelar">
                      <img src="img/cancelar.png" /><strong >Cancelar</strong>
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div id="tab2" class="tab-content-item">
          <div class="fundo">
            <button class="voltar" id="voltar"><img src="img/voltar.png">Voltar</button>
            <table class="lista-produto">
              <thead>
                <tr>
                  <th>Data da Venda</th>
                  <th>Produto</th>
                  <th>Quantidade</th>
                  <th>Valor pago</th>
                  <th>Forma de pagamento</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="venda" items="${vendas}">
                    <tr>
                      <td>${venda.getDataVenda()}</td>
                      <td>${venda.getProduto()}</td>
                      <td>${venda.getQuantidade()}</td>
                      <td>${venda.getValorTotal()}</td>
                      <td>${venda.getFormaPagamento()}</td>
                      <td>
                        <!--<button class="btn-acao" id="editar" data-data="${venda.getDataVenda()}"><img src="img/editar.png"></button>-->
                        <button class="btn-acao" onclick="window.location.href='vendas?action=delete&id=${venda.getId()}'"><img src="img/excluir.png"></button>
                      </td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
          </div>
          <div id="conteiner"></div>
        </div>
      </div>
    </div>
    <script src="js/tabsVenda.js"></script>
  </body>
</html>
