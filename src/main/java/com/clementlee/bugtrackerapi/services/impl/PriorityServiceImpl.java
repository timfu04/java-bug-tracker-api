package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.PriorityDTO;
import com.clementlee.bugtrackerapi.exceptions.PriorityNotFoundException;
import com.clementlee.bugtrackerapi.models.Priority;
import com.clementlee.bugtrackerapi.repositories.PriorityRepository;
import com.clementlee.bugtrackerapi.services.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    @Override
    public PriorityDTO createPriority(PriorityDTO priorityDTO) {
        Priority priority = new Priority();
        priority.setName(priorityDTO.getName());
        Priority newPriority = priorityRepository.save(priority);
        return mapToPriorityDto(newPriority);
    }

    @Override
    public List<PriorityDTO> getAllPriorities() {
        List<Priority> priorities = priorityRepository.findAll();
        // Convert list of Priority to list of PriorityDTO
        return priorities.stream().map(priority -> mapToPriorityDto(priority)).collect(Collectors.toList());
    }

    @Override
    public PriorityDTO getPriorityByPriorityId(int priorityId) {
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
        return mapToPriorityDto(priority);
    }

    @Override
    public PriorityDTO updatePriorityByPriorityId(int priorityId, PriorityDTO priorityDTO) {
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
        priority.setName(priorityDTO.getName());
        Priority updatedPriority = priorityRepository.save(priority);
        return mapToPriorityDto(updatedPriority);
    }

    @Override
    public void deletePriorityByPriorityId(int priorityId) {
        priorityRepository.findById(priorityId).orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
        priorityRepository.deleteById(priorityId);
    }

    // Map Priority to PriorityDTO
    private PriorityDTO mapToPriorityDto(Priority priority){
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setId(priority.getId());
        priorityDTO.setName(priority.getName());
        priorityDTO.setIssues(priority.getIssues());
        return priorityDTO;
    }

}
