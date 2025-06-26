package org.learnify.com.counsellor_portal_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.learnify.com.counsellor_portal_app.common.EnquiryStatus;
import org.learnify.com.counsellor_portal_app.dtos.DashboardDto;

import java.util.List;

@Entity
@Setter
@Getter
public class Counsellor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long phoneNumber;
    private String email;
    private String pwd;

    public static DashboardDto getDashboard(List<Enquiry> enquiryList) {
        DashboardDto dashboardDto = new DashboardDto();
        Integer totalEnquiryCount = enquiryList.size();
        Integer openEnquiryCount = (int) enquiryList.stream()
                .filter(enquiry -> enquiry.getEquiryStatus().equals(EnquiryStatus.OPEN))
                .count();
        Integer enrolledEnquiryCount = (int) enquiryList.stream()
                .filter(enquiry -> enquiry.getEquiryStatus().equals(EnquiryStatus.ENROLLED))
                .count();
        Integer lostEnquiryCount = totalEnquiryCount - openEnquiryCount - enrolledEnquiryCount;

        dashboardDto.setTotalEnquiries(totalEnquiryCount);
        dashboardDto.setOpenEnquiries(openEnquiryCount);
        dashboardDto.setEnrolledEnquiries(enrolledEnquiryCount);
        dashboardDto.setLostEnquiries(lostEnquiryCount);
        return dashboardDto;
    }
}
