package org.learnify.com.counsellor_portal_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.learnify.com.counsellor_portal_app.dtos.EnquiryDto;

@Entity
@Setter
@Getter
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is must")
    private String name;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private Long phoneNumber;
    private String classMode;
    private String equiryStatus;
    private String CourseName;

    //association (type : Composition)
    @ManyToOne
    @JoinColumn(name = "counsellor_id")
    private Counsellor counsellor;

    public static Enquiry from(EnquiryDto enquiryDto, Counsellor counsellor) {
        Enquiry enquiry = new Enquiry();
        enquiry.setName(enquiryDto.getName());
        enquiry.setPhoneNumber(enquiryDto.getPhoneNumber());
        enquiry.setCourseName(enquiryDto.getCourseName());
        enquiry.setClassMode(enquiryDto.getClassMode());
        enquiry.setEquiryStatus(enquiryDto.getEnquiryStatus());
        return enquiry;
    }

    public static Enquiry fromIfNull(EnquiryDto enquiryDto, Long counsellorId) {
        Enquiry enquiry = new Enquiry();
        enquiry.setPhoneNumber(enquiryDto.getPhoneNumber());
        enquiry.setCourseName(enquiryDto.getCourseName());
        enquiry.setClassMode(enquiryDto.getClassMode());
        enquiry.setEquiryStatus(enquiryDto.getEnquiryStatus());
        Counsellor counsellor = new Counsellor();
        counsellor.setId(counsellorId);
        enquiry.setCounsellor(counsellor);
        return enquiry;
    }

    public static EnquiryDto to(Enquiry enquiry) {
        EnquiryDto enquiryDto = new EnquiryDto();
        enquiryDto.setPhoneNumber(enquiry.getPhoneNumber());
        enquiryDto.setCourseName(enquiry.getCourseName());
        enquiryDto.setClassMode(enquiry.getClassMode().toString());
        enquiryDto.setEnquiryStatus(enquiry.getEquiryStatus().toString());
        enquiryDto.setName(enquiry.getName());
        enquiryDto.setClassMode(enquiry.getClassMode().toString());
        return enquiryDto;
    }
}
