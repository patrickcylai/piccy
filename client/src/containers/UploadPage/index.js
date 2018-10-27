import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';

import NavBar from '../../components/NavBar';
import UploadImage from '../../components/UploadImage';

const styles = theme => ({});

class UploadPage extends Component {
  render() {
    const { classes } = this.props;

    return (
      <div>
        <NavBar displaySearchBar />
        <UploadImage />
      </div>
    );
  }
}

UploadPage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(UploadPage);
