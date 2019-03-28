import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'

class LoginForm extends Component{
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
        console.log('user changed');
    }

    handlePasswordchange = (event : any) => {
        this.setState({password: event.target.value});
        console.log('pass changed');
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
                                    <button type = "button" className = "add-skill-button login-button" > ورود </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>   
            </div>    
        
        );
    }
}
export default LoginForm;