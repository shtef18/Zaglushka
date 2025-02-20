package com.example.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RequestDTO {

    public String rqUID;
    public String clientId;
    public String account;
    public String openDate;
    public String closeDate;

}

