import React, { Component } from 'react'

export default class UserCard extends Component<any, State> {
    constructor(props : any){
        super(props);
        this.state = {
            id : "",
            name : "",
            lastname : "",
            jobTitle : "",
            proLink : "",
            linkToProPage : ""
        };
      }
    componentDidMount(){
       
        var key = Object.keys(this.props)[0]
        console.log('here', this.props[key])        
        this.setState({id: this.props[key].id});
        this.setState({name : this.props[key].name});
        this.setState({lastname : this.props[key].lastname});
        this.setState({jobTitle : this.props[key].jobTitle});
        this.setState({proLink : this.props[key].proLink});
        var link = "http://localhost:3000/user?id=" + this.props[key].id;
        this.setState({linkToProPage : link});
        console.log(this.state)
    }
  render() {
    return (
        <div className = "row">
            <div className = "search-user">
                <a id = "userCard-a" href = {this.state.linkToProPage}>
                    <div className = "col">
                        <img className="img-rounded search-image img-rounded img-responsive" src = {this.state.proLink} alt = "image" />
                    </div>
                    <div className = "col">
                        <p className = "search-user-name"> {this.state.name} {' '} {this.state.lastname} </p>
                        <p className = "search-user-job">{this.state.jobTitle}</p>
                    </div>
                </a>
            </div>
        </div>
    )
  }
}

interface State{
    id : "",
    name : "",
    lastname : "",
    jobTitle : "",
    proLink : "",
    linkToProPage : any
  }
