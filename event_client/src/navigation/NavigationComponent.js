import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import '../App.css';

class NavigationComponent extends React.Component {
    handleClick = () => {
        return sessionStorage.clear();
    }

    render(){
        var manoBilietaiLink = <p/>
        var loginlogout = "";
        if(!sessionStorage.getItem("user")) {
            loginlogout =   <Nav className="ml-auto">
                    <Nav.Link href="/login" className="ml-auto">Prisijungti</Nav.Link>
                </Nav>
        }
        else {
            loginlogout =   <Nav className="ml-auto">
                <Nav.Link href='/events' onClick={this.handleClick} className="ml-auto">Atsijungti</Nav.Link>
            </Nav>;

            manoBilietaiLink=<Nav.Link href="/tickets">Mano bilietai</Nav.Link>
        }

    return (
            <Navbar className="mynav" variant="dark">
            <Navbar.Brand href="/events">Renginiai</Navbar.Brand>
            <Nav className="mr-auto">
                {manoBilietaiLink}
            </Nav>
                {loginlogout}
        </Navbar>
        );
    }
}
export default NavigationComponent;
