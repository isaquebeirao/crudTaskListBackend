const formulario = document.getElementById("form_task");
const Ititulo = document.getElementById("titulo");
const Idescricao = document.getElementById("descricao");
const IdataVencimento = document.getElementById("dataVencimento");
const Iprioridade = document.getElementById("prioridade");
const listaTarefas = document.getElementById("to-do-list");
function cadastrar() {

    fetch("http://localhost:8080/todo", 
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            titulo: Ititulo.value,
            descricao: Idescricao.value,
            dataVencimento: IdataVencimento.value,
            prioridade: Iprioridade.value
        })
    })
    .then(function (res) {console.log(res)})
    .catch(function (res) {console.log(res)})
};

function criarElementoTarefa(tarefa) {
    const listItem = document.createElement("li");

    // Crie um elemento de título clicável
    const titulo = document.createElement("div");
    titulo.textContent = `${tarefa.titulo}`;
    titulo.style.cursor = "pointer";
    titulo.addEventListener("click", () => mostrarDetalhes(tarefa, detalhes, botaoAtualizar));

    // Crie um elemento de detalhes inicialmente oculto
    const detalhes = document.createElement("div");
    detalhes.style.display = "none";

    // Adicione as informações detalhadas ao elemento de detalhes
    detalhes.innerHTML = `
        Descrição: ${tarefa.descricao}<br>
        Data de Vencimento: ${tarefa.dataVencimento}<br>
        Prioridade: ${tarefa.prioridade}
    `;

    // Crie um botão "Atualizar"
    const botaoAtualizar = document.createElement("button");
    botaoAtualizar.innerHTML = `
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-repeat" viewBox="0 0 16 16">
        <path d="M11 5.466V4H5a4 4 0 0 0-3.584 5.777.5.5 0 1 1-.896.446A5 5 0 0 1 5 3h6V1.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384l-2.36 1.966a.25.25 0 0 1-.41-.192Zm3.81.086a.5.5 0 0 1 .67.225A5 5 0 0 1 11 13H5v1.466a.25.25 0 0 1-.41.192l-2.36-1.966a.25.25 0 0 1 0-.384l2.36-1.966a.25.25 0 0 1 .41.192V12h6a4 4 0 0 0 3.585-5.777.5.5 0 0 1 .225-.67Z"/>
    </svg>`;
    botaoAtualizar.classList.add("update-button");
    botaoAtualizar.style.display = "none";  // Inicialmente, oculte o botão
    botaoAtualizar.addEventListener("click", () => {
        atualizarTarefa(tarefa.id);
    });

    // Crie um botão "OK"
    const botaoOK = document.createElement("button");
    botaoOK.innerHTML = `
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
        <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022"/>
    </svg>`;
    botaoOK.classList.add("ok-button");
    botaoOK.addEventListener("click", () => {
        excluirTarefa(tarefa.id);
    });

    // Adicione elementos à lista
    listItem.appendChild(titulo);
    listItem.appendChild(detalhes);
    listItem.appendChild(botaoAtualizar);
    listItem.appendChild(botaoOK);

    return listItem;
}

function mostrarDetalhes(tarefa, detalhes, botaoAtualizar) {
    // Alterne a visibilidade do elemento de detalhes ao clicar no título
    detalhes.style.display = detalhes.style.display === "none" ? "block" : "none";

    // Exiba o botão "Atualizar" somente quando os detalhes estiverem visíveis
    botaoAtualizar.style.display = detalhes.style.display === "none" ? "none" : "block";
}

function atualizarTarefa(id) {
    // Lógica para obter informações atualizadas da tarefa (pode ser uma chamada à API)
    const novoTitulo = prompt("Digite o novo título da tarefa:");
    const novaDescricao = prompt("Digite a nova descrição da tarefa:");
    
    // Crie um objeto com os dados a serem atualizados
    const dadosAtualizados = {
        id: id,
        titulo: novoTitulo,
        descricao: novaDescricao,
        // Adicione outros campos conforme necessário
    };

    // Faça a chamada à API para atualizar a tarefa
    fetch(`http://localhost:8080/todo`, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dadosAtualizados)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro ao atualizar a tarefa: ${response.statusText}`);
        }
        console.log(`Tarefa com ID ${id} atualizada com sucesso.`);
        // Atualize a lista de tarefas após atualizar
        listarTarefas();
    })
    .catch(error => console.error('Erro ao atualizar tarefa:', error));
}

function excluirTarefa(id) {
    fetch(`http://localhost:8080/todo/${id}`, {
        method: "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Erro ao excluir a tarefa: ${response.statusText}`);
        }
        console.log(`Tarefa com ID ${id} excluída com sucesso.`);
        // Atualize a lista de tarefas após excluir
        listarTarefas();
    })
    .catch(error => console.error('Erro ao excluir tarefa:', error));
}

function listarTarefas() {
    fetch("http://localhost:8080/todo")
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            // Limpa a lista antes de adicionar os novos itens
            listaTarefas.innerHTML = "";

            // Itera sobre os dados recebidos e os adiciona à lista
            data.forEach(tarefa => {
                const elementoTarefa = criarElementoTarefa(tarefa);
                listaTarefas.appendChild(elementoTarefa);
            });
        })
        .catch(error => console.error('Erro ao obter tarefas:', error));
}


function limpar() {
    Ititulo.value = "";
    Idescricao.value = "";
    IdataVencimento.value = "";
    Iprioridade.value = "";
}


formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    cadastrar();
    limpar();
    listarTarefas();
});

listarTarefas();
    