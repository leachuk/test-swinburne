const videos = {};
let youTubeScriptAttached = false;
let kalturaScriptAttached = false;

const getVideoDetails = (id) => {
  return videos[id];
}

const onYouTubePlayerStateChange = (event, id) => {
  const {
    timer,
    timeSpent
  } = getVideoDetails(id);

  if (event.data === 1) { // Started playing
    if (!timeSpent.length) {
      for (let i = 0; i < 10; i++) {
        videos[id].timeSpent.push(false);
      }
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

const record = (id) => {
  console.log('record');
  const {
    player,
    timeSpent,
    lastProgress
  } = getVideoDetails(id);
  var progress = Math.floor(player.getCurrentTime() / player.getDuration() * 10);
  timeSpent.forEach((value, index) => {
    if (index < progress) {
      videos[id].timeSpent[Math.floor(index)] = true;
    }
  });

  if (progress !== lastProgress) {
    videos[id].lastProgress = progress;
    setAnalytics(id);
  }
}

const setAnalytics = (id) => {
  const {
    lastProgress,
    iframe
  } = getVideoDetails(id);
  console.log(getVideoDetails(id));
}

const loadScripts = (provider) => {
  return new Promise((resolve) => {
    let source;
    if (provider === 'youtube') {
      if (youTubeScriptAttached) { resolve(); }
      source = 'https://www.youtube.com/iframe_api';
    } else if (provider === 'kaltura') {
      if (kalturaScriptAttached) { resolve(); }
      source = 'https://cdnapisec.kaltura.com/p/1971581/sp/197158100/embedIframeJs/uiconf_id/35724971/partner_id/1971581';
    }

    const tag = document.createElement('script');
    tag.src = source;
    const firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    tag.addEventListener('load', () => {
      if (provider === 'youtube') { youTubeScriptAttached = true; }
      if (provider === 'kaltura') { kalturaScriptAttached = true; }
      resolve();
    });
  })
};

const initializeComponents = (components) => {
  components.forEach((component) => {
    const id = component.getAttribute('id');
    const videoDiv = component.querySelector('.js-video-embed');
    const source = videoDiv.getAttribute('src');
    const entryId = component.getAttribute('data-entry-id');
    const provider = component.getAttribute('data-provider');
    const title = component.getAttribute('data-linkdescription');
    const playerId = component.getAttribute('data-player-id');
    let player;

    loadScripts(provider).then(() => {
      if (provider === 'youtube') {
        player = new YT.Player(videoDiv, {
          videoId: entryId,
          events: {
            'onStateChange': (event) => {
              onYouTubePlayerStateChange(event, id);
            }
          }
        });
      }
      else if (provider === 'kaltura') {
        kWidget.addReadyCallback( function( playerId ){
          console.log('loaded!!!!!!!!!!!!!!!', playerId);
        });
        kWidget.embed({
          "targetId": id, // unique object id
          "wid": "_691292", // partner id
          "uiconf_id": 20499062, // player id
          entry_id: "1_lph7zzb1" // media
        });
      }

      if (!videos[id]) {
        videos[id] = {
          player,
          videoDiv,
          timer: null,
          timeSpent: [],
          lastProgress: null,
          provider,
        }
      }
    });
  });
};
// if (!youTubeScriptAttached) {
//   const tag = document.createElement('script');
//   tag.src = "//www.youtube.com/iframe_api";
//   const firstScriptTag = document.getElementsByTagName('script')[0];
//   firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
//   youTubeScriptAttached = true;
// }
//
// if (!kalturaScriptAttached) {
//   const tag = document.createElement('script');
//   tag.src = "https://cdnapisec.kaltura.com/p/1971581/sp/197158100/embedIframeJs/uiconf_id/35724971/partner_id/1971581";
//   const firstScriptTag = document.getElementsByTagName('script')[0];
//   firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
//   var kalturaScriptAttached = true;
//   tag.addEventListener('load', () => {
//     console.log('kWidget loaded', kWidget);
//     console.log('kWidget', kWidget);
//     kWidget.addReadyCallback( function( playerId ){
//       console.log('loaded!!!!!!!!!!!!!!!', playerId);
//     });
//     kWidget.embed({
//       "targetId": "kaltura-player", // unique object id
//       "wid": "_691292", // partner id
//       "uiconf_id": 20499062, // player id
//       entry_id: "1_lph7zzb1" // media
//     });
//   });
// }

export default () => {
  const youTubeVideos =  document.querySelectorAll('.onlinemedia[data-provider="youtube"]');
  const kalturaVideos =  document.querySelectorAll('.onlinemedia[data-provider="kaltura"]');
  if (youTubeVideos.length) {
    loadScripts('youtube').then(() => {
      window.onYouTubeIframeAPIReady = () => {
        initializeComponents(youTubeVideos);
      }
    });
  }
  if (kalturaVideos.length) {
    loadScripts('kaltura').then(() => {
      initializeComponents(kalturaVideos);
    });
  }
}
