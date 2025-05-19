package org.learnify.com.counsellor_portal_app.dtos.responseDtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDto {
    private Long totalEnquiries;
    private Long openEnquiries;
    private Long enrolledEnquiries;
    private Long lostEnquiries;
}
