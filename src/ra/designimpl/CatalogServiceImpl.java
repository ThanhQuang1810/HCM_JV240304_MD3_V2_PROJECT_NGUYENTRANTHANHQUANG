package ra.designimpl;

import ra.design.IGeneric;
import ra.entity.Catalogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogServiceImpl implements IGeneric<Catalogs, Integer> {
    private List<Catalogs> catalogs = new ArrayList<>();

    @Override
    public List<Catalogs> getAll() {
        return new ArrayList<>(catalogs);
    }

    @Override
    public void save(Catalogs catalog) {
        catalogs.add(catalog);
    }

    @Override
    public Catalogs findById(Integer catalogId) {
        Optional<Catalogs> catalog = catalogs.stream()
                .filter(c -> c.getCatalogId() == catalogId)
                .findFirst();
        return catalog.orElse(null);
    }

    @Override
    public void delete(Integer catalogId) {
        catalogs.removeIf(c -> c.getCatalogId() == catalogId);
    }
}
