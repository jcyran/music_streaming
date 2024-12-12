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

fetchFiles('', -1);
