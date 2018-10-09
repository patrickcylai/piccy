import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Input from '@material-ui/core/Input';
import EditIcon from '@material-ui/icons/Edit';

class Profile extends Component {
  render() {
    const { classes } = this.props;
    return (
      <div>
        <EditIcon />
      </div>
    );
  }
}

export default Profile;
