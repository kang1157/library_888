public class Role {
    String name;
    int role;//1:admin 2:user

    public Role(String name, int role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }
}
