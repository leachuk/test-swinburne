import ResponsiveIframes from './responsive-iframes';

const videos = {}; // object to store video information. Indexed by component id attribute
let youTubeScriptAttached = false;
let kalturaScriptAttached = false;

/**
 * Retrieves a given video's information from the object videos (see above)
 *
 * @param {id} ID attribute of video component
 */
const getVideoDetails = (id) => {
  return videos[id];
}

/**
 * Sets a given video's status to complete and executes the analytics update
 *
 * @param {id} ID attribute of video component
 */
const videoFinish = (id) => {
  videos[id].lastWatchedSegment = 10;
  setAnalytics(id);
};

/**
 * Handles a state change of a given media player.
 *
 * @param {Object} State change event, provided by 3rd party API
 * @param {id} ID attribute of video component
 */
const onPlayerStateChange = (event, id) => {
  const {
    timer,
    segments,
    provider
  } = getVideoDetails(id);

  if ((provider === "youtube" && event.data === 1) // start playing
  || (provider === "kaltura" && event === 'playing')) {
    if (!segments.length) {
      // create array with 10 booleans, each representing 1/10 of the video
      for (let i = 0; i < 10; i++) {
        videos[id].segments.push(false);
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

/**
 * Checks the current progress of the given video. If the video watched time has progressed by at least 10%, then
 * execute analytics update
 *
 * @param {id} ID attribute of video component
 */
const record = (id) => {
  const {
    player,
    segments,
    lastWatchedSegment,
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

  const currentSegment = Math.floor(currentTime / duration * 10);
  segments.forEach((value, index) => {
    if (index < currentSegment) {
      videos[id].segments[Math.floor(index)] = true;
    }
  });

  if (currentSegment !== lastWatchedSegment) {
    videos[id].lastWatchedSegment = currentSegment;
    setAnalytics(id);
  }
};

/**
 * Updates the window's analytics object with details of a given video
 *
 * @param {id} ID attribute of video component
 */
const setAnalytics = (id) => {
  const {
    lastWatchedSegment,
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
    progress: `${lastWatchedSegment}0`, // percentage value in 10% increments, eg '10', '20', '30' etc
    provider: provider
  };
  window.digitalData.event.push({"eventAction" : "video-interact"});
};

/**
* Dynamically loads 3rd party javascript files into the page. Returns a promise that resolves after done.
*
* @param {String} Provider, either 'youtube' or 'kaltura'
*/
const loadScripts = (provider) => {
  return new Promise((resolve) => {
    let source;
    if (provider === 'youtube') {
      if (youTubeScriptAttached) { resolve(); } // check if script is already attached
      source = 'https://www.youtube.com/iframe_api';
    } else if (provider === 'kaltura') {
      if (kalturaScriptAttached) { resolve(); } // check if script is already attached
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

/**
 * Stores the details for a given video
 *
 * @param {id} ID attribute of video component
 */
const createVideoDetails = ({ id, player, provider, title }) => {
  if (!videos[id]) {
    videos[id] = {
      player,
      timer: null,
      segments: [],
      lastWatchedSegment: null,
      provider,
      title
    }
  }
};

/**
 * Dynamically loads an embedded video in each of the given set of nodes.
 *
 * @param {Array} Array of nodes
 */
const initializeComponents = (components) => {
  components.forEach((component) => {
    const id = component.getAttribute('id');
    const videoDiv = component.querySelector('.js-video-embed');
    const source = videoDiv.getAttribute('src');
    const entryId = component.getAttribute('data-entry-id');
    const provider = component.getAttribute('data-provider');
    const title = component.getAttribute('data-title');
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
  ResponsiveIframes();
};

/**
 * Checks if embedded video components are on the page and if so, initializes them
 */
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
