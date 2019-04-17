import React, { Component } from "react";

export default class ProjectSkills extends Component {
  render() {
    return (
      <div className="row">
        <div className="skillsBorder col-sm-12">
          <p className="text textPosition">مهارت های لازم:</p>
          <button type="button" className="btn btnColor button-margin">
            HTML <span className="badge badgeColor">5</span>
          </button>
          <button type="button" className="btn btnColor button-margin">
            CSS <span className="badge badgeColor">3</span>
          </button>
          <button type="button" className="btn btnColor button-margin">
            JavaScript <span className="badge badgeColor">16</span>
          </button>
          <button type="button" className="btn btnColor button-margin">
            TypeScript <span className="badge badgeColor">2</span>
          </button>
        </div>
      </div>
    );
  }
}
