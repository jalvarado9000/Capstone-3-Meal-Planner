import React from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios';
import './RecipesCardView.css'

export default function RecipesCardView({ recipes }) {

    const handleDelete = (id) => {
        console.log(id)
        axios({
            method: 'DELETE',
            url: 'http://localhost:8081/recipes/' + id
        });
    }

    return (
        <div className='recipe-card-container'>
            {recipes.map((recipe, index) => (
                <div className='recipe-card-item' key={recipe.recipeId}>
                    <div className='recipe-card-info'>
                        <Link to={`/recipes/${recipe.recipeId}`}>
                            <h3>{recipe.title}</h3>
                            <div className='recipe-instructions'>
                                {recipe.instructions.substring(0, 100)}...
                            </div>
                        </Link>
                    </div>
                    <div className='recipe-card-buttons'>
                        {/* TODO edit recipe function */}
                        <button class="small-btn" role="button">&#x270E;</button>
                        <button class="small-btn" role="button" onClick={() => handleDelete(recipe.recipeId)}>&#x2716;</button>
                    </div>
                </div>
            ))}
        </div>
    )
}
