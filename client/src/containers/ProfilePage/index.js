import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

import NavBar from '../../components/NavBar';

const styles = theme => ({});

class ProfilePage extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div>
        <NavBar />
      </div>
    );
  }
}

ProfilePage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ProfilePage);
