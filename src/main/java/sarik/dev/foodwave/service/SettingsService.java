package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.settings.SettingsCreateDTO;
import sarik.dev.foodwave.dto.settings.SettingsDTO;
import sarik.dev.foodwave.dto.settings.SettingsUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface SettingsService {

    SettingsDTO createSetting(SettingsCreateDTO settingsCreateDTO);

    SettingsDTO getSettingById(UUID settingId);

    SettingsDTO getSettingByKey(String key);

    List<SettingsDTO> getAllSettings();

    SettingsDTO updateSetting(UUID settingId, SettingsUpdateDTO settingsUpdateDTO);

    void deleteSetting(UUID settingId);
}
