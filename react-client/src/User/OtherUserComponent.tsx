import React, { Component, ReactNode } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';
import UserCommon from './UserCommon'
export default class OtherUserComponent extends Component<any,  State> {
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
      hasEndorsedSkills : []
    };
    console.log('ddddddd', this.props.userId)
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
      this.setState({hasEndorsedSkills : []});
      
    })
    .catch(function (error : any) {
      toast.error('اتصال با سرور با خطا مواجه شد');
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
  var skillsJSX : JSX.Element[] = [];
  var num = keys.length;
  console.log(num);
  for(var i = 0; i < keys.length; i ++) {
    var key = keys[i];
    var tkn : string = "skillBtn" + (i + 1).toString();
    skillsJSX.push(<button type="button" onClick = {this.endorse} value = 
    { key.split(',')[0]} id = {tkn} className="btn skill-btn">
    {key.split(',')[0]} <div className="badge badge-blue"  data-hover="-" data-active = {key.split(',')[1]}> <span>{key.split(',')[1]}</span> </div>
    </button>)
   
 }
    return skillsJSX;
  }
  
  endorse = (event : any) : any => {
    console.log(event.target.value);
    var linktmp = 'http://localhost:8080/user/endorse?id='
    var  link = linktmp.concat(this.props.userId, '&name=')
    var selectedSkill = event.target.value;
    var finalLink = link.concat(selectedSkill);
    console.log(finalLink)
    axios.post(finalLink)
    .then((response : any) => {
        var array : any[] = [...this.state.hasEndorsedSkills]; // make a separate copy of the array
        array.push(selectedSkill);
        this.setState({hasEndorsedSkills: array});  
        var mySkills : any[] = [...this.state.skills]; // make a separate copy of the array
        for(var i  = 0; i < mySkills.length; i ++){
          if(mySkills[i].hasOwnProperty(selectedSkill)){
            mySkills[i][selectedSkill] =  (Number(mySkills[i][selectedSkill]) + 1).toString();
            this.setState({skills: mySkills});
          }  
        }
        toast.success('مهارت با موفقیت تشویق شد');
        }
      )
      .catch(function (error : any) {
          toast.error('خطا در تشویق مهارت');
      })

  }

  render() {
    document.body.classList.add('htmlBodyStyle');

    return (
      <div>
        <div className= "container-fluid main">
        <UserCommon {...this.state} />
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
  hasEndorsedSkills : any[]
}

