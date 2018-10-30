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
      <Avatar className={classNames(classes.bigAvatar)}>
        {this.props.username.substring(0, 1).toUpperCase()}
      </Avatar>
    );
  }
}

ProfileImage.propTypes = {
  classes: PropTypes.object.isRequired,
  username: PropTypes.string
};

export default withStyles(styles)(ProfileImage);
