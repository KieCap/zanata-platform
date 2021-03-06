import React, { Component } from 'react'
import PropTypes from 'prop-types'
import Button from '../Button'
import Dropdown from '../Dropdown'
import { Row } from 'react-bootstrap'
import { Modal } from '../../../components'
import { Icon } from '../../../components'
/**
 * TODO add a concise description of this component
 */
export const MINOR = 'Minor'
export const MAJOR = 'Major'
export const CRITICAL = 'Critical'

export class RejectTranslationModal extends Component {
  static propTypes = {
    show: PropTypes.bool,
    className: PropTypes.string,
    key: PropTypes.string,
    onHide: PropTypes.func,
    toggleDropdown: PropTypes.func,
    isOpen: PropTypes.bool.isRequired,
    priority: PropTypes.oneOf(
        [
          MINOR,
          MAJOR,
          CRITICAL
        ]
    ).isRequired,
    textState: PropTypes.oneOf (
        [
            'u-textWarning',
            'u-textDanger'
        ]
    ),
    criteria: PropTypes.string.isRequired
  }

  constructor (props) {
    super(props)
    this.state = {
      dropdownOpen: false,
      dropdownOpen2: false
    }
  }

  toggleDropdown = () => {
    this.setState(prevState => ({
      dropdownOpen: !prevState.dropdownOpen
    }))
  }

  toggleDropdown2 = () => {
    this.setState(prevState => ({
      dropdownOpen2: !prevState.dropdownOpen2
    }))
  }


  render () {
    const {
      show,
      key,
      className,
      onHide,
      isOpen,
      criteria,
      priority,
      textState
    } = this.props


    return (
      <Modal show={show}
        onHide={close}
        key="reject-translation-modal"
        id="RejectTranslationModal">
        <Modal.Header>
          <Modal.Title>Reject translation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="flex">
          <span id="criteriatitle">
            Criteria
          </span>
          <Dropdown enabled isOpen={this.state.dropdownOpen}
            onToggle={this.toggleDropdown}
            className="dropdown-menu criteria">
            <Dropdown.Button>
            <a className="Dropdown-item">
              {this.props.criteria}
              <Icon className="n1" name="chevron-down" />
              </a>
            </Dropdown.Button>
            <Dropdown.Content>
              <ul>
                <li className="Dropdown-item" onClick={this.toggleDropdown}>
                  Translation Errors (terminology, mistranslated, addition, omission, un-localized, do not translate, etc)</li>
                <li className="Dropdown-item" onClick={this.toggleDropdown}>
                  Language Quality (grammar, spelling, punctuation, typo, ambiguous wording, product name, sentence structuring,
                  readability, word choice, not natural, too literal, style and tone, etc)
                </li>
                <li className="Dropdown-item" onClick={this.toggleDropdown}>
                  Consistency (inconsistent style or vocabulary, brand inconsistency, etc.)</li>
                <li className="Dropdown-item" onClick={this.toggleDropdown}>Style Guide & Glossary Violations</li>
                <li className="Dropdown-item" onClick={this.toggleDropdown}>
                  Format (mismatches, white-spaces, tag error or missing, special character, numeric format, truncated, etc.)</li>
                <li className="Dropdown-item" onClick={this.toggleDropdown}>
                  Other (reason may be in comment section/history if necessary)</li>
              </ul>
            </Dropdown.Content>
          </Dropdown>
          <span className="priority-dd">
            <Icon name="warning" className="s2 u-textWarning" />
            <span id="prioritytitle">Priority</span>
          <Dropdown enabled isOpen={this.state.dropdownOpen2}
                    onToggle={this.toggleDropdown2}
                    className="dropdown-menu priority">
            <Dropdown.Button>
              <a className="Dropdown-item">
                <span className={textState}>{this.props.priority}</span>
                <Icon className="n1" name="chevron-down" />
              </a>
            </Dropdown.Button>
            <Dropdown.Content>
              <ul>
                <li className="Dropdown-item" onClick={this.toggleDropdown2}>
                  <span>Minor</span></li>
                <li className="Dropdown-item" onClick={this.toggleDropdown2}>
                    <span className="u-textWarning">Major</span></li>
                <li className="Dropdown-item" onClick={this.toggleDropdown2}>
                    <span className="u-textDanger">Critical</span></li>
              </ul>
            </Dropdown.Content>
          </Dropdown>
          </span>
          </div>
          <textarea ref="input"
             type="comment"
             placeholder="Provide a comment for why this translation has been rejected"
             cols="50"
             rows="10"
             className='InputGroup-input is-focused InputGroup--outlined commenting' />
        </Modal.Body>
        <Modal.Footer>
          <span>
            <Row>
              <Button className="Button Button--large u-rounded Button--secondary">
                Cancel
              </Button>
              <Button className="Button Button--large u-rounded Button--primary">
                Reject translation
              </Button>
            </Row>
          </span>
        </Modal.Footer>
      </Modal>
    )
  }
}

export default RejectTranslationModal
