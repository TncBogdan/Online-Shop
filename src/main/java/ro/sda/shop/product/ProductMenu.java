package ro.sda.shop.product;

import ro.sda.shop.common.AbstractMenu;
import ro.sda.shop.common.ConsoleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductMenu extends AbstractMenu {
    private ProductService service = new ProductService();
    private ProductReader reader = new ProductReader();
    private ProductWriter writer = new ProductWriter();
    private Scanner scanner = new Scanner(System.in);

    protected void displayOptions() {
        System.out.println("\nProducts menu");
        System.out.println("1 - View all products");
        System.out.println("2 - View product details");
        System.out.println("3 - Edit product");
        System.out.println("4 - Add product");
        System.out.println("5 - Delete product");
        System.out.println("6 - Search products by name");
        System.out.println("7 - Search products by price");
        System.out.println("0 - Exit");
    }

    protected void executeCmd(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(service.getAllProducts());
                break;
            case 2:
                viewProductDetails();
                break;
            case 3:
                editProduct();
                break;
            case 4:
                Product newProduct = reader.read();
                service.save(newProduct);
                System.out.println("Product added");
                break;
            case 5:
                deleteProduct();
                break;
            case 6:
                searchByName();
                break;
            case 7:
                searchByPrice();
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void viewProductDetails() {
        if (service.getAllProducts().isEmpty()) {
            System.out.println("No products available.");
        } else {
            writer.writeAll(service.getAllProducts());
            System.out.print("Select product to view: ");
            Product foundProduct = service.getProduct(ConsoleUtil.readLong());
            if (foundProduct == null) {
                System.out.println("Product not found");
            } else {
                System.out.println("Product details are: ");
                writer.write(foundProduct);
            }
        }
    }

    private void editProduct() {
        if (service.getAllProducts().isEmpty()) {
            System.out.println("No products available.");
        } else {
            writer.writeAll(service.getAllProducts());
            System.out.print("Select product to edit: ");
            Product foundProduct = service.getProduct(ConsoleUtil.readLong());
            if (foundProduct == null) {
                System.out.println("Product not found");
            } else {
                System.out.print("Enter new name (leave empty if the same): ");
                String name = scanner.nextLine().trim();
                if (!name.isEmpty()) {
                    foundProduct.setName(ConsoleUtil.toTitleCase(name));
                }
                System.out.print("Enter new description (leave empty if the same): ");
                String description = scanner.nextLine().trim();
                if (!description.isEmpty()) {
                    foundProduct.setDescription(ConsoleUtil.toSentenceCase(description));
                }
                System.out.print("Enter new price (leave empty if the same): ");
                Double price = ConsoleUtil.getPrice(foundProduct);
                if (price != 0) {
                    foundProduct.setPrice(price);
                }
                service.save(foundProduct);
                System.out.println("Product updated");
            }
        }
    }

    private void deleteProduct() {
        if (service.getAllProducts().isEmpty()) {
            System.out.println("No products available.");
        } else {
            writer.writeAll(service.getAllProducts());
            System.out.print("Select product to delete: ");
            boolean isDeleted = service.delete(ConsoleUtil.readLong());
            if (!isDeleted) {
                System.out.println("Product not found");
            } else {
                System.out.println("Product deleted");
            }
        }
    }

    private void searchByName() {
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No clients available.");
        } else {
            System.out.print("Enter name: ");
            String name = scanner.nextLine().trim();
            List<Product> foundProducts = new ArrayList<>();
            for (Product product : products) {
                if (name.equals(product.getName())) {
                    foundProducts.add(product);
                }
            }
            if (foundProducts.isEmpty()) {
                System.out.println("No products match your criteria");
            } else {
                System.out.println("Found products: ");
                writer.writeAll(foundProducts);
            }
        }
    }


    private void searchByPrice() {
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.print("Enter min price: ");
            int min = ConsoleUtil.getInteger();
            System.out.print("Enter max price: ");
            int max = ConsoleUtil.getInteger();
            while (max < min) {
                System.out.print("Max limit should be >= min limit. Try again: ");
                max = ConsoleUtil.getInteger();
            }
            List<Product> foundProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getPrice() >= min && product.getPrice() <= max) {
                    foundProducts.add(product);
                }
            }
            if (foundProducts.isEmpty()) {
                System.out.println("No products match your criteria");
            } else {
                System.out.println("Found products: ");
                writer.writeAll(foundProducts);
            }
        }
    }
}
