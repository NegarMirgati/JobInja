import React, { Component } from "react";
const axios = require("axios");
import { toast } from "react-toastify";
import ProjectInfo from "./ProjectInfo";

export default class ProjectsList extends Component<any, State> {
  constructor(props: any) {
    super(props);
    this.state = {
      projects: []
    };
  }
  componentDidMount() {
    var link = "http://localhost:8080/projects";
    axios
      .get(link)
      .then((response: any) => {
        this.setState({ projects: response.data });
      })
      .catch(function(error: any) {
        console.log(error);
        toast.error("خطا در دریافت پروژه ها");
      });
  }

  getProjectsInfo = () => {
    var projectJSX: JSX.Element[] = [];
    var numProjects = this.state.projects.length;
    console.log(numProjects);

    for (var i = 0; i < numProjects; i = i + 1) {
      projectJSX.push(<ProjectInfo {...this.state.projects[i]} />);
    }
    return projectJSX;
  };

  render() {
    return (
      <div>
        <div className="row">
          <div className="col">{this.getProjectsInfo()}</div>
        </div>
      </div>
    );
  }
}

interface State {
  projects: any[];
}
