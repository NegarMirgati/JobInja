import React, { Component } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';
import UserCard from './UserCard';

export default class SearchUsers extends Component<any, State> {
  constructor(props : any) {
    super(props);
    this.state = {
      users :  [],
      id : ""
    };
  }

  setDataToState = (response : any) => {
    var users : any[] = [];
    if(response.data.length != 0){
      var key = Object.keys(response.data[0])[0]
      for(var i = 0; i < response.data.length; i++){
        var key = Object.keys(response.data[i])[0]
        console.log('here', response.data[i][key])  
        var link = "/user?id=" + response.data[i][key].id;
        var user = {
          id : response.data[i][key].id,
          name : response.data[i][key].name,
          lastname : response.data[i][key].lastname,
          proLink : response.data[i][key].proLink,
          jobTitle : response.data[i][key].jobTitle,
          linkToProPage  : link   
        } 
        console.log('u', user)
        users.push(user);
      }
    }
    this.setState({ users: users});
}

  componentDidMount(){
    var link = 'http://localhost:8080/test/users?q='
    axios.get(link)
    .then((response : any) => {
        this.setDataToState(response)
        })
      .catch(function (error : any) {
        console.log(error)
          toast.error('خطا در دریافت کاربران');
      })
  }

  handleChangeinput = (event : any) => {
    var link = 'http://localhost:8080/test/users?q='
    link += event.target.value;
    console.log('searching for ' + link);
    axios.get(link)
    .then((response : any) => {
      this.setDataToState(response)
    })
      .catch(function (error : any) {
        console.log(error)
          toast.error('خطا در دریافت کاربران');
      })

  }

  getUserCards = () => {
    var userCardsJSX : JSX.Element[] = [];
    var numUsers = this.state.users.length;
    for(var i = 0; i < numUsers ; i = i + 1){

      var key = Object.keys(this.state.users[i])[0]
      console.log('geteeeee', this.state.users[i][key])     
      userCardsJSX.push(<UserCard id = {this.state.users[i][key].id}  {...this.state.users[i]}/>); 
      
    }
    return userCardsJSX;
  }

  render() {
    return (
          <div>
            <div className = "row">
              <div className = "search-container-user">
                <form action = "">
                  <input onChange = {this.handleChangeinput} type="text" placeholder="جستجو نام کاربر" name="search"/>
                </form>
              </div>
            </div>
          {this.getUserCards()}
        </div>
    )
  }
}

interface State{
  users : any[],
  id : ""
}


