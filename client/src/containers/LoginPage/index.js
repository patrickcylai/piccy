import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';

import logo from '../../images/logo.svg';

const styles = theme => ({
  root: {
    display: 'flex',
    height: '100vh',
    justifyContent: 'center',
    background: 'linear-gradient(90deg, #2980B9 0%, #6DD5FA 50%, #FFFFFF 100%)'
  },
  titleContainer: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    textAlign: 'center'
  },
  logo: {
    height: '230px',
    marginBottom: '30px'
  }
});

class LoginPage extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <div className={classes.titleContainer}>
          <img className={classes.logo} src={logo} alt="logo" />
          <Typography variant="display2">Welcome to Piccy.</Typography>
          <Typography variant="subheading" gutterBottom>
            Image sharing for everyone
          </Typography>
        </div>
      </div>
    );
  }
}

LoginPage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(LoginPage);
