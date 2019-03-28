import React from 'react';
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import RegisterForm from "./RegisterForm"


class Register extends React.Component {
    render(){
        return(
            <div>
                <Header/>
                <RegisterForm/>
                <Footer/>
            </div>
        );
    }
}

export default Register;