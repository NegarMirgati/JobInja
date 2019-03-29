import React, { Component } from 'react'

export default class UserCommon extends Component<any, any> {
    constructor(props : any) {
        super(props);
    }

  render() {
    return (
        <div className= "container-fluid main">
          <div className="row" id = "picRow">
            <div className = "col-sm-12">
              <div className="blue-box"><br></br> <br></br> <br></br> <br></br> <br></br></div>
                <div className = "text-right">
                  <p className = "profile-name"> {this.props.name} {' '} {this.props.lastname} </p>

                  <p className = "profile-job"> {this.props.job} </p>
                </div>
              <div>
                <img  src={this.props.proLink} className="img-rounded profile-photo img-responsive " alt="image"/>
                  <div className = "rectangle"></div>
                  <div className = "rectangle-2"></div>
                  <div className = "rectangle-3"></div>
                  <div id = "parallelogram-1"></div>
                  <div id = "parallelogram-2"></div>
              </div>
            </div>
          </div>
          <div className = "row">
            <div className = "col-sm-12">
              <p className = "rtl-text"> {this.props.bio} </p>
          </div>
        </div>
    </div>
    )
  }
}
