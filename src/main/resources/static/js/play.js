let request = new XMLHttpRequest();
request.overrideMimeType("text/plain");
let video;
let parameterMovieId;
let parameterPlayedTime;
let url = new URL(window.location.href);
let seconds = url.searchParams.get("seconds");

document.addEventListener("DOMContentLoaded", () => {

   document.body.style.margin = 0;
   document.body.style.backgroundColor = "black";
   video = document.querySelector("video");
   video.style.width = window.innerWidth - 5 + "px";
   video.style.height = window.innerHeight - 5 + "px";
   parameterMovieId = "movieId=" + document.body.getAttribute("data-movie-id");

   if(seconds != null) {
      video.currentTime = seconds;
   }

});

