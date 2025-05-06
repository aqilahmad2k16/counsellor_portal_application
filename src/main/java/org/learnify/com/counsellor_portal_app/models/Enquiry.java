package org.learnify.com.counsellor_portal_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;

@Entity
@Setter
@Getter
public class Enquiry extends BaseClass {
    @NotBlank(message = "Name is must")
    private String name;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phNo;
    private ClassMode classMode;
    private EnquiryStatus equiryStatus;
    private String CourseName;

    //association (type : Composition)
    @ManyToOne
    @JoinColumn(name = "counsellor_id")
    private Counsellor counsellor;

    public static Enquiry from(EnquiryDto enquiryDto, Counsellor counsellor) {
        Enquiry enquiry = new Enquiry();
        enquiry.setName(enquiryDto.getName());
        enquiry.setPhNo(enquiryDto.getPhNo());
        enquiry.setCourseName(enquiryDto.getCourseName());
        enquiry.setClassMode(ClassMode.valueOf(enquiryDto.getClassMode()));
        enquiry.setEquiryStatus(EnquiryStatus.valueOf(enquiryDto.getEnquiryStatus()));
        return enquiry;
    }

    public static Enquiry fromIfNull(EnquiryDto enquiryDto, Long counsellorId) {
        Enquiry enquiry = new Enquiry();
        enquiry.setPhNo(enquiryDto.getPhNo());
        enquiry.setCourseName(enquiryDto.getCourseName());
        enquiry.setClassMode(ClassMode.valueOf(enquiryDto.getClassMode()));
        enquiry.setEquiryStatus(EnquiryStatus.valueOf(enquiryDto.getEnquiryStatus()));
        Counsellor counsellor = new Counsellor();
        counsellor.setId(counsellorId);
        enquiry.setCounsellor(counsellor);
        return enquiry;
    }

    public static EnquiryDto to(Enquiry enquiry) {
        EnquiryDto enquiryDto = new EnquiryDto();
        enquiryDto.setPhNo(enquiry.getPhNo());
        enquiryDto.setCourseName(enquiry.getCourseName());
        enquiryDto.setClassMode(enquiry.getClassMode().toString());
        enquiryDto.setEnquiryStatus(enquiry.getEquiryStatus().toString());
        enquiryDto.setName(enquiry.getName());
        enquiryDto.setId(enquiry.getId().toString());
        return enquiryDto;
    }
}
