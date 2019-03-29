import React, { Component } from 'react'
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import UserComponent from './UserComponent'
export default class User extends Component {
  render() {
    return (
      <div>
           <Header/>
           <UserComponent/>
           <Footer/>
        
      </div>
    )
  }
}
