const audio = document.getElementById('audio-player');
const playBtn = document.getElementById('play-btn');
const volumeControl = document.getElementById('volume-control');
const progressBar = document.getElementById('progress-bar');
const progress = document.getElementById('progress');

playBtn.addEventListener('click', () => {
    if (audio.paused || audio.ended) {
        audio.play();
        document.getElementById("play-pause").textContent = "pause_circle";
    } else {
        audio.pause();
        document.getElementById("play-pause").textContent = "play_circle";
    }
});

volumeControl.addEventListener('input', (e) => {
    audio.volume = e.target.value;
})

audio.addEventListener('timeupdate', () => {
    const progressPercentage = (audio.currentTime / audio.duration) * 100;
    progress.style.width = progressPercentage + "%";
});

progressBar.addEventListener('click', (e) => {
    const rect = progressBar.getBoundingClientRect();
    const offsetX = e.clientX - rect.left;
    const width = rect.width;
    const percentage = offsetX / width;

    audio.currentTime = percentage * audio.duration;
})
