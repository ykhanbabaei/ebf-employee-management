import React from "react";
import './EmployeeList.css';

export default class EmployeeList extends React.Component {

  constructor(props) {
    super(props);
    this.companyId = this.props.match.params.id;
    this.state = {employees: [], company: "", averageSalary: ""};
    this.host = window.location.protocol + "//" + window.location.host;

  }

  componentDidMount(){
     this.loadEmployees();
     this.loadCompanyAndAverageSalary();
  }

  loadCompanyAndAverageSalary = async ()=> {
    const companyResponse = await fetch(this.host + '/api/companies/' + this.companyId);
    const company = await companyResponse.json();
    const averageSalaryResponse = await fetch(this.host + '/api/companies/' + this.companyId + '/average-salary');
    const averageSalary = await averageSalaryResponse.text();
    this.setState({company, averageSalary})
  }

  async loadEmployees() {
  	const response = await fetch(this.host + '/api/companies/' + this.companyId + '/employees');
  	const employees = await response.json();
    this.setState({employees: employees})
  }

    handleDelete = async (id) => {
        const options = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.state.employee)
        };
        const response = await fetch(this.host + '/api/employees/' + id, options);
        if(!response.ok){
          console.log("employee creation failed!");
        }
        else{
            let newEmployees = this.state.employees.filter(employee=>employee.id !== id);
            this.setState({employees: newEmployees})
        }
      }

    handleEdit = (id) => {
        this.props.history.push({pathname: "/companies/" + this.companyId + "/update-employee/" + id});
    }

    handleCreate = () => {
        this.props.history.push({pathname: "/companies/" + this.companyId + "/new-employee"});
    }

    handleBack = () => {
        this.props.history.push({pathname: "/" });
    }

  render() {
    let items = this.state.employees.map(( employee, index ) => {
          return (
              <tr key={index}>
                <td>{employee.name}</td>
                <td>{employee.surname}</td>
                <td>{employee.email}</td>
                <td>{employee.address}</td>
                <td>{employee.salary}</td>
                <td><button onClick={()=>this.handleEdit(employee.id)}>edit</button><button onClick={()=>this.handleDelete(employee.id)}>delete</button></td>
              </tr>
          );
            })
    let company = this.state.company ? this.state.company.name : 'Unknown';
    let averageSalary = this.state.averageSalary ? this.state.averageSalary : '-';
    return (
        <div id="container">
        <button id="create" onClick={()=>this.handleBack()}>Back</button>
        <button id="create" onClick={()=>this.handleCreate()}>Create Employee</button>
        <span>Company: <span class="title">{company}</span>  Average Salary: <span class="title">{averageSalary}</span></span>
          <table>
          <tbody>
            <tr>
              <th>Name</th>
              <th>Surname</th>
              <th>Email</th>
              <th>Address</th>
              <th>Salary</th>
              <th>Actions</th>
            </tr>
             {items}
          </tbody>
          </table>
      </div>
    );
  }
}