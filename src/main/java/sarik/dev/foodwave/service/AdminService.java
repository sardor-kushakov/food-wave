package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.admin.AdminCreateDTO;
import sarik.dev.foodwave.dto.admin.AdminDTO;
import sarik.dev.foodwave.dto.admin.AdminUpdateDTO;
import sarik.dev.foodwave.enums.admin.AdminRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminService {

    AdminDTO createAdmin(AdminCreateDTO adminCreateDTO);

    Optional<AdminDTO> getAdminById(UUID adminId);

    List<AdminDTO> getAdminsByRole(AdminRole role);

    List<AdminDTO> getAllActiveAdmins();

    AdminDTO updateAdmin(UUID adminId, AdminUpdateDTO adminUpdateDTO);

    void deleteAdmin(UUID adminId);

    void activateAdmin(UUID adminId);

    void deactivateAdmin(UUID adminId);
}
