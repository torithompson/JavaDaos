package entities;
public class Item {
    Integer id;
    String name;
    String description;
    //Getter for id
    public Integer getId() {
        return id;
    }
    //Setter for id
    public void setId(Integer id) {
        this.id = id;
    }
    //Getter for name
    public String getName() {
        return name;
    }
    //Setter for name
    public void setName(String name) {
        this.name = name;
    }
    //Getter for description
    public String getDescription() {
        return description;
    }
    //Setter for description
    public void setDescription(String description) {
        this.description = description;
    }
    //Override toString function to display a formatted string 
    @Override
    public String toString() {
        return "Item ID = " + id + ", Name = " + name + ", Description = " + description;
    }
}
