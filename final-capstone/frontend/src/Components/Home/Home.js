import {Link, Redirect, Route} from 'react-router-dom'
import React from 'react';
import AddRecipe from '../Home/images/AddRecipe.png'
import RecipeBook from '../Home/images/RecipeBook.png'
import WeeklyPlanner from '../Home/images/WeeklyPlanner.png'
import '../Home/Home.css'
import jwt_decode from "jwt-decode";
import Calendar from '../Calendar/Calendar';

export default function Home(props) {

    let token = props.token;
    let decoded = jwt_decode(token);
    console.log(decoded)

    return(
        <body>
            <section class="home-section">
                <div class="color"></div>
                <div class="color"></div>
                <div class="color"></div>
                
                <div class="box">
                    <div class="form">
                       <form>
                           <div class="link-container">  
                                <div className="home-button-group" >
                                    <Link to='/create'>
                                        <img className="home-button-image" src={AddRecipe} alt="add-recipe" />
                                    </Link>
                                    <p className="home-button-title" >Add <br />Recipe</p>
                                </div>
                                <div className="home-button-group" >
                                    <Link to='/recipes'>
                                        <img className="home-button-image" src={RecipeBook} alt="add-recipe" />
                                    </Link>
                                    <p className="home-button-title" >Recipe <br />Book</p>
                                </div>
                                <div className="home-button-group" >
                                    <Link to='/mealplans'>
                                        <img className="home-button-image" src={WeeklyPlanner} alt="add-recipe" />
                                    </Link>
                                    <p className="home-button-title" >Weekly <br />Planner</p>
                                </div>
                                <div className='home-button-group' >
                                    <Link to="/calendar" src={Calendar}>
                                        <p className="home-button-title">Calendar</p>
                                    </Link>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </section> 
        </body>
    )
}