import React from "react";
import axios from "axios";
import {Button, Card, Table} from "react-bootstrap";

class FirstComponent extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            people : []
        }
    }

    componentDidMount(){
        axios.get("http://localhost:8080/react-app/first-responder")
            .then(response => response.data)
            .then(data => {
                this.setState({people: data});
            });
    }

    render() {
        return (
            <div>
                <Card style={{ width: '18rem' }}>
                    <Card.Body>
                        <Card.Title>You have {this.state.people.length} people</Card.Title>
                        <Card.Text>
                            They are:
                        </Card.Text>
                        <Table>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Age</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.people.map(person => (
                                    <tr>
                                        <td>{person.name}</td>
                                        <td>{person.age}</td>
                                    </tr>
                                ))
                            }
                            </tbody>
                        </Table>
                        <Button variant="primary">Go somewhere</Button>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}

export default FirstComponent; //allows other components import this class