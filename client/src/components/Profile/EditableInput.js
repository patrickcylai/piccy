import React, { Component } from 'react';
import PropTypes from 'prop-types';
import className from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import Input from '@material-ui/core/Input';

import Label from './Label';

const styles = {
  styledInput: {
    display: 'inline-block'
  }
};

class EditableInput extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userInput: this.props.value,
      isEditing: false
    };
  }

  $inputRef: HTMLInputElement;

  handleClickLabel = () => {
    const { value } = this.props;
    this.setState({
      isEditing: true,
      userInput: value
    });
  };

  handleSave = () => {
    const { onSave } = this.props;
    const { userInput, isEditing } = this.state;
    if (onSave) {
      onSave(userInput);
    }
    this.setState({
      userInput: userInput,
      isEditing: false
    });
  };

  handleChange = (event: SyntheticInputEvent<*>) => {
    this.setState({
      userInput: event.target.value
    });
  };

  handleKeyDown = (event: KeyboardEvent) => {
    switch (event.key) {
      case 'Enter':
        this.handleSave();
        event.stopPropagation();
        break;
      default:
        break;
    }
  };

  handleBlur = () => {
    this.handleSave();
  };

  handleCancel = () => {
    const { value } = this.props;
    const { userInput, isEditing } = this.state;

    this.setState({
      isEditing: false,
      userInput: value
    });
  };

  setInputRef = (ref: HTMLInputElement) => {
    this.$inputRef = ref;
  };

  componentDidUpdate(prevProps: Props, prevState: State) {
    const { isEditing } = this.state;
    if (isEditing && !prevState.isEditing && this.$inputRef) {
      this.$inputRef.focus();
    }
  }

  render() {
    const { value, onCancel, classes } = this.props;
    const { isEditing, userInput } = this.state;
    return isEditing ? (
      <Input
        className={classes.styledInput}
        value={userInput}
        innerRef={this.setInputRef}
        onBlur={this.handleBlur}
        onKeyDown={this.handleKeyDown}
        onChange={this.handleChange}
        onCancel={this.handleCancel}
      />
    ) : (
      <Label content={userInput} onClick={this.handleClickLabel} />
    );
  }
}

Input.propTypes = {
  classes: PropTypes.object.isRequired,
  value: PropTypes.string,
  onSave: PropTypes.func
};

export default withStyles(styles)(EditableInput);
