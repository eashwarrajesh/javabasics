import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Mall {
    
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Location cannot be null")
    private String location;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
