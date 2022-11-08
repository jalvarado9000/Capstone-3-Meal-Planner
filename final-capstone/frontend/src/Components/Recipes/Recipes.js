import React, { useEffect, useState } from "react"
import RecipesListView from "../RecipesListView/RecipesListView"
import { Link } from "react-router-dom"
import RecipesCardView from "../RecipesCardView/RecipesCardView"
import "./Recipes.css"

export default function Recipes() {


    const [recipeCollection, setRecipeCollection] = useState([])
    const [listView, setListView] = useState(false)
    const [query, setQuery] = useState('')

    useEffect(() => {
        fetch('http://localhost:8081/recipes')
            .then(response => response.json())
            .then(json => setRecipeCollection(json))
    }, [])


    // TODO Ability to search recipes by title,
    // Create a temp recipe Collection when there is a query value
    
     const queryUrl = `http://localhost:3000/recipes?title_like=${query}`
     async function searchRecipes() {
         const response = await fetch(queryUrl)
         const result = await response.json()
         console.log(result)
     }
     const onSubmit = (e) => {
         e.preventDefault();
         searchRecipes()
     }
    

    return (

        <>
        <div class="box">
                    <div class="form">
                       <form>
            <div className="recipes-container">
                <div className="recipes-top-container">
                    <h1>My Recipes</h1>
                    <div className="search-row">
                        <form className="search-form">
                            <input
                                type="text"
                                className="search-input"
                                placeholder="Search recipes"
                                value={query}
                                onChange={(e) => setQuery(e.target.value)}
                            />
                            <input id="search-btn" type="submit" value="Search" />
                            {/* <button onClick={handleSubmit}>Search</button> */}
                        </form>
                    </div>
                </div>


                {listView ? <RecipesListView recipes={recipeCollection} /> :
                    <RecipesCardView recipes={recipeCollection} />}

                <div className="bottom-rec-buttons">
                    <div id="btn-1">
                        <Link to="/create">
                            <button class="large-btn" role="button"><span>New Recipe</span></button>
                        </Link>
                    </div>
                    <div id="btn-2">
                        <button class="large-btn" role="button">New Meal Plan</button>
                    </div>
                </div>
            </div>
        </form>
        </div>
        </div>
        </>
    )
}