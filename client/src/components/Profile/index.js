import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';

import ProfileImage from './ProfileImage';
import EditableInput from './EditableInput';

const styles = {
  headRow: {
    display: 'flex',
    justifyContent: 'center',
    marginTop: '130px'
  },
  contentRow: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: '20px'
  },
  styledLabel: {
    display: 'inline-block'
  },
  styledTag: {
    display: 'inline-block',
    paddingRight: '10px'
  },
  styledEmailTag: {
    marginLeft: '31px',
    paddingRight: '10px',
    display: 'inline-block'
  },
  styledEmail: {
    display: 'inline-block'
  },
  headerContent: {
    marginLeft: '10px'
  }
};
class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: this.props.username,
      password: this.props.password
    };
  }
  handleSaveUsername = (username: PropTypes.string) => {
    this.setState({
      username
    });
  };
  handleSavePassword = (password: PropTypes.string) => {
    this.setState({
      password
    });
  };
  render() {
    const { classes, firstName, lastName, email } = this.props;
    const { username, password } = this.state;
    return (
      <div>
        <div className={classes.headRow}>
          <ProfileImage />
          <div className={classes.headerContent}>
            <p>{username}</p>
            <p>
              {firstName} {lastName}
            </p>
          </div>
        </div>
        <div className={classes.contentRow}>
          <div>
            <div>
              <p className={classes.styledEmailTag}>email:</p>
              <div className={classes.styledEmail}> {email}</div>
            </div>
            <div>
              <p className={classes.styledTag}> {'username:'}</p>
              <EditableInput
                name="username"
                value={username}
                onSave={this.handleSaveUsername}
                className={classes.styledLabel}
              />
            </div>
            <div>
              <p className={classes.styledTag}> {'password:'} </p>
              <EditableInput
                name="password"
                value={password}
                onSave={this.handleSavePassword}
                className={classes.styledLabel}
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Profile.propTypes = {
  classes: PropTypes.object,
  username: PropTypes.string,
  firstName: PropTypes.string,
  lastName: PropTypes.string,
  email: PropTypes.string,
  password: PropTypes.string
};

export default withStyles(styles)(Profile);
