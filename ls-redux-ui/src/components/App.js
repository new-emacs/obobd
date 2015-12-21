import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import ReactDOM from 'react-dom';
import { Col, Tabs , Tab, Row, Grid } from 'react-bootstrap';
import JQGrid from './jqgrid/JQGrid';

class App extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {

        var options = {
            data: [
                { id: "1", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "2", invdate: "2007-10-02", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "3", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "4", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "5", invdate: "2007-10-05", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "6", invdate: "2007-09-06", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "7", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "8", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "9", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "1", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "2", invdate: "2007-10-02", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "3", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "4", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "5", invdate: "2007-10-05", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "6", invdate: "2007-09-06", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "7", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "8", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "9", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" }
            ],
            colModel: [
                { label: 'Inv No', name: 'id', width: 75, key:true },
                { label: 'Date', name: 'invdate', width: 90 },
                { label: 'Client', name: 'name', width: 100 },
                { label: 'Amount', name: 'amount', width: 80 },
                { label: 'Tax', name: 'tax', width: 80 },
                { label: 'Total', name: 'total', width: 80 },
                { label: 'Notes', name: 'note', width: 150 }
            ],
            caption: "第一个JQGrid",
        };
var options2 = {
            data: [
                { id: "1", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "2", invdate: "2007-10-02", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "3", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "4", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "5", invdate: "2007-10-05", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "6", invdate: "2007-09-06", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "7", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "8", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "9", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "1", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "2", invdate: "2007-10-02", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "3", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "4", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "5", invdate: "2007-10-05", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "6", invdate: "2007-09-06", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" },
                { id: "7", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00" },
                { id: "8", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00" },
                { id: "9", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00" }
            ],
            colModel: [
                { label: 'Inv No', name: 'id', width: 75, key:true },
                { label: 'Date', name: 'invdate', width: 90 },
                { label: 'Client', name: 'name', width: 100 },
                { label: 'Amount', name: 'amount', width: 80 },
                { label: 'Tax', name: 'tax', width: 80 },
                { label: 'Total', name: 'total', width: 80 },
                { label: 'Notes', name: 'note', width: 150 }
            ],
            caption: "第二个JQGrid",
        };

        var groupHeaderOption = [
            {startColumnName: 'id', numberOfColumns: 2, titleText: '.'},
            {startColumnName: 'name', numberOfColumns: 3, titleText: 'Nice'},
        ] ;

        var groupHeaderOption2 = [
            {startColumnName: 'name', numberOfColumns: 3, titleText: 'Nice'}
        ] ;

        return (
            <div>
                <Tabs defaultActiveKey={2} >
                    <Tab eventKey={1} title='One' >
                        <JQGrid options={options}  groupHeaderOption ={groupHeaderOption}/>
                    </Tab>
                    <Tab eventKey={2} title='two' >
                        <Row>
                        <Col xs={5} md={5} lg={5} >
                            <JQGrid options={options} groupHeaderOption ={groupHeaderOption}/>
                        </Col>
                        </Row>
                        <Row>
                        <Col xs={5} md={5} lg={5}>
                            <JQGrid options={options2} groupHeaderOption ={groupHeaderOption2}/>
                        </Col>
                        </Row>
                    </Tab>
                </Tabs>
            </div>

        );
    }
}
export default App;
