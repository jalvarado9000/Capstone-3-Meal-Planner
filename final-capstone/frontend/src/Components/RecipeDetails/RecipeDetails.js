import React, { useEffect, useState } from "react"
import { useFetch } from '../../hooks/useFetch'
import './RecipeDetails.css'

export default function RecipeDetails(props) {

    const id = props.id
    const url = 'http://localhost:8081/recipes/' + id

    
    const [convertedTimes, setConvertedTimes] = useState({
                                                        prepTimeMins : null,
                                                        prepTimeHours : null,
                                                        cookMins : null,
                                                        cookHours : null })
    const [recipeDetails, setRecipeDetails] = useState(Object)

    useEffect(() => {
        fetch(url)
            .then(response => response.json())
            .then(json => setRecipeInfo(json))
            .then()
    }, [])

    //uses JSON retrieved from useEffect to populate converted time and recipe details
    const setRecipeInfo = (recipeJSON) => { 
        setRecipeDetails(recipeJSON)
        setConvertedTimes({ prepTimeMins : recipeJSON.prepTime % 60,
        prepTimeHours : Math.floor(recipeJSON.prepTime / 60),
        cookMins : recipeJSON.cookingTime % 60,
        cookHours : Math.floor(recipeJSON.cookingTime / 60)
    })
        }
    return (
        <div className="recipe-container">
            {recipeDetails && (
                <div className="recipe-details">
                    <div class="recipe-section title-section" >
                        <h2>{recipeDetails.title}</h2>
                    </div>
                    <div className="recipe-section time-section" >
                        <p>Prep Time: {convertedTimes.prepTimeHours != 0 && convertedTimes.prepTimeHours + " Hrs"} {convertedTimes.prepTimeMins != 0&& convertedTimes.prepTimeMins + " Mins"}
                        </p>
                        <p>Cook Time: {convertedTimes.cookHours != 0 && convertedTimes.cookHours + " Hrs"} {convertedTimes.cookMins != 0 && convertedTimes.cookMins + " Mins"}
                        </p>
                    </div>

                    <div className="recipe-section ingredients-section">
                        {recipeDetails.ingredients && (
                            <ul>
                                {recipeDetails.ingredients.map((ingredient) => {
                                    return (
                                        <li key={ingredient.recipeNote}>
                                            {ingredient.recipeNote}
                                        </li>
                                    )
                                })}
                            </ul>
                        )}
                    </div>

                    <div className="recipe-section instructions-section">
                        <p>{recipeDetails.instructions}</p>
                    </div>
                    <div className="recipe-section bottom-section">
                        Created: {recipeDetails.dateAdded}
                    </div>
                </div >
            )}
        </div>
    )
}
