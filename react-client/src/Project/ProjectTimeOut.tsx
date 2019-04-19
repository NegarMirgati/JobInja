import React, { Component } from "react";

import "bootstrap/dist/css/bootstrap.min.css";
import "src/Styles/style.css";

export default class ProjectTimeOut extends Component {
  render() {
    return (
      <div>
        <p className="textPosition timeNeg insideRowPos">
          <span className="flaticon-deadline" />
          مهلت تمام شده
        </p>
      </div>
    );
  }
}
