/**
 * @format
 */

import React from 'react'
import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import AlertProvider from './src/functionSample/AlertDialog/AlertDialogProvider'

const WrapApp = () => {
    return (
        <AlertProvider>
            <App/>
        </AlertProvider>
    )
}
AppRegistry.registerComponent(appName, () => WrapApp);
