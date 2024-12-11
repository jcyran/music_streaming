const paramType = ['genre', 'artist', 'album', 'song_title'];
let params = ['', '', ''];
let depth = -1;
let paramString = new URLSearchParams();

async function fetchFiles(name) {
    params[depth] = name;

    if (depth >= 0)
        paramString.append(paramType[depth], name);

    if (depth < 3)
        depth++;

    const response = await fetch("/filesystem?" + paramString);
    const data = await response.json();
    renderFilesystem(data, document.getElementById("home-container"));
}

function renderFilesystem(node, container) {
    if (node.children.length === 0)
        //TODO
        //popros o plik dzwiekowy
        return;

    const ul = document.createElement('ul');
    ul.className = paramType[depth] + '-list';

    node.children.forEach(child => {
        const li = document.createElement('li');
        li.textContent = child.name;
        if (child.directory) {
            li.addEventListener('click', (event) => {
                event.stopPropagation();
                fetchFiles(child.name);
            });
        } else {
            li.addEventListener('click', (event) => {
                event.stopPropagation();
                fetchAudioFile(child.name);
            });
        }
        ul.append(li);
    });

    container.appendChild(ul);
}

async function fetchAudioFile(name) {
    if (paramString.size > 3)
        return;

    try {
        paramString.append(paramType[3], name);

        const response = await fetch("/filesystem/audio_file?" + paramString);

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

fetchFiles('');
