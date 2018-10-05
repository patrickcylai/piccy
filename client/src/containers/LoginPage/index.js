import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import Fade from '@material-ui/core/Fade';

import logo from '../../images/logo.svg';

const styles = theme => ({
  root: {
    display: 'flex',
    height: '100vh',
    justifyContent: 'center',
    background:
      'linear-gradient(60deg, #845EC2 0%, #D65DB1 10%, #FF6F91 20%, #FF9671 50%, #FFC75F 95%, #F9F871 100%)'
  },
  titleContainer: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    textAlign: 'center',
    color: 'white'
  },
  logo: {
    height: '230px',
    marginBottom: '30px',
    filter: 'invert(100%)'
  }
});

class LoginPage extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <Fade in={true} timeout={3000}>
          <div className={classes.titleContainer}>
            <img className={classes.logo} src={logo} alt="logo" />
            <Typography color="inherit" variant="display2">
              Welcome to Piccy.
            </Typography>
            <Typography color="inherit" variant="subheading" gutterBottom>
              image sharing for everyone
            </Typography>
          </div>
        </Fade>
      </div>
    );
  }
}

LoginPage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(LoginPage);
