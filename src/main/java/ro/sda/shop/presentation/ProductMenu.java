package ro.sda.shop.presentation;

import ro.sda.shop.model.Product;
import ro.sda.shop.storage.ProductDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductMenu extends AbstractMenu {
    private ProductDAO productDAO = new ProductDAO();
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
                writer.writeAll(productDAO.findAll());
                break;
            case 2:
                if (productDAO.findAll().isEmpty()) {
                    System.out.println("No products available.");
                } else {
                    writer.writeAll(productDAO.findAll());
                    System.out.print("Select product to view: ");
                    displayProductDetails();
                }
                break;
            case 3:
                if (productDAO.findAll().isEmpty()) {
                    System.out.println("No products available.");
                } else {
                    writer.writeAll(productDAO.findAll());
                    System.out.print("Select product to edit: ");
                    editProduct();
                }
                break;
            case 4:
                Product newProduct = reader.read();
                productDAO.add(newProduct);
                System.out.println("Product added");
                break;
            case 5:
                if (productDAO.findAll().isEmpty()) {
                    System.out.println("No products available.");
                } else {
                    writer.writeAll(productDAO.findAll());
                    System.out.print("Select product to delete: ");
                    String inputMessage = " ProductID: ";
                    String invalidMessage = "Invalid Product Id. Please, retry!";
                    Long id = ConsoleUtil.readLong(inputMessage,invalidMessage);
                    boolean isDeleted = productDAO.deleteById(id);
                    if (!isDeleted) {
                        System.out.println("Product not found");
                    } else {
                        System.out.println("Product deleted");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editProduct() {
        Scanner scanner = new Scanner(System.in);
        String inputMessage = " ProductID: ";
        String invalidMessage = "Invalid Product Id. Please, retry!";
        Long id = ConsoleUtil.readLong(inputMessage,invalidMessage);
        Product foundProduct = productDAO.findById(id);
        if (foundProduct == null) {
            System.out.println("Product not found");
        } else {
            System.out.print("Enter new name: ");
            foundProduct.setName(scanner.next());
            System.out.print("Enter new description: ");
            foundProduct.setDescription(scanner.next());
            System.out.print("Enter new price: ");
            try {
                foundProduct.setPrice(scanner.nextDouble());
            } catch (InputMismatchException e) {
                System.out.println("Price not changed");
            }
            productDAO.update(foundProduct);
            System.out.println("Product updated");
        }
    }

    private void displayProductDetails() {
        String inputMessage = " ProductID: ";
        String invalidMessage = "Invalid Product Id. Please, retry!";
        Long id = ConsoleUtil.readLong(inputMessage,invalidMessage);
        Product foundProduct = productDAO.findById(id);
        if (foundProduct == null) {
            System.out.println("Product not found");
        } else {
            System.out.println("Product details are: ");
            writer.write(foundProduct);
        }
    }
}
