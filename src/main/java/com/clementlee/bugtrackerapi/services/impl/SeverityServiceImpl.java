package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.SeverityDTO;
import com.clementlee.bugtrackerapi.exceptions.SeverityNotFoundException;
import com.clementlee.bugtrackerapi.models.Severity;
import com.clementlee.bugtrackerapi.repositories.SeverityRepository;
import com.clementlee.bugtrackerapi.services.SeverityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeverityServiceImpl implements SeverityService {

    private final SeverityRepository severityRepository;

    @Override
    public SeverityDTO createSeverity(SeverityDTO severityDTO) {
        Severity severity = new Severity();
        severity.setName(severityDTO.getName());
        Severity newSeverity = severityRepository.save(severity);
        return mapToSeverityDto(newSeverity);
    }

    @Override
    public List<SeverityDTO> getAllSeverities() {
        List<Severity> severities = severityRepository.findAll();
        return severities.stream().map(severity -> mapToSeverityDto(severity)).collect(Collectors.toList());
    }

    @Override
    public SeverityDTO getSeverityBySeverityId(int severityId) {
        Severity severity = severityRepository.findById(severityId).orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
        return mapToSeverityDto(severity);
    }

    @Override
    public SeverityDTO updateSeverityBySeverityId(int severityId, SeverityDTO severityDTO) {
        Severity severity = severityRepository.findById(severityId).orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
        severity.setName(severityDTO.getName());
        Severity updatedSeverity = severityRepository.save(severity);
        return mapToSeverityDto(updatedSeverity);
    }

    @Override
    public void deleteSeverityBySeverityId(int severityId) {
        Severity severity = severityRepository.findById(severityId).orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
        severityRepository.deleteById(severityId);
    }

    // Map Severity to SeverityDTO
    private SeverityDTO mapToSeverityDto(Severity severity){
        SeverityDTO severityDTO = new SeverityDTO();
        severityDTO.setId(severity.getId());
        severityDTO.setName(severity.getName());
        return severityDTO;
    }
}
