package com.techelevator.model;

import org.springframework.data.relational.core.mapping.Column;

import java.sql.Date;
import java.util.Arrays;

public class Recipe
{

    private long recipeId;
    private int creatorId;
    private String title;
    private Date dateAdded;
    private int cookingTime;
    private int prepTime;
    private String instructions;
    private boolean isPrivate;
    private String[] pictureLinks;
    private String referenceLink;
    private String subHeader;
    private Ingredient[] ingredients;
    
    public Recipe()
    {
    }
    
    @Override
    public String toString()
    {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", creatorId=" + creatorId +
                ", title='" + title + '\'' +
                ", dateAdded=" + dateAdded +
                ", cookingTime=" + cookingTime +
                ", prepTime=" + prepTime +
                ", instructions='" + instructions + '\'' +
                ", isPrivate=" + isPrivate +
                ", pictureLinks=" + Arrays.toString(pictureLinks) +
                ", referenceLink='" + referenceLink + '\'' +
                ", subHeader='" + subHeader + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                '}';
    }
    
    public Recipe(long recipeId, int creatorId, String title, Date dateAdded, int cookingTime, int prepTime, String instructions, boolean isPrivate, String[] pictureLinks, String referenceLink, String subHeader, Ingredient[] ingredients)
    {
        this.recipeId = recipeId;
        this.creatorId = creatorId;
        this.title = title;
        this.dateAdded = dateAdded;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
        this.isPrivate = isPrivate;
        this.pictureLinks = pictureLinks;
        this.referenceLink = referenceLink;
        this.subHeader = subHeader;
        this.ingredients = ingredients;
    }
    
    public long getRecipeId()
    {
        return recipeId;
    }
    
    public void setRecipeId(long recipeId)
    {
        this.recipeId = recipeId;
    }
    
    public long getCreatorId()
    {
        return creatorId;
    }
    
    public void setCreatorId(int creatorId)
    {
        this.creatorId = creatorId;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public Date getDateAdded()
    {
        return dateAdded;
    }
    
    public void setDateAdded(Date dateAdded)
    {
        this.dateAdded = dateAdded;
    }
    
    public int getCookingTime()
    {
        return cookingTime;
    }
    
    public void setCookingTime(int cookingTime)
    {
        this.cookingTime = cookingTime;
    }
    
    public int getPrepTime()
    {
        return prepTime;
    }
    
    public void setPrepTime(int prepTime)
    {
        this.prepTime = prepTime;
    }
    
    public String getInstructions()
    {
        return instructions;
    }
    
    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }
    
    public boolean isPrivate()
    {
        return isPrivate;
    }
    
    public void setPrivate(boolean aPrivate)
    {
        isPrivate = aPrivate;
    }
    
    public String[] getPictureLinks()
    {
        return pictureLinks;
    }
    
    public void setPictureLinks(String[] pictureLinks)
    {
        this.pictureLinks = pictureLinks;
    }
    
    public String getReferenceLink()
    {
        return referenceLink;
    }
    
    public void setReferenceLink(String referenceLink)
    {
        this.referenceLink = referenceLink;
    }
    
    public String getSubHeader()
    {
        return subHeader;
    }
    
    public void setSubHeader(String videoLink)
    {
        this.subHeader = videoLink;
    }
    
    public Ingredient[] getIngredients()
    {
        return ingredients;
    }
    
    public void setIngredients(Ingredient[] ingredients)
    {
        this.ingredients = ingredients;
    }
}
