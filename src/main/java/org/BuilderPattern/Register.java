package org.BuilderPattern;

public class Register {

    private String firstname ;
    private String lastname ;
    private String email ;
    private String telephone ;
    private String password ;
    private String confirmpassword ;

    public Register(RegisterBuilder registerbuilder)
    {
       this.firstname=registerbuilder.firstname;
       this.lastname=registerbuilder.lastname;
       this.email=registerbuilder.email;
       this.telephone=registerbuilder.telephone;
       this.password=registerbuilder.password;
       this.confirmpassword=registerbuilder.confirmpassword;
    }

//    getters


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public static class RegisterBuilder
    {
        private String firstname ;
        private String lastname ;
        private String email ;
        private String telephone ;
        private String password ;
        private String confirmpassword ;

        public RegisterBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public RegisterBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public RegisterBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public RegisterBuilder setTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public RegisterBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public RegisterBuilder setConfirmpassword(String confirmpassword) {
            this.confirmpassword = confirmpassword;
            return this;
        }
        public Register build()
        {
            return new Register(this);
        }

    }



}
