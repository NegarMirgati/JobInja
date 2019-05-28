import React, { Component } from "react";
import {RouteComponentProps, withRouter} from "react-router";
const axios = require('axios');
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import HomeMain from "./HomeMain";

class Home extends Component<any, any> {

  componentDidMount(){
    var config = {
      headers: {'Authorization': "bearer " + localStorage.getItem('jwt')}
    };
    axios.get('http://localhost:8080/test/', config, config) .then((response : any) => {
      console.log(response.username)
    })
    .catch((error : any) => {
      localStorage.removeItem('jwt');
      sessionStorage.removeItem('jwt');
      sessionStorage.removeItem('username');
      this.props.history.push("/login");
    })
  }
  render() {
    return (
      <div className="page-container">
        <Header />
        <div id="content-wrap">
          <HomeMain />
        </div>
        <Footer />
      </div>
    );
  }
}

export default withRouter(Home);
