package ra.designimpl;

import ra.entity.Customers;
import ra.util.IOFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomersServiceImpl {
    private List<Customers> customersList;
    private IOFile<Customers> ioFile;

    public CustomersServiceImpl() {
        this.customersList = new ArrayList<>();
        this.ioFile = new IOFile<>();
        loadCustomers();
    }

    private void loadCustomers() {
        try {
            List<Customers> loadedCustomers = ioFile.readFromFile(IOFile.USER_PATH);
            this.customersList = loadedCustomers != null ? loadedCustomers : new ArrayList<>();
            if (!this.customersList.isEmpty()) {
                int maxId = this.customersList.stream()
                        .mapToInt(Customers::getCustomerId)
                        .max()
                        .orElse(0);
                Customers.setCount(maxId + 1);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }

    public List<Customers> getAll() {
        return customersList;
    }

    public void setCustomersList(List<Customers> customersList) {
        this.customersList = customersList != null ? customersList : new ArrayList<>();
        // Optional: Update count here based on the new list
    }

    public Optional<Customers> findById(int id) {
        return customersList.stream()
                .filter(customer -> customer.getCustomerId() == id)
                .findFirst();
    }

    public Optional<Customers> findByUsername(String username) {
        return customersList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst();
    }

    public void save(Customers customer) {
        customersList.add(customer);
        saveAllCustomers();
    }

    public void delete(int id) {
        customersList.removeIf(customer -> customer.getCustomerId() == id);
        saveAllCustomers();
    }

    public void updateAccess(Customers customer) {
        // Optional: Implement if needed to update user access
        saveAllCustomers();
    }

    private void saveAllCustomers() {
        try {
            ioFile.writeToFile(IOFile.USER_PATH, customersList);
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }
}


