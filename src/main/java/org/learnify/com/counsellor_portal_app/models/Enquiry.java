package org.learnify.com.counsellor_portal_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Enquiry extends BaseClass {
    private String name;
    private String phNo;
    private ClassMode classMode;
    private EnquiryStatus equiryStatus;
    private String CourseName;

    //association (type : Composition)
    @ManyToOne
    @JoinColumn(name = "counsellor_id")
    private Counsellor counsellor;
}
