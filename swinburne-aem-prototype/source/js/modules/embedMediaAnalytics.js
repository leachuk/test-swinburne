const videos = {};

function getVideoDetails(id) {
  return videos[id];
}

function onYouTubePlayerStateChange(event) {
  const id = event.target.getIframe().getAttribute('id');
  // console.log('onYouTubePlayerStateChange', event.target.getIframe().getAttribute('id'));
  const {
    timer,
    timeSpent
  } = getVideoDetails(id);

  if (event.data === 1) { // Started playing
    if (!timeSpent.length) {
      for (var i = 0; i < 10; i++) videos[id].timeSpent.push(false);
    }
    videos[id].timer = setInterval(record.bind(this, id), 100);
  } else {
    if (event.data === 0) { // finished playing
      videos[id].lastProgress = 10;
      setAnalytics(id);
    }
    clearInterval(timer);
  }
}

function record(id) {
  const {
    player,
    timeSpent,
    lastProgress
  } = getVideoDetails(id);

  var progress = Math.floor(player.getCurrentTime() / player.getDuration() * 10);

  for (var i = 0, l = timeSpent.length; i < l; i++) {
    if (i < progress) {
      videos[id].timeSpent[Math.floor(i)] = true;
    }
  }

  if (progress !== lastProgress) {
    videos[id].lastProgress = progress;
    setAnalytics(id);
  }
}

function setAnalytics(id) {
  const {
    lastProgress,
    iframe
  } = getVideoDetails(id);
  console.log('iframe', iframe.contentDocument);
}

if (!youTubeScriptAttached) {
  const tag = document.createElement('script');
  tag.src = "//www.youtube.com/iframe_api";
  const firstScriptTag = document.getElementsByTagName('script')[0];
  firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
  var youTubeScriptAttached = true;
}

export default () => {
  const components = document.querySelectorAll('.onlinemedia');
  console.log('components', components);
  if (components.length) {
    window.onYouTubeIframeAPIReady = () => {
      components.forEach((component) => {
        const id = component.getAttribute('id')
        const iframe = component.querySelector('iframe');
        const source = iframe.getAttribute('src');
        console.log('attaching', onYouTubePlayerStateChange);
        const player = new YT.Player(iframe, {
          events: {
            'onStateChange': onYouTubePlayerStateChange
          }
        });
        let provider;

        if (source.includes('youtube.com')) {
          provider = 'youtube';
        } else if (source.includes('kaltura.com')) {
          provider = 'kaltura'
        }

        console.log('PLAYERRRRRRRRR', player);

        if (!videos[id]) {
          videos[id] = {
            player,
            iframe: iframe,
            timer: null,
            timeSpent: [],
            lastProgress: null,
            provider,
            // title: component.getAttribute('data-layerdescription')
          }
        }
      });
    }
  }
}
