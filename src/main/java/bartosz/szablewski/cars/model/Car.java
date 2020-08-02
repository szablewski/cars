package bartosz.szablewski.cars.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date purchaseDate;
    private String color;

    public Car() {
    }

    public Car(long id, String name, Date purchaseDate, String color) {
        this.id = id;
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", color='" + color + '\'' +
                '}';
    }
}
