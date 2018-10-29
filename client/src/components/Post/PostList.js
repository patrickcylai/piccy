import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Zoom from '@material-ui/core/Zoom';

import { getApi } from '../../api/api';
import Post from './Post';

const styles = theme => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    marginTop: '80px',
    alignItems: 'center'
  }
});

class PostList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      loaded: false,
      posts: []
    };
  }

  componentDidMount() {
    getApi('/posts/all').then(json => {
      this.setState({ posts: json });
      this.setState({ loaded: true });
    });
  }

  handleChange = () => {
    this.setState(state => ({ checked: !state.checked }));
  };

  render() {
    const { classes } = this.props;
    const { loaded, posts } = this.state;

    return (
      <div className={classes.root}>
        {posts.map(post => (
          // let postDate = new Date(post.post.creationDate);
          // let options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
          // console.log(postDate.toLocaleDateString('en-AU', options));

          <Zoom in={loaded}>
            <div>
              <Post
                avatar={post.post.userId}
                user={post.post.userId}
                postDate={new Date(post.post.creationDate).toLocaleDateString(
                  'en-AU',
                  {
                    weekday: 'long',
                    year: 'numeric',
                    month: 'long',
                    day: 'numeric'
                  }
                )}
                // content="Some inspirational quote here"
                likes={post.rating.likes - post.rating.dislikes}
                // dislikes={post.rating.dislikes}
                image={'http://localhost:8080/images/' + post.post.imageRef}
                postId={post.post.id}
              />
            </div>
          </Zoom>
        ))}
      </div>
    );
  }
}

PostList.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(PostList);
