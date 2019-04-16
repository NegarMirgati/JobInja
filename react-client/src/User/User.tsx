import React, { Component } from 'react'
import {RouteComponentProps, withRouter} from "react-router";
import queryString from 'query-string'
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import UserComponentLogged from './UserComponentLogged'
import OtherUserComponent from './OtherUserComponent'
export default class User extends Component<RouteComponentProps<any>,  State> {
  constructor(props : any) {
    super(props);
    this.state = {
        isLoggedInUser : "",
        userId : ""
    };
}
  componentDidMount(){
    const parsed = queryString.parse(this.props.location.search);
    console.log(parsed)  
    let thisId = parsed.id
    this.setState({userId : parsed.id});

    if(thisId == '1') 
      this.setState({isLoggedInUser: 'true'});
    else 
      this.setState({isLoggedInUser: 'false'});  
  }

  render() {
    return (
      <div id = "fill-view-port">
          <Header/>
           { this.state.isLoggedInUser == 'true'? <UserComponentLogged {...this.state}/> : null}
            {this.state.isLoggedInUser == 'false'? <OtherUserComponent {...this.state}/> : null}
          <Footer/>
      </div>
    )
  }
}

interface State{
  isLoggedInUser : string 
  userId : any
}
