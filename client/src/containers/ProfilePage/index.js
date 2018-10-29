import React, { Component } from 'react';
import PropTypes from 'prop-types';

import NavBar from '../../components/NavBar';
import Profile from '../../components/Profile';

class ProfilePage extends Component {
  render() {
    return (
      <div>
        <NavBar displayProfile />
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

export default ProfilePage;
