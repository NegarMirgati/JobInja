import React, { Component } from "react";
import ProjectRemainingTime from "./ProjectRemainingTime";
import ProjectTimeOut from "./ProjectTimeOut";

import "bootstrap/dist/css/bootstrap.min.css";
import "src/Styles/style.css";
import ProjectWinner from "./ProjectWinner";

export default class ProjectDescription extends Component<any, State> {
  constructor(props: any) {
    super(props);
    this.state = {
      deadline: 0,
      timeOut: false,
      time: Date.now(),
      isBidingPossible: "",
      alreadyBade: "",
      title: "",
      description: "",
      imageURL: "",
      projectId: "",
      budget: 0
    };
  }

  static getDerivedStateFromProps(props, state) {
    if (
      props.description !== state.description ||
      props.projectId !== state.projectId ||
      props.title !== state.title ||
      props.budget !== state.budget ||
      props.imageURL !== state.imageURL ||
      // props.isBidingPossible !== state.isBidingPossible ||
      // props.timeOut !== state.timeOut ||
      props.deadline !== state.deadline
    ) {
      return {
        projectId: props.projectId,
        title: props.title,
        description: props.description,
        budget: props.budget,
        imageURL: props.imageURL,
        // isBidingPossible: props.isBidingPossible,
        // timeOut: state.props,
        deadline: props.deadline
      };
    }
    return null;
  }

  // componentDidMount() {
  //   // this.setState({ skills: this.props.skills });
  //   // console.log("did mount windows");
  //   // console.log(this.props.skills);

  // //  this.intervalID = setInterval(() => this.tick(), 1000);
  // }
  // componentWillUnmount() {
  //   clearInterval(this.intervalID);
  // }

  // tick = () => {
  //   this.setState({
  //     time: Date.now()
  //   });
  // };

  calcRemainingTime = () => {
    //  if (Date.now() < this.state.deadline) {
    console.log("calcRemainingTime");
    console.log(this.state.deadline);
    console.log("dddddddddiddd");
    //cons
    return this.state.deadline - Date.now();
    //  }
  };

  render() {
    return (
      <div className="projectBorder row">
        <div className="col-sm-12">
          <img
            className="project-photo img-responsive"
            src={this.state.imageURL}
            alt=""
          />
          <h2 className="textPosition projectTitle insideRowPos">
            {this.state.title}
          </h2>
          {Date.now() < this.state.deadline ? (
            <ProjectRemainingTime {...this.state} />
          ) : (
            <ProjectTimeOut />
          )}

          <p className="textPosition budget insideRowPos">
            <span className="flaticon-money-bag">
              بودجه: {this.state.budget}
            </span>
          </p>
          {Date.now() < this.state.deadline ? null : (
            <ProjectWinner {...this.state} />
          )}
          <h6 className="textPosition descriptionTitle insideRowPos">
            توضیحات
          </h6>
          <p className="textPosition description insideRowPos">
            {this.state.description}
          </p>
        </div>
      </div>
    );
  }
}

interface State {
  isBidingPossible: string;
  timeOut: boolean; //
  alreadyBade: string;
  title: ""; //
  description: ""; //
  imageURL: ""; //
  budget: number; //
  deadline: number; //
  projectId: any;
  time; //
}
