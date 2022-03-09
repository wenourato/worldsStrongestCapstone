let cookieArray = document.cookie.split(";")
let userId = cookieArray[0].split("=")
let cartId = cookieArray[1].split("=")
let favoriteFighterId;
if(cookieArray.length === 3){
    let temp = cookieArray[2].split("=")
    favoriteFighterId = temp[1]
}
console.log(favoriteFighterId)
console.log(cookieArray)
let fighterModalImg = document.getElementById("fighterModalImage")
let fighterName = document.getElementById("fighterName")
let fighterOrigin = document.getElementById("fighterOrigin")
let fighterStats = document.getElementById("fighterStats")
let fighterAttackOne = document.getElementById("fighterAttackOne")
let fighterAttackTwo = document.getElementById("fighterAttackTwo")
let fightersContainer = document.getElementById("fighterContainer")
let headers = {'Content-Type': 'application/json'}
let modalBuynowBtn = document.getElementById("modal-buynow-btn")
const baseUrl = " http://localhost:8081/api/v1/fighters"


let handleLogout = () => {
    eraseCookieFromAllPaths("userId")
    eraseCookieFromAllPaths("cartId")
    eraseCookieFromAllPaths("favoriteFighterId")
    window.location.replace("http://localhost:8081/Login2.html")


}
function eraseCookieFromAllPaths(name) {
    // This function will attempt to remove a cookie from all paths.
    var pathBits = location.pathname.split('/');
    var pathCurrent = ' path=';

    // do a simple pathless delete first.
    document.cookie = name + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT;';

    for (var i = 0; i < pathBits.length; i++) {
        pathCurrent += ((pathCurrent.substr(-1) != '/') ? '/' : '') + pathBits[i];
        document.cookie = name + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT;' + pathCurrent + ';';
    }
}

let logoutButton = document.getElementById("logout-btn")
logoutButton.addEventListener("click", handleLogout)

const getAllFighters = async() => {
    const response = await fetch(`${baseUrl}`, {
        method: "GET",
        headers: headers
    })
        .catch(err => console.log(err.message))

    let responseArray = await response.json();

    createFighterCards(responseArray)
    console.log(responseArray)
    buttonActivation()
}

const addFighterToCart = async(fighterId, cartId) => {
    await fetch(`http://localhost:8081/api/v1/users/cart/${cartId}/fighter/${fighterId}`, {
        method: "POST",
        headers:headers

    })
        .catch(err => console.log(err))

}

const createFighterCards = (array) => {
    fightersContainer.innerHTML = ""
    array.forEach(obj => {
        let fighterCard = document.createElement("div")
        fighterCard.classList.add("card", "d-flex", "flex-column", "justify-content-end", "m-1")
        fighterCard.style.width = "18rem"
        if(favoriteFighterId == obj.id){

            fighterCard.innerHTML = `

                <button class="btn" style="color: #D8D8D8; background-color: rebeccapurple; font-size: x-large"><i class="bi bi-heart-fill"></i></button>
                <img src="${obj.imgUrl}" className="card-img-top" alt="fighter-img"
                     style="height: 200px; object-fit: scale-down">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <h5 class="card-title" style="cursor: pointer">${obj.name} ${obj.origin}</h5>
                        <p class="card-text">hp: ${obj.hp} def: ${obj.def}</p>
                        <button type="button" class="btn btn-purple" id="fighterDetailsBtn" data-bs-toggle="modal"
                                data-bs-target="#staticBackdrop" data-product="${obj.id}">
                            Attacks
                        </button>

                    </div>
       `

        } else {

            fighterCard.innerHTML = `

                <button class="btn" style="background-color: rebeccapurple; color: #D8D8D8; font-size: x-large" onclick="makeFavoriteFighter(${obj.id})"><i class="bi bi-heart"></i></button>
                <img src="${obj.imgUrl}" className="card-img-top" alt="fighter-img"
                     style="height: 200px; object-fit: scale-down">
                    <div class="card-body d-flex flex-column justify-content-between">
                        <h5 class="card-title" style="cursor: pointer">${obj.name} ${obj.origin}</h5>
                        <p class="card-text">hp: ${obj.hp} def: ${obj.def}</p>
                        <button type="button" class="btn btn-purple" id="fighterDetailsBtn" data-bs-toggle="modal"
                                data-bs-target="#staticBackdrop" data-product="${obj.id}">
                            Attacks
                        </button>

                    </div>
       `

        }
        fightersContainer.append(fighterCard)

    })

}
const getFighterById = async (id)=> {
    const response = await fetch(`${baseUrl}/${id}`, {
        method: "GET", headers: headers
    })
        .catch(err => console.log(err))
    let fighter = await response.json()
    return fighter

}

const populateModal = async (id) => {
    let obj = await getFighterById(id)
    fighterModalImg.src = obj.imgUrl
    fighterName.innerText = obj.name
    fighterOrigin.innerText = obj.origin
    fighterStats.innerText = `hp: ${obj.hp} def: ${obj.def}`
    fighterAttackOne.innerText = `name: ${obj.attackList[0].atkName} value: ${obj.attackList[0].atkValue}`
    fighterAttackTwo.innerText = `name: ${obj.attackList[1].atkName} value: ${obj.attackList[1].atkValue}`
    modalBuynowBtn.onclick = addFighterToCart(obj.id, cartId[1])
}

const clearModal = () => {
    fighterModalImg.src = ""
    fighterName.innerText = ""
    fighterOrigin.innerText = ""
    fighterStats.innerText = ""
    fighterAttackOne.innerText = ""
    fighterAttackTwo.innerText = ""
    modalBuynowBtn.onclick = ""
}

const makeFavoriteFighter = async(id) => {
    await fetch(`http://localhost:8081/api/v1/users/${userId[1]}/fighter/${id}`, {
        method: "POST",
        header:headers
    }) .catch(err => console.log(err))
    document.cookie = `favoriteFighterId=${id}`
    window.location.reload()
}



getAllFighters()
const buttonActivation = () => {

    setTimeout(() => {
        let fighterButtons = document.querySelectorAll("#fighterDetailsBtn")
        fighterButtons.forEach(btn => {
            btn.addEventListener("click", (e) => {
                let fighterId = e.target.getAttribute("data-product")
                populateModal(fighterId)
            })
        })


    },500)


}


