const events = [
  'click',
  'keyup',
  'keydown',
  'mousedown',
  'mouseup',
]

const subscribers = [
  'form-input',
  'play-video',
]

export default () => {
  console.info('[Subscribers] Loading...')

  subscribers.forEach(subscriber => {
    const subscriberTree = require(`@global/subscribers/${subscriber}`)

    if (typeof subscriberTree === 'object') {
      console.info(`[Subscribers] Getting '${subscriber}' ready...`)
      let localEvents = events.map(event => `${event}.${subscriber}`).join(' ')

      // Applies `event`, `originalEvent` to the callback function
      $(document.body)
        .off(localEvents)
        .on(
          localEvents, `[data-modules*='${subscriber}']`,
          (event: JQuery.Event) => {
            const originalEvent = event as JQuery.TriggeredEvent

            subscriberTree.default(event, originalEvent, (originalEvent && originalEvent.type) || '')
          }
        )

      console.info(`[Subscribers] '${subscriber}' is now subscribed!`)
    }
  })

  console.info('[Subscribers] All done!')
}
