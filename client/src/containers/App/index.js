import React, { Component } from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import { Switch, Route } from 'react-router-dom';

import './App.css';

import HomePage from '../HomePage';
import LoginPage from '../LoginPage';
import ProfilePage from '../ProfilePage';

class App extends Component {
  render() {
    return (
      <React.Fragment>
        <CssBaseline />
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route path="/login" component={LoginPage} />
          <Route path="/profile" component={ProfilePage} />
        </Switch>
      </React.Fragment>
    );
  }
}

export default App;
