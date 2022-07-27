import React from "react";
import './CompanyList.css';

export default class CompanyList extends React.Component {

  constructor(props) {
    super(props);
    this.companyId = this.props.match.params.id;
    this.state = {companies: []};
    this.host = window.location.protocol + "//" + window.location.host;

  }

  componentDidMount(){
     this.loadCompanies();
  }

  async loadCompanies() {
  	const response = await fetch(this.host + '/api/companies');
  	const companies = await response.json();
    this.setState({companies: companies})
  }

    handleView = async (id) => {
        this.props.history.push({pathname: "/companies/" + id + "/employees/"});
    }

  render() {
    let items = this.state.companies.map(( employee, index ) => {
          return (
              <tr key={index}>
                <td>{employee.name}</td>
                <td><button onClick={()=>this.handleView(employee.id)}>view</button></td>
              </tr>
          );
            })
    return (
        <div id="container">
          <table>
          <tbody>
            <tr>
              <th>Company</th>
              <th>Actions</th>
            </tr>
             {items}
          </tbody>
          </table>
      </div>
    );
  }
}