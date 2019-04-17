import React, { Component } from "react";

export default class ProjectBidding extends Component {
  render() {
    return (
      <div className="row">
        <div className="resultsBorder col-sm-12  textPosition">
          <form className="bidForm">
            <label className="bidFormTitle">ثبت پیشنهاد</label>
            <div className="form-row align-items-center">
              <div className="col-auto">
                <div className="input-group mb-2 bidBoeder">
                  <input
                    type="text"
                    className="form-control"
                    id="bid"
                    placeholder="پیشنهاد خود را وارد کنید"
                  />
                  <div className="input-group-prepend">
                    <div className="input-group-text bidColor">تومان</div>
                  </div>
                </div>
              </div>
              <div className="col-auto">
                <button
                  type="submit"
                  className="btn btn-primary bidBtnColor mb-2"
                >
                  ارسال
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    );
  }
}
