import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link, withRouter } from 'react-router-dom';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';

import SearchBar from './SearchBar';

import logo from '../../images/logo.svg';

const styles = theme => ({
  root: {
    flexGrow: 1
  },
  button: {
    margin: theme.spacing.unit
  },
  logoContainer: {
    display: 'flex'
  },
  logo: {
    height: '42px'
  },
  searchBarContainer: {
    display: 'flex',
    flex: 1,
    justifyContent: 'center',
    marginLeft: '120px'
  },
  toolBarContainer: {
    display: 'flex',
    flexWrap: 'wrap',
    alignItems: 'center'
  }
});

class NavBar extends Component {
  constructor(props) {
    super(props);

    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogout() {
    this.props.history.push('/login');
    localStorage.clear();
  }

  render() {
    const { classes, displaySearchBar, displayProfile } = this.props;

    return (
      <div className={classes.root}>
        <AppBar position="fixed" color="inherit">
          <Toolbar className={classes.toolBarContainer}>
            <IconButton
              className={classes.logoContainer}
              aria-label="logo"
              disableRipple={true}
              component={Link}
              to="/"
            >
              <img alt="logo" src={logo} className={classes.logo} />
            </IconButton>
            <div className={classes.searchBarContainer}>
              <SearchBar displaySearchBar={displaySearchBar} />
            </div>
            {displayProfile ? (
              <Button
                color="inherit"
                className={classes.button}
                component={Link}
                to="/"
              >
                Home
              </Button>
            ) : (
              <Button
                color="inherit"
                className={classes.button}
                component={Link}
                to="/profile"
              >
                Profile
              </Button>
            )}
            <Button
              onClick={this.handleLogout}
              color="inherit"
              className={classes.button}
            >
              Log Out
            </Button>
          </Toolbar>
        </AppBar>
      </div>
    );
  }
}

NavBar.propTypes = {
  classes: PropTypes.object.isRequired,
  displaySearchBar: PropTypes.bool,
  displayProfile: PropTypes.bool
};

export default withRouter(withStyles(styles)(NavBar));
