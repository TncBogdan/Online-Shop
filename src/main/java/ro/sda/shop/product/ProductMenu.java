package ro.sda.shop.product;

import ro.sda.shop.common.AbstractMenu;
import ro.sda.shop.common.ConsoleUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductMenu extends AbstractMenu {
    private ProductService service = new ProductService();
    private ProductReader reader = new ProductReader();
    private ProductWriter writer = new ProductWriter();

    protected void displayOptions() {
//        productDAO.initialize();
        System.out.println("\nProducts menu");
        System.out.println("1 - View all products");
        System.out.println("2 - View product details");
        System.out.println("3 - Edit product");
        System.out.println("4 - Add product");
        System.out.println("5 - Delete product");
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
            Long id = ConsoleUtil.readLong();
            Product foundProduct = service.getProduct(id);
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
            Long id = ConsoleUtil.readLong();
            Product foundProduct = service.getProduct(id);
            if (foundProduct == null) {
                System.out.println("Product not found");
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                foundProduct.setName(scanner.next());
                System.out.print("Enter new description: ");
                foundProduct.setDescription(scanner.next());
                System.out.print("Enter new price: ");
                foundProduct.setPrice(ConsoleUtil.getPrice());
//                try {
//                    foundProduct.setPrice(scanner.nextDouble());
//                } catch (InputMismatchException e) {
//                    System.out.println("Price not changed");
//                }
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
            Long id = ConsoleUtil.readLong();
            boolean isDeleted = service.delete(id);
            if (!isDeleted) {
                System.out.println("Product not found");
            } else {
                System.out.println("Product deleted");
            }
        }
    }
}
