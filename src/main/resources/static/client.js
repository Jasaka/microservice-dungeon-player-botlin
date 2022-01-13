let refreshRate = 5000;
let bearerToken = null;
let games = null;
let robots = null;
let currentRound = null;
let currentMoneten = 0;

const gameURL = "http://localhost:8080/games/9e8fe5c1-1b31-4942-bc3b-059a9c5ecdde"

const updatableInterval = () => {
    if (refreshRate !== 5000){
        clearInterval(informationPullInterval);
        informationPullInterval = setInterval(updatableInterval, refreshRate)
    }
    //fetch(gameURL).then(response => console.log(response));
    //pullNewInformation();
}

let informationPullInterval = setInterval(updatableInterval, refreshRate);

const pullNewInformation = async () => {
    console.log("Here be REST Requests for games or robots and current game.")
}