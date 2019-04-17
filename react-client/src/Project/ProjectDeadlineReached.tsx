import React, { Component } from "react";

export default class ProjectDeadlineReached extends Component {
  render() {
    return (
      <div className="row">
        <div className="resultsBorder col-sm-12  textPosition">
          <p className="resultNeg">
            <span className="flaticon-danger" />
            مهلت ارسال پیشنهاد برای این پروژه به اتمام رسیده است
          </p>
        </div>
      </div>
    );
  }
}
