import React, { Component } from "react";
import ProjectDescription from "./ProjectDescription";
import ProjectSkills from "./ProjectSkills";
import ProjectBidding from "./ProjectBidding";
import ProjectDeadlineReached from "./ProjectDeadlineReached";

export default class ProjectWindow extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      skills: [],
      projectId: "",
      deadline: 0,
      time: Date.now()
    };
  }

  static getDerivedStateFromProps(nextProps: Props, prevState: State) {
    console.log(" in gdsfp");
    console.log(nextProps.skills);
    if (
      nextProps.skills !== prevState.skills ||
      nextProps.projectId !== prevState.projectId ||
      nextProps.deadline !== prevState.deadline
    ) {
      return {
        skills: nextProps.skills,
        projectId: nextProps.projectId,
        deadline: nextProps.deadline
      };
    }
    // Return null if the state hasn't changed
    return null;
  }

  checkIfBidingIsPossible = () => {
    console.log("checkIfBidingIsPossible");
    console.log(this.props.deadline);
    if (Date.now() > this.state.deadline) {
      //  if (Date.now() > 1555697826506) {
      console.log("time out");
      return false;
    } else {
      return true;
    }
  };

  render() {
    console.log("in render window");
    console.log(this.state.skills);
    console.log(this.state.projectId);
    console.log(this.state.deadline);
    console.log(new Date(this.state.time));
    console.log(this.state.time);
    return (
      <div className="container align-self-center position">
        <ProjectDescription {...(this.state, this.props)} />
        <ProjectSkills {...this.state} />
        {this.checkIfBidingIsPossible() == true ? (
          <ProjectBidding {...this.state} />
        ) : (
          <ProjectDeadlineReached />
        )}
      </div>
    );
  }
}

interface State {
  skills: any[];
  projectId: any;
  deadline: number;
  time;
}

interface Props {
  isProjectAvailable;
  alreadyBade: string;
  projectId: any;
  title: "";
  description: "";
  imageURL: "";
  skills: any[];
  budget: number;
  deadline: number;
}
