import { createStore, combineReducers, applyMiddleware } from 'redux'
import thunk from 'redux-thunk'
import { Token } from './token'
import { User } from './user'
import { connect } from "react-redux"


export const ConfigureStore = () => {
    const store = createStore(
        combineReducers({
            token: Token,
            user: User
        }),
        applyMiddleware(thunk)
    );

    return store;
}

//NOTE: SAME AS ABOVE BUT EASIER TO READ
// const reducer = combineReducers({
//     token: Token,
//     user: User
// });

// const store = createStore(
//     reducer,
//     // following line allows you to use the Redux DevTools in the browser
//     window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
// );

// export default store;