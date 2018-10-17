const videos = {};
let youTubeScriptAttached = false;
let kalturaScriptAttached = false;

const getVideoDetails = (id) => {
  return videos[id];
}

const videoFinish = (id) => {
  videos[id].lastProgress = 10;
  setAnalytics(id);
};

const onPlayerStateChange = (event, id) => {
  const {
    timer,
    timeSpent,
    provider
  } = getVideoDetails(id);

  if ((provider === "youtube" && event.data === 1) // start playing
  || (provider === "kaltura" && event === 'playing')) {
    if (!timeSpent.length) {
      for (let i = 0; i < 10; i++) {
        videos[id].timeSpent.push(false);
      }
    }
    videos[id].timer = setInterval(record.bind(this, id), 100);
  } else if ((provider === 'youtube') // any other youtube state change is considered a pause
  || (provider === 'kaltura' && event === 'paused')) { // kaltura paused playing
    if ((provider==="youtube" && event.data === 0)) { // youtube finished playing
      videoFinish(id);
    }
    clearInterval(timer);
  }
};

const record = (id) => {
  const {
    player,
    timeSpent,
    lastProgress,
    provider
  } = getVideoDetails(id);

  let duration;
  let currentTime;

  if (provider === 'youtube') {
    duration = player.getDuration();
    currentTime = player.getCurrentTime();
  } else if (provider === 'kaltura') {
    duration = player.evaluate('{duration}');
    currentTime = player.evaluate('{video.player.currentTime}');
  }

  const progress = Math.floor(currentTime / duration * 10);
  timeSpent.forEach((value, index) => {
    if (index < progress) {
      videos[id].timeSpent[Math.floor(index)] = true;
    }
  });

  if (progress !== lastProgress) {
    videos[id].lastProgress = progress;
    setAnalytics(id);
  }
};

const setAnalytics = (id) => {
  const {
    lastProgress,
    iframe,
    provider,
    title
  } = getVideoDetails(id);

  if (!window.digitalData) {
    window.digitalData = {
      event: []
    }
  }

  window.digitalData.video = {
    title,
    progress: `${lastProgress}0`, // percentage value in 10% increments, eg '10', '20', '30' etc
    provider: provider
  };
  window.digitalData.event.push({"eventAction" : "video-interact"});
  console.log('aaaa', window.digitalData);
};

const loadScripts = (provider) => {
  return new Promise((resolve) => {
    let source;
    if (provider === 'youtube') {
      if (youTubeScriptAttached) { resolve(); }
      source = 'https://www.youtube.com/iframe_api';
    } else if (provider === 'kaltura') {
      if (kalturaScriptAttached) { resolve(); }
      source = 'https://cdnapisec.kaltura.com/p/691292/sp/69129200/embedIframeJs/uiconf_id/20499062/partner_id/691292';
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

const createVideoDetails = ({ id, player, provider, title }) => {
  if (!videos[id]) {
    videos[id] = {
      player,
      timer: null,
      timeSpent: [],
      lastProgress: null,
      provider,
      title
    }
  }
};

const initializeComponents = (components) => {
  components.forEach((component) => {
    const id = component.getAttribute('id');
    const videoDiv = component.querySelector('.js-video-embed');
    const source = videoDiv.getAttribute('src');
    const entryId = component.getAttribute('data-entry-id');
    const provider = component.getAttribute('data-provider');
    const title = component.getAttribute('data-linkdescription');
    let player;

    if (provider === 'youtube') {
      player = new YT.Player(videoDiv, {
        videoId: entryId,
        events: {
          'onStateChange': (event) => {
            onPlayerStateChange(event, id);
          }
        }
      });
      createVideoDetails({
        id,
        player,
        provider,
        title
      });
    } else if (provider === 'kaltura') {
      let videoId = videoDiv.getAttribute('id');
      if (!videoId) {
        videoId = `${id}-video`;
        videoDiv.setAttribute('id', videoId);
      }
      const playerId = component.getAttribute('data-player-id');
      const partnerId = component.getAttribute('data-partner-id');

      kWidget.addReadyCallback( function( kalturaPlayerId) {
        player = document.getElementById(kalturaPlayerId);
        createVideoDetails({
          id,
          player,
          provider,
          title
        });

        player.kBind('playerStateChange', (event) => {
          onPlayerStateChange(event, id);
        });

        player.kBind('playerPlayEnd', () => {
          videoFinish(id);
        });
      });

      kWidget.embed({
        "targetId": videoId, // kaltura requires an ID on the div
        "wid": partnerId, // partner id
        "uiconf_id": playerId, // player id
        entry_id: entryId // media
      });
    }
  });
};

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
