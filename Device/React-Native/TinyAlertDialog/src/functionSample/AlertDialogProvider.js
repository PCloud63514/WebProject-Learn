import React, { createContext } from 'react'
import AlertDialog from './AlertDialog'
const initialState = {
    visible: false,
    children:undefined,
}

export const alertContext = createContext(initialState)

const {Provider} = alertContext

export default props => {
    const [alertState, setAlertState] = React.useState(initialState)

    alert = (visible, children) => {
        setAlertState({
            visible:visible,
            children:children
        })
    };

    close = () => {
        setAlertState(initialState)
    };

    return (
        <>
            <Provider value={alert}>{props.children}</Provider>
            <AlertDialog {...alertState} close={close}/>
        </>
    );
}