import React, { Component } from 'react'
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import HomeMain from "./HomeMain";

export default class Home extends Component {
  render() {
    return (
      <div className = "page-container">
      <Header/>
      <div id="content-wrap">
        <HomeMain/>
      </div>
      <Footer/>
  </div>
    )
  }
}
