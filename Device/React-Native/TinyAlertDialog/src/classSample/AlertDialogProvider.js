import React, { createContext } from 'react'
import AlertDialog from './AlertDialog'
const initialState = {
    visible: false,
    children:undefined,
}

export const alertContext = createContext(initialState)

const {Provider} = alertContext

export default class WrapProvider extends React.PureComponent {
    state = {
        visible:false,
        children:undefined
    }
    
    componentDidMount() {

    }

    alert = (visible, children) => {
        this.setState({visible:visible, children:children})
    }

    close = () => {
        this.setState({visible:initialState.visible, children:initialState.children})
    }

    render() {
        return (
        <>
            <Provider value={this.alert}>{this.props.children}</Provider>
            <AlertDialog {...this.state} close={this.close}/>
        </>
        )
    }
}