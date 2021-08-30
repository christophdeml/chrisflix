document.addEventListener("DOMContentLoaded", () => {
    if(!document.location.href.includes("/admin")) {
        (function fetchMessage() {
            __fetchMessage__();
            setTimeout(fetchMessage, 1000);
        }) ();
    }

    (function resetexecutioncount() {
        fetch(document.location.origin + '/resetexecutioncount').then();
        setTimeout(resetexecutioncount, 1000);
    }) ();
});

function __fetchMessage__() {
    fetch(document.location.origin + '/hasmessage').then((response) => {
        response.text().then((text) => {
            if(text !== "") {
                let msg = JSON.parse(text);
                if(msg.command === "play" && msg.movieid !== null) {
                    console.info(text);
                    window.location.href = window.location.origin + '/movie?movieid=' + msg.movieid + "&play=true";
                }
            }
        });
    });
}