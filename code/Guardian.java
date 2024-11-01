package code;
public class Guardian extends User {
    private Client client;
    
    public Guardian(String name, long phone, UserType type, int age, String user, String pass, Client client) {
        super(name, phone, type, age, user, pass);
        this.client = client;
    }

    public Client getClient(){
        return client;
    }

    public void setClient(Client client){
        this.client = client;
    }
    
}
