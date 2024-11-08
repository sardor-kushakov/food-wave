package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.settings.SettingsCreateDTO;
import sarik.dev.foodwave.dto.settings.SettingsDTO;
import sarik.dev.foodwave.dto.settings.SettingsUpdateDTO;
import sarik.dev.foodwave.entity.admin.Settings;
import sarik.dev.foodwave.repository.SettingsRepository;
import sarik.dev.foodwave.service.SettingsService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;

    @Override
    @Transactional
    public SettingsDTO createSetting(SettingsCreateDTO settingsCreateDTO) {
        Settings setting = Settings.builder()
                .key(settingsCreateDTO.getKey())
                .value(settingsCreateDTO.getValue())
                .build();

        settingsRepository.save(setting);
        return SettingsDTO.fromEntity(setting);
    }

    @Override
    public SettingsDTO getSettingById(UUID settingId) {
        Settings setting = settingsRepository.findById(settingId)
                .orElseThrow(() -> new IllegalArgumentException("Setting not found"));
        return SettingsDTO.fromEntity(setting);
    }

    @Override
    public SettingsDTO getSettingByKey(String key) {
        Settings setting = settingsRepository.findByKey(key)
                .orElseThrow(() -> new IllegalArgumentException("Setting not found for the given key"));
        return SettingsDTO.fromEntity(setting);
    }

    @Override
    public List<SettingsDTO> getAllSettings() {
        return settingsRepository.findAll().stream()
                .map(SettingsDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SettingsDTO updateSetting(UUID settingId, SettingsUpdateDTO settingsUpdateDTO) {
        Settings setting = settingsRepository.findById(settingId)
                .orElseThrow(() -> new IllegalArgumentException("Setting not found"));

        setting.updateValue(settingsUpdateDTO.getValue());
        settingsRepository.save(setting);
        return SettingsDTO.fromEntity(setting);
    }

    @Override
    @Transactional
    public void deleteSetting(UUID settingId) {
        if (settingsRepository.existsById(settingId)) {
            settingsRepository.deleteById(settingId);
        } else {
            throw new IllegalArgumentException("Setting not found");
        }
    }
}
