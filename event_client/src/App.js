import React, { Component } from 'react';
import './App.css';
import './index.css';
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import NavigationComponent from './navigation/NavigationComponent';
import Footer from './navigation/Footer';
import EventContainer from './event/EventContainer';
import Login from './user/Login';
import UserRegistration from './user/Registration';
import EventDetailContainer from './event/EventDetailContainer';
import TicketListContainer from './tickets/UserTicketsContainer';
import FeedbackComponent from "./feedback/FeedackComponent";

class App extends Component {
  render(props) {
    return (
        <BrowserRouter>
            <div>
            <NavigationComponent/>
            <Switch>
                <Route exact path="/events" component={EventContainer} />
                <Route exact path="/login" component={Login} />
                <Route exact path="/userregistration" component={UserRegistration} />
                <Route exact path="/eventDetails" component={EventDetailContainer} />
                <Route exact path="/tickets" component={TicketListContainer} />
                <Route exact path="/feedback" component={FeedbackComponent} />
            </Switch>
            <Footer/>
            </div>
        </BrowserRouter>
    );
  }
}

export default App;
