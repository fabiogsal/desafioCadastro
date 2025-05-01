package Model;

public class Pet {
    private String name;
    private PetType petType;
    private PetGender petGender;
    private String age;
    private String weight;
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

    public String getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
