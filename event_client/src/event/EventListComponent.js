import React from 'react';
import {Button} from 'semantic-ui-react';
import Card from 'react-bootstrap/Card'
import { Link } from 'react-router-dom';
import { Icon } from 'semantic-ui-react';
import OverlayTrigger from 'react-bootstrap/OverlayTrigger';
import Tooltip from 'react-bootstrap/Tooltip';
import '../App.css';

const EventLineComponent = (props) => {

    var urlForDetails = props.event.links.filter(l=>l.rel=='details')[0];
    var urlForFavourite = props.event.links.filter(l=>l.rel=='favourite')[0];
    var urlForBuying = props.event.links.filter(l=>l.rel=='buy')[0]? props.event.links.filter(l=>l.rel=='buy')[0]:null;
    var urlForFeedback = props.event.links.filter(l=>l.rel=='feedback')[0];

    var priceDiv=<p>Bilieto kaina: {props.event.event.basicPrice} Eur.</p>

    var buttonBuyDiv =
        <OverlayTrigger overlay={
            <Tooltip>
                Pirkti bilietą
            </Tooltip>
        }>
            <button onClick={()=>props.handleBuy(urlForBuying)} type="button" className="btn btn-link">
                <Icon color='blue' size='large' name='shopping cart'/>
            </button>
        </OverlayTrigger>;


    if(!urlForBuying){
        buttonBuyDiv=<p/>
        priceDiv=<p>Renginys nemokamas</p>;
    }
    var feedbackDiv=<p/>

    var sponsor = <p/>
    if (props.event.event.sponsors[0]){
        sponsor=  <Card.Text className="sponsor">
            Renginio remėjas: {props.event.event.sponsors[0].name}
        </Card.Text>
    }
    var isFavourite = false;
    if (props.userFavourites.filter(event => event.event.id == props.event.event.id)[0]) {
        isFavourite = true;
    }

    var favouriteButton = <p/>
    if (isFavourite) {
        favouriteButton =
            <OverlayTrigger overlay={
                <Tooltip>
                    Užmiršti
                </Tooltip>
            }>
                <button onClick={() => {props.handleFavouriteRemove(urlForFavourite);
                   document.activeElement.style.color="green"}}
                        type="button" className="btn btn-link">
                    <Icon id="faveouriteIcon" color='red' size='large' name='heart'/>
                </button>
            </OverlayTrigger>
    }
    else {
        favouriteButton =
            <OverlayTrigger overlay={
                <Tooltip>
                    Įsiminti
                </Tooltip>
            }>
                <button onClick={() => props.handleFavouriteAdd(urlForFavourite)}
                        type="button" className="btn btn-link">
                    <Icon id="faveouriteIcon" color='grey' size='large' name='heart'/>
                </button>
            </OverlayTrigger>
    }

    var currentDate = new Date();
    var eventDate = new Date(props.event.event.startDate);
    if (eventDate < currentDate) {
        buttonBuyDiv = <p/>
        priceDiv = <p/>;
        favouriteButton = <p/>
  //      if (sessionStorage.getItem("user")) {
            feedbackDiv = <Link to={{
                pathname: '/feedback',
                state: {
                    urlForFeedback: urlForFeedback,
                    eventName: props.event.event.name
                }
            }}>Atsiliepimai</Link>
  //      }
    }

    return (
    <Card className="text-center m-2">
        <Card.Header>{props.event.event.city}</Card.Header>
        <Card.Body>
            <Link to={{
                pathname:'/eventDetails',
                state: {
                    url: urlForDetails
                }
            }}>
                <Card.Title>{props.event.event.name}</Card.Title>
            </Link>
            <Card.Text>
                {props.event.event.description}
            </Card.Text>
            <Card.Text>
                {priceDiv}
            </Card.Text>
            {buttonBuyDiv}
            {favouriteButton}
            {feedbackDiv}
            {sponsor}
        </Card.Body>
        <Card.Footer className="text-muted">Renginio data: {props.event.event.startDate}</Card.Footer>
    </Card>
);
}
export default EventLineComponent;