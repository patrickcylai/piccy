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

const styles = theme => ({
  media: {
    height: 600,
    width: 600,
    maxWidth: '100%',
    maxHeight: '100%'
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    flexWrap: 'wrap'
  },
  card: {
    maxWidth: 600,
    margin: theme.spacing.unit * 3
  },
  likes: {
    borderLeft: 'thin solid #bfbfbf',
    padding: '0.5em'
  }
});

class Post extends Component {
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
            <IconButton aria-label="Likes">
              <TrendingUpIcon />
            </IconButton>
            <IconButton aria-label="Dislikes">
              <TrendingDownIcon />
            </IconButton>
            <Typography />
            <Typography variant="subheading" className={classes.likes}>
              {this.props.likes}
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
  likes: PropTypes.number
  // dislikes: PropTypes.number
};

export default withStyles(styles)(Post);
