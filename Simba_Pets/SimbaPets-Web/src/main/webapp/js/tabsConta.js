document.addEventListener('DOMContentLoaded', function() {
    const tabs = document.querySelectorAll('.tab-link');
    const contents = document.querySelectorAll('.tab-content-item');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            contents.forEach(content => content.classList.remove('active'));
            tab.classList.add('active');
            document.getElementById(tab.getAttribute('data-tab')).classList.add('active');
        });
    });

    function formatarMoeda(valor) {
        return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    }

    function abrirTelaEditar(event){
        const btn = event.currentTarget;
        const id = btn.getAttribute('data-id');
        const beneficiario = btn.getAttribute('data-beneficiario');
        const dataVencimento = btn.getAttribute('data-vencimento');
        const valor = btn.getAttribute('data-valor');
        const status = btn.getAttribute('data-status');

        const conteiner = document.getElementById('conteiner');
        conteiner.style.position = 'fixed';
        conteiner.style.top = '50%';
        conteiner.style.left = '50%';
        conteiner.style.transform = 'translate(-50%, -50%)';
        conteiner.style.width = '110vh';
        conteiner.style.height = '40vh';
        conteiner.style.backgroundColor = 'white';
        conteiner.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
        conteiner.style.padding = '20px';
        conteiner.style.borderRadius = '8px';
        conteiner.style.zIndex = '1000';
        conteiner.style.display = 'block';
        conteiner.style.overflowY = 'auto';
        conteiner.style.maxHeight = '80vh';
        conteiner.innerHTML=`
            <form method="post" action="contas" class="contas-formEdit">
              <div class="titulo">
                <p><strong>EDITAR CONTA</strong></p>
              </div>
              <div class="input-group">
                <div class="form-control">
                  <label for="beneficiario">Beneficiário</label>
                  <input type="text" id="beneficiario" name="beneficiario" value="${beneficiario}"/>
                </div>
                <div class="form-control">
                  <label for="data-vencimento">Data de Vencimento</label>
                  <input type="date" name="dataVencimento" id="data-vencimento" value="${dataVencimento}"/>
                </div>
                <div class="form-control">
                  <label for="valor">Valor</label>
                  <input type="number" name="valor" id="valorEdit" value="${valor}"/>
                </div>
                <div class="form-control">
                  <label for="status">Status</label>
                  <select id="status" name="status">
                    <option value="pendente" ${status === 'pendente' ? 'selected' : ''}>Pendente</option>
                    <option value="pago" ${status === 'pago' ? 'selected' : ''}>Pago</option>
                  </select>
                </div>

              </div>
              <div class="form-control">
                <input type="hidden" id="id" name="id" value="${id}"/>
              </div>
              <div class="form-buttons">
                <input type="submit" class="save-button" value="Registrar">
                <button type="button" class="cancel-button" id="cancelButton">Cancelar</button>
              </div>
            </form>
        `;

        document.querySelector(".contas-formEdit").addEventListener("submit", function(event){
            if (!verificarFormularioEditar()) {
                event.preventDefault();
            }
        });

        document.getElementById('valorEdit').addEventListener('input', function(e) {
            let input = e.target.value.replace(/\D/g, '');
            input = (input / 100).toFixed(2);
            e.target.value = formatarMoeda(input).replace('R$', '').trim();
        });

        document.getElementById('cancelButton').addEventListener('click', function() {
            conteiner.style.display = "none";
        });
    }

    function verificarFormularioEditar(){
        var formulario = document.querySelector(".contas-formEdit");
        var inputs = formulario.querySelectorAll("input, select, textarea");

        for (var i = 0; i < inputs.length; i++){
            if (inputs[i].value.trim() === "") {
                alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
                inputs[i].focus();
                return false;
            }
        }

        return true;
    }

    function verificarFormulario(){
        var formulario = document.querySelector(".contas-form");
        var inputs = document.querySelectorAll("input, select, textarea");

        for (var i = 0; i < inputs.length; i++){
            if (inputs[i].value.trim() === "") {
                alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
                inputs[i].focus();
                return false;
            }
        }

        return true;
    }


    document.querySelector(".contas-form").addEventListener("submit", function(event){
        if (!verificarFormulario()) {
            event.preventDefault();
        }
    });

    document.getElementById('valor').addEventListener('input', function(e) {
      let input = e.target.value.replace(/\D/g, '');
      input = (input / 100).toFixed(2);
      e.target.value = formatarMoeda(input).replace('R$', '').trim();
    });

    document.getElementById('cancelar').addEventListener('click', function(){
        window.location.href="/SimbaPets/";
    });

    const editarBtns = document.querySelectorAll("#editar");
    editarBtns.forEach(btn => {
        btn.addEventListener('click', abrirTelaEditar);
    });
});
