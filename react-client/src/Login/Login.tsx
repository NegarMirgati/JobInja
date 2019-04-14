import React from 'react';
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import LoginForm from './LoginForm';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'

class Login extends React.Component {
    render(){
        return(
            <div className = "page-container">
                <Header/>
                <div id="content-wrap">
                <LoginForm/>
                </div>
                <Footer/>
            </div>
        );
    }
}

export default Login;