const baseURL = "http://localhost:8081/mealplans/"

const MealPlannerService = {
    getById(recipeId) {
        return fetch(baseURL + recipeId)
        .then(result => result.json())
    },

    getAll() {
        return fetch(baseURL)
        .then(result => result.json())
    },

    create (recipe){
        const plannerRecipe = { recipeId: recipe._id, ingredients: recipe.ingredients}
        return fetch(baseURL, {
            method: 'POST',
            body: JSON.stringify(plannerRecipe),
            headers: { 'Content-Type': 'application/json' }
        })
        .then(res => res.json())
    },

    delete (id) {
        return fetch(baseURL + id, {
            method: 'DELETE'
        }) 
    },

    update (id) {
        return fetch(baseURL + id, {
            method: 'PUT'
        }) 
    }
}
export default MealPlannerService;