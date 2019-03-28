import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'
import logo from 'src/Assets/logo/logo v1.png';
class Header extends Component{
    render(){
        return(
            <nav className="site-header sticky-top py-1">
                <div className="container d-flex flex-column flex-md-row">  
                    <a className="d-inline-block h-100 p-1 "  href="#">خروج </a>
                    <a id = "account" className="d-inline-block h-100 p-1 " href="#">حساب کاربری</a>
                    <div className="d-inline h-100 p-1">
                        <div id = "homelogo"> <a href="home.html"><img className = "header-img" src = {logo} alt = "image"/> </a> </div>
                    </div>
                </div>
            </nav>
        );
    }
}
export default Header;