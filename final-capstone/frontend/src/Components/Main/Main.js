import { Component } from 'react'
import { Switch, Route, Redirect, Link } from 'react-router-dom'
import Login from '../Login/Login'
import Register from '../Register/Register'
import Home from '../Home/Home'
import { addToken, deleteUser } from '../../Redux/actionCreators'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import Recipes from '../Recipes/Recipes'
import Create from '../Create/Create'
import Search from '../Search/Search'
import RecipeDetails from '../RecipeDetails/RecipeDetails'
import './Main.css'
import Mealplans from '../Mealplans/Mealplans'
import jwt_decode from "jwt-decode";
import Calendar from '../Calendar/Calendar'


const mapStateToProps = state => {
    return {
        token: state.token,
        user: state.user
    }
}

const mapDispatchToProps = (dispatch) => ({
    addToken: () => { dispatch(addToken()) },
    deleteUser: () => { dispatch(deleteUser()) }
});

class Main extends Component {
    constructor(props) {
        super(props);
    }

    handleLogout = () => {
        this.props.addToken("")
        this.props.deleteUser()
    }

    

    render() {

        const RecipeWithId = ({ match }) => {
            return <RecipeDetails id={match.params.id} />
        }

        return (



            <div>

                
    <div className='box'>
                <header class="header-container">
                    

                    <img
                        src=".././LogoIcon-Small.png"
                        className="logo-icon"
                    />
                    <img
                        src=".././LogoText-Large.png"
                        className="logo-text"
                    />
                    {/* </header> */}


                    {/* <div className='header-links'> */}

                    

                    {this.props.token.token !== undefined ?
                    
                    
                        <div className='header-links'>

                            <ul>
                                <li>Welcome {jwt_decode(this.props.token.token).sub}</li>
                                <li><Link to='/home'>Home</Link></li>
                                {/* <li><Link to='/recipes'>Recipes</Link></li> */}
                                <li><Link to='/login' onClick={this.handleLogout}>Logout</Link></li>
                                <Redirect to='/home' />
                            </ul>

                        </div>
                        :
                        <div className='header-links'>
                            <ul>


                                <li> <Link to='/login'>Home </Link> </li>
                                {/* recipes on main for non logged in users? */}
                                <li><Link to='/recipes'> Recipes </Link></li>
                                <li><Link to='/mealplans'>Meal Plans</Link></li>
                                <Redirect to='/login' />

                            </ul>
                        </div>
                    }

                    
                </header>
</div>
                <section>
                <div class="color"></div>
                <div class="color"></div>
                <div class="color"></div>
                

                <Switch>
                    <Route path='/login' component={() => <Login />} />
                    <Route path='/register' component={() => <Register />} />
                    {/* <Route path='/recipes/:id' component={() => <RecipeDetails />} /> */}
                    {/* <Route path='/recipes/:id' component={RecipeDetails} /> */}
                    <Route exact path="/recipes/:id" component={this.props.token.token !== undefined ? RecipeWithId : null} />
                    <Route path='/recipes' component={this.props.token.token !== undefined ? () => <Recipes/> : null} />
                    <Route path='/home' component={this.props.token.token !== undefined ? () => <Home token={this.props.token.token} /> : null} />
                    <Route path='/create' component={this.props.token.token !== undefined ? () => <Create /> : null} />
                    <Route path="/search" component={() => <Search />} />
                    <Route path="/mealplans" component={() => this.props.token.token !== undefined ? <Mealplans /> : null} />
                    <Route path="/calendar" component={() => this.props.token.token !== undefined ? <Calendar/> : null} />

                    {/* <Route path="/search"><Search /></Route>
                    <Route path="/create"><Create /></Route>
                    <Route path='/recipes/:id'><RecipeDetails /></Route>
                    <Redirect to='/login' /> */}
                </Switch>

            </section>

            </div>
        )
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Main));