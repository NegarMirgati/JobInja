import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import slide6 from 'src/Assets/images/slide6.jpg';
import slide2 from 'src/Assets/images/slide2.jpg';
import slide8 from 'src/Assets/images/slide8.jpg';
import slide7 from 'src/Assets/images/slide7.jpg';
import slide5 from 'src/Assets/images/slide5.jpg';

class RegisterForm extends Component<Props, State>{
    constructor(props : any, state : State) {
        super(props);
        this.state = {
            result: [],
            name : "",
            lastname : "",
            username: "",
            password: "",
            job : "",
            bio : "",
            proLink : "",
            confirmPassword : ""
        };
    }

    handleInputChange = (event : any) => {
        switch(event.target.id){
            case "name" : 
                this.setState({name: event.target.value});
                break;
            case "lastname":
                this.setState({lastname: event.target.value});
                break;
            case 'username' :
                this.setState({username : event.target.value});
                break;        
            case 'password': 
                this.setState({password: event.target.value});
                break;
            case 'job': 
                this.setState({job: event.target.value});
                break;
            case 'bio':
                this.setState({bio: event.target.value});
                break;
            case 'proLink':
                this.setState({proLink: event.target.value});
                break;
            case 'confirmPassword' :
                this.setState({confirmPassword: event.target.value});
                break;
            default :
                console.log('oops')
                break;
        }
    }

    handleSubmit = (event : any) => {
        event.preventDefault();
        const {password, confirmPassword} = this.state;
        let hasError: boolean = false;
        
        Object.values(this.state).map(value => {
            if(!value && hasError == false){
            toast.error("لطفا تمامی فیلد ها را وارد کنید")
            hasError = true
        }
        });

        if(password.length < 6){
            toast.error("رمز عبور باید دارای حداقل ۶ حرف باشد");
            hasError = true
        }
        if (password !== confirmPassword) {
            toast.error("عدم همخوانی رمز عبور");
            hasError = true
        } 
        if(hasError == false){
            toast.success("ثبت نام موفق");
            // TODO : make API call
        }
    }

    render(){
        return(
            <div>
                <div className= "container-fluid main">
                    <div className="row">
                        <div className="col blue-box"><br></br> <br></br> <br></br> <br></br> <br></br> <br></br></div>
                    </div>
                    <div className = "row">
                        <div id="slider">
                            <figure>
                                <img className="img-rounded slide-img img-responsive" src={slide6} alt = "image" />
                                <img className="img-rounded slide-img img-responsive" src={slide2} alt = "image" />
                                <img className="img-rounded slide-img img-responsive" src={slide8} alt = "image" />
                                <img className="img-rounded slide-img img-responsive" src={slide7} alt = "image" />
                                <img className="img-rounded slide-img img-responsive" src={slide5} alt = "image" />
                            </figure>
                        </div>
                    </div>

                    <div className = "row">
                        <div className = "col-sm-12">
                            <div className = "registerlogin-container register-container">
                            <p id = "registerlogin"> ساخت حساب کاربری جدید </p>
                            <form className = "register-form">
                                    <label htmlFor="firstName"><b>نام</b></label><br/> 
                                    <input onChange = {this.handleInputChange} type="text" id="name" placeholder="نام"/><br/>
                                    <label htmlFor="lastName"><b>نام خوانوادگی</b></label><br/>
                                    <input onChange = {this.handleInputChange}  type="text" id="lastname" placeholder = "نام خانوادگی"/><br/>
                                    <label htmlFor="userName"><b>نام کاربری</b></label><br/>
                                    <input onChange = {this.handleInputChange} type="text" id="username" placeholder = "نام کاربری"/><br/>
                                    <label htmlFor="password"><b>رمز عبور</b></label><br/>
                                    <input onChange = {this.handleInputChange} type="password" id="password" placeholder= "رمز عبور"/><br/>
                                    <label htmlFor="confirmPassword"><b>تکرار رمز عبور</b></label><br/>
                                    <input onChange = {this.handleInputChange} type="password" id="confirmPassword" placeholder= "تکرار رمز عبور"/><br/>
                                    <label htmlFor="job"><b>عنوان شغلی</b></label><br/>
                                    <input onChange = {this.handleInputChange} type="text" id="job" placeholder = "عنوان شغلی"/><br/>
                                    <label htmlFor="bio"><b>بیو</b></label><br/>
                                    <input onChange = {this.handleInputChange} type="text" id="bio" placeholder = "بیو"/><br/>
                                    <label htmlFor="proLink"><b>لینک تصویر پروفایل</b></label><br/>
                                    <input onChange = {this.handleInputChange} type="text" id="proLink" placeholder = "لینک تصویر پروفایل"/><br/><br/>
                                    <button onClick = {this.handleSubmit } type = "button" className = "add-skill-button" > ثبت نام </button>
                                </form>
                                
                                <a id = "enter-account-login" href = "login">ورود به حساب کاربری</a> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default RegisterForm;

interface State{
    result : [],
    name : string,
    lastname : string,
    username: string,
    password: string,
    job : string,
    bio : string,
    proLink : string,
    confirmPassword : string    
}

interface Props{}
