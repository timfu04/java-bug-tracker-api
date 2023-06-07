package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.StatusDTO;
import com.clementlee.bugtrackerapi.exceptions.StatusNotFoundException;
import com.clementlee.bugtrackerapi.models.Status;
import com.clementlee.bugtrackerapi.repositories.StatusRepository;
import com.clementlee.bugtrackerapi.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public StatusDTO createStatus(StatusDTO statusDTO) {
        Status status = new Status();
        status.setName(statusDTO.getName());
        Status newStatus = statusRepository.save(status);
        return mapToStatusDto(newStatus);
    }

    @Override
    public List<StatusDTO> getAllStatuses() {
        List<Status> statuses = statusRepository.findAll();
        // Convert list of Status to list of StatusDTO
        return statuses.stream().map(status -> mapToStatusDto(status)).collect(Collectors.toList());
    }

    @Override
    public StatusDTO getStatusByStatusId(int statusId) {
        Status status = statusRepository.findById(statusId).orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
        return mapToStatusDto(status);
    }

    @Override
    public StatusDTO updateStatusByStatusId(int statusId, StatusDTO statusDTO) {
        Status status = statusRepository.findById(statusId).orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
        status.setName(statusDTO.getName());
        Status updatedStatus = statusRepository.save(status);
        return mapToStatusDto(updatedStatus);
    }

    @Override
    public void deleteStatusByStatusId(int statusId) {
        statusRepository.findById(statusId).orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
        statusRepository.deleteById(statusId);
    }

    // Map Status to StatusDTO
    private StatusDTO mapToStatusDto(Status status){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setId(status.getId());
        statusDTO.setName(status.getName());
        statusDTO.setIssues(status.getIssues());
        return statusDTO;
    }

}
