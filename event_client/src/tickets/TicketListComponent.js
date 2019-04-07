import React from 'react';
import { Card } from 'semantic-ui-react';

const TicketListComponent = (props) => {

    return (
        <div className="col-md-8">
            <Card.Group>
                <Card fluid color='red' header='Option 1'>
                    <Card.Content>
                        <Card.Header>Renginio bilietas</Card.Header>
                        <Card.Meta>{props.ticket.event.startDate}</Card.Meta>
                        <Card.Description>{props.ticket.event.name}</Card.Description>
                    </Card.Content>
                </Card>
                <br/>
            </Card.Group>
        </div>
    );
}

export default TicketListComponent;