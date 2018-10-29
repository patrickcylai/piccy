import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ImageUploader from 'react-images-upload';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

import { postFormDataApi } from '../../api/api';

const styles = {
  container: {
    height: '100vh',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center'
  },
  button: {
    // display: 'flex',
    width: '200px'
  }
};

class UploadImage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      picture: []
    };

    this.handleOnChange = this.handleOnChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  //submit
  handleOnChange = picture => {
    this.setState({ picture: picture });
  };

  handleSubmit() {
    let formData = new FormData();
    formData.append('userid', localStorage.getItem('userid'));
    formData.append('file', this.state.picture[0]);

    postFormDataApi(formData, '/post/create').then(json => {
      if (json !== null && json !== undefined) {
        alert('Image has been successfully uploaded');
      } else {
        alert('Image upload unsuccessful');
      }
    });
  }

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
          buttonText="Choose a piccy"
          onChange={this.handleOnChange}
          imgExtension={['.jpg', '.gif', '.png', '.gif']}
          maxFileSize={5242880}
        />
        <Button className={classes.button} onClick={this.handleSubmit}>
          Confirm Upload
        </Button>
      </div>
    );
  }
}

UploadImage.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(UploadImage);
