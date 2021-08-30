document.addEventListener('DOMContentLoaded', () => {

    document.querySelectorAll('[is="tile"]').forEach(t => {
        t.addEventListener('click', () => {
            window.location.href='/movie?movieid=' + t.getAttribute("data-movieid");
        });
    });

    document.querySelectorAll('img').forEach(image => {
        image.addEventListener('error', () => {
            console.log(image.src);
            image.src = "/images/alternative.png";
            image.parentElement.querySelector('h1').style.visibility='visible';
        });
    });
});