import React, { Component } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'src/Styles/style.css'
import 'react-toastify/dist/ReactToastify.css';

export default class HomeMain extends Component {
  render() {
    return (
      <div>
        <div className= "container-fluid main">
            <div className="row">
                <div className="col blue-box">
                    <p id = "home-header"> !جاب اونجا خوب است</p>
                    <p id = "home-subheader">لورم ایپسوم متنی ساختگی با تولید سادگی نا مفهوم در صنعت چاپ و با استفاده از طراحان گرافیک اسن. چاپگر ها و متون</p>
                    <div className="search-container">
                        <form action="/action_page.php">
                            <input type="text" placeholder="جست و جو در جاب اونجا" name="search"/>
                            <button type="submit"><i className="fa fa-search"></i></button>
                        </form>
                    </div>
            </div>  
        </div> 
      </div>
    </div>
    )
  }
}
