import React, { Component } from 'react';
import PropTypes from 'prop-types';
import className from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import EditIcon from '@material-ui/icons/Edit';

import ProfileImage from './ProfileImage';
import EditableInput from './EditableInput';

const styles = {
  headRow: {
    display: 'flex',
    justifyContent: 'center',
    marginTop: '80px'
  },
  contentRow: {
    display: 'flex',
    justifyContent: 'center',
    marginTop: '20px'
  },
  styledparagraph: {
    display: 'inline-block'
  },
  styledEmail: {
    marginLeft: '32px'
  },
  headerContent: {
    marginLeft: '10px'
  }
};
class Profile extends Component {
  render() {
    const {
      classes,
      username,
      firstName,
      lastName,
      email,
      password
    } = this.props;
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
            <p className={classes.styledEmail}>email: {email}</p>
            <p>username:</p>
            <EditableInput name="username" value={username} />
            <p>password:</p>
            <EditableInput name="password" value={password} />
          </div>
        </div>
      </div>
    );
  }
}

Profile.propTypes = {
  classes: PropTypes.object.isRequired,
  username: PropTypes.string,
  firstName: PropTypes.string,
  lastName: PropTypes.string,
  email: PropTypes.string,
  password: PropTypes.string
};

export default withStyles(styles)(Profile);
