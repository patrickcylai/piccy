import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

import NavBar from '../../components/NavBar';
import Profile from '../../components/Profile';

const styles = theme => ({});

class ProfilePage extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div>
        <NavBar />
        <Profile
          username="stewiebae"
          firstName="Stewie"
          lastName="Griffin"
          email="stewiegriffin@piccy.com"
          password="********"
        />
      </div>
    );
  }
}

ProfilePage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(ProfilePage);
