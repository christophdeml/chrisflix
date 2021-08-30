let video;
let videoControls;
let btnClose;
let btnFullscreen;
let btnPlayPause;
let trackContainer;
let trackLine;
let trackThumb;
let timeContainer;
let timeElapsed;
let timeTotal;

let request = new XMLHttpRequest();
request.overrideMimeType("text/plain");

document.addEventListener("DOMContentLoaded", () => {

    video = document.querySelector("video");
    videoControls = document.querySelector(".video-container .video-controls");
    btnClose = document.querySelector(".video-container .video-controls .btn-close");
    btnFullscreen = document.querySelector(".video-container .video-controls .btn-fullscreen");
    btnPlayPause = document.querySelector(".video-container .video-controls .btn-play-pause");
    trackContainer = document.querySelector(".video-container .video-controls .track-container");
    trackLine = document.querySelector(".video-container .video-controls .track-container .track-line");
    trackThumb = document.querySelector(".video-container .video-controls .track-container .track-line .track-thumb");
    timeContainer = document.querySelector(".video-container .video-controls .time-container");
    timeElapsed = document.querySelector(".video-container .video-controls .time-container .time-elapsed");
    timeTotal = document.querySelector(".video-container .video-controls .time-container .time-total");

    video.src = video.getAttribute("data-src");
    video.addEventListener("play", () => btnPlayPause.src = "/images/pause.png");
    video.addEventListener("pause", () => btnPlayPause.src = "/images/play.png");
    video.addEventListener("loadeddata", () => timeTotal.textContent = getTimeStringFromSeconds(video.duration));

    setupVideoControls();

    if (document.location.href.includes("play=true")) {
        playMovie();
    }

    document.querySelectorAll(".play-button").forEach(playbutton => {
        playbutton.addEventListener("click", () => {
            let device;
            document.querySelectorAll('.play-button-container').forEach(playButtonContainer => {
                if (window.getComputedStyle(playButtonContainer, null).display !== 'none') {
                    device = playButtonContainer.querySelector(".select-device");
                }
            });
            if (document.cookie.includes(device.value)) {
                playMovie();
            } else {
                let movieId = document.location.search.substr(document.location.search.indexOf("=") + 1);
                fetch(document.location.origin + '/postmessage?useragentId=' + device.value + '&movieId=' + movieId).then();
            }
        });
    });

    (function updateLiveVideoElements() {
        trackThumb.style.left = (video.currentTime / video.duration) * (video.clientWidth - 128) + "px";
        timeElapsed.textContent = getTimeStringFromSeconds(video.currentTime);
        setTimeout(updateLiveVideoElements, 250);
    })();

});

function playMovie() {
    video.play().then(() => {
        video.classList.add('playing');
        videoControls.classList.add('playing');
        btnClose.classList.add('btn-playing');
        btnFullscreen.classList.add('btn-playing');
        btnPlayPause.classList.add('btn-playing');
        trackContainer.classList.add('track-container-playing');
        timeContainer.classList.add('show');
    }).then(() => video.muted = false);
}

function minimizeMovie() {
    video.pause();
    video.classList.remove('playing');
    videoControls.classList.remove('playing');
    btnClose.classList.remove('btn-playing');
    btnFullscreen.classList.remove('btn-playing');
    btnPlayPause.classList.remove('btn-playing');
    trackContainer.classList.remove('track-container-playing');
    timeContainer.classList.remove('show');
}

function toggleFullscreen() {
    if(document.fullscreen) {
        document.exitFullscreen().then();
    } else {
        document.documentElement.requestFullscreen().then();
    }
}

function toggleMovie() {
    if (video.paused) {
        video.play();
    } else {
        video.pause();
    }
}

function setupVideoControls() {
    let mousepoint = {x: undefined, y: undefined};

    function show() {
        let triggerMousepoint = {x: mousepoint.x, y: mousepoint.y};
        btnClose.style.opacity = "1";
        btnFullscreen.style.opacity = "1";
        btnPlayPause.style.opacity = "1";
        trackContainer.style.opacity = "1";
        timeContainer.style.opacity = "1";

        setTimeout(() => {
            if (!video.paused && triggerMousepoint.x === mousepoint.x && triggerMousepoint.y === mousepoint.y) {
                btnClose.style.opacity = "0";
                btnFullscreen.style.opacity = "0";
                btnPlayPause.style.opacity = "0";
                trackContainer.style.opacity = "0";
                timeContainer.style.opacity = "0";
            }
        }, 1500);
    }

    videoControls.addEventListener('mousemove', (event) => {
        show();
        mousepoint.x = event.clientX;
        mousepoint.y = event.clientY;
    });

    trackContainer.addEventListener('click', (event) => {
        video.currentTime = video.duration * (event.clientX - 64) / trackContainer.clientWidth;
    });
}

function getTimeStringFromSeconds(time) {
    time = Math.floor(time);
    let hours = Math.floor(time / 3600);
    let minutes = Math.floor((time - hours * 3600)/60);
    let seconds = time - hours * 3600 - minutes * 60;
    return fillWithZero(hours) + ":" + fillWithZero(minutes) + ":" + fillWithZero(seconds);
}

function fillWithZero(timeString) {
    return timeString < 10 ? "0" + String(timeString) : String(timeString);
}