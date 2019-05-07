import React, { Component } from "react";
import { RouteComponentProps, withRouter } from "react-router";
import queryString from "query-string";
import Footer from "../Common/Footer";
import Header from "../Common/Header";
const axios = require("axios");
import { toast } from "react-toastify";

import "bootstrap/dist/css/bootstrap.min.css";
import "src/Styles/style.css";
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
      alreadyBade: "",
      projectId: "",
      title: "",
      description: "",
      imageURL: "",
      skills: [],
      budget: 0,
      deadline: 0,
      hasBade: ""
    };
  }
  componentDidMount() {
    const parsed = queryString.parse(this.props.location.search);

    let changeProjectState = (errorType: number) => {
      if (errorType == 404 || errorType == 403) {
        console.log("403 404");
        this.setState({ isProjectAvailable: "false" });
      }
    };

    this.setState({ projectId: parsed.id }, () => {
      var linktmp = "http://localhost:8080/project?id=";
      var link = linktmp.concat(this.state.projectId);
      axios
        .get(link)
        .then((response: any) => {
          let obj: any = JSON.parse(JSON.stringify(response.data));

          this.setState({ title: obj["title"] });
          this.setState({ description: obj["description"] });
          this.setState({ imageURL: obj["imageURL"] });
          this.setState({ skills: obj["skills"] });
          console.log("did mount project ");
          console.log(obj["skills"]);
          this.setState({ budget: obj["budget"] });
          this.setState({ deadline: obj["deadline"] });
          this.setState({ hasBade: obj["hasBade"] });
          //toast.success("اطلاعات با موفقیت کسب شد");
        })
        .catch(function(error: any) {
          toast.error(error.response.data["message"]);
          if (error.response.status == 404) {
            console.log("here eee 404");
            changeProjectState(404);
          }
          if (error.response.status == 403) {
            console.log("here eee 403");
            changeProjectState(403);
          }
          console.log(error.response.status);
          console.log(error.response.data["message"]);
        });
    });
  }

  render() {
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
  isProjectAvailable: any;
  alreadyBade: string;
  projectId: any;
  title: "";
  description: "";
  imageURL: "";
  skills: any[];
  budget: number;
  deadline: number;
  hasBade: any;
}
