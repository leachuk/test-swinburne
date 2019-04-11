import { hasParent, matches, sanitizeHTML } from '@global/utilities/dom'

// Internal
const QUICK_SEARCH_CLASS = '.brand-header__quick-search'

let searchField: HTMLInputElement
let searchForm: HTMLFormElement
let submitButton: HTMLElement

/**
 * Submits the user input to the defined action URL set in the backend.
 *
 * @return void
 */
function submitSearchForm() {
  const userInput = encodeURIComponent(sanitizeHTML(searchField.value))

  if (userInput.length) {
    window.location.href = searchForm.action.replace('{{query}}', userInput)
  }
}

export default () => {
  const quickSearch = document.querySelector(QUICK_SEARCH_CLASS)

  if (quickSearch) {
    searchField  = quickSearch.querySelector('input[type=search]') as HTMLInputElement
    searchForm   = quickSearch.querySelector('form') as HTMLFormElement
    submitButton = quickSearch.querySelector('button[type=submit]') as HTMLElement

    if (searchField && searchForm && submitButton) {
      submitButton.addEventListener('click', (event: Event) => {
        event.preventDefault()

        if (!quickSearch.classList.contains('show')) {
          quickSearch.classList.add('show')

          // Focus on the search input field, the timeout ensures that some mobile devices don't
          // miss this action.
          setTimeout(() => searchField.focus(), 1)
        } else {
          submitSearchForm()
        }
      })

      searchForm.addEventListener('submit', (event: Event) => {
        event.preventDefault()
        submitSearchForm()
      })
    }

    // Listen for when the user clicks around the window
    window.addEventListener('click', (event: Event) => {
      const target: HTMLElement = event.target as HTMLElement

      if (!matches(target, QUICK_SEARCH_CLASS) && !hasParent(target.parentNode, QUICK_SEARCH_CLASS)) {
        quickSearch.classList.remove('show')
      }
    })
  }
}
