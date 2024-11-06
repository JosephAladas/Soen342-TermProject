package code;
public class Guardian extends User {
    private Client client;
    
    public Guardian(){}

    public Guardian(String name, long phone, int age, String user, String pass, Client client) {
        super(name, phone, UserType.GUARDIAN, age, user, pass);
        this.client = client;
    }

    public Client getClient(){
        return client;
    }

    public void setClient(Client client){
        this.client = client;
    }
    
}
