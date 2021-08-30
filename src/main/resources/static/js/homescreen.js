const Cookies = {
    JSESSIONID: "JSESSIONID",
    USERAGENTNAME: "USERAGENTNAME"
}

document.addEventListener('DOMContentLoaded', () => {
    updateUserAgentCookie();

    let movies = document.querySelectorAll('.tile');
    let searchInput = document.querySelector('#search-input');
    let searchGenre = document.querySelector('#search-genre');
    searchInput.addEventListener('input', handleSearchAndGenreSwitch);
    searchGenre.addEventListener('input', handleSearchAndGenreSwitch);

    function handleSearchAndGenreSwitch() {
        let selectText = searchGenre.value;
        let inputText = searchInput.value.toLowerCase();
        movies.forEach(m => {
            if (isSearchedAfter(m, inputText) && isGenreSelected(m.getAttribute("data-movie-genre"), selectText)) {
                m.classList.remove('display-none');
            } else {
                m.classList.add('display-none');
            }
        });
    }

    function isSearchedAfter(movie, searchString) {
        let isMovieName = movie.getAttribute("data-movie-name").toLowerCase().includes(searchString) || searchString.includes(movie.getAttribute("data-movie-name").toLowerCase());
        let isBuzzword = movie.getAttribute("data-movie-buzzwords").split(';').find(buzz => buzz.toLowerCase().includes(searchString)) != null;
        let isActors = movie.getAttribute("data-movie-actors").split(';').find(actor => actor.toLowerCase().includes(searchString)) != null;
        return isMovieName || isBuzzword || isActors;
    }

    function isGenreSelected(movieGenreString, selectedGenre) {
        return selectedGenre === "alle" || movieGenreString.includes(selectedGenre);
    }
});

function updateUserAgentCookie() {
    if (!document.cookie.includes(Cookies.USERAGENTNAME)) {
        document.cookie = Cookies.USERAGENTNAME + "=" + getCookie(Cookies.JSESSIONID);
    }
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}