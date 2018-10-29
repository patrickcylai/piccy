import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

import { postFormDataApi } from '../../api/api';

const styles = theme => ({
  rootContainer: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginTop: '80px'
  },
  formContainer: {
    display: 'flex',
    width: '400px',
    flexDirection: 'column',
    marginTop: '20px'
  },
  passwordContainer: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: '10px'
  }
});

class SignUpPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: '',
      password: '',
      confirmPassword: '',
      username: '',
      validPassword: false
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleSubmit() {
    let validPass = this.validatePassword();

    if (validPass) {
      let formData = new FormData();
      formData.append('email', this.state.email);
      formData.append('password', this.state.password);
      formData.append('username', this.state.username);

      postFormDataApi(formData, '/register').then(json => {
        let res = json;
        if (res.success) {
          console.log('registration success');
          alert('You have successfully created your account!');
          this.props.history.push('/login');
        } else {
          alert(res.error);
        }
      });
    } else {
      alert("Passwords don't match");
    }
  }

  handleChange(event) {
    this.setState({ [event.target.id]: event.target.value });
  }

  validatePassword() {
    if (this.state.password !== this.state.confirmPassword) {
      return false;
    } else {
      return true;
    }
  }

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.rootContainer}>
        <Typography variant="h2">Sign Up</Typography>
        <div className={classes.formContainer}>
          <TextField
            id="email"
            label="Email"
            type="email"
            value={this.state.email}
            onChange={this.handleChange}
          />
          <div className={classes.passwordContainer}>
            <TextField
              id="password"
              label="Password"
              type="password"
              value={this.state.password}
              onChange={this.handleChange}
            />
            <TextField
              id="confirmPassword"
              label="Confirm Password"
              type="password"
              value={this.state.confirmPassword}
              onChange={this.handleChange}
            />
          </div>
          <br />
          <TextField
            id="username"
            label="Username"
            type="text"
            value={this.state.username}
            onChange={this.handleChange}
          />
          <br />
          <Button type="submit" onClick={this.handleSubmit}>
            Create your account
          </Button>
        </div>
      </div>
    );
  }
}

SignUpPage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(SignUpPage);
