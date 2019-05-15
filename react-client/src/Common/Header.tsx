import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'
import logo from 'src/Assets/logo/logo v1.png';
class Header extends Component{
    deleteJWT = (event : any) => {
        console.log('here')
        localStorage.removeItem('jwt')
        sessionStorage.removeItem('jwt')
        sessionStorage.removeItem('username')
    }
    getAccoutLink = () => {
        return "user?id=" + sessionStorage.getItem('username')  as string   
    }
    render(){
        return(
            <nav className="site-header sticky-top py-1">
                <div className="container d-flex flex-column flex-md-row">  
                    <a className="d-inline-block h-100 p-1 " onClick = {this.deleteJWT}  href="login">خروج </a>
                    <a id = "account" className="d-inline-block h-100 p-1 " href = {'/user?id=' + sessionStorage.getItem('username')}  >حساب کاربری</a>
                    <div className="d-inline h-100 p-1" id = "test"> 
                        <div id = "homelogo"> <a href="home"><img className = "header-img" src = {logo} alt = "image"/> </a> </div>
                    </div>
                </div>
            </nav>
        );
    }
}
export default Header;