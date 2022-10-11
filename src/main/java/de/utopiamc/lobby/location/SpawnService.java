package de.utopiamc.lobby.location;

import com.google.inject.Inject;
import de.utopiamc.framework.api.stereotype.Service;
import de.utopiamc.framework.api.storage.Storage;
import de.utopiamc.framework.api.storage.StorageService;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpawnService {

    public static final String KEYS_PATH = "keys";
    public static final String LOCATIONS_FILE_NAME = "locations";
    private final Storage storage;

    @Inject
    public SpawnService(StorageService storage) {
        this.storage = storage.setupStorage(LOCATIONS_FILE_NAME, true);
    }


    public void saveLocation(Location location, String name, Material material) {
        String key = name.toLowerCase();
        StorageLocation storageLocation = new StorageLocation(name ,location, material);
        storage.set(key, storageLocation);

        List<String> keys = storage.getStringList(KEYS_PATH);
        if (keys == null){
            keys = new LinkedList<>();
        }
        keys.add(key);
        storage.set(KEYS_PATH, keys);
    }

    public StorageLocation loadLocation(String name){
        String key = name.toLowerCase();
        return storage.getSerializable(key, StorageLocation.class);
    }

    public List<StorageLocation> getLocations(){
        List<String> keys = storage.getStringList(KEYS_PATH);
        return keys.stream()
                .map(key -> storage.getSerializable(key, StorageLocation.class))
                .collect(Collectors.toList());
    }

    public List<String> getNames(){
        return storage.getStringList(KEYS_PATH);
    }
}
