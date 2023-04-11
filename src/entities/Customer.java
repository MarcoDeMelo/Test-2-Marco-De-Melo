package entities;

public class Customer {
    Integer ID; //PK
    String Name;
    String Address;
    String City;
    String State;
    String Zip;
    public Integer getID() {return ID;}
    public void setID(Integer ID) {this.ID = ID;}
    public String getName() {return Name;}
    public void setName(String name) {Name = name;}
    public String getAddress() {return Address;}
    public void setAddress(String address) {Address = address;}
    public String getCity() {return City;}
    public void setCity(String city) {City = city;}
    public String getState() {return State;}
    public void setState(String state) {State = state;}
    public String getZip() {return Zip;}
    public void setZip(String zip) {Zip = zip;}
    @Override 
    public String toString(){
return "Customer [ID= " + ID + ", Name= " + Name + ", Address= " + Address + ", City= " + City + ", State= " + State + ", Zip= " + Zip + " ]";
    }
}
