import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';

import SearchBar from '../../components/SearchBar';

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
        <AppBar position="fixed" color="inherit">
          <Toolbar className={classes.toolBarContainer}>
            <IconButton
              className={classes.logoContainer}
              aria-label="logo"
              disableRipple={true}
            >
              <img alt="logo" src={this.props.logo} className={classes.logo} />
            </IconButton>
            <div className={classes.searchBarContainer}>
              <SearchBar />
            </div>
            <Button color="inherit" className={classes.button}>
              Sign In
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
  title: PropTypes.string
};

export default withStyles(styles)(NavBar);
