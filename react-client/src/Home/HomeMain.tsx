import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "src/Styles/style.css";
import "react-toastify/dist/ReactToastify.css";

import SearchUsers from "./SearchUsers";
import MainSearch from "./MainSearch";
import ProjectsList from "./ProjectsList";

export default class HomeMain extends Component {
  render() {
    return (
      <div>
        <div className="container-fluid main">
          <div className="row">
            <div className="col blue-box">
              <p id="home-header">!جاب اونجا خوب است</p>
              <p id="home-subheader">
                لورم ایپسوم متنی ساختگی با تولید سادگی نا مفهوم در صنعت چاپ و با
                استفاده از طراحان گرافیک است. چاپگر ها و متون
              </p>
              <MainSearch />
              <br />
              <br />
              <br />
              <br />
            </div>
          </div>
          <SearchUsers /> <ProjectsList />
        </div>
      </div>
    );
  }
}
