import React, {Component} from 'react';
import { withRouter } from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'
const axios = require('axios');
import  'react-router';
import UserId from '../Common/UserId';

class LoginForm extends Component<any, State>{
    constructor(props : any) {
        super(props);
        this.state = {
            result: [],
            username: "",
            password: ""
        };
    }

    handleUsernameChange = (event : any) => {
        this.setState({username: event.target.value});
    }

    handlePasswordchange = (event : any) => {
        this.setState({password: event.target.value});
    }

    handleSubmit = (event : any) => {
        event.preventDefault();
        let hasError: boolean = false;
        
        Object.values(this.state).map(value => {
            if(!value && hasError == false){
            toast.error("لطفا تمامی فیلد ها را وارد کنید")
            hasError = true
        }
        });
        if(hasError == false){
            let url = 'http://localhost:8080/login?id=';
            url += this.state.username +  "&password=" + this.state.password;
            console.log(url);
            axios.post(url)
                .then((response : any) => {
                    console.log(response)
                    localStorage.setItem("jwt", response.data.jwt.toString());
                    toast.success("ورود موفق");
                    const React = require('react-router');
                    console.log(React.version);
                    UserId.setId(this.state.username)
                    sessionStorage.setItem('username', this.state.username as string)
                    this.props.history.push("/home");
                    
                })
                .catch(function (error : any) {
                    console.log(error)
                    toast.error('نام کاربری یا رمز عبور اشتباه است');
            })
        }
    }

    componentDidMount(){
        var config = {
            headers: {'Authorization': "bearer " + localStorage.getItem('jwt')}
          };
        axios.get('http://localhost:8080/', config, config) .then((response : any) => {
            this.props.history.push("/home");
        })
        .catch((error : any) => {  
        })
    }

    render(){
        return(
            <div>
                <div className= "container-fluid main">
                    <div className="row">
                        <div className="col blue-box"><br></br> <br></br> <br></br> <br></br> <br></br></div>
                    </div>

                    <div className = "row">
                        <div className = "col-sm-12">
                            <div className = "registerlogin-container">
                                <p id = "registerlogin"> ورود به حساب کاربری </p>
                                <form className = "register-form">
                                    <label htmlFor="userName"><b>نام کاربری</b></label><br/>
                                    <input onChange = {this.handleUsernameChange} type="text" id="userName" placeholder = "نام کاربری"/><br/>
                                    <label htmlFor="password"><b>رمز عبور</b></label><br/>
                                    <input onChange = {this.handlePasswordchange} type="password" id="password" placeholder= "رمز عبور"/><br/>
                                    <button onClick = {this.handleSubmit} type = "button" className = "add-skill-button login-button" > ورود </button>
                                </form>
                                <a id = "enter-account-login" href = "register">ساخت حساب کاربری جدید</a> 
                            </div>
                        </div>
                    </div>
                </div>   
            </div>    
        
        );
    }
}
export default withRouter(LoginForm);

interface State{
    result : [],
    username : String,
    password : String   
}


