import React, { Component } from 'react';

import NavBar from '../../components/NavBar';
import UploadButton from '../../components/UploadButton';
import PostList from '../../components/Post/PostList';

class HomePage extends Component {
  render() {
    return (
      <div>
        <NavBar />
        <PostList />
        <UploadButton />
      </div>
    );
  }
}

export default HomePage;
