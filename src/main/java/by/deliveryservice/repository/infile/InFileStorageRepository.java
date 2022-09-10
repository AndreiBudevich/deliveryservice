package by.deliveryservice.repository.infile;

import by.deliveryservice.model.Storage;
import by.deliveryservice.repository.BaseRepository;

public class InFileStorageRepository extends InFileRepository<Storage> implements BaseRepository<Storage> {

    public InFileStorageRepository() {
        super("json/storage.json", Storage.class);
    }
}