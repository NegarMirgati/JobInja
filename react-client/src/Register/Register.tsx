import React from 'react';
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import RegisterForm from "./RegisterForm"


class Register extends React.Component {
    render(){
        return(
            <div className = "page-container">
            <Header/>
            <div id="content-wrap">
            <RegisterForm/>
            </div>
            <Footer/>
        </div>
        );
    }
}

export default Register;