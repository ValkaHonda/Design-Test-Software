import React from 'react';
import ReactDOM from 'react-dom';
import { Switch, Route } from 'react-router'
import { BrowserRouter as Router } from 'react-router-dom';

import SignUp from './components/SignUp';
import SignIn from './components/SignIn';

import './css/index.scss';

const Application = () => 
    <Router>
        <Switch>
            <Route exact path="/signup" component={SignUp}/>
            <Route exact path="/signin" component={SignIn}/>
        </Switch>
    </Router>;


ReactDOM.render(
    <Application/>,
    document.getElementById('root')
);