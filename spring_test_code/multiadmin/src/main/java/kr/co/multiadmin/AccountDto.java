package kr.co.multiadmin;

import lombok.Getter;
import lombok.Setter;

public class AccountDto {
    @Getter
    @Setter
    public static class RegisterRequest {
        private String username;
        private String password;
        private String rote;

        public static RegisterRequest with(String username, String password , String rote){
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setPassword(password);
            registerRequest.setRote(rote);
            registerRequest.setUsername(username);
            return registerRequest;
        }

    }
}
