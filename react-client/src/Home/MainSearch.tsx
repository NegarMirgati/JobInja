import React, { Component } from "react";
import { toast } from "react-toastify";

export default class MainSearch extends Component<any, State> {
  render() {
    return <div />;
  }

  submitForm(e: React.FormEvent<HTMLFormElement>): void {
    const { inputValue } = this.state;
    e.preventDefault();
    //toast.success(this.state.inputValue);
    // var link = "http://localhost:8080/users?q=";
    // link += this.state.inputValue;
    // console.log("searching for " + link);
    // axios
    //   .get(link)
    //   .then((response: any) => {
    //     this.setDataToState(response);
    //   })
    //   .catch(function(error: any) {
    //     console.log(error);
    //     toast.error("خطا در دریافت پروژه ها");
    //   });
    //  toast.success(this.state.projectId);
  }
  handleInputChange(e: React.ChangeEvent<HTMLInputElement>): void {
    this.setState({ inputValue: e.target.value });
  }
}

interface State {
  inputValue: string;
}
