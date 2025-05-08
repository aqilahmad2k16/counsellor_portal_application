package org.learnify.com.counsellor_portal_app.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.learnify.com.counsellor_portal_app.dtos.responseDtos.DashboardDto;

import java.util.List;

@Entity
@Setter
@Getter
public class Counsellor extends BaseClass {
    private String name;
    private String phoneNumber;
    private String email;
    private String pwd;

    public static DashboardDto getDashboard(List<Enquiry> enquiryList) {
        DashboardDto dashboardDto = new DashboardDto();
        Long totalEnquiryCount = (long) enquiryList.size();
        Long openEnquiryCount = enquiryList.stream()
                .filter(enquiry -> enquiry.getEquiryStatus().equals(EnquiryStatus.OPEN))
                .count();
        Long enrolledEnquiryCount = enquiryList.stream()
                .filter(enquiry -> enquiry.getEquiryStatus().equals(EnquiryStatus.ENROLLED))
                .count();
        Long lostEnquiryCount = totalEnquiryCount - openEnquiryCount - enrolledEnquiryCount;

        dashboardDto.setTotalEnquiries(totalEnquiryCount);
        dashboardDto.setOpenEnquiries(openEnquiryCount);
        dashboardDto.setEnrolledEnquiries(enrolledEnquiryCount);
        dashboardDto.setLostEnquiries(lostEnquiryCount);
        return dashboardDto;
    }
}
