import React, { Component } from 'react';
import PropTypes from 'prop-types';

import NavBar from '../../components/NavBar';
import UploadImage from '../../components/UploadImage';

class UploadPage extends Component {
  render() {
    return (
      <div>
        <NavBar displayProfile />
        <UploadImage />
      </div>
    );
  }
}

UploadPage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default UploadPage;
