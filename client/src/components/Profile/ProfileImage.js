import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';

const styles = {
  bigAvatar: {
    width: 90,
    height: 90
  }
};

class ProfileImage extends Component {
  render() {
    const { classes } = this.props;

    return (
      <Avatar
        alt="Stewie Griffin"
        src="https://static.vecteezy.com/system/resources/previews/000/071/998/non_2x/vector-stewie-griffin.jpg"
        className={classNames(classes.bigAvatar)}
      />
    );
  }
}

ProfileImage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ProfileImage);
