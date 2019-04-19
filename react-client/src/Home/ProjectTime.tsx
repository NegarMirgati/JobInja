import React, { Component } from "react";

export default class ProjectTime extends Component<any, State> {
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
    let tmp = this.state.deadline - this.state.time;
    var arr = new Array();
    if (tmp > 0) {
      var day = Math.floor(tmp / 86400000);
      var remainder = tmp % 86400000;
      var hour = Math.floor(remainder / 3600000);
      var remainder1 = remainder % 3600000;
      var min = Math.floor(remainder1 / 60000);
      var remainder2 = remainder1 % 60000;
      var sec = Math.floor(remainder2 / 1000);

      if (day == 0 && hour != 0) {
        arr.push(hour + ":", min + ":", sec);
      } else if (day == 0 && hour == 0 && min != 0) {
        arr.push(min + ":", sec);
      } else if (day == 0 && hour == 0 && min == 0) {
        arr.push(sec);
      } else {
        arr.push(day + "day and ", hour + ":", min + ":", sec);
      }
    } else {
      arr.push("مهلت تمام شده");
    }
    return arr;
  };

  render() {
    return <div>{this.calcRemainigTime()}</div>;
  }
}

interface State {
  deadline: number;
  time: any;
}

interface Props {
  deadline: number;
}
