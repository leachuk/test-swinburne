import { hot } from 'react-hot-loader'

import Modal from './Modal.jsx'
import VideoPlayer from './VideoPlayer.jsx'

import {
  CALLBACK_END_EARLY,
  CALLBACK_READY,
  TOPIC_VIDEO_GLOBAL,
} from '../utilities/constants'

class GlobalVideoModal extends Modal {
  callbacks = {
    endEarly : [],
    ready    : [],
  }

  constructor() {
    super()

    PubSub.unsubscribe(TOPIC_VIDEO_GLOBAL)

    // Listen for a global video trigger
    PubSub.subscribe(TOPIC_VIDEO_GLOBAL, (message, data) => {
      console.info(`Global event (${TOPIC_VIDEO_GLOBAL}) was triggered:`, data)

      // Did we get a valid URL to use?
      if (data.url) {
        let parsedUrl = urlParser.parse(data.url)

        // Invalid URL? Could be an internal video from the DAM
        if (/\.(webm|mp4|ogv)$/.test(data.url)) {
          parsedUrl = false
          console.warn('DAM content is not yet supported!')
        }

        if (parsedUrl) {
          this.modalElement.modal('show')

          // Let the player know that we're ready for it
          this.triggerCallback(CALLBACK_READY, {
            type    : parsedUrl.provider,
            videoId : parsedUrl.id,
          })
        }
      }
    })
  }

  get playerCallbacks() {
    return {
      end      : this.videoEnded.bind(this),
      endEarly : this.setEndEarlyCallback.bind(this),
      ready    : this.setReadyCallback.bind(this),
    }
  }

  triggerCallback(type, data) {
    let callbacks

    switch (type) {
      case CALLBACK_END_EARLY:
        callbacks = this.callbacks.endEarly
        break
      case CALLBACK_READY:
        callbacks = this.callbacks.ready
        break
    }

    if (callbacks) {
      callbacks.forEach(callback => callback(data))
    }
  }

  videoEnded() {
    this.modalElement.modal('hide')
  }

  setReadyCallback(callback) {
    this.callbacks.ready.push(callback)
  }

  setEndEarlyCallback(callback) {
    this.callbacks.endEarly.push(callback)
  }

  modalClosed() {
    this.triggerCallback(CALLBACK_END_EARLY)

    // Call the parent
    super.modalClosed()
  }

  modalChildren() {
    return <VideoPlayer callbacks={this.playerCallbacks} />
  }
}

export default hot(module)(GlobalVideoModal)
