function newTask() {

    let input = document.getElementById('input-new-task')

    //validation
    if(!input.value) {
        alert('Digite algo para inserir em sua lista')
    }
    //else if()
    else {
        
    }
}

function cadastrarTask(dados) {
    const url = 'http://localhost:8080/todo';

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
    };

    fetch(url, options).then((response) => {
        if(response.ok) {
            return response.json();
        }else {
            throw new Error('Erro ao inserir task')
        }
    })
}

document.getElementById("")