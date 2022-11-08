//styles
import './RecipesListView.css'


export default function RecipesListView({ recipes, handleClick }) {

    // fetch(' http://localhost:3000/recipes')
    //     .then(response => response.json())
    //     .then(json => console.log(json))

    // managing recipe state in Recipes vs here (would remove events from parameter)
    // const [events, setEvents] = useState()
    // useEffect(() => {
    //     fetch(' http://localhost:3000/recipes')
    //         .then(response => response.json())
    //         .then(json => setEvents(json))
    // }, [])
    // console.log(events)

    const table = recipes.map((recipe, i) => {
        return (
            <tr key={i}>
                <td>{recipe.title}</td>
                {/* <td>{recipe.ingredients}</td>
                <td>{recipe.servings}</td>
            <td>{recipe.instructions}</td> */}
            </tr>
        )
    })

    return (
        <div>
            <table>
                <tr>
                    <td>Title</td>
                    <td>Date Saved</td>
                    <td>Tags</td>
                </tr>
                {table}
            </table>
        </div>
        // <div className='recipe-list-container'>
        //     {recipes.map((recipe, index) => (
        //         <div className='recipe-list-item' key={recipe.id}>
        //             <h3>{index} - {recipe.title}</h3>
        //             <div className='recipe-list-buttons'>
        //                 <button onClick={() => handleClick(recipe.id)}>delete recipe</button>
        //                 <button>edit recipe</button>
        //             </div>
        //         </div>
        //     ))}
        // </div>
    )
}



    // var query = 'italian wedding soup'
    // const url = 'https://api.api-ninjas.com/v1/recipe?query=' + query

    // const table = sampleRecipes.map((values, i) => {
    //     return (
    //         <tr key={i}>
    //             <td>{values.title}</td>
    //             <td>{values.ingredients}</td>
    //             <td>{values.servings}</td>
    //             <td>{values.instructions}</td>
    //         </tr>
    //     )
    // })