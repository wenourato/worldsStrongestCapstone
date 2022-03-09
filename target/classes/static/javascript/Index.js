let cookieArray = document.cookie.split(";")
let userId = cookieArray[0].split("=")
let cartId = cookieArray[1].split("=")
let headers = {'Content-Type': 'application/json'}
console.log(cartId)
console.log(userId)


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

const addFighterToCart = async(fighterId, cartId) => {
    await fetch(`http://localhost:8081/api/v1/users/cart/${cartId}/fighter/${fighterId}`, {
        method: "POST",
        headers:headers

    })
        .catch(err => console.log(err))

}
let buynowBtns = document.querySelectorAll('.buy-btn')
buynowBtns.forEach(btn => {
    btn.addEventListener("click",(e) => {
        let fighterId = e.target.getAttribute('data-fighter-id')

        addFighterToCart(fighterId, cartId[1])
    })
})
