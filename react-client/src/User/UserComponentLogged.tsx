import React, { Component, ReactNode } from 'react'
const axios = require('axios');
import UserCommon from './UserCommon'
export default class UserComponentLogged extends Component<any,  State> {

  constructor(props : any) {
    super(props);
    this.state = {
      id : "",
      name : "",
      lastname : "",
      job : "",
      bio : "",
      proLink : "",
      skills : []
    };
    var linktmp = 'http://localhost:8080/user?id='
    var  link = linktmp.concat(this.props.userId)
    axios.get(link)
    .then((response : any) => {
      let obj: any = JSON.parse(JSON.stringify(response.data));
      this.setState({name: obj["name"]});
      this.setState({lastname: obj["lastname"]});
      this.setState({id: obj["id"]});
      this.setState({job: obj["jobTitle"]});
      this.setState({bio: obj["bio"]});
      this.setState({proLink: obj["proLink"]});
      this.setState({skills : obj["skills"]});
    })
    .catch(function (error : any) {
    // handle error
    console.log(error);

    })
    .then(function () {
    // always executed
    });
  }

  createSkills = () : any => {
  var keys : string[] = [];
  for(var i = 0; i < this.state.skills.length; i++){
    Object.keys(this.state.skills[i]).map((key) => {
      const value = this.state.skills[i][key];
      var test = key.concat(", ",value)
      console.log(test)
      keys.push(test)
    });
  }
  const skillsJSX = (keys).map( key =>{
      return(
        <button type="button" className="btn skill-btn">
        {key.split(',')[0]} <span className="badge badge-blue"> {key.split(',')[1]} </span>
        </button>
      )
    });
      return skillsJSX;
  }
  

  render() {
    document.body.classList.add('htmlBodyStyle');

    return (
      <div>
        <div className= "container-fluid main">
        <UserCommon {...this.state} />
        <div className = "row justify-content" id = "skill-row">
            <div className = "col-sm-12">
              <div id = "add-skill-name">
                <p>  :مهارت ها </p>
              </div>
              <div className = "add-skill-form-container">
                <form className = "add-skill-form">
                  <button type = "button" className = "add-skill-button" > افزودن مهارت </button>
                    <select className =  "add-skill-input" placeholder ="--انتخاب مهارت--">
                      <option>  --انتخاب مهارت--  </option>
                      <option value="Python">Python</option>
                      <option value="Java">Java</option>
                      <option value="Database">Database</option>
                    </select>
                </form>
              </div>
            </div>
          </div>

        <div className = "row justify-content-end skills">
          <div className = "col">
          <div>{this.createSkills()} </div>
          <div className = "col"></div>
        </div>
      </div>

    </div>   
    </div>
    )
  }
}

interface State{
  id : ""
  name : "",
  lastname : "",
  job : "",
  bio : "",
  proLink : "",
  skills : []
}

