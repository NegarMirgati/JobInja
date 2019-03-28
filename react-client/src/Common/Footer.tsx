import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'
class Footer extends Component{
    render(){
        return(
            <div className = "row">
                <div className = "col-sm-12">
                    <div className = "footer">
                        <div className = "container">
                            <p> تمامی حقوق این سایت متعلق به جاب اونجا می باشد © </p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default Footer;