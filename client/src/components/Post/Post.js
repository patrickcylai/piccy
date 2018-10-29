import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Typography from '@material-ui/core/Typography';
import TrendingUpIcon from '@material-ui/icons/TrendingUp';
import TrendingDownIcon from '@material-ui/icons/TrendingDown';
import { IconButton } from '@material-ui/core';

import { postFormDataApi } from '../../api/api';

const styles = theme => ({
  media: {
    height: 700,
    width: 700,
    maxWidth: '100%',
    maxHeight: '100%'
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    flexWrap: 'wrap'
  },
  card: {
    maxWidth: 700,
    margin: theme.spacing.unit * 3
  },
  likes: {
    borderLeft: 'thin solid #bfbfbf',
    padding: '0.5em'
  }
});

class Post extends Component {
  constructor(props) {
    super(props);
    this.state = {
      likes: 0,
      rated: false
    };

    this.handleLike = this.handleLike.bind(this);
    this.handleDislike = this.handleDislike.bind(this);
  }

  handleLike() {
    if (this.state.rated) {
      alert('Already rated post');
      return;
    }
    let formData = new FormData();
    formData.append('userid', localStorage.getItem('userid'));
    formData.append('isLike', 1);

    postFormDataApi(formData, '/post/' + this.props.postId + '/rate').then(
      json => {
        if (json !== null && json !== undefined) {
          let newLike = this.state.likes + 1;
          this.setState({ likes: newLike, rated: true });
        }
      }
    );
  }

  handleDislike() {
    if (this.state.rated) {
      alert('Already rated post');
      return;
    }
    if (this.state.likes === 0) {
      alert('Cannot Dislike a post with 0 likes');
      return;
    }
    let formData = new FormData();
    formData.append('userid', localStorage.getItem('userid'));
    formData.append('isLike', 0);

    postFormDataApi(formData, '/post/' + this.props.postId + '/rate').then(
      json => {
        if (json !== null && json !== undefined) {
          let newLike = this.state.likes - 1;
          this.setState({ likes: newLike, rated: true });
        }
      }
    );
  }

  componentDidMount() {
    this.setState({ likes: this.props.likes });
  }

  render() {
    const { classes } = this.props;

    return (
      <Card className={classes.card}>
        <CardMedia className={classes.media} image={this.props.image} />
        <div className={classes.header}>
          <CardHeader
            avatar={<Avatar>{this.props.avatar}</Avatar>}
            title={this.props.user}
            subheader={this.props.postDate}
          />
          <CardActions>
            <IconButton aria-label="Likes" onClick={this.handleLike}>
              <TrendingUpIcon />
            </IconButton>
            <IconButton aria-label="Dislikes" onClick={this.handleDislike}>
              <TrendingDownIcon />
            </IconButton>
            <Typography />
            <Typography variant="subtitle1" className={classes.likes}>
              {this.state.likes}
            </Typography>
            {/* <Typography>{this.props.dislikes}</Typography> */}
          </CardActions>
        </div>
        <CardContent>
          <Typography>{this.props.content}</Typography>
        </CardContent>
      </Card>
    );
  }
}

Post.propTypes = {
  classes: PropTypes.object.isRequired,
  avatar: PropTypes.string,
  user: PropTypes.string,
  postDate: PropTypes.string,
  image: PropTypes.any,
  content: PropTypes.string,
  likes: PropTypes.number,
  postId: PropTypes.number
  // dislikes: PropTypes.number
};

export default withStyles(styles)(Post);
