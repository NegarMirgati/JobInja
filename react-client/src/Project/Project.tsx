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

class Project extends Component<RouteComponentProps<any>, State> {
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
    var linktmp = "http://localhost:8080/test/project?id=";
    var link = linktmp.concat(parsed.id as string);

    let changeProjectState = (errorType: number) => {
      if (errorType == 404 || errorType == 403 || errorType == 401) {
        console.log("403 404 401");
        this.setState({ isProjectAvailable: "false" });
      }
    };
    let directToLogin = () => {
      localStorage.removeItem('jwt')
      sessionStorage.removeItem('jwt')
      sessionStorage.removeItem('username')
      this.props.history.push("/login");
    };
    let directToHome = () => {
      this.props.history.push("/home");
    };
    var config = {
      headers: { Authorization: "bearer " + localStorage.getItem("jwt") }
    };
    axios
      .get(link, config, config)
      .then((response: any) => {
        console.log("success");
        let obj: any = JSON.parse(JSON.stringify(response.data));
        this.setState({ projectId: parsed.id });
        this.setState({ title: obj["title"] });
        this.setState({ description: obj["description"] });
        this.setState({ imageURL: obj["imageURL"] });
        this.setState({ skills: obj["skills"] });
        console.log("did mount project ");
        console.log(obj["skills"]);
        this.setState({ budget: obj["budget"] });
        this.setState({ deadline: obj["deadline"] });
        this.setState({ hasBade: obj["hasBade"] });
      })
      .catch(function(error: any) {
        toast.error(error.response.data["message"]);
        if (error.response.status == 404) {
          console.log("here eee 404");
          changeProjectState(404);
          directToHome();
        }
        if (error.response.status == 403) {
          console.log("here eee 403");
          changeProjectState(403);
          directToHome();
        }
        console.log(error.response.status);
        console.log(error.response.data["message"]);
        if (error.response.status == 401) {
          console.log("here eee 401");
          changeProjectState(401);
          directToLogin();
        }
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

export default withRouter(Project);

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
