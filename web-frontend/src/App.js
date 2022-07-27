import './App.css';
import EmployeeList from './EmployeeList'
import CompanyList from './CompanyList'
import EmployeeForm from './EmployeeForm'
import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom'

function App() {
  return (
<Router>
      <div>
         <Switch>
          <Route exact path={"/"} component={CompanyList} />
          <Route exact path={"/companies"} component={CompanyList} />
          <Route exact path={"/companies/:id/employees"} component={EmployeeList} />
          <Route path={"/companies/:companyId/new-employee"} component={EmployeeForm} />
          <Route path={"/companies/:companyId/update-employee/:id"} component={EmployeeForm} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
