import React, { Component } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';

export default class Skills extends Component<Props, State> {

    constructor(props : Props) {
        super(props);
        this.state = {
            id : "",
            skills : [],
            hasEndorsedSkills : []
        };
    }

    componentWillReceiveProps() {
        console.log('name', this.props.name);
        console.log(this.props);
        this.setState({id: this.props.id});
        this.setState({skills : this.props.skills});
        this.setState({hasEndorsedSkills : []});
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
        var  link = linktmp.concat(this.state.id, '&name=')
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
    return (
        <div>
        <div className = "row justify-content-end skills">
            <div className = "col">
                <div>{this.createSkills()} </div>
                <div className = "col"></div>
            </div>
        </div>
    </div>
    )
  }
}

interface State{
    id : "",
    skills : any[],
    hasEndorsedSkills : any[]
  }

interface Props{
    id : ""
    name : "",
    lastname : "",
    job : "",
    bio : "",
    proLink : "",
    skills : any[],
    hasEndorsedSkills : any[]
  }


