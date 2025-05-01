package Model;

public class Pet {
    private String name;
    private PetType petType;
    private PetGender petGender;
    private double age;
    private double weight;
    private String breed;
    private Address address;

    public void DefinePetType(int choice){
        if (choice == 1){
            setPetType(PetType.CACHORRO);
        }
        else {
            setPetType(PetType.GATO);
        }
    }
    public void DefinePetGender(int choice){
        if (choice == 1){
            setPetGender(PetGender.MACHO);
        }
        else {
            setPetGender(PetGender.FEMEA);
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetGender getPetGender() {
        return petGender;
    }

    public void setPetGender(PetGender petGender) {
        this.petGender = petGender;
    }

    public double getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
