import { sanitizeHTML } from '@global/utilities/dom'

// Internal
const QUICK_SEARCH_CLASS = '.brand-header__quick-search'

export default () => {
  const quickSearch = document.querySelector(QUICK_SEARCH_CLASS)

  if (quickSearch) {
    const searchField: HTMLInputElement = quickSearch.querySelector('input[type=search]') as HTMLInputElement
    const submitButton: HTMLElement = quickSearch.querySelector('button[type=submit]') as HTMLElement

    if (submitButton && searchField) {
      submitButton.addEventListener('click', (event: Event) => {
        event.preventDefault()

        if (!quickSearch.classList.contains('show')) {
          quickSearch.classList.add('show')

          // Focus on the search input field, the timeout ensures that some mobile devices don't
          // miss this action.
          setTimeout(() => searchField.focus(), 1)
        } else {
          console.info(
            'Normal submission!\n',
            'Field Value:', sanitizeHTML(searchField.value), '\n',
            'Field Value (URI encoded):', encodeURIComponent(sanitizeHTML(searchField.value)),
          )
        }
      })
    }

    // Listen for when the user clicks around the window
    // TODO: Convert to vanilla JS
    $(window).on('click.header.search', (e: JQuery.TriggeredEvent) => {
      const $target = $(e.target)

      if (!$target.is(QUICK_SEARCH_CLASS) && !$target.parents(QUICK_SEARCH_CLASS).length) {
        quickSearch.classList.remove('show')
      }
    })
  }
}
