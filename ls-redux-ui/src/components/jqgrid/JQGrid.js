import React from 'react';
import ReacDOM from 'react-dom';
import 'free-jqgrid/js/i18n/grid.locale-cn.js';
import 'free-jqgrid/css/ui.jqgrid.css';
import jqGrid from 'free-jqgrid/js/jquery.jqgrid.src.js';
import 'jquery-bootstrap-theme/css/custom-theme/jquery-ui-1.10.3.custom.css';

export default class JQGrid extends React.Component{

    constructor(props){
        super (props);
        this.state= {pagerID: 'jqGridPager'+Math.random()};
    }

    componentDidMount() {
        this.initJQueryPlugin();
    }

    initJQueryPlugin(){
        $(this.refs.eventsgrid).jqGrid(
            $.extend(
                {
                    viewrecords: true, // show the current page, data rang and total records on the toolbar
                    datatype: "local",
                    height: 'auto',
                    width: 'auto',
                    autowidth:true,//自动宽
                    pager: '#' + this.state.pagerID,
                    rowNum: 10
                },
                this.props.options,
            ));

        // activate the toolbar searching
        $(this.refs.eventsgrid).jqGrid('filterToolbar');
        $(this.refs.eventsgrid).jqGrid('navGrid',"#"+this.state.pagerID, {
            search: true, // show search button on the toolbar
            add: false,
            edit: false,
            del: false,
            refresh: true
        });

        //表头合并
        if(this.props.groupHeaderOption){
            $(this.refs.eventsgrid).jqGrid('setGroupHeaders', {
                useColSpanStyle: true,
                groupHeaders: this.props.groupHeaderOption
            });
        }
    }
    componentWillUpdate(){
        $(this.refs.eventsgrid).GridUnload();
    }

    componentDidUpdate(prevProps, prevState) {
        this.initJQueryPlugin();
    }

    componentWillUnmount(){
        $(this.refs.eventsgrid).GridUnload();
    }

    render () {
        return (
            <div><table ref="eventsgrid" /><div id={this.state.pagerID}></div></div>
        );
    }
}
