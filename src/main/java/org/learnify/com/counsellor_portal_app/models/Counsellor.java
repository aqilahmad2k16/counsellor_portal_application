package org.learnify.com.counsellor_portal_app.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Counsellor extends BaseClass {
    private String name;
    private String address;
    private String postalCode;
    private String qualification;
    private String phoneNumber;
    private String email;
    private String pwd;
}
