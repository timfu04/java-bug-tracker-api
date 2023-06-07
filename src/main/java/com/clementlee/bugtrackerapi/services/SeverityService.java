package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.SeverityDTO;

import java.util.List;

public interface SeverityService {

    SeverityDTO createSeverity (SeverityDTO severityDTO);
    List<SeverityDTO> getAllSeverities();
    SeverityDTO getSeverityBySeverityId(int severityId);
    SeverityDTO updateSeverityBySeverityId(int severityId, SeverityDTO severityDTO);
    void deleteSeverityBySeverityId(int severityId);

}
