import React, { Component } from 'react';

import NavBar from '../../components/NavBar';
import UploadButton from '../../components/UploadButton';

class HomePage extends Component {
  render() {
    return (
      <div>
        <NavBar displaySearchBar />
        <UploadButton />
      </div>
    );
  }
}

export default HomePage;
