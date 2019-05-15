import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Link} from 'react-router-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';

import Login from './Login/Login'
import Register from './Register/Register';
import User from './User/User';
import Home from './Home/Home';
import Project from "./Project/Project";

ReactDOM.render( 
    <Router>
      <Route exact path = "/login" component={Login} />
      <Route exact path = "/register" component={Register} />
      <Route exact path = "/user" component={User} />
      <Route exact path = "/home" component={Home} />
      <Route exact path="/project" component={Project} />
    </Router>,
  document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
