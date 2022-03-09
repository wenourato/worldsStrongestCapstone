let submitForm = document.getElementById("login-form")

const headers = {
    'Content-Type':'application/json'
}

const handleSubmit = async (e) =>{
    e.preventDefault()
    let bodyObj = {
        username: document.getElementById("loginUsername").value,
        password: document.getElementById("loginPassword").value
    }
    console.log(bodyObj)
    const response = await fetch("http://localhost:8081/api/v1/users/login", {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    const responseArr = await response.json()
    console.log(responseArr)
    if (response.status === 200 && responseArr[0].includes("http")){
        if(responseArr.length === 3){
            document.cookie = `userId=${responseArr[1]}`
            document.cookie = `cartId=${responseArr[2]}`
        } else if(responseArr.length === 4) {
            document.cookie = `userId=${responseArr[1]}`
            document.cookie = `cartId=${responseArr[2]}`
            document.cookie = `favoriteFighterId=${responseArr[3]}`
        }
        window.location.replace(responseArr[0])
    }
}

submitForm.addEventListener("submit", handleSubmit)