document.addEventListener('DOMContentLoaded', function() {
  const tabs = document.querySelectorAll('.tab-link');
  const contents = document.querySelectorAll('.tab-content-item');

  tabs.forEach(tab => {
    tab.addEventListener('click', function() {
      tabs.forEach(t => t.classList.remove('active'));
      contents.forEach(content => content.classList.remove('active'));

      this.classList.add('active');
      const contentId = this.getAttribute('data-tab');
      document.getElementById(contentId).classList.add('active');
    });
  });

  function formatarMoeda(valor) {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  function formatarPesoOuVolume(valor) {
      return valor.toLocaleString('pt-BR', { minimumFractionDigits: 0, maximumFractionDigits: 3 });
  }

  function abrirTelaView(event){
    const btn = event.currentTarget;
    const id = btn.getAttribute('data-id');
    const codigo = btn.getAttribute('data-codigo');
    const nome = btn.getAttribute('data-nome');
    const marca = btn.getAttribute('data-marca');
    const categoria = btn.getAttribute('data-categoria');
    const tipoPeso = btn.getAttribute('data-tipoPeso');
    const peso = btn.getAttribute('data-peso');
    const precoFornecimento = btn.getAttribute('data-pf');
    const precoRevenda = btn.getAttribute('data-pr');
    const vendaGranel = btn.getAttribute('data-isVendaGranel');
    const porcentagem = btn.getAttribute('data-porcentagem');
    const descricao = btn.getAttribute('data-descricao');

    const conteiner = document.getElementById('conteiner');
    conteiner.style.position = 'fixed';
    conteiner.style.top = '50%';
    conteiner.style.left = '50%';
    conteiner.style.transform = 'translate(-50%, -50%)';
    conteiner.style.width = '150vh';
    conteiner.style.height = 'auto';
    conteiner.style.backgroundColor = 'white';
    conteiner.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
    conteiner.style.padding = '20px';
    conteiner.style.borderRadius = '8px';
    conteiner.style.zIndex = '1000';
    conteiner.style.display = 'block';
    conteiner.style.overflowY = 'auto';
    conteiner.style.maxHeight = '80vh';
    conteiner.innerHTML= `
      <form class="product-form" action="post">
        <div class="titulo">
          <p><strong>DETALHES PRODUTO</strong></p>
        </div>
        <div class="row">
          <div class="form-control">
            <label for="codigo">Código de barras</label>
            <input type="text" name="codigo" id="codigo" value="${codigo}" disabled=""/>
          </div>
          <div class="form-control">
            <label for="nome">Nome</label>
            <input type="text" name="nome" id="nomeEdit" value="${nome}" disabled=""/>
          </div>
          <div class="form-control">
            <label for="marca">Marca</label>
            <input type="text" name="marca" id="marcaEdit" value="${marca}" disabled=""/>
          </div>
        </div>
        <div class="row">
          <div class="form-control">
            <label for="categoria">Categoria</label>
            <input type="text" name="categoria" id="categoria" value="${categoria}" disabled=""/>
          </div>
          <div class="form-control">
            <label for="tipo-peso">Tipo de peso</label>
            <select name="tipoPeso" id="tipoPesoEdit">
              <option value="seleção" ${!tipoPeso ? 'selected' : ''}>Selecione</option>
              <option value="KG" ${tipoPeso === 'KG' ? 'selected' : ''}>Kg</option>
              <option value="G" ${tipoPeso === 'G' ? 'selected' : ''}>g</option>
              <option value="L" ${tipoPeso === 'L' ? 'selected' : ''}>L</option>
              <option value="ML" ${tipoPeso === 'ML' ? 'selected' : ''}>mL</option>
            </select>
          </div>
          <div class="form-control">
            <label for="peso" id="lpeso">Peso</label>
            <input type="number" name="peso" id="pesoEdit" value="${peso}" disabled=""/>
          </div>
          <div class="form-control">
            <label for="preco-compra">Preço de fornecimento</label>
            <input type="number" name="precoFornecimento" id="precoCompraEdit" value="${precoFornecimento}" disabled=""/>
          </div>
          <div class="form-control">
            <label for="preco-venda">Preço de revenda</label>
            <input type="number" name="precoRevenda" id="precoVendaEdit" value="${precoRevenda}" disabled=""/>
          </div>
        </div>
        <div class="row">
          <div class="checkbox-group1">
            <label for="checkbox-group">Venda a granel</label>
            <input type="checkbox" name="vendaGranel" id="checkbox-group" ${vendaGranel === 'true' ? 'checked' : ''} />
          </div>
          <div class="form-control">
            <label for="porcentagem" id="lporcentagem">Porcentagem de lucro</label>
            <input type="number" name="porcentagem" id="porcentagem" value="${porcentagem}" disabled=""/>
          </div>
          <div class="form-control">
            <input type="hidden" id="id" name="id" value="${id}" disabled=""/>
          </div>
        </div>
        <div class="form-control">
          <label for="descricao">Descrição do produto</label>
          <textarea id="descricaoEdit" name="descricao" cols="200" rows="6" disabled="">${descricao}</textarea>
        </div>
        <div class="form-buttons">
          <button class="voltar" id="voltarView"><img src="img/voltar.png">Voltar</button>
        </div>
      </form>
    `;

     document.getElementById("voltarView").addEventListener('click', function(event){
       event.preventDefault();
       conteiner.style.display = 'none';
    });
  }

  function abrirTelaEditar(event) {
    const btn = event.currentTarget;
    const id = btn.getAttribute('data-id');
    const codigo = btn.getAttribute('data-codigo');
    const nome = btn.getAttribute('data-nome');
    const marca = btn.getAttribute('data-marca');
    const categoria = btn.getAttribute('data-categoria');
    const tipoPeso = btn.getAttribute('data-tipoPeso');
    const peso = btn.getAttribute('data-peso');
    const precoFornecimento = btn.getAttribute('data-pf');
    const precoRevenda = btn.getAttribute('data-pr');
    const vendaGranel = btn.getAttribute('data-isVendaGranel');
    const porcentagem = btn.getAttribute('data-porcentagem');
    const descricao = btn.getAttribute('data-descricao');

    const conteiner = document.getElementById('conteiner');
    conteiner.style.position = 'fixed';
    conteiner.style.top = '50%';
    conteiner.style.left = '50%';
    conteiner.style.transform = 'translate(-50%, -50%)';
    conteiner.style.width = '150vh';
    conteiner.style.height = 'auto';
    conteiner.style.backgroundColor = 'white';
    conteiner.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
    conteiner.style.padding = '20px';
    conteiner.style.borderRadius = '8px';
    conteiner.style.zIndex = '1000';
    conteiner.style.display = 'block';
    conteiner.style.overflowY = 'auto';
    conteiner.style.maxHeight = '80vh';

    conteiner.innerHTML = `
      <form method="post" class="product-formEdit" action="produtos">
        <div class="titulo">
          <p><strong>EDITAR PRODUTO</strong></p>
        </div>
        <div class="row">
          <div class="form-control">
            <label for="codigo">Código de barras</label>
            <input type="text" name="codigo" id="codigoEdit" value="${codigo}" />
          </div>
          <div class="form-control">
            <label for="nome">Nome</label>
            <input type="text" name="nome" id="nomeEdit" value="${nome}" />
          </div>
          <div class="form-control">
            <label for="marca">Marca</label>
            <input type="text" name="marca" id="marcaEdit" value="${marca}" />
          </div>
        </div>
        <div class="row">
          <div class="form-control">
            <label for="categoria">Categoria</label>
            <input type="text" name="categoria" id="categoriaEdit" value="${categoria}" />
          </div>
          <div class="form-control">
            <label for="tipo-peso">Tipo de peso</label>
            <select name="tipoPeso" id="tipoPesoEdit">
              <option value="seleção" ${!tipoPeso ? 'selected' : ''}>Selecione</option>
              <option value="KG" ${tipoPeso === 'KG' ? 'selected' : ''}>Kg</option>
              <option value="G" ${tipoPeso === 'G' ? 'selected' : ''}>g</option>
              <option value="L" ${tipoPeso === 'L' ? 'selected' : ''}>L</option>
              <option value="ML" ${tipoPeso === 'ML' ? 'selected' : ''}>mL</option>
            </select>
          </div>
          <div class="form-control">
            <label for="peso" id="lpeso">Peso</label>
            <input type="number" name="peso" id="pesoEdit" value="${peso}" step="0.001"/>
          </div>
          <div class="form-control">
            <label for="preco-compra">Preço de fornecimento</label>
            <input type="number" name="precoFornecimento" id="preco-compraEdit" value="${precoFornecimento}" step="0.01"/>
          </div>
          <div class="form-control">
            <label for="preco-venda">Preço de revenda</label>
            <input type="number" name="precoRevenda" id="preco-vendaEdit" value="${precoRevenda}" step="0.01"/>
          </div>
        </div>
        <div class="row">
          <div class="checkbox-group1   ">
            <label for="checkbox-group">Venda a granel</label>
            <input type="checkbox" name="vendaGranel" id="checkbox-group" ${vendaGranel === 'true' ? 'checked' : ''} />
          </div>
          <div class="form-control">
            <label for="porcentagem" id="lporcentagem">Porcentagem de lucro</label>
            <input type="number" name="porcentagem" id="porcentagemEdit" value="${porcentagem}" />
          </div>
          <div class="form-control">
            <input type="hidden" id="id" name="id" value="${id}"/>
          </div>
        </div>
        <div class="form-control">
          <label for="descricao">Descrição do produto</label>
          <textarea id="descricaoEdit" name="descricao" cols="200" rows="6">${descricao}</textarea>
        </div>
        <div class="form-buttons">
          <button type="submit" class="save-button">Atualizar</button>
          <button type="button" class="cancel-button" id="cancelButton">Cancelar</button>
        </div>
      </form>
    `;

    document.querySelector(".product-formEdit").addEventListener("submit", function(event){
      if (!verificarFormularioEditar()) {
        event.preventDefault();
      }
    });

    document.getElementById('pesoEdit').addEventListener('input', function(e) {
      let input = e.target.value.replace(/[^\d,]/g, '');
      input = input.replace(',', '.');
      let valor = parseFloat(input) || 0;
      e.target.value = formatarPeso(valor);
    });

    document.getElementById('preco-compraEdit').addEventListener('input', function(e) {
      let input = e.target.value.replace(/\D/g, '');
      input = (input / 100).toFixed(2);
      e.target.value = formatarMoeda(input).replace('R$', '').trim();
    });

    document.getElementById('preco-vendaEdit').addEventListener('input', function(e) {
      let input = e.target.value.replace(/\D/g, '');
      input = (input / 100).toFixed(2);
      e.target.value = formatarMoeda(input).replace('R$', '').trim();
    });

    document.getElementById('cancelButton').addEventListener('click', function() {
        conteiner.style.display = 'none';
    });
  }

  function verificarFormulario(){
    var formulario = document.querySelector(".product-form");
    var inputs = document.querySelectorAll("input, select, textarea");

    for (var i = 0; i < inputs.length; i++){
      if (inputs[i].value.trim() === "" || inputs[i].value.trim() == null) {
        alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
        inputs[i].focus();
        return false;
      }
    }
     return true;
  }

  function verificarFormularioEditar(){
    var formulario = document.querySelector(".product-formEdit");
    var inputs = formulario.querySelectorAll("input, select, textarea");

    for (var i = 0; i < inputs.length; i++){
      if (inputs[i].value.trim() === ""|| inputs[i].value.trim() == null) {
        alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
        inputs[i].focus();
        return false;
      }
    }
    return true;
  }

  document.querySelector(".product-form").addEventListener("submit", function(event){
    if (!verificarFormulario()) {
      event.preventDefault();
    }
  });

  document.getElementById('Peso').addEventListener('input', function(e) {
    let input = e.target.value.replace(/\D/g, '');
    input = (input / 100).toFixed(2);
    e.target.value = formatarMoeda(input).replace('R$', '').trim();
  });

  document.getElementById('preco-compra').addEventListener('input', function(e) {
    let input = e.target.value.replace(/\D/g, '');
    input = (input / 100).toFixed(2);
    e.target.value = formatarMoeda(input).replace('R$', '').trim();
  });

  document.getElementById('preco-venda').addEventListener('input', function(e) {
    let input = e.target.value.replace(/\D/g, '');
    input = (input / 100).toFixed(2);
    e.target.value = formatarMoeda(input).replace('R$', '').trim();
  });

  document.getElementById('cancelar').addEventListener('click', function(){
    window.location.href="/SimbaPets/";
  });

  document.getElementById("voltar").addEventListener('click', function(){
    window.location.href="/SimbaPets/";
  });

  const viewBtns = document.querySelectorAll("#view");
  viewBtns.forEach(btn => {
    btn.addEventListener('click', abrirTelaView);
  });

  const editarBtns = document.querySelectorAll("#editar");
  editarBtns.forEach(btn => {
    btn.addEventListener('click', abrirTelaEditar);
  });
});
