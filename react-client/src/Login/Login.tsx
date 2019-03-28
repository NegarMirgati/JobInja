import React from 'react';
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import LoginForm from './LoginForm';

class Login extends React.Component {
    render(){
        return(
            <div>
                <Header/>
                <LoginForm/>
                <Footer/>
            </div>
        );
    }
}

export default Login;