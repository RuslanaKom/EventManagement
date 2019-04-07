import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './index.css';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import NavigationComponent from './navigation/NavigationComponent';
import Footer from './navigation/Footer';
import EventContainer from './event/EventContainer';

ReactDOM.render(
        <App />, document.getElementById('root'));
serviceWorker.unregister();
