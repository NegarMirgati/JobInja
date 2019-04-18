import React, { Component } from "react";
import { RouteComponentProps, withRouter } from "react-router";
import queryString from "query-string";
import Footer from "../Common/Footer";
import Header from "../Common/Header";
import { string } from "prop-types";
const axios = require("axios");
import LoadingOverlay from "react-loading-overlay";
import { toast } from "react-toastify";

import "bootstrap/dist/css/bootstrap.min.css";
import "src/Styles/style.css";
import User from "../User/User";
import ProjectCommon from "./ProjectCommon";
import ProjectWindow from "./ProjectWindow";

export default class Project extends Component<
  RouteComponentProps<any>,
  State
> {
  constructor(props: any) {
    super(props);
    this.state = {
      isProjectAvailable: true,
      isBidingPossible: "",
      timeOut: "",
      alreadyBade: "",
      projectId: "",
      title: "",
      description: "",
      imageURL: "",
      skills: [],
      bids: [],
      budget: 0,
      deadline: 0,
      loading: true
    };
  }
  componentDidMount() {
    const parsed = queryString.parse(this.props.location.search);
    //let thisId = parsed.id;
    //this.setState({ projectId: parsed.id });
    this.setState({ projectId: parsed.id }, () => {
      let changeProjectState = (errorType: number) => {
        if (errorType == 404 || errorType == 403) {
          console.log("khaaaaaaaaaaaaaaaaaaaaarrrrrr  403 404");
          this.setState({ isProjectAvailable: "false" });
        }
      };

      //    console.log(this.state.projectId);
      var linktmp = "http://localhost:8080/project?id=";
      var link = linktmp.concat(this.state.projectId);
      axios
        .get(link)
        .then((response: any) => {
          let obj: any = JSON.parse(JSON.stringify(response.data));
          //      console.log(obj);
          this.setState({ title: obj["title"] });
          this.setState({ description: obj["description"] });
          this.setState({ imageURL: obj["imageURL"] });
          this.setState({ bids: obj["bids"] });
          this.setState({ skills: obj["skills"] });
          console.log("did mount project ");
          console.log(obj["skills"]);
          this.setState({ budget: obj["budget"] });
          this.setState({ deadline: obj["deadline"] });
          this.setState({ loading: false });
          toast.success("اطلاعات با موفقیت کسب شد");
        })
        .catch(function(error: any) {
          toast.error(error.response.data["message"]);
          if (error.response.status == 404) {
            console.log("here eee 404");
            changeProjectState(404);
            //isProjectAccessible: string;
            isBidingPossible: false;
            //timeOut: string;
            // alreadyBade: string;
          }
          if (error.response.status == 403) {
            console.log("here eee 403");
            changeProjectState(403);
            //isProjectAccessible: string;
            isBidingPossible: false;
            //timeOut: string;
            // alreadyBade: string;
          }
          console.log(error.response.status);
          console.log(error.response.data["message"]);
        })
        .then(() => {
          this.setState({ loading: false });
        });
    });
  }

  render() {
    //document.body.classList.add("body-color");
    console.log(" in render of project");
    console.log(this.state.skills);
    return (
      <div className="page-container">
        <Header />
        <div id="content-wrap">
          <ProjectCommon />
          {this.state.isProjectAvailable == true ? (
            <ProjectWindow {...this.state} />
          ) : null}
        </div>
        <Footer />
      </div>
    );
  }
}

interface State {
  isProjectAvailable;
  //isProjectAccessible: string;
  isBidingPossible: string;
  timeOut: string;
  alreadyBade: string;
  projectId: any;
  title: "";
  description: "";
  imageURL: "";
  skills: any[];
  bids: any[];
  budget: number;
  deadline: number;
  loading;
}
