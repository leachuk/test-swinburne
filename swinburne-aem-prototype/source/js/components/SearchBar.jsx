import { hot } from 'react-hot-loader'
import { PureComponent } from 'react'
import PropTypes from 'prop-types'
import Bloodhound from 'bloodhound-js'
import classNames from 'classnames'
import JSONPath from 'jsonpath-plus/lib/jsonpath'

import {
  TOPIC_HIDE_SUGGESTIONS,
  TOPIC_SEARCH_SUBMIT,
} from '../utilities/constants'

import GUID from '../utilities/guid'
import xml2json from '../utilities/xml2json'

PubSub.unsubscribe(TOPIC_SEARCH_SUBMIT)
PubSub.unsubscribe(TOPIC_HIDE_SUGGESTIONS)

class SearchBar extends PureComponent {
  static propTypes = {
    action             : PropTypes.string,
    feeds              : PropTypes.array,
    inputPlaceholder   : PropTypes.string,
    isMobile           : PropTypes.bool,
    needsSubmit        : PropTypes.bool,
    noClearButton      : PropTypes.bool,
    pageSearch         : PropTypes.bool,
    plainButton        : PropTypes.bool,
    smallSuggestButton : PropTypes.bool,
    submitButtonLabel  : PropTypes.string,
  }

  static defaultProps = {
    action             : '/search-results',
    feeds              : [],
    inputPlaceholder   : 'Search',
    isMobile           : false,
    needsSubmit        : true,
    noClearButton      : false,
    pageSearch         : false,
    plainButton        : false,
    smallSuggestButton : false,
    submitButtonLabel  : 'Search the site',
  }

  suggestEngine = null
  searchInput   = null
  suggestions   = []

  state = {
    hasInput        : false,
    hasSuggestions  : false,
    inputInvalid    : false,
    shouldSubmit    : false,
    showSuggestions : true,
    suggestions     : null,
  }

  componentDidMount() {
    const { feeds, pageSearch } = this.props

    // Do we have any feeds to listen in on?
    if (feeds.length) {
      this.fetchFeedsForSearchBar(feeds)
    }

    // Submission listener
    if (pageSearch) {
      PubSub.subscribe(TOPIC_SEARCH_SUBMIT, this.searchSubmissionSubscriber.bind(this))
    }

    // Hide suggestions listener
    PubSub.subscribe(TOPIC_HIDE_SUGGESTIONS, () => this.setState({ showSuggestions: false }))
  }

  componentDidUpdate() {
    const {
      selectedTags,
      shouldSubmit,
    } = this.state

    if (shouldSubmit) {
      this.submitQueryToServer(selectedTags)
    }
  }

  fetchFeedsForSearchBar(feeds) {
    const promises = []

    feeds.forEach(feed => {
      promises.push(new Promise(resolve => {
        $.ajax({
          async    : true,
          cache    : false,
          dataType : 'xml',
          type     : 'GET',
          url      : feed,
        }).done(response => {
          const json = xml2json(response)

          // Try to get any `<item>` elements first
          let items = JSONPath({ json: json, path: '$..item' })

          // If no items are found, try looking for `<entry>` elements
          if (!items.length) {
            items = JSONPath({ json: json, path: '$..entry' })
          }

          resolve((items.length && items) || null)
        }).fail(() => {
          // We don't want to kill the entire autocomplete functionality so we will simply pass a
          // null value so that everything works as normal.
          resolve(null)
        })
      }))
    })

    Promise.all(promises).then(feedResults => {
      const suggestions = []

      feedResults.forEach(items => {
        if (!items) {
          return
        }

        items[0].forEach(item => {
          const suggestion = {}

          // Retrieve the description or summary (if it has one)
          if (item.summary || item.description) {
            suggestion.description = (item.summary || item.description)['#text']
          }

          // Retrieve the link
          if (item.link['@attributes'] && item.link['@attributes'].href) {
            suggestion.link = item.link['@attributes'].href
          } else if (item.link['#text']) {
            suggestion.link = item.link['#text']
          }

          // Retrieve the title
          suggestion.title = item.title['#text']

          // Was a valid object created?
          if (suggestion.link && suggestion.title) {
            suggestion.guid = GUID()
            suggestions.push(suggestion)
          }
        })
      })

      // Create a new suggestion engine if we have suggestions to use
      if (suggestions.length) {
        const engine = new Bloodhound({
          local          : suggestions,
          identify       : suggestion => suggestion.guid,
          datumTokenizer : Bloodhound.tokenizers.obj.whitespace('description', 'title'),
          queryTokenizer : Bloodhound.tokenizers.whitespace,
        });

        engine.initialize().then(() => {
          this.suggestEngine = engine
        })
      }
    })
  }

  searchSubmissionSubscriber(message, data) {
    this.setState({
      selectedTags: data.selectedTags || [],
      shouldSubmit: true,
    })
  }

  submitQueryToServer(selectedTags = []) {
    const { action } = this.props
    const inputValue = this.searchInput.value.trim()

    if (selectedTags.length && inputValue) {
      window.location.href = `${action}?q=${encodeURIComponent(inputValue)}&tags=${selectedTags.join(',')}`
    } else if (selectedTags.length && !inputValue) {
      window.location.href = `${action}?tags=${selectedTags.join(',')}`
    } else {
      window.location.href = `${action}?q=${encodeURIComponent(inputValue)}`
    }
  }

  setSearchInputRef(element) {
    this.searchInput = element
  }

  searchInputFocused() {
    if (this.state.showSuggestions) {
      return
    }

    // Set a small delay to prevent other event listeners around the site from cancelling out the
    // suggestions box before it opens.
    setTimeout(() => this.setState({ showSuggestions: true }), 300)
  }

  capatureInput({ target }) {
    const userInput      = target.value
    const inputHasLength = userInput.length > 0

    // Look for any suggestions
    let suggestions = null
    if (this.suggestEngine) {
      this.suggestEngine.search(userInput, results => {
        suggestions = results.slice(0, 5)
      })
    }

    this.setState({
      hasInput       : inputHasLength,
      hasSuggestions : suggestions && suggestions.length > 0,
      inputInvalid   : !inputHasLength,
      suggestions    : suggestions,
    })
  }

  clearInput(event) {
    event.preventDefault()

    this.setState({
      hasInput       : false,
      hasSuggestions : false,
      suggestions    : null,
    }, () => {
      // Clear the input field value and focus on the input field
      this.searchInput.value = ''
      this.searchInput.focus()
    })
  }

  formSubmission(event) {
    event.preventDefault()

    if (this.searchInput.value.length) {
      this.submitQueryToServer()
    } else {
      console.error('Invalid value given, at least one character is expected!')

      this.setState({
        inputInvalid: true,
      })
    }
  }

  mobileSearch(event) {
    event.preventDefault()

    // Toggle the page state for the search bar
    document.body.classList.toggle('global-search')

    // If the search bar is visible, focus on it straight away
    if (document.body.classList.contains('global-search')) {
      this.searchInput.focus()
    }
  }

  getSuggestionsContainer() {
    const {
      isMobile,
      smallSuggestButton,
      submitButtonLabel,
    } = this.props

    const {
      hasSuggestions,
      showSuggestions,
      suggestions,
    } = this.state

    if ((hasSuggestions && !showSuggestions) || !(hasSuggestions && showSuggestions)) {
      return null
    }

    // Define the classes for the suggestion box submit button
    const suggestButtonClasses = classNames('btn', {
      'btn-primary'   : isMobile,
      'btn-secondary' : !isMobile,
      'btn-sm'        : smallSuggestButton,
    })

    return (
      <div className="suggestions">
        <ul>
          {suggestions.map(suggestion => (
            <li key={suggestion.guid}>
              <a href={suggestion.link}>{suggestion.title}</a>
            </li>
          ))}
        </ul>
        <button className={suggestButtonClasses} type="submit">{submitButtonLabel}</button>
      </div>
    )
  }

  render() {
    const {
      inputPlaceholder,
      isMobile,
      needsSubmit,
      noClearButton,
      plainButton,
      submitButtonLabel,
    } = this.props

    const {
      hasInput,
      inputInvalid,
    } = this.state

    // Define the input wrapper classes
    const searchInputWrapperClasses = classNames('search-input', {
      'has-input'     : hasInput,
      'invalid-input' : inputInvalid,
    })

    // Define the classes for the clear button
    const clearSearchClasses = classNames('clear-search', {
      'active': hasInput,
    })

    // Define the claseses for the submit button
    const buttonClasses = classNames({
      'btn btn-primary': !plainButton,
    })

    return (
      <>
        {
          isMobile &&
          <a className="mobile-search" href="#" onClick={this.mobileSearch.bind(this)}>
            <i className="icon">search</i>
          </a>
        }
        <form autoComplete="off" onSubmit={this.formSubmission.bind(this)}>
          <div className={searchInputWrapperClasses}>
            <input
              type="text"
              placeholder={inputPlaceholder}
              name="query"
              autoComplete="off"
              onFocus={this.searchInputFocused.bind(this)}
              onInput={this.capatureInput.bind(this)}
              ref={this.setSearchInputRef.bind(this)}
            />
            {
              !noClearButton &&
              <button type="button" className={clearSearchClasses} onClick={this.clearInput.bind(this)}>
                <i className="icon">cancel</i>
              </button>
            }
            { this.getSuggestionsContainer() }
          </div>
          {
            needsSubmit &&
            <button className={buttonClasses} onClick={this.formSubmission.bind(this)} type="submit">
              {plainButton ? <i className="icon">search</i> : submitButtonLabel}
            </button>
          }
        </form>
      </>
    )
  }
}

export default hot(module)(SearchBar)
