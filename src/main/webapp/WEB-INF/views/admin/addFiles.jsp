<!doctype html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>audio</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="../../css/general/general.css">
    <link rel="stylesheet" href="../../css/general/sideBar.css">
    <link rel="stylesheet" href="../../css/admin/addFiles.css">
</head>
<body>
    <jsp:include page="navBar.jsp" />
    <div class="add-file-container">
        <form class="add-file-form" method="POST" action="/admin/addFiles" enctype="multipart/form-data">
            <div class="file-info-group">
                <label for="genre">Genre</label>
                <input type="text" id="genre" name="genre" placeholder="Genre" value="" required>
            </div>
            <div class="file-info-group">
                <label for="artist">Artist</label>
                <input type="text" id="artist" name="artist" placeholder="Artist" value="" required>
            </div>
            <div class="file-info-group">
                <label for="album">Album</label>
                <input type="text" id="album" name="album" placeholder="Album" value="" required>
            </div>
            <div class="file-info-group">
                <label for="song-name">Song name</label>
                <input type="text" id="song-name" name="song_name" placeholder="Song name" value="" required>
            </div>
            <input type="file" id="file-input" name="file_input" accept="audio/mpeg">
            <label for="file-input">Browse</label>
            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </div>
</body>
</html>
