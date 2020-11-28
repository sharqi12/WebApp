package com.WEBAPP.WEBAPP.web.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserPromoDto {
    @Pattern(regexp = "^(.+)@(.+)$", message = "Nieprawidłowy email!")
    @Size(min=7, max=40, message = "Minimum 7 znakow!")
    private String email;

   public UserPromoDto(){
   }

    public UserPromoDto(String email){
       this.email = email;
   }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
