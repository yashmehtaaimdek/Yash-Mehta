import React from 'react';
import Container from '@material-ui/core/Container';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';

class App extends React.Component {
  state = {
    details: []
  };
  handleSubmit = e => {
    this.state.details.push(this.state)
    this.setState({fname:'', lname:'', contact:'' });
  };
  render() {
    return (
      <React.Fragment>
          <form>
            <label>First Name :</label>
            <input type="text" onChange={e => this.setState({fname: e.target.value})} value={this.state.fname}/><br/>

            <label>Last Name :</label>
            <input type="text" onChange={e => this.setState({lname: e.target.value})} value={this.state.lname}/><br/>

            <label>Conatct :</label>
            <input type="text" onChange={e => this.setState({contact: e.target.value})} value={this.state.contact}/><br/>


            <Button onClick={this.handleSubmit} variant="contained" color="primary">Submit</Button>
          </form>
          <div>
            <Grid container spacing={1}>
              <Grid item xs={12}>
                {this.state.details.map((data) => (
                  <p>First Name: {data.fname}<br/> Last Name {data.lname} <br/ > Contact: {data.contact}</p>))}
              </Grid>
            </Grid>
          </div>
      </React.Fragment>
    );
  }
}
export default App;
