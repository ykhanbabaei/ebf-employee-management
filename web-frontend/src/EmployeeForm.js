import React from "react";
import './EmployeeForm.css';

export default class EmployeeForm extends React.Component {

  constructor(props) {
    super(props);
    this.id = this.props.match.params.id;
    this.companyId = this.props.match.params.companyId;
    this.state = {employee: {name:"", surname:"", email:"", address:"", salary:""}};
    this.host = window.location.protocol + "//" + window.location.host ;
  }

  componentDidMount(){
    if(this.id){
        this.loadEmployee();
    }
  }

  async loadEmployee() {
  	const response = await fetch(this.host + '/api/employees/' + this.id);
  	const employee = await response.json();
    this.setState({employee: employee})
  }

  handleSubmit = (e) => {
      e.preventDefault();
      if(this.state.employee.id){
        this.handleUpdate()
      }
      else{
        this.handleCreate()
      }
  }

  handleUpdate = async () => {
      const options = {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.state.employee)
      };
      const response = await fetch(this.host + '/api/employees/' + this.state.employee.id, options);
      if(!response.ok){
        console.log("employee update failed!");
      }
      else{
          this.props.history.push({pathname: '/companies/'+ this.companyId + '/employees/'});
      }
    }

    handleCancel = async () => {
        this.props.history.push({pathname: "/companies/"+ this.companyId + "/employees"});
    }

  handleCreate = async () => {
      const options = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.state.employee)
      };
      const response = await fetch(this.host + '/api/companies/'+ this.companyId + '/employees', options);
      if(!response.ok){
        console.log("employee creation failed!");
      }
      else{
        this.props.history.push({pathname: '/companies/'+ this.companyId + '/employees/'});
      }
    }

  render() {

    return (
      <div>
            <form onSubmit={this.handleSubmit}>
             <ul>
              <li>
                <label htmlFor="name">Name: </label>
                <input
                  id="name"
                  type="text"
                  value={this.state.employee.name}
                  onChange={(e) => this.setState(prevState => ({employee: { ...prevState.employee, name: e.target.value}}))}
                  required/>
            </li>

            <li>
                <label htmlFor="surname">Surname: </label>
                <input
                  id="surname"
                  type="text"
                  value={this.state.employee.surname}
                  onChange={(e) => this.setState(prevState => ({employee: { ...prevState.employee, surname: e.target.value}}))}
                  required/>
            </li>

            <li>
                <label htmlFor="email">Email: </label>
                <input
                  id="email"
                  type="email"
                  value={this.state.employee.email}
                  onChange={(e) => this.setState(prevState => ({employee: { ...prevState.employee, email: e.target.value}}))}
                  required/>
            </li>

            <li>
                <label htmlFor="address">Address: </label>
                <input
                  id="address"
                  type="text"
                  value={this.state.employee.address}
                  onChange={(e) => this.setState(prevState => ({employee: { ...prevState.employee, address: e.target.value}}))}
                  required/>
            </li>

            <li>
                <label htmlFor="salary">Salary: </label>
                <input
                  id="salary"
                  type="number"
                  value={this.state.employee.salary}
                  onChange={(e) => this.setState(prevState => ({employee: { ...prevState.employee, salary: e.target.value}}))}/>
            </li>

            <li>
                <label htmlFor="name"></label>
                <button type="submit">Save</button>
                <button onClick={()=>this.handleCancel()}>Cancel</button>
            </li>
            </ul>

                  </form>
      </div>
    );
  }
}