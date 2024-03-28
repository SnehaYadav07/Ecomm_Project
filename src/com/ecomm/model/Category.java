package com.ecomm.model;

import java.util.Objects;

public class Category {
    
    private int categoryId;
    private String categoryName;
    private String image;
    private static int counter;
    
    static {
        counter = 0;
    }

    {
        this.categoryId = ++counter;
    }

    public Category() {
        super();
    }

    public Category(String categoryName, String image) {
        super();
        this.categoryName = categoryName;
        this.image = image;
    }
    

    public Category(int categoryId, String categoryName, String image) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.image = image;
	}

	public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        return categoryId == other.categoryId;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", image=" + image + "]";
    }

    public void updateImage(String newImage) {
        this.image = newImage;
        System.out.println("Image updated successfully.");
    }
}
