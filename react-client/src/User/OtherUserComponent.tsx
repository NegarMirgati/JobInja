import React, { Component, ReactNode } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';
import UserCommon from './UserCommon'
import Skills from '../Common/Skills'

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

  render() {
    document.body.classList.add('htmlBodyStyle');
    return (
      <div>
        <div className= "container-fluid main">
        <UserCommon {...this.state} />
        <Skills {...this.state} />
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

