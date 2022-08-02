package by.deliveryservice.repository.infile;

import by.deliveryservice.model.BaseEntity;
import by.deliveryservice.repository.Repository;

import java.util.*;

import static by.deliveryservice.util.FileUtil.isEmpty;
import static by.deliveryservice.util.JsonUtil.readValues;
import static by.deliveryservice.util.JsonUtil.writeEntity;

public class InFileRepository<T extends BaseEntity> implements Repository<T> {

    protected final String nameFile;
    private final Class<T> clazz;
    protected Map<Integer, T> repositoryInMemory = new HashMap<>();

    public InFileRepository(String nameFile, Class<T> clazz) {
        this.nameFile = nameFile;
        this.clazz = clazz;
    }

    @Override
    public List<T> getAll() {
        readInFile();
        return new ArrayList<>(repositoryInMemory.values());
    }

    @Override
    public Optional<T> get(int id) {
        readInFile();
        return Optional.of(repositoryInMemory.get(id));
    }

    @Override
    public void delete(int id) {
        readInFile();
        T remove = repositoryInMemory.remove(id);
        if (remove != null) {
            saveInFile();
            System.out.println("remove " + remove + " by id :" + id);
        } else {
            System.out.println("remove didn't");
        }
    }

    @Override
    public T save(T t) {
        int maxId = 0;
        if (!isEmpty(nameFile)) {
            readInFile();
            maxId = getMaxId();
        }
        if (t.isNew()) {
            maxId++;
            t.setId(maxId);
            repositoryInMemory.put(t.getId(), t);
            System.out.println("create " + t + "by id :" + maxId);
        } else {
            T tOld = repositoryInMemory.get(t.getId());
            if (tOld != null) {
                updateEntity(t, tOld);
                System.out.println("update " + t + "by id :" + tOld.getId());
            } else {
                System.out.println("update did't");
            }
        }
        saveInFile();
        return t;
    }

    protected void saveInFile() {
        writeEntity(nameFile, repositoryInMemory);
    }

    protected void readInFile() {
        if (isEmpty(nameFile)) {
            System.out.println("Нет сохраненных данных");
            return;
        }
        repositoryInMemory = readValues(nameFile, Integer.class, clazz);
    }

    protected int getMaxId() {
        return Collections.max(repositoryInMemory.keySet());
    }

    protected void updateEntity(T t, T tOld) {
        repositoryInMemory.computeIfPresent(t.getId(), (id, oldUser) -> t);
    }
}
