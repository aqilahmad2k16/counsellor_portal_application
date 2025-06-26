package org.learnify.com.counsellor_portal_app.dtos;

import lombok.Data;

@Data
public class DashboardDto {
    private Integer totalEnquiries;
    private Integer openEnquiries;
    private Integer enrolledEnquiries;
    private Integer lostEnquiries;
}
