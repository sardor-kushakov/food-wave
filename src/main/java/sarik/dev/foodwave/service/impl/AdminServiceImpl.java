package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.admin.AdminCreateDTO;
import sarik.dev.foodwave.dto.admin.AdminDTO;
import sarik.dev.foodwave.dto.admin.AdminUpdateDTO;
import sarik.dev.foodwave.entity.admin.Admin;
import sarik.dev.foodwave.enums.admin.AdminRole;
import sarik.dev.foodwave.repository.AdminRepository;
import sarik.dev.foodwave.service.AdminService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public AdminDTO createAdmin(AdminCreateDTO adminCreateDTO) {
        Admin admin = Admin.builder()
                .firstName(adminCreateDTO.getFirstName())
                .lastName(adminCreateDTO.getLastName())
                .email(adminCreateDTO.getEmail())
                .password(adminCreateDTO.getPassword()) // Parolni himoyalashni unutmaslik kerak
                .role(adminCreateDTO.getRole())
                .isActive(true)
                .build();

        adminRepository.save(admin);
        return AdminDTO.fromEntity(admin);
    }

    @Override
    public Optional<AdminDTO> getAdminById(UUID adminId) {
        return adminRepository.findById(adminId).map(AdminDTO::fromEntity);
    }

    @Override
    public List<AdminDTO> getAdminsByRole(AdminRole role) {
        return adminRepository.findByRole(role).stream()
                .map(AdminDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminDTO> getAllActiveAdmins() {
        return adminRepository.findByIsActive(true).stream()
                .map(AdminDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AdminDTO updateAdmin(UUID adminId, AdminUpdateDTO adminUpdateDTO) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        
        admin.setFirstName(adminUpdateDTO.getFirstName());
        admin.setLastName(adminUpdateDTO.getLastName());
        admin.setEmail(adminUpdateDTO.getEmail());
        admin.setRole(adminUpdateDTO.getRole());
        adminRepository.save(admin);
        
        return AdminDTO.fromEntity(admin);
    }

    @Override
    @Transactional
    public void deleteAdmin(UUID adminId) {
        if (adminRepository.existsById(adminId)) {
            adminRepository.deleteById(adminId);
        } else {
            throw new IllegalArgumentException("Admin not found");
        }
    }

    @Override
    @Transactional
    public void activateAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        admin.activate();
        adminRepository.save(admin);
    }

    @Override
    @Transactional
    public void deactivateAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        admin.deactivate();
        adminRepository.save(admin);
    }
}
