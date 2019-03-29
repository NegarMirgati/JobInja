import React, { Component, ReactNode } from 'react'
const axios = require('axios');
import UserCommon from './UserCommon'
import { toast } from 'react-toastify';
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
      skills : [],
      possibleSkills : []
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
      this.setState({possibleSkills: obj["possibleSkills"]});
    })
    .catch(function (error : any) {
    // handle error
    console.log(error);
    })
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

  addPossibleSkills = () : any => {
    const skillsJSX = (this.state.possibleSkills).map( key =>{
      return(
        <option value={key}> {key} </option>
      )
    });
      return skillsJSX;

  }

  addSkill = () => {
    var linktmp = 'http://localhost:8080/user/addSkill?id='
    var  link = linktmp.concat(this.props.userId, '&name=')
    var e = document.getElementById("addSkill") as  HTMLSelectElement;
    var selectedSkill = e.options[e.selectedIndex].value;
    var finalLink = link.concat(selectedSkill)
    console.log(finalLink)
    axios.put(finalLink)
    .then((response : any) => {
        var array : any[] = [...this.state.possibleSkills]; // make a separate copy of the array
        var index = array.indexOf(selectedSkill)
        if (index !== -1) {
          array.splice(index, 1);
          this.setState({possibleSkills: array});
        }
        var newelem = {};
        (newelem as any)[selectedSkill] = "0";
        this.setState({ skills: this.state.skills.concat([newelem])
        })
      
      toast.success('مهارت با موفقیت اضافه شد')
    })
    .catch(function (error : any) {
      toast.error('خطا در اضافه کردن مهارت')
      console.log(error);
    })

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
                  <button onClick= {this.addSkill} type = "button" className = "add-skill-button" > افزودن مهارت </button>
                    <select className =  "add-skill-input" placeholder ="--انتخاب مهارت--" id = "addSkill">
                      <option selected disabled>  --انتخاب مهارت--  </option>
                      {this.addPossibleSkills()}
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
  skills : any[],
  possibleSkills : any[]
}

