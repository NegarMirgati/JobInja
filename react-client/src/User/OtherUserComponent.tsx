import React, { Component, ReactNode } from 'react'
const axios = require('axios');
import { toast } from 'react-toastify';
import UserCommon from './UserCommon';
import Skills from './SkillsOther';
import Loader from '../Common/Loader'
import Footer from "../Common/Footer";
import Header from "../Common/Header";

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
      endorsedSkills : [],
      loading : true
    };
  }

  componentDidMount(){
    if(this.props.userId){
      var link = 'http://localhost:8080/user?id='.concat(this.props.userId as string) ;
      var config = {
        headers: {'Authorization': "bearer " + localStorage.getItem('jwt')}
      };
      axios.get(link, config, config)
      .then((response : any) => {
        let obj: any = JSON.parse(JSON.stringify(response.data));
        this.setState({name: obj["name"]});
        this.setState({lastname: obj["lastname"]});
        this.setState({id: obj["id"]});
        this.setState({job: obj["jobTitle"]});
        this.setState({bio: obj["bio"]});
        this.setState({skills : obj["skills"]}); 
        this.setState({proLink: obj["proLink"]});
        this.setState({endorsedSkills : obj["endorsedSkills"]})
      })
      .catch(function (error : any) {
        toast.error('اتصال با سرور با خطا مواجه شد');
      })
      .then(() => {
        this.setState({loading : false});
        console.log('state', this.state)
     });
   }
  }

  getMainElements(){
    console.log('sss', this.state)
    if(this.state.loading)
    return(
      <Loader/>
    )
    else
    return (      
      <div className= "container-fluid main">
      <UserCommon {...this.state} />
      <Skills {...this.state} />
      </div>
    );
  }

  render() {
    document.body.classList.add('htmlBodyStyle');
    return (
        <div id = "fill-view-point">
            <Header/>
            {this.getMainElements()}
            <Footer/>
        </div>
    )
  }
}

interface State{
  id : "",
  name : "",
  lastname : "",
  job : "",
  bio : "",
  proLink : "",
  skills : any[],
  endorsedSkills : any[],
  loading : boolean
}

