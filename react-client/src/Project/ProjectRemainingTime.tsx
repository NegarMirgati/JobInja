import React, { Component } from "react";
import { array } from "prop-types";

export default class ProjectRemainingTime extends Component<any, State> {
  intervalID;
  constructor(props: Props) {
    super(props);
    this.state = {
      deadline: 0,
      time: Date.now()
    };
  }

  static getDerivedStateFromProps(props, state) {
    if (props.deadline !== state.deadline) {
      return {
        deadline: props.deadline
      };
    }
    return null;
  }

  componentDidMount() {
    // this.setState({ skills: this.props.skills });
    // console.log("did mount windows");
    // console.log(this.props.skills);

    this.intervalID = setInterval(() => this.tick(), 1000);
  }
  componentWillUnmount() {
    clearInterval(this.intervalID);
  }

  tick = () => {
    this.setState({
      time: Date.now()
    });
  };

  calcRemainigTime = () => {
    //if ( this.state.deadline > this.state.time){
    let tmp = this.state.deadline - this.state.time;
    var day = Math.floor(tmp / 86400000);
    var remainder = tmp % 86400000;
    var hour = Math.floor(remainder / 3600000);
    var remainder1 = remainder % 3600000;
    var min = Math.floor(remainder1 / 60000);
    var remainder2 = remainder1 % 60000;
    var sec = Math.floor(remainder2 / 1000);

    var arr = new Array();

    if (day == 0 && hour != 0) {
      arr.push(hour + " ساعت و ", min + " دقیقه و ", sec + " ثانیه  ");
    } else if (day == 0 && hour == 0 && min != 0) {
      arr.push(min + " دقیقه و ", sec + " ثانیه  ");
    } else if (day == 0 && hour == 0 && min == 0) {
      arr.push(sec + " ثانیه  ");
    } else {
      arr.push(
        day + " روز و ",
        hour + " ساعت و ",
        min + " دقیقه و ",
        sec + " ثانیه  "
      );
    }
    return arr;
  };

  render() {
    return (
      <div>
        <p className="textPosition timePos insideRowPos">
          <span className="flaticon-deadline">
            زمان باقی مانده: {this.calcRemainigTime()}
          </span>
        </p>
      </div>
    );
  }
}

interface State {
  deadline: number;
  time: any;
}

interface Props {
  deadline: number;
}
