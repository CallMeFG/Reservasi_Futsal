package manajemen.model;

public class User {
    private String userId;
    private String namaLengkap;
    private String noHp;
    private String username;
    private String password;
    private String role;

    public User(String userId, String namaLengkap, String noHp, String username, String password, String role) {
        this.userId = userId;
        this.namaLengkap = namaLengkap;
        this.noHp = noHp;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUserId() {
        return userId;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }
    public String getNoHp() {
        return noHp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.namaLengkap; 
    }
}