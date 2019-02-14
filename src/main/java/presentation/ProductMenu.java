package presentation;

import model.Product;
import storage.ProductDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductMenu extends AbstractMenu {
    ProductDAO productDAO = new ProductDAO();
    ProductReader reader = new ProductReader();
    ProductWriter writer = new ProductWriter();
    Scanner scanner = new Scanner(System.in);

    protected void displayOptions() {
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
                    boolean isDeleted = productDAO.deleteById(getNumericInput());
                    if (isDeleted) {
                        System.out.println("Client deleted");
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

    private Long getNumericInput() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return -1L;
    }

    private void editProduct() {
        Scanner scanner = new Scanner(System.in);
        Product foundProduct = productDAO.findById(getNumericInput());
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
        Product foundProduct = productDAO.findById(getNumericInput());
        if (foundProduct == null) {
            System.out.println("Product not found");
        } else {
            System.out.println("Product details are: ");
            writer.write(foundProduct);
        }
    }
}
