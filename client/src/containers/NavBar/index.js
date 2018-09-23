import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
  withStyles,
  MuiThemeProvider,
  createMuiTheme
} from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import SearchBar from '../../components/SearchBar';
import blue from '@material-ui/core/colors/blue';

const styles = theme => ({
  root: {
    flexGrow: 1
  },
  button: {
    margin: theme.spacing.unit
  },
  logoContainer: {
    display: 'flex',
    justifyContent: 'flex-start'
  },
  logo: {
    height: '42px'
  },
  searchBarContainer: {
    display: 'flex',
    flex: 1,
    justifyContent: 'center',
    marginLeft: '120px'
  }
});

const theme = createMuiTheme({
  palette: {
    primary: blue
  }
});

class NavBar extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <AppBar position="static" color="inherit">
          <Toolbar>
            <div className={classes.logoContainer}>
              <a href="/">
                <img
                  alt="logo"
                  src={this.props.logo}
                  className={classes.logo}
                />
              </a>
            </div>
            <div className={classes.searchBarContainer}>
              <SearchBar />
            </div>
            <Button color="inherit" className={classes.button}>
              Sign In
            </Button>
            <MuiThemeProvider theme={theme}>
              <Button
                variant="contained"
                color="primary"
                className={classes.button}
              >
                Sign up
              </Button>
            </MuiThemeProvider>
          </Toolbar>
        </AppBar>
      </div>
    );
  }
}

NavBar.propTypes = {
  classes: PropTypes.object.isRequired,
  title: PropTypes.string
};

export default withStyles(styles)(NavBar);
