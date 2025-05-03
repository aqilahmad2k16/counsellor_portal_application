package org.learnify.com.counsellor_portal_app.dtos.responseDtos;

import lombok.Data;

@Data
public class DashboardDto {
    private Long totalEnquiries;
    private Long openEnquiries;
    private Long enrolledEnquiries;
    private Long lostEnquiries;
}
