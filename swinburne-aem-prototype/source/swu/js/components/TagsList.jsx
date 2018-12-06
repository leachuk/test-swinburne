import { hot } from 'react-hot-loader'
import { PureComponent } from 'react'
import { findDOMNode } from 'react-dom'
import PropTypes from 'prop-types'
import classNames from 'classnames'

import {
  TOPIC_SEARCH_SUBMIT,
} from '../utilities/constants'

class TagsList extends PureComponent {
  static propTypes = {
    actionLabel    : PropTypes.string,
    buttonLabel    : PropTypes.string,
    invalidMessage : PropTypes.string,
    tags           : PropTypes.array.isRequired,
  }

  static defaultProps = {
    actionLabel    : 'Go',
    buttonLabel    : 'Filters',
    invalidMessage : 'Please select at least one tag!',
  }

  state = {
    invalidSelection : false,
    listOpen         : false,
    selectedTags     : [],
  }

  componentDidMount() {
    const instanceElement = findDOMNode(this)

    // Find an element in the DOM with a theme of `theme--contentblock-search`
    let parentElement     = instanceElement.parentElement
    let tagsParentElement = null

    while (parentElement instanceof window.HTMLElement) {
      if (parentElement.classList.contains('theme--contentblock-search')) {
        parentElement = null
      } else {
        tagsParentElement = parentElement
        parentElement     = parentElement.parentElement
      }
    }

    // Retrieve the left side margin to correctly align the tags container
    if (tagsParentElement instanceof window.HTMLElement) {
      let offsetCount     = 0
      let previousElement = tagsParentElement.previousElementSibling

      while (previousElement) {
        ++offsetCount
        previousElement = previousElement.previousElementSibling || null
      }

      // Pull the tags container to the left
      const computedStyles = window.getComputedStyle(tagsParentElement)
      const leftMargin     = parseInt(computedStyles.marginLeft, 10) / 2 * offsetCount

      instanceElement.nextElementSibling.style.marginLeft = `-${leftMargin}px`
    }
  }

  classForTag({ value }) {
    return classNames({
      'selected': this.state.selectedTags.indexOf(value) !== -1,
    })
  }

  toggleListState(event) {
    event.preventDefault()

    this.setState({
      listOpen: !this.state.listOpen,
    })
  }

  toggleTagState(event, { value }) {
    let { selectedTags } = this.state

    event.preventDefault()

    if (selectedTags.indexOf(value) !== -1) {
      selectedTags = selectedTags.filter(tag => tag !== value)
    } else {
      selectedTags = [...selectedTags, value]
    }

    this.setState({
      invalidSelection: false,
      selectedTags,
    })
  }

  submitTagsToSearch() {
    const selectedTags = this.state.selectedTags

    if (selectedTags.length) {
      PubSub.publish(TOPIC_SEARCH_SUBMIT, {
        selectedTags,
      })
    } else {
      this.setState({
        invalidSelection: true,
      })
    }
  }

  render() {
    const {
      actionLabel,
      buttonLabel,
      invalidMessage,
      tags,
    } = this.props

    const {
      invalidSelection,
      listOpen,
    } = this.state

    const toggleClasses = classNames('taglist__toggle', {
      'active': listOpen,
    })

    const tagsListClasses = classNames('taglist__tags', {
      'open': listOpen,
    })

    return (
      <>
        <button className={toggleClasses} onClick={this.toggleListState.bind(this)}>
          {buttonLabel}<i className="icon chevron-down"></i>
        </button>
        <div className={tagsListClasses}>
          {
            invalidSelection &&
            <p className="taglist__invalid">{invalidMessage}</p>
          }
          <ol className="tags">
            {tags.map(tag => (
              <li key={tag.value}>
                <a className={this.classForTag(tag)} href="#" onClick={e => this.toggleTagState(e, tag)}>{tag.label}</a>
              </li>
            ))}
          </ol>
          <button
            className="taglist__action btn btn-secondary btn-sm"
            onClick={this.submitTagsToSearch.bind(this)}
          >{actionLabel}</button>
        </div>
      </>
    )
  }
}

export default hot(module)(TagsList)
