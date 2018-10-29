import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

const styles = {
  styledLabel: {
    display: 'inline-block'
  }
};

class Label extends Component {
  render() {
    const { classes, content, onClick } = this.props;
    return (
      <div className={classes.styledLabel} onClick={onClick}>
        {content}
      </div>
    );
  }
}

Label.propTypes = {
  classes: PropTypes.object.isRequired,
  content: PropTypes.string,
  onClick: PropTypes.func
};

export default withStyles(styles)(Label);
