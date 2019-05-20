import React from 'react';
import axios from 'axios';
import EventListComponent from './EventListComponent';
import Dropdown from '../navigation/Dropdown';
import SearchWindow from '../navigation/SearhWindow';
import {Redirect} from 'react-router-dom';
import MyEventCheckbox from '../user/MyEventsCheckbox';

class EventContainer extends React.Component {
     constructor(props) {
        super(props);
        this.state = {
            events: [],
            userFavourites:[],
            name: "",
            city: "",
            category: "",
            free: "",
            date: "",
        };
        this.updateSearchCriteria = this.updateSearchCriteria;
    }

    sendAxiosRequest = () => {
        axios.get('/events', {
            params: {
                name: this.state.name,
                city: this.state.city,
                category: this.state.category,
                isFree: this.state.free=="" ? (""):(this.state.free=="Nemokami"? true:false),
                future: this.state.date=="" ? (""):(this.state.date=="Busimi renginiai"? true:false),
            }
        })
            .then(response => this.setState({
                events:response.data
            }));
    }

    sendAxiosRequestForFavourites = () => {
        var id = sessionStorage.getItem("user");
        axios.get('/users/' + id + '/favourites')
            .then(response => this.setState({
                userFavourites: response.data,
            }))
    }
    componentDidMount() {
        this.sendAxiosRequest();
        if (sessionStorage.getItem("user")){
            this.sendAxiosRequestForFavourites()
        }
    }

    updateSearchCriteria = (text, param) => {
        this.setState({[param]: text},() => {
            this.sendAxiosRequest();
        });
    }

    handleBuy = (url) => {
        if (sessionStorage.getItem("user")) {
            axios({
                method: 'post',
                url: url.href,
                params: {
                    userId: sessionStorage.getItem("user")
                },
                headers: {'Content-Type': 'application/json;charset=utf-8'}
            })
                .then(()=>this.sendAxiosRequestForFavourites())
        }
        else {
            this.props.history.push("/login")
        }
    }

    handleFavouriteAdd = (url) => {
        if (sessionStorage.getItem("user")) {
            axios({
                method: 'put',
                url: url.href,
                params: {
                    userId: sessionStorage.getItem("user")
                },
                headers: {'Content-Type': 'application/json;charset=utf-8'}
            })

.then(()=>this.sendAxiosRequestForFavourites())
        }
        else {
            this.props.history.push("/login")
        }
    }

    handleFavouriteRemove = (url) => {
        if (sessionStorage.getItem("user")) {
            axios.delete(url.href, {
                params: {
                    userId: sessionStorage.getItem("user")
                }
            }).then(()=>this.sendAxiosRequestForFavourites())
        }
        else {
            this.props.history.push("/login")
        }
    }
    updateToUserFavourite = (value) => {
        if (value == "true") {
            var id = sessionStorage.getItem("user");
            axios.get('/users/' + id + '/favourites')
                .then(response => this.setState({
                    events: response.data,
                }))
        }
        else {
            this.sendAxiosRequest();
        }
    }

    render() {
        const eventList = this.state.events.map((event, index) => {
            return (
                <EventListComponent
                    key={index}
                    event={event}
                    handleBuy={this.handleBuy}
                    handleFavouriteAdd={this.handleFavouriteAdd}
                    handleFavouriteRemove={this.handleFavouriteRemove}
                    userFavourites={this.state.userFavourites}
                />
            );
        });

        var manoRenginiaiCheckbox = <p></p>
        if(sessionStorage.getItem("user")){
            manoRenginiaiCheckbox = <MyEventCheckbox updateToUserFavourite={this.updateToUserFavourite}/>
        }
        return (
            <div className="row col-md-12">
                <div>
                    <Dropdown updateSearchParameter={this.updateSearchCriteria} parameterName='city' placeholder='Visi miestai' fetchUrl={'/selections/cities'}/>
                </div>
                <div>
                    <Dropdown updateSearchParameter={this.updateSearchCriteria} parameterName='category' placeholder='Visos kategorijos' fetchUrl={'/selections/categories'}/>
                </div>
                <div>
                <Dropdown updateSearchParameter={this.updateSearchCriteria} parameterName='free' placeholder='Visi renginiai' fetchUrl={'/selections/free'}/>
                </div>
                <div>
                    <Dropdown updateSearchParameter={this.updateSearchCriteria} parameterName='date' placeholder='Visos datos' fetchUrl={'/selections/date'}/>
                </div>
                <div>
                    <SearchWindow updateSearchParameter={this.updateSearchCriteria} parameterName='name' placeholder='Visi renginiai'/>
                </div>
                <div className="offset-4">
                {manoRenginiaiCheckbox}
                </div>
                <div className="row col-md-12">
                    {eventList}
                </div>
            </div>
         );
    }
}

export default EventContainer;