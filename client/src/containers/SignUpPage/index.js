import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

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
    alert();
  }

  handleChange(event) {
    this.setState({ [event.target.id]: event.target.value });
  }

  validatePassword() {
    if (this.state.password !== this.state.confirmPassword) {
    }
  }

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.rootContainer}>
        <Typography variant="h2">Sign Up</Typography>
        <form className={classes.formContainer} onSubmit={this.handleSubmit}>
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
          <Button type="submit">Create your account</Button>
        </form>
      </div>
    );
  }
}

SignUpPage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(SignUpPage);
