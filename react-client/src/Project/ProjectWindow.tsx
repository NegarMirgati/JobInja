import React, { Component } from "react";
import ProjectDescription from "./ProjectDescription";
import ProjectSkills from "./ProjectSkills";
import ProjectBidding from "./ProjectBidding";

export default class ProjectWindow extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      skills: []
    };
  }

  static getDerivedStateFromProps(nextProps: Props, prevState: State) {
    console.log(" in gdsfp");
    console.log(nextProps.skills);
    if (nextProps.skills !== prevState.skills) {
      return {
        skills: nextProps.skills
      };
    }
    // Return null if the state hasn't changed
    return null;
  } /*
  componentDidUpdate(prevProps: Props, prevState: State) {
    console.log(" in cdu");
    console.log(prevProps.skills);
    if (prevProps.skills  !==  prevState.skills) {
      return {
        skills: prevProps.skills
      };
    }
    return null;
  }
  */
  /*
  componentDidMount() {
    this.setState({ skills: this.props.skills });
    console.log("did mount windows");
    console.log(this.props.skills);
  }
*/
  checkIfBidingIsPossible = () => {};
  render() {
    console.log("in render window");
    console.log(this.state.skills);
    return (
      <div className="container align-self-center position">
        <ProjectDescription {...this.state} />
        <ProjectSkills {...this.state} />
        <ProjectBidding {...this.state} />
      </div>
    );
  }
}

interface State {
  skills: any[];
}

interface Props {
  isProjectAvailable;
  // isProjectAccessible;
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
  loading: boolean;
}
