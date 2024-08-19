package ra.designimpl;

import ra.entity.Products;
import ra.design.IGeneric;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements IGeneric<Products, Integer> {
    private List<Products> products = new ArrayList<>();

    @Override
    public List<Products> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public void save(Products product) {
        products.add(product);
    }

    @Override
    public Products findById(Integer productId) {
        Optional<Products> product = products.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst();
        return product.orElse(null);
    }

    @Override
    public void delete(Integer productId) {
        products.removeIf(p -> p.getProductId() == productId);
    }
}
