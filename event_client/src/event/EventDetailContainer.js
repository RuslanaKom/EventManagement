import React from 'react';
import axios from "axios/index";

import EventDetailComponent from './EventDetailComponent';

class EventDetailContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            detailUrl: '',
            event: {},
        };
    }

    sendAxiosRequest = () => {
        axios.get(this.state.detailUrl)
            .then(response => this.setState({
                event: response.data,
            }));
    }

    componentDidMount() {
        const {url} = this.props.location.state;
        this.setState({
                detailUrl: url.href
            }, () => {
                this.sendAxiosRequest()
            }
        );
    }

    render() {
        return (
            <EventDetailComponent event={this.state.event}/>
        );
    }
}
    export default EventDetailContainer;