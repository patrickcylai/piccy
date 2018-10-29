import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Zoom from '@material-ui/core/Zoom';

import Post from './Post';

const styles = theme => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column'
  }
});

class PostList extends Component {
  state = {
    checked: true
  };

  handleChange = () => {
    this.setState(state => ({ checked: !state.checked }));
  };

  render() {
    const { classes } = this.props;
    const { checked } = this.state;

    return (
      <div className={classes.root}>
        <Zoom in={checked}>
          <div>
            <Post
              avatar="P"
              user="patricklai.f"
              postDate="23 September 2018"
              content="Some inspirational quote here"
              likes={123}
              dislikes={10}
              image="https://instagram.fsyd4-1.fna.fbcdn.net/vp/02a206e90fff2a0e554d8079d62dc7ec/5C289412/t51.2885-15/e35/40662140_504347826697733_707036205279042603_n.jpg"
            />
          </div>
        </Zoom>
        <Zoom in={checked}>
          <div>
            <Post
              avatar="P"
              user="patricklai.f"
              postDate="23 September 2018"
              content="Some inspirational quote here"
              likes={123}
              dislikes={10}
              image="https://instagram.fsyd4-1.fna.fbcdn.net/vp/ae5d8e8af9245630e2c9f2bff05cf313/5C5EE31F/t51.2885-15/e35/39009777_317756425449814_4916197059016712194_n.jpg"
            />
          </div>
        </Zoom>
      </div>
    );
  }
}

PostList.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(PostList);
