import React, { Component } from "react";

import "bootstrap/dist/css/bootstrap.min.css";
import "src/Styles/style.css";

export default class ProjectDescription extends Component {
  render() {
    return (
      <div className="projectBorder row">
        <div className="col-sm-12">
          <img
            className="project-photo img-responsive"
            src="resources/images/famil.jpg"
            alt=""
          />
          <h2 className="textPosition projectTitle insideRowPos">
            پروژه طراحی سایت جاب اونجا
          </h2>
          <p className="textPosition timePos insideRowPos">
            <span className="flaticon-deadline" />
            زمان باقی مانده: 17 دقیقه و 25 ثانیه
          </p>
          <p className="textPosition budget insideRowPos">
            <span className="flaticon-money-bag" />
            بودجه: 2500 تومان
          </p>
          <h6 className="textPosition descriptionTitle insideRowPos">
            توضیحات
          </h6>
          <p className="textPosition description insideRowPos">
            تیم گیک لب (آزمایشگاه گیک) قصد دارد یک پروژه نظارتی امنیتی را
            برونسپاری کند. این پروژه شامل بخش های مختلفی است که نیاز به تخصص های
            ذکر شده داردما به دوستانی برنامه نویس هستند نیاز داریم نه کسانی که
            کد نویس هستند.
          </p>
        </div>
      </div>
    );
  }
}
