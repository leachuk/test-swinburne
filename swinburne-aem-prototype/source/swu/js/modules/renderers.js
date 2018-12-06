import { render } from 'react-dom'

// Components
import GlobalVideoModal from '../components/GlobalVideoModal.jsx'
import SearchBar from '../components/SearchBar.jsx'
import TagsList from '../components/TagsList.jsx'

/**
 * Generates a new or uses an existing component slot to render React components
 * to the page without needing to know where they exist.
 *
 * @param  {String}  id              An identifier to separate this component from others
 * @param  {Boolean} ignoreOnMissing Should the component be rendered if the parent doesn't exist?
 * @return Array<Element>
 */
function getComponentSlot(id, ignoreOnMissing) {
  let root = document.querySelector('#react-components')

  // Ensure the root element exists
  if (!root) {
    root = document.createElement('div')
    root.setAttribute('id', 'react-components')

    document.body.appendChild(root)
  }

  // Check if the parent exists in the global scope (i.e. the `<body>`)
  let componentParent = [...document.querySelectorAll(`[data-react-component='${id}']`)]
  let componentModule = [...document.querySelectorAll(`[data-modules='${id}']`)]

  if (componentParent.length || componentModule.length) {
    return (componentParent.length && componentParent) || componentModule
  }

  // Check if the child exists, if not, create it.
  componentParent = root.querySelector(`[data-react-component='${id}']`)

  if (!componentParent && !ignoreOnMissing) {
    componentParent = document.createElement('div')
    componentParent.setAttribute('data-react-component', id)

    root.appendChild(componentParent)
  }

  return [componentParent]
}

/**
 * Renders the component to the screen.
 *
 * @param {String|HTMLElement} target          The unique identifier/element for the component instance
 * @param {Function}           component       A callback function that generates the React component
 * @param {Boolean}            ignoreOnMissing Should the component be rendered if the parent doesn't exist?
 */
function renderComponent(target, component, ignoreOnMissing = false) {
  if (target instanceof window.HTMLElement) {
    render(component(target, target.dataset), target)
    return
  }

  // If the target isn't an element, simply continue on...
  const parentElements = getComponentSlot(target, ignoreOnMissing)

  if (parentElements) {
    parentElements.filter(x => x).forEach(parent => {
      render(component(parent, parent.dataset), parent)
    })
  }
}

/**
 * Determines if the parent exists within a specific context.
 *
 * @param  {HTMLElement} parent    The parent element ot search upon
 * @param  {String}      className The CSS class name in which to search for
 * @return {Boolean}
 */
function belongsToClass(parent, className) {
  let parentElement = parent.parentElement

  while (parentElement) {
    if (parentElement.classList.contains(className)) {
      parentElement = null
      return true
    } else {
      parentElement = parentElement.parentElement
    }
  }

  return false
}

/**
 * Renders the global video modal on the page.
 */
function renderGlobalVideoPlayer() {
  const slotIdentifier = 'global-video-player'

  renderComponent(slotIdentifier, () => (
    <GlobalVideoModal id={slotIdentifier} hideFooter={true} hideTitle={true} />
  ))
}

/**
 * Renders the search bar for the header (top bar)
 */
function renderGlobalSearchBar() {
  renderComponent('global-search', (parent, data) => {
    const config = {
      action      : parent.querySelector('form').getAttribute('action'),
      isMobile    : belongsToClass(parent, 'navbar'),
      needsSubmit : (data.needsSubmit && data.needsSubmit === 'true' && true) || false,
    }

    if (parent.dataset.feedUrls) {
      config.feeds = parent.dataset.feedUrls.split(',')
    }

    return <SearchBar {...config} />
  }, true)
}

/**
 * Renders the search bar for the header (page details)
 */
function renderHeaderSearchBar() {
  renderComponent('header-search', (parent, data) => (
    <SearchBar
      action={parent.querySelector('form').getAttribute('action')}
      feeds={(data.feedUrls || '').split(',')}
      inputPlaceholder="Type your search"
    />
  ), true)
}

/**
 * Renders a search bar in place of the existing one that AEM generates for the search widget.
 */
function renderPageLevelSearchBar() {
  renderComponent('search', (parent, data) => {
    return (
      <SearchBar
        action={parent.querySelector('form').getAttribute('action')}
        feeds={(data.feedUrls || '').split(',')}
        plainButton
        noClearButton
        pageSearch
      />
    )
  }, true)
}

function renderTagsList() {
  const tagLists = [...document.querySelectorAll('.taglist')]

  if (tagLists.length) {
    tagLists.forEach(list => {
      // Convert the `select` list into an array of options
      list.tags = [...list.querySelectorAll('option:not(:disabled)')].map(item => ({
        label: item.innerText,
        value: item.value,
      }))

      // Render the internal `TagsList` component over top of the AEM component
      renderComponent(list, parent => (
        <TagsList tags={parent.tags} />
      ))
    })
  }
}

export default () => {
  renderGlobalVideoPlayer()
  renderGlobalSearchBar()
  renderPageLevelSearchBar()
  renderHeaderSearchBar()
  renderTagsList()
}
