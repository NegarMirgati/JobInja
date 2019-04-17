import React, { Component } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';
import $ from 'jquery'; 

export default class SkillsLogged extends Component<Props, State> {
    constructor(props : Props) {
        super(props);
        this.state = {
            id : "",
            possibleSkills : [],
            skills : []
        };
    }

    componentDidMount(){
      this.setState({id : this.props.id});
      this.setState({skills : this.props.skills});
      this.setState({possibleSkills : this.props.possibleSkills});
   }

    handleButtonHover = (event : any) => {
        console.log('test')
        this.injectStyles('button:hover .badge', 'background-color: red;');
        this.injectStyles('button:hover .badge', 'content: "-";');
    }

    injectStyles(name : any, color : any) {
        $(name).css("background-color", color);  
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
            skillsJSX.push(<button type="button" onClick = {this.delSkill} onMouseEnter = {this.handleButtonHover} value = 
            { key.split(',')[0]} id = {tkn} className="btn skill-btn">
            {key.split(',')[0]} <div className="badge badge-blue"  data-hover="-" data-active = {key.split(',')[1]}> <span>{key.split(',')[1]}</span> </div>
            </button>)
         }
        return skillsJSX;
    }

    addSkill = () => {
        var linktmp = 'http://localhost:8080/user/skill?id='
        var  link = linktmp.concat(this.props.id, '&name=')
        var e = document.getElementById("addSkill") as  HTMLSelectElement;
        if(e.options[e.selectedIndex] === undefined)
          return;
        var selectedSkill = e.options[e.selectedIndex].value;
        var toAdd = selectedSkill.replace(/\+/g, "%2B");
        var finalLink = link.concat(toAdd);
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

      delSkill = (event : any) => {
        console.log(event.target.value);
        var linktmp = 'http://localhost:8080/user/skill?id=' + this.props.id + '&name=';
        var selectedSkill = event.target.value;
        if(selectedSkill === undefined)
          return;

        var toDelete = selectedSkill.replace(/\+/g, "%2B");
        var finalLink = linktmp.concat(toDelete);
        console.log(finalLink)
        axios.delete(finalLink)
        .then((response : any) => {
            var mySkills : any[] = [...this.state.skills]; // make a separate copy of the array
            for(var i  = 0; i < mySkills.length; i ++){
              if(mySkills[i].hasOwnProperty(selectedSkill)){
                mySkills.splice(i, 1);
                this.setState({skills: mySkills});
              }
            }
            var array : any[] = [...this.state.possibleSkills]; // make a separate copy of the array
            array.push(selectedSkill);
            this.setState({possibleSkills: array});    
            toast.success('مهارت با موفقیت حذف شد');
            })
          .catch(function (error : any) {
              toast.error('خطا در حذف مهارت');
          })
      }
    
      addPossibleSkills = () : any => {
        const skillsJSX = (this.state.possibleSkills).map( key =>{
          return(
            <option value={key}> {key} </option>
          )
        });
          return skillsJSX;
    
      }

  render() {
    return (
      <div>
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
          <div className = "col"></div></div>
      </div>
      </div>
    )
  }
}

interface State{
    id : "",
    skills : any[],
    possibleSkills : any[]
  }

  interface Props{
    id : ""
    name : "",
    lastname : "",
    job : "",
    bio : "",
    proLink : "",
    skills : any[],
    possibleSkills : any[]
  }
  
