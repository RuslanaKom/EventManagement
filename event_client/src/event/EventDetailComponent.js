import React from 'react'
import {Grid, Item, Image, Label, Segment,Header,Icon} from 'semantic-ui-react'
import Login from "../user/Login";
import OverlayTrigger from 'react-bootstrap/OverlayTrigger';
import Tooltip from 'react-bootstrap/Tooltip';
import { Link } from 'react-router-dom';

const EvantDetailComponent = (props) => {

    var agenda = <p/>;

    if (props.event.agenda) {
        agenda = props.event.agenda.map(a => {
            var time=a.time.substring(0, 5);
            var addressDiv='';
            if(a.address){
                console.log(a.address)
                addressDiv=(
                    <Label tag className="float-right">
                        {a.address.city} {a.address.street}  {a.address.building}-{a.address.apartment}
                    </Label>
                )
            }

            var pristato=<p/>

            if(a.presenters[0]){
                pristato=<p>Pristato: {a.presenters[0].firstName} {a.presenters[0].lastName}</p>;
            }

            return (
                <Segment raised>
                    <div className='row'>
                <Label as='a' color='olive' ribbon>
                    {a.date} {time}
                </Label>
                    <span> <h4>{a.name}</h4></span>
                    </div>
                    <br/>
                    <h6>{a.description}</h6>
                    {addressDiv}
                    {pristato}
                </Segment>
            )
        })
    }

    return (
        <div className="col-md-10 offset-1">
        <Segment color='olive'>
            <div className="row col-md-10 offset-4">
                <h2>{props.event.name}</h2>

                <OverlayTrigger overlay={
                    <Tooltip>
                        Atgal
                    </Tooltip>
                }>
                    <Link to='/events'>
                        <Icon name='reply' size='large' color='red' className="col-md-1 offset-12"/>
                    </Link>
                </OverlayTrigger>
            </div>
        <Grid columns={1}>
            <Grid.Column>
                    {agenda}
            </Grid.Column>
        </Grid>
        </Segment>

        </div>
            );
}
    export default EvantDetailComponent;
