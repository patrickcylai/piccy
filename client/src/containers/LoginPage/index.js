import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import Fade from '@material-ui/core/Fade';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';

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
  },
  formContainer: {
    display: 'flex',
    flexDirection: 'column'
  }
});

class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: ''
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit() {
    alert(
      'username: ' + this.state.username + ', password: ' + this.state.password
    );
  }

  handleChange(event) {
    this.setState({ [event.target.id]: event.target.value });
  }

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <Fade in={true} timeout={3000}>
          <div className={classes.titleContainer}>
            <img className={classes.logo} src={logo} alt="logo" />
            <Typography color="inherit" variant="h2">
              Welcome to Piccy.
            </Typography>
            <Typography color="inherit" variant="subtitle1" gutterBottom>
              image sharing for everyone
            </Typography>
            <form
              className={classes.formContainer}
              onSubmit={this.handleSubmit}
            >
              <TextField
                id="username"
                value={this.state.username}
                onChange={this.handleChange}
                label="Username"
                type="email"
              />
              <TextField
                id="password"
                value={this.state.password}
                onChange={this.handleChange}
                label="Password"
                type="password"
              />
              <br />
              <Button type="submit">Login</Button>
            </form>
            <br />
            <Typography color="inherit" variant="subtitle2">
              or
            </Typography>
            <br />
            <Button component={Link} to="/signup">
              Sign Up
            </Button>
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
