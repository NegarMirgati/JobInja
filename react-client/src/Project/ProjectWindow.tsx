import React, { Component } from "react";
import ProjectDescription from "./ProjectDescription";
import ProjectSkills from "./ProjectSkills";
import ProjectBidding from "./ProjectBidding";

export default class ProjectWindow extends Component {
  render() {
    return (
      <div className="container align-self-center position">
        <ProjectDescription {...this.state} />
        <ProjectSkills {...this.state} />
        <ProjectBidding {...this.state} />
      </div>
    );
  }
}
