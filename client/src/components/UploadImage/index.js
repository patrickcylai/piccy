import React, { Component } from 'react';
import PropTypes from 'prop-types';
import className from 'classnames';
import ImageUploader from 'react-images-upload';

import { withStyles } from '@material-ui/core/styles';
import UploadButton from '../UploadButton';

const styles = {
  container: {
    height: '100vh',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center'
  }
};

class UploadImage extends Component {
  constructor(props) {
    super(props);
    this.handleOnChange = this.handleOnChange.bind(this);
  }
  //submit
  handleOnChange = picture => {
    console.log(picture);
  };
  render() {
    const { classes } = this.props;
    return (
      <div className={classes.container}>
        <ImageUploader
          fileContainerStyle={{
            background: 'transparent',
            borderRadius: 0,
            boxShadow: 'none'
          }}
          buttonStyles={{
            borderRadius: 2
          }}
          label={' '}
          withIcon={false}
          withPreview={true}
          singleImage={true}
          buttonText="Choose images"
          onChange={this.handleOnChange}
          imgExtension={['.jpg', '.gif', '.png', '.gif']}
          maxFileSize={5242880}
        />
      </div>
    );
  }
}

UploadImage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(UploadImage);
