import { PureComponent } from 'react'
import PropTypes from 'prop-types'

class Modal extends PureComponent {
  static propTypes = {
    hideFooter : PropTypes.bool.isRequired,
    hideHeader : PropTypes.bool.isRequired,
    hideTitle  : PropTypes.bool.isRequired,
    id         : PropTypes.string.isRequired,
    title      : PropTypes.string,
  }

  static defaultProps = {
    hideFooter : false,
    hideHeader : false,
    hideTitle  : false,
    id         : 'bootstrap-modal',
  }

  componentDidMount() {
    // Bind the Bootstrap modal logic
    this.modalElement.modal({
      show: false,
    })

    // Listen for when the modal is closing
    this.modalElement.on('hidden.bs.modal', this.modalClosed.bind(this))
  }

  get modalElement() {
    return $(`#${this.props.id}`)
  }

  modalClosed() {
    console.info(`Modal '#${this.modalElement.prop('id')}' has closed`)
  }

  modalHeader() {
    const { hideTitle, id, title } = this.props

    return (
      <div className="modal-header">
        {
          !hideTitle ?
            <h5 className="modal-title" id={`${id}-label`}>{title}</h5>
            : null
        }
        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden={true}>&times;</span>
        </button>
      </div>
    )
  }

  modalFooter() {
    return (
      <div className="modal-footer">
        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    )
  }

  modalChildren() {
    return this.props.children
  }

  render() {
    const { hideFooter, hideHeader, id } = this.props

    return (
      <div className="modal" id={id} tabIndex="-1" role="dialog" aria-labelledby={`${id}-label`} aria-hidden={true}>
        <div className="modal-dialog modal-dialog-centered" role="document">
          <div className="modal-content">
            { !hideHeader && this.modalHeader() }
            <div className="modal-body">
              { this.modalChildren() }
            </div>
            { !hideFooter && this.modalFooter() }
          </div>
        </div>
      </div>
    )
  }
}

export default Modal
