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
      isUserAllowed: "",
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
      console.log(this.state.projectId);
      var linktmp = "http://localhost:8080/project?id=";
      var link = linktmp.concat(this.state.projectId);
      axios
        .get(link)
        .then((response: any) => {
          let obj: any = JSON.parse(JSON.stringify(response.data));
          console.log(obj);
          this.setState({ title: obj["title"] });
          this.setState({ description: obj["description"] });
          this.setState({ imageURL: obj["imageURL"] });
          this.setState({ bids: obj["bids"] });
          this.setState({ skills: obj["skills"] });
          this.setState({ budget: obj["budget"] });
          this.setState({ deadline: obj["deadline"] });
          this.setState({ loading: false });
          toast.success("اطلاعات با موقیت کسب شد");
        })
        .catch(function(error: any) {
          toast.error("اتصال با سرور با خطا مواجه شد");
        })
        .then(() => {
          this.setState({ loading: false });
        });
    });
  }

  render() {
    //document.body.classList.add("body-color");
    return (
      <div className="page-container">
        <Header />
        <div id="content-wrap">
          <ProjectCommon {...this.state} />
          <ProjectWindow {...this.state} />
        </div>
        <Footer />
      </div>
    );
  }
}

interface State {
  isUserAllowed: string;
  projectId: any;
  title: "";
  description: "";
  imageURL: "";
  skills: any[];
  bids: any[];
  budget: number;
  deadline: number;
  loading: boolean;
}
