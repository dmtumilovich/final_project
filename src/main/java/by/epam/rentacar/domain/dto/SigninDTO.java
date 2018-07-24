package by.epam.rentacar.domain.dto;

public class SigninDTO {

    private String username;
    private String password;

    public SigninDTO() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SigninDTO)) return false;

        SigninDTO signinDTO = (SigninDTO) o;

        if (username != null ? !username.equals(signinDTO.username) : signinDTO.username != null) return false;
        return password != null ? password.equals(signinDTO.password) : signinDTO.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
