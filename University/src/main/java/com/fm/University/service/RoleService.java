package com.fm.University.service;

import com.fm.University.model.Role;
import com.fm.University.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public Role updateRole(Long roleId, Role roleDetails) {
        Role role = roleRepository.findById(roleId)
                                   .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
