import React, { Component } from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import { Switch, Route } from 'react-router-dom';

import './App.css';

import HomePage from '../HomePage';
import LoginPage from '../LoginPage';
import ProfilePage from '../ProfilePage';
import SignUpPage from '../SignUpPage';
import UploadPage from '../UploadPage';

class App extends Component {
  constructor(props) {
    super(props);
    localStorage.setItem('isAuth', false);
  }

  render() {
    return (
      <React.Fragment>
        <CssBaseline />
        <Switch>
          <Route
            exact
            path="/"
            component={
              localStorage.getItem('isAuth') === 'true' ? HomePage : LoginPage
            }
          />
          <Route path="/login" component={LoginPage} />
          <Route
            exact
            path="/profile"
            component={
              localStorage.getItem('isAuth') === 'true'
                ? ProfilePage
                : LoginPage
            }
          />
          <Route path="/signup" component={SignUpPage} />
          <Route
            exact
            path="/upload"
            component={
              localStorage.getItem('isAuth') === 'true' ? UploadPage : LoginPage
            }
          />
        </Switch>
      </React.Fragment>
    );
  }
}

export default App;
