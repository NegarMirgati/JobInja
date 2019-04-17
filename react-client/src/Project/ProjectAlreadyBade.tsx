import React, { Component } from "react";

export default class ProjectAlreadyBade extends Component {
  render() {
    return (
      <div className="row">
        <div className="resultsBorder col-sm-12  textPosition">
          <p className="resultPos">
            <span className="flaticon-check-mark" />
            شما قبلا پیشنهاد خود را ثبت کرده اید
          </p>
        </div>
      </div>
    );
  }
}
