package by.epam.rentacar.dto;

public class SignupDTO {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public SignupDTO() {

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignupDTO)) return false;

        SignupDTO signupDTO = (SignupDTO) o;

        if (username != null ? !username.equals(signupDTO.username) : signupDTO.username != null) return false;
        if (password != null ? !password.equals(signupDTO.password) : signupDTO.password != null) return false;
        if (confirmPassword != null ? !confirmPassword.equals(signupDTO.confirmPassword) : signupDTO.confirmPassword != null)
            return false;
        return email != null ? email.equals(signupDTO.email) : signupDTO.email == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
