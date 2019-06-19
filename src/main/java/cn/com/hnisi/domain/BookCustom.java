package cn.com.hnisi.domain;

public class BookCustom extends Book
{
    private Category category;

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
}
