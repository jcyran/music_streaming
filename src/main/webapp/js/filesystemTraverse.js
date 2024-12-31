const paramType = ['genre', 'artist', 'album', 'song_title'];
let params = ['', '', ''];
let paramString = new URLSearchParams();

async function fetchFiles(name, depth) {
    params[depth] = name;

    try {
        paramString.delete(paramType[depth]);

        for (let i = depth + 1; i < 4; i++) {
            const paramName = paramType[i];
            document.getElementById(paramName + "-list").remove();
            paramString.delete(paramName);
        }
    } catch (error) {}

    if (depth >= 0)
        paramString.append(paramType[depth], name);

    const response = await fetch("/filesystem?" + paramString);
    const data = await response.json();
    renderFilesystem(data, document.getElementById("home-container"), depth);
}

function renderFilesystem(node, container, depth) {
    depth++;

    const ul = document.createElement('ul');
    ul.id = paramType[depth] + '-list';

    node.children.forEach(child => {
        const li = document.createElement('li');
        li.textContent = child.name;

        if (child.directory) {

            li.addEventListener('click', (event) => {
                event.stopPropagation();
                fetchFiles(child.name, depth);
            });
        } else {
            const playButton = document.createElement('span')
            playButton.className = 'material-symbols-outlined';
            playButton.textContent = 'play_arrow';
            playButton.id = 'play-arrow';
            li.prepend(playButton);

            li.addEventListener('click', (event) => {
                event.stopPropagation();
                paramString.delete("song_title");
                fetchAudioFile(child.name, paramString);
            });
        }
        ul.append(li);
    });

    container.appendChild(ul);
}

async function fetchAudioFile(name, dataSet) {
    console.log(dataSet);
    if (dataSet.size > 3)
        return;

    try {
        dataSet.append(paramType[3], name + ".mp3");

        const response = await fetch("/filesystem/audio_file?" + dataSet);

        if (!response.ok) {
            throw new Error("Network response was not OK");
        }

        const audioBlob = await response.blob();
        const audioURL = URL.createObjectURL(audioBlob);
        const audioPlayer = document.getElementById('audio-player');
        audioPlayer.src = audioURL;
        audioPlayer.play();
    } catch (error) {
        console.error('Error fetching or playing audio:', error);
    }
}

document.getElementById('search-form').addEventListener('submit', async function findAudio(e) {
    e.preventDefault()
    const searchValue = document.getElementById('search-input').value;
    const resultsList = document.getElementById('results-list');

    resultsList.innerHTML = '';

    if (searchValue === '')
        return;
    try {
        const formData = new URLSearchParams();
        formData.append('searchValue', searchValue);

        const response = await fetch('/search', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData.toString(),
        });

        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const data = await response.json();

        showData(data, resultsList);
    } catch (error) {
        console.error('Error fetching data:', error);
        const listItem = document.createElement('li');
        listItem.textContent = 'An error occurred. Please try again later.';
        resultsList.appendChild(listItem);
    }
});

function showData(data, container) {
    if (data && data.length > 0) {
        data.forEach(item => {
            const listItem = document.createElement('li');
            listItem.textContent = item.song_title.substring(0, item.song_title.length - 4);
            listItem.dataset.genre = item.genre;
            listItem.dataset.artist = item.artist;
            listItem.dataset.album = item.album;

            listItem.addEventListener('click', (event) => {
                event.stopPropagation();
                let searchParams = new URLSearchParams(listItem.dataset);
                fetchAudioFile(listItem.textContent, searchParams);
            });

            container.appendChild(listItem);
        });
    } else {
        const listItem = document.createElement('li');
        listItem.textContent = 'No results found.';
        container.appendChild(listItem);
    }
}

fetchFiles('', -1);
