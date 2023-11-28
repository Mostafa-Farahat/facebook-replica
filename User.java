public class User {
    private final String username;
    private final String gender;
    private final String email;
    private final String password;
    private final String birth_date;
    private int birth_day;
    private int birth_month;
    private int birth_year;
    private final long phone_number;

    public User(String username, String gender, String email, String password, int birth_day, int birth_month, int birth_year, long phone_number) {
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.birth_date = birth_day + "/" + birth_month + "/" + birth_year;
        this.phone_number = phone_number;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public long getPhone_number() {
        return phone_number;
    }
}
