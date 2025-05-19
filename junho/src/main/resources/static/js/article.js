//deletion

const deleteButton = document.getElementById('delete-btn');

if (deleteButton){
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`,{
            method: 'DELETE'
        })
            .then(()=>{
                alert("삭제 완료");
                location.replace('/articles');
            });
    });
}

const modifyButton = document.getElementById('modify-btn');

if (modifyButton){
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert("updated!");
                location.replace(`/articles/${id}`);
            });
    });
}

const createButton = document.getElementById('create-btn')

if (createButton){
    createButton.addEventListener('click', event=>{
        fetch(`/api/articles`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert("created!");
                location.replace(`/articles`);
            });
    })
}