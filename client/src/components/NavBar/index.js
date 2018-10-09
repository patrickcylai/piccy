import React, { Component } from 'react';
import PropTypes from 'prop-types';
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
  render() {
    const { classes, displaySearchBar } = this.props;

    return (
      <div className={classes.root}>
        <AppBar position="fixed" color="inherit">
          <Toolbar className={classes.toolBarContainer}>
            <IconButton
              className={classes.logoContainer}
              aria-label="logo"
              disableRipple={true}
            >
              <img alt="logo" src={logo} className={classes.logo} />
            </IconButton>
            <div className={classes.searchBarContainer}>
              <SearchBar displaySearchBar={displaySearchBar} />
            </div>
            <Button color="inherit" className={classes.button}>
              Profile
            </Button>
            <Button color="inherit" className={classes.button}>
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
  displaySearchBar: PropTypes.bool
};

export default withStyles(styles)(NavBar);
