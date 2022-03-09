let fightersContainer = document.getElementById("fighterContainer")
let headers = {'Content-Type': 'application/json'}
let cookieArray = document.cookie.split("=")
let userId = cookieArray[1]

const baseUrl = " http://localhost:8081/api/v1/fighters/users"


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


const getFavoriteFighter = async() => {
    const response = await fetch(`${baseUrl}/${userId}`, {
        method: "GET",
        headers: headers
    })
        .catch(err => console.log(err.message))

    let responseObject = await response.json();

    createFavoriteFighter(responseObject)
    console.log(responseObject)
    buttonActivation()
}

const createFavoriteFighter = (obj) => {
    fightersContainer.innerHTML = ""
        let fighterCard = document.createElement("div")
        fighterCard.classList.add("card", "d-flex", "flex-column", "justify-content-end", "m-1")
        fighterCard.style.width = "18rem"
        fighterCard.innerHTML = `
       
       
               <img src = "${obj.imgUrl}" className = "card-img-top" alt = "fighter-img" style = "height: 200px; object-fit: scale-down">
        <div class="card-body d-flex flex-column justify-content-between">
            <h5 class="card-title" style="cursor: pointer" >${obj.name} ${obj.origin}</h5>
            <p class="card-text">hp: ${obj.hp} def: ${obj.def}</p>
           <button type="button" class="btn btn-primary" id="fighterDetailsBtn" data-bs-toggle="modal" data-bs-target="#staticBackdrop" data-product="${obj.id}">
    Attacks
</button>

        </div>
       
    `
        fightersContainer.append(fighterCard)


}
getFavoriteFighter()

