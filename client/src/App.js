import React, { Component } from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';

import './App.css';

import NavBar from './containers/NavBar';
import logo from './images/logo.svg';
import PostList from './components/Post/PostList';

class App extends Component {
  render() {
    return (
      <React.Fragment>
        <CssBaseline />
        <NavBar title="piccy" logo={logo} />
        <div className="container-center">
          <PostList />
        </div>
      </React.Fragment>
    );
  }
}

export default App;
