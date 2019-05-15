import React, { Component } from 'react'
import {RouteComponentProps, withRouter} from "react-router";
const axios = require('axios');
import queryString from 'query-string'

import UserComponentLogged from './UserComponentLogged'
import OtherUserComponent from './OtherUserComponent'
import UserId from '../Common/UserId'


export default class User extends Component<any,  State> {
  constructor(props : any) {
    super(props);
    this.state = {
        isLoggedInUser : "",
        userId : "",
        loading : "true",
    };
  }
  componentDidMount(){
    const parsed = queryString.parse(this.props.location.search); 
    let thisId = parsed.id
    let tokenId = ""
    this.setState({userId : parsed.id});
    var config = {
      headers: {'Authorization': "bearer " + localStorage.getItem('jwt')}
    };
    axios.get('http://localhost:8080/', config, config) .then((response : any) => {
      console.log(response.username)
      tokenId = response.username
  })
    .catch(function (error : any) {
      console.log(error)

    })
    console.log('id : ', sessionStorage.getItem('username'))
    if(thisId == sessionStorage.getItem('username')) 
      this.setState({isLoggedInUser: 'true'});
    else 
      this.setState({isLoggedInUser: 'false'});    
  }

  render() {
    return (
      <div id = "fill-view-port">
        {this.state.isLoggedInUser == 'true'  ?  <UserComponentLogged {...this.state}/> : null}
        {this.state.isLoggedInUser == 'false' ? <OtherUserComponent {...this.state}/> : null}
      </div>
    )
  }
}

interface State{
  isLoggedInUser : string,
  loading : string,
  userId : any
}
