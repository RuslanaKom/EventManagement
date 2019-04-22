import React from 'react';
import axios from 'axios';
import { Button, Comment, Form, Header } from 'semantic-ui-react'

export default class FeedbackComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            feedbackUrl: '',
            eventName:'',
            allFeedbacks:[],
            newFeedback: ''
        };
    }

    sendAxiosRequestToPostFeedbacks = () => {
        axios({
            method: 'post',
            url: this.state.feedbackUrl.href,
            headers: { "Accept": "application/json"},
            params: {
                feedback: this.state.newFeedback,
                userId: sessionStorage.getItem("user")
            }
        })
            .then(()=>this.sendAxiosRequestForAllFeedbacks())
            .then(() => this.setState({
                newFeedback: ''
            }));
    }

    sendAxiosRequestForAllFeedbacks = () => {
        axios.get(this.state.feedbackUrl.href)
            .then(response => this.setState({
            allFeedbacks: response.data,
        }));
    }

    componentDidMount() {
        const {urlForFeedback} = this.props.location.state;
        const {eventName} =  this.props.location.state;
        this.setState({
                feedbackUrl: urlForFeedback,
                eventName: eventName,
            }, () => {
                this.sendAxiosRequestForAllFeedbacks()
            }
        );
    }

    saveFeedback  = (value) => {
        this.sendAxiosRequestToPostFeedbacks();
    }

    handleChange = event => {
        this.setState({
            newFeedback: event.target.value
        });
    }

    render() {
        const feedbackList = this.state.allFeedbacks.map((feedback, index) => {
            return (
                <Comment>
                    <Comment.Avatar src='https://react.semantic-ui.com/images/avatar/small/matt.jpg' />
                    <Comment.Content>
                        <Comment.Author as='a'>{feedback.user.firstName}</Comment.Author>
                        <Comment.Text>{feedback.feedback}</Comment.Text>
                    </Comment.Content>
                </Comment>
            );
        });
        var reply = <p/>
        if (sessionStorage.getItem("user")) {
            reply= <Form reply >
                <Form.TextArea value={this.state.newFeedback} onChange={this.handleChange}/>
                <Button  onClick={()=>{this.saveFeedback()}} content='Add Reply' labelPosition='left' icon='edit' primary />
            </Form>
        }
        return (
            <div className="col-md-5 offset-3">
            <Comment.Group>
                <Header as='h3' dividing>
                    Atsiliepimai apie renginį {this.state.eventName}
                </Header>
                    {feedbackList}
                    {reply}
            </Comment.Group>
            </div>
        );
    }
}