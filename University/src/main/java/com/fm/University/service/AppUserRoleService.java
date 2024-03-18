package com.fm.University.service;

import com.fm.University.model.AppUserRole;
import com.fm.University.repository.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserRoleService {

	private final AppUserRoleRepository appUserRoleRepository;

	@Autowired
	public AppUserRoleService(AppUserRoleRepository appUserRoleRepository) {
		this.appUserRoleRepository = appUserRoleRepository;
	}

	public AppUserRole createAppUserRole(AppUserRole appUserRole) {
		return appUserRoleRepository.save(appUserRole);
	}

	public List<AppUserRole> getAllAppUserRoles() {
		return appUserRoleRepository.findAll();
	}

	public Optional<AppUserRole> getAppUserRoleById(Long appUserRoleId) {
		return appUserRoleRepository.findById(appUserRoleId);
	}

	public AppUserRole updateAppUserRole(Long appUserRoleId, AppUserRole appUserRoleDetails) {
		AppUserRole appUserRole = appUserRoleRepository.findById(appUserRoleId)
				.orElseThrow(() -> new RuntimeException("AppUserRole not found with id: " + appUserRoleId));
		return appUserRoleRepository.save(appUserRole);
	}

	public void deleteAppUserRole(Long appUserRoleId) {
		appUserRoleRepository.deleteById(appUserRoleId);
	}
}
