import React from 'react';
import axios from 'axios';
import TicketListComponent from './TicketListComponent';

export default class UserTicketsContainer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tickets: []
        };
    }
    sendAxiosRequest = () => {
        var id = sessionStorage.getItem("user")
        axios.get('/users/'+id+'/tickets')
            .then(response => this.setState({
                tickets: response.data,
            }));
    }

    componentDidMount() {
        this.sendAxiosRequest();
    }
    render() {
        const ticketList = this.state.tickets.map((ticket, index) => {
            return (
                <TicketListComponent
                    key={index}
                    ticket={ticket}
                />
            );
        });
        return (
            <div className="row col-md-12">
                 <div className="row col-md-12">
                    {ticketList}
                </div>
            </div>
        );
    }
}