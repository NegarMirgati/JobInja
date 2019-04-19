import React, { Component } from "react";

export default class ProjectSkills extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = {
      skills: []
    };
  }

  static getDerivedStateFromProps(nextProps: Props, prevState: State) {
    if (nextProps.skills !== prevState.skills) {
      return {
        skills: nextProps.skills
      };
    }
    // Return null if the state hasn't changed
    return null;
  }

  createSkills = (): any => {
    var keys: string[] = [];
    for (var i = 0; i < this.state.skills.length; i++) {
      Object.keys(this.state.skills[i]).map(key => {
        const value = this.state.skills[i][key];
        var test = key.concat(", ", value);
        keys.push(test);
      });
    }
    var skillsJSX: JSX.Element[] = [];
    var num = keys.length;
    for (var i = 0; i < keys.length; i++) {
      var key = keys[i];
      var tkn: string = "skillBtn" + (i + 1).toString();
      skillsJSX.push(
        <button id="btn" type="button" className="btn btnColor button-margin">
          {key.split(",")[0]}
          <span className="badge badgeColor">{key.split(",")[1]}</span>
        </button>
      );
    }
    return skillsJSX;
  };

  render() {
    return (
      <div className="row">
        <div className="skillsBorder col-sm-12">
          <p className="text textPosition">مهارت های لازم:</p>
          {this.createSkills()}
        </div>
      </div>
    );
  }
}

interface State {
  skills: any[];
}

interface Props {
  skills: any[];
}
