import React, { Component } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';
import $ from 'jquery'; 
import 'src/Styles/OtherUser.css'

export default class Skills extends Component<Props, State> {

    constructor(props : Props) {
        super(props);
        this.state = {
            id : "",
            skills : [],
            endorsedSkills : []
        };
    }

    componentWillReceiveProps(nextProps : Props) {
        console.log('props', nextProps)
        this.setState({id: nextProps.id});
        this.setState({skills : nextProps.skills});
        this.setState({endorsedSkills : nextProps.endorsedSkills});
    }

    createSkills = () : any => {
        var keys : string[] = [];
        for(var i = 0; i < this.state.skills.length; i++){
          Object.keys(this.state.skills[i]).map((key) => {
            const value = this.state.skills[i][key];
            var test = key.concat(", ",value)
            keys.push(test)
          });
        }
        var skillsJSX : JSX.Element[] = [];
        var num = keys.length;
        for(var i = 0; i < keys.length; i ++) {
          var key = keys[i];
          var tkn : string = "skillBtn" + (i + 1).toString();
          var hasEndorsed = this.state.endorsedSkills.includes(key.split(',')[0]);
          skillsJSX.push(<button type="button" onClick = {this.endorse} value = 
          { key.split(',')[0]} id = {tkn} className="btn skill-btn1">
          {key.split(',')[0]} <div className = {hasEndorsed == false ? "badge badge-green1" : "badge badge-blue1"}  data-hover="-" data-active = {key.split(',')[1]}> <span>{key.split(',')[1]}</span> </div>
          </button>)
         
       }
          return skillsJSX;
    }

    endorse = (event : any) : any => {
        if(this.state.endorsedSkills.includes(event.target.value))
          return;

        var linktmp = 'http://localhost:8080/user/endorse?id='
        var  link = linktmp.concat(this.state.id, '&name=')
        var selectedSkill = event.target.value;
        var finalLink = link.concat(selectedSkill);
        axios.post(finalLink)
        .then((response : any) => {
            var array : any[] = [...this.state.endorsedSkills]; // make a separate copy of the array
            array.push(selectedSkill);
            this.setState({endorsedSkills: array}); 
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
    endorsedSkills : any[]
  }

interface Props{
    id : ""
    name : "",
    lastname : "",
    job : "",
    bio : "",
    proLink : "",
    skills : any[],
    endorsedSkills : any[]
  }


