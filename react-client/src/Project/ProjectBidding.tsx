import React, { Component } from "react";
import { toast } from "react-toastify";
import ProjectAlreadyBade from "./ProjectAlreadyBade";
const axios = require("axios");

export default class ProjectBidding extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      projectId: "",
      inputValue: "",
      bidingAlreadyDone: false
    };
  }

  static getDerivedStateFromProps(props, state) {
    if (props.projectId !== state.projectId) {
      return {
        projectId: props.projectId
      };
    }
    return null;
  }
  render() {
    return (
      <div>
        {this.state.bidingAlreadyDone == false ? (
          <div className="row">
            <div className="resultsBorder col-sm-12  textPosition">
              <form className="bidForm" onSubmit={e => this.submitForm(e)}>
                <label className="bidFormTitle">ثبت پیشنهاد</label>
                <div className="form-row align-items-center">
                  <div className="col-auto">
                    <div className="input-group mb-2 bidBoeder">
                      <input
                        type="text"
                        className="form-control"
                        id="bid"
                        onChange={e => this.handleInputChange(e)}
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
        ) : (
          <ProjectAlreadyBade />
        )}
      </div>
    );
  }

  submitForm(e: React.FormEvent<HTMLFormElement>): void {
    const { inputValue } = this.state;
    e.preventDefault();
    //  toast.success(this.state.inputValue);
    //  toast.success(this.state.projectId);

    var isnum = /^\d+$/.test(this.state.inputValue);
    if (isnum == true) {
      this.postBid();
    } else {
      toast.error("invalid input");
    }
  }
  handleInputChange(e: React.ChangeEvent<HTMLInputElement>): void {
    this.setState({ inputValue: e.target.value });
  }

  postBid = () => {
    var linktmp1 = "http://localhost:8080/project/bid?id=";
    var link2 = linktmp1.concat(this.state.projectId, "&amount=");
    var link = link2.concat(this.state.inputValue);

    var config = {
      headers: { Authorization: "bearer " + localStorage.getItem("jwt") }
    };
    axios
      .post(link, config, config)
      .then((response: any) => {
        let obj: any = JSON.parse(JSON.stringify(response.data));
        toast.success(response.data["status"]);
        console.log(response.data["status"]);
        this.setState({ bidingAlreadyDone: true });
      })
      .catch(function(error: any) {
        toast.error(error.response.data["message"]);
        console.log(error.response.status);
      });
  };
}

interface State {
  inputValue: string;
  projectId: string;
  bidingAlreadyDone: boolean;
}

interface Props {
  projectId: string;
}
