import { PureComponent } from 'react'
import { hot } from 'react-hot-loader'
import PropTypes from 'prop-types'
import Vimeo from 'react-vimeo'
import YouTube from 'react-youtube'

import {
  BRAND_VIMEO,
  BRAND_YOUTUBE,
} from '../utilities/constants'

const defaultCallbacks = {
  done() { },
  end() { },
  endEarly() { },
  ready() { },
}

class VideoPlayer extends PureComponent {
  static propTypes = {
    callbacks: PropTypes.objectOf(PropTypes.func),
  }

  static defaultProps = {
    callbacks: defaultCallbacks,
  }

  state = {
    type    : null,
    videoId : null,
  }

  constructor(props) {
    super(props)

    // Event methods
    this.onVideoEnd   = this.onVideoEnd.bind(this)
    this.onVideoReady = this.onVideoReady.bind(this)

    // Listen for when a video should start
    props.callbacks.ready(this.setPlayerState.bind(this))

    // Listen for when the video should end without warning
    props.callbacks.endEarly(this.setPlayerState.bind(this, {}))
  }

  setPlayerState({ type, videoId }) {
    this.setState({
      type    : type || null,
      videoId : videoId || null,
    })
  }

  onVideoReady() {
    console.info('Video is ready!')
  }

  onVideoEnd() {
    console.info('Video has ended!')

    // Reset the video state
    this.setPlayerState({})

    // Let something else know that the video has ended
    this.props.callbacks.end()
  }

  getVimeoVideo() {
    return (
      <Vimeo
        autoplay={true}
        onEnded={this.onVideoEnd}
        onReady={this.onVideoReady}
        videoId={this.state.videoId}
      />
    )
  }

  getYouTubeVideo() {
    return (
      <YouTube
        onEnd={this.onVideoEnd}
        onReady={this.onVideoReady}
        videoId={this.state.videoId}
        opts={{
          playerVars: {
            autoplay       : 1,
            enablejsapi    : 1,
            modestbranding : 1,
            playsinline    : 0,
            rel            : 0,
            showinfo       : 0,
          },

          height : 768,
          width  : 1024,
        }}
      />
    )
  }

  getVideoHTML() {
    const { type, videoId } = this.state

    if (videoId !== null) {
      switch (type) {
        case BRAND_VIMEO:
          return this.getVimeoVideo()

        case BRAND_YOUTUBE:
          return this.getYouTubeVideo()

        default:
          console.warn(`Video platform '${type}' not yet supported!`)
      }
    }

    return null
  }

  render() {
    return this.getVideoHTML() || <div />
  }
}

export default hot(module)(VideoPlayer)
