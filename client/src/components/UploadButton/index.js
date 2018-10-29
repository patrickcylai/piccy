import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import Button from '@material-ui/core/Button';
import AddIcon from '@material-ui/icons/Add';
import { withStyles } from '@material-ui/core/styles';

const styles = theme => ({
  button: {
    position: 'fixed',
    bottom: theme.spacing.unit * 2,
    right: theme.spacing.unit * 5,
    background: '#3f4257',
    color: 'lightgray',
    '&:hover': {
      color: '#3f4257'
    }
  }
});

class UploadButton extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div>
        <Button
          variant="fab"
          aria-label="Add"
          className={classes.button}
          component={Link}
          to="/upload"
        >
          <AddIcon />
        </Button>
      </div>
    );
  }
}

UploadButton.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(UploadButton);
