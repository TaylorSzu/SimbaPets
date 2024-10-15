<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8" />
    <meta name="author" content="TaylorSz" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Gestão de Contas</title>
    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
      rel="stylesheet"
    />
    <!-- Stylesheet -->
    <link rel="stylesheet" href="css/tabsConta.css" />
  </head>
  <body>
    <div class="container">
      <div class="tabs">
        <h3 class="tab-link active" data-tab="tab1">Gestão de Contas</h3>
      </div>
      <div class="tab-content">
        <div id="tab1" class="tab-content-item active">
          <div class="fundo">
            <form method="post" action="contas" class="contas-form">
              <h2>Registrar conta</h2>
              <div class="input-group">
                <div class="form-control">
                  <label for="beneficiario">Beneficiário</label>
                  <input type="text" id="beneficiario" name="beneficiario" placeholder="Beneficiário"/>
                </div>
                <div class="form-control">
                  <label for="data-vencimento">Data de Vencimento</label>
                  <input type="date" name="dataVencimento" id="data-vencimento" />
                </div>
                <div class="form-control">
                  <label for="valor">Valor</label>
                  <input type="number" name="valor" id="valor" placeholder="Valor" />
                </div>
                <div class="form-control">
                  <label for="status">Status</label>
                  <select id="status" name="status">
                    <option value="pendente" selected>Pendente</option>
                    <option value="pago">Pago</option>
                  </select>
                </div>
              </div>
              <div class="form-buttons">
                <input type="submit" class="save-button" value="Registrar">
                <button type="button" class="cancel-button" id="cancelar">Cancelar</button>
              </div>
            </form>
            <hr/>
            <br/>
            <div class="total-contas">
              <h2>Total de contas</h2>
              <p>Contas Pagas: ${contasPagas.size()}</p>
              <p>Contas Pendentes: ${contasPendentes.size()}</p>
              <p>Contas Vencidas: ${contasVencidas.size()}</p>
            </div>
            <hr/>
            <br/>
            <h2>Contas</h2>
            <table class="contas">
              <thead>
                <tr>
                  <th>Beneficiário</th>
                  <th>Data de Vencimento</th>
                  <th>Valor</th>
                  <th>Status</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="contas" items="${contas}">
                    <tr>
                        <td>${contas.getBeneficiario()}</td>
                        <td>${contas.getDataVencimento()}</td>
                        <td>${contas.getValor()}</td>
                        <td>${contas.getStatus()}</td>
                        <td>
                            <button class="btn-acao" id="editar" data-id="${contas.getId()}" data-beneficiario="${contas.getBeneficiario()}"
                             data-vencimento="${contas.getDataVencimento()}" data-valor="${contas.getValor()}" data-status="${contas.getStatus()}"
                             ><img src="img/editar.png"></button>
                            <button class="btn-acao" onclick="window.location.href='contas?action=delete&id=${contas.getId()}'"><img src="img/excluir.png"></button>
                        </td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div id="conteiner"></div>
    </div>
    <!-- Script -->
    <script src="js/tabsConta.js"></script>
  </body>
</html>
