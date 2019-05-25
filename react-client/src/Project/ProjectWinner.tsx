import React, { Component } from "react";
const axios = require("axios");
import { toast } from "react-toastify";

import "src/Styles/flaticon.css";
import { WSAEINVAL } from "constants";

export default class ProjectWinner extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      winner: "",
      projectId: ""
    };
  }

  static getDerivedStateFromProps(nextProps: Props, prevState: State) {
    console.log(" in gdsfp");
    console.log(nextProps.projectId);
    var winnerOfProject;
    if (nextProps.projectId !== prevState.projectId) {
      return {
        projectId: nextProps.projectId
      };
    }
    // Return null if the state hasn't changed
    return null;
  }
  componentDidUpdate() {
     if (this.state.winner == "" && this.state.projectId != "") {
      var linktmp = "http://localhost:8080/project?id=";
      var link = linktmp.concat(this.state.projectId);
      //toast.error(this.state.projectId);
      var config = {
        headers: { Authorization: "bearer " + localStorage.getItem("jwt") }
      };
      axios
        .get(link, config, config)
        .then((response: any) => {
          let obj: any = JSON.parse(JSON.stringify(response.data));
          //this.setState({ winner: obj["winner"] });
          this.setState({ winner: obj["winner"] });
          //toast.success(obj["winner"]);
          //toast.success("اطلاعات برنده موفقیت کسب شد");
          console.log(obj["winner"]);
        })
        .catch(function(error: any) {
          toast.error(error.response.data["message"]);
          //toast.error("خطا در برنده");
          //  rvalue = false;
        });
    }

    // return rvalue;
  }
  render() {
    //toast.success(this.state.winner);
    return (
      <div>
        <p className="textPosition winner insideRowPos">
          <span className="flaticon-check-mark" />
          برنده: {this.state.winner}
        </p>
      </div>
    );
  }
}
interface State {
  winner: any;
  projectId: any;
}

interface Props {
  projectId: any;
}
