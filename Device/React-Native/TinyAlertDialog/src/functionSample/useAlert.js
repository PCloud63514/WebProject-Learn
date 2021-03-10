import React, { useContext } from 'react'
import { alertContext } from './AlertDialogProvider'

function useAlert() {
    const alert = React.useContext(alertContext)
    console.log("TEST1")
    return alert
}

export default useAlert