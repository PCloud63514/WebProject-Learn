import React from 'react'
import { alertContext } from './AlertDialogProvider'

function useAlert() {
    return alertContext
}

export default useAlert