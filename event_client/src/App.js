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
                <Route  path="/events" component={EventContainer} />
                <Route  path="/login" component={Login} />
                <Route  path="/userregistration" component={UserRegistration} />
                <Route  path="/eventDetails" component={EventDetailContainer} />
                <Route  path="/tickets" component={TicketListContainer} />
                <Route  path="/feedback" component={FeedbackComponent} />
            </Switch>
            <Footer/>
            </div>
        </BrowserRouter>
    );
  }
}

export default App;
