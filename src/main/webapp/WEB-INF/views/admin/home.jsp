<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>audio</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />
    <link rel="stylesheet" href="../../css/general/general.css">
    <link rel="stylesheet" href="../../css/general/sideBar.css">
    <link rel="stylesheet" href="../../css/filesystem/fetchedFiles.css">
</head>
<body>
    <jsp:include page="navBar.jsp" />
    <div id="home-container"></div>
    <audio id="audio-player" preload="auto"></audio>
    <div id="player-container">
        <div id="controls">
            <button id="play-btn">
                <span class="material-symbols-outlined" id="play-pause">pause_circle</span>
            </button>
            <input id="volume-control" type="range" min="0" max="1" step="0.05" value="1">
        </div>
        <div id="progress-bar">
            <div id="progress"></div>
        </div>
    </div>
    <script src="../../js/filesystemTraverse.js"></script>
    <script src="../../js/audioPlayer.js"></script>
</body>
</html>
