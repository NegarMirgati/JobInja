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
  componentDidMount(){
    var link = 'http://localhost:8080/users'
    axios.get(link)
    .then((response : any) => {
        this.setState({users : response.data});
        }
      )
      .catch(function (error : any) {
        console.log(error)
          toast.error('خطا در دریافت کاربران');
      })
  }

  getUserCards = () => {
    var userCardsJSX : JSX.Element[] = [];
    var numUsers = this.state.users.length;
    console.log(numUsers);

    for(var i = 0; i < numUsers ; i = i + 1){
      userCardsJSX.push(<UserCard {...this.state.users[i]}/>);
    }
    return userCardsJSX;
  }

  render() {
    return (
      <div>
        <div className = "row">
          <div className = "col">
            <div className = "row">
              <div className = "search-container-user">
                <form action = "">
                  <input type="text" placeholder="جستجو نام کاربر" name="search"/>
                </form>
              </div>
            </div>
          {this.getUserCards()}

        </div>
      </div>
    </div>
    )
  }
}

interface State{
  users : any[],
  id : ""
}


